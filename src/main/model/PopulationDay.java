package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

import static java.lang.Math.pow;

//Represents the state of population in 1 day of the simulation
public class PopulationDay implements Writable {
    private final String name;
    private ArrayList<Person> people;
    private int day;
    private int contactedAmount;
    private final double contactRate;
    private final double transmissionRate;
    private final double deathRate;
    private final double recoveryRate;
    private String testMessage;


    //REQUIRES: c > 0, d >= 0
    //EFFECTS: Creates a population with an empty arrayList of people, a contactRate where
    //         it represents the amount of people someone will be expected to contact in a
    //         day, the transmission rate where t >= 0 && t <= 1, the amount of dead people
    //         already dead, the death rate where dr >= 0 && dr <= 1,the contactedAmount for
    //         the whole list, number of days since simulation
    public PopulationDay(String n, double c, double t, double dr, double rr, int day) {
        this.name = n;
        this.people = new ArrayList<>();
        this.contactRate = c;
        this.transmissionRate = t;
        this.contactedAmount = 0;
        this.deathRate = dr;
        this.recoveryRate = rr;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public int getDay() {
        return day;
    }

    public void increaseDay() {
        day++;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public int getPeopleSize() {
        return people.size();
    }

    public void addPeople(Person p) {
        people.add(p);
    }

    //EFFECTS: Adds a group of people to people in our day
    public void addGroupPeople(ArrayList<Person> pl) {
        people.addAll(pl);
        EventLog.getInstance().logEvent(new Event("Added group of " + pl.size()));
    }

    public double getTransmissionRate() {
        return transmissionRate;
    }

    public double getContactRate() {
        return contactRate;
    }

    public int getContactedAmount() {
        return contactedAmount;
    }

    public double getRecoveryRate() {
        return recoveryRate;
    }

    public double getDeathRate() {
        return deathRate;
    }

    public String getTestMessage() {
        return testMessage;
    }

    public void resetContactedAmount() {
        for (Person person : people) {
            person.resetContactedTimes();
        }
    }

    //EFFECTS: returns the total number of people in the arraylist
    //         that are sick
    public int returnTotalSickPopulation() {
        int temp = 0;
        for (Person person : people) {
            if (person.getState().equals("Sick")) {
                temp++;
            }
        }
        return temp;
    }

    //EFFECTS: returns the total number of people in the arraylist
    //         that are Alive
    public int returnTotalAlivePopulation() {
        int temp = 0;
        for (Person person : people) {
            if (person.getState().equals("Alive")) {
                temp++;
            }
        }
        return temp;
    }

    //EFFECTS: returns the total number of people in the arraylist
    //         that are Dead
    public int returnTotalDeadPopulation() {
        int temp = 0;
        for (Person person : people) {
            if (person.getState().equals("Dead")) {
                temp++;
            }
        }
        return temp;
    }

    // EFFECTS: checks if all people are dead
    public Boolean checkAllDead() {
        for (Person person : people) {
            if (!person.getState().equals("Dead")) {
                return false;
            }
        }
        return true;
    }

    //MODIFIES: this
    //EFFECTS: Simulate the amount of contact that will happen in one day,
    //         where contactRate represents the amount of people one
    //         person will be expected to see each day,
    //         People can contact each other multiple times in 1 day, but,
    //         cannot contact one another
    public void simulateContactedAmount() {
        resetContactedAmount();
        if (!checkAllDead() && (returnTotalAlivePopulation() + returnTotalSickPopulation()) != 1) {
            for (int i = 0; i < getPeopleSize(); i++) {
                if (!people.get(i).getState().equals("Dead")) {
                    double temp1 = getContactRate() - (int) getContactRate();
                    double temp2 = Math.random();

                    int contactRate = temp2 <= temp1 ? (int) (getContactRate() + 1) : (int) getContactRate();

                    for (int k = 0; k < contactRate; k++) {
                        int index = (int) (Math.random() * getPeopleSize());
                        if (index == i || people.get(index).getState().equals("Dead")) {
                            contactRate++;
                        } else {
                            people.get(i).contact(getPeople().get(index));
                        }
                    }
                }
            }
        }
        textMessage();
        returnTotalContact();
        EventLog.getInstance().logEvent(new Event("Finished simulating day: " + day + "of simulation"));
    }

    //MODIFIES: this
    //EFFECTS: Changes the text message of population day to determine whether
    //         if there's only one person alive, sick, or if everyone is dead
    public void textMessage() {
        if (checkAllDead()) {
            testMessage = "AllDead";
        } else if (returnTotalAlivePopulation() == 1) {
            testMessage = "OneAlive";
        } else if (returnTotalSickPopulation() == 1) {
            testMessage = "OneSick";
        }
    }

    //MODIFIES: this
    //EFFECTS: calculate total number of normal contact in list
    public void returnTotalContact() {
        int temp = 0;
        for (Person person : people) {
            temp += person.getContactedTimes();
        }
        contactedAmount = temp;
    }


    //MODIFIES: this
    //EFFECTS: Calculates the number of sick people that will be
    //         generated based on the transmissionRate and contactedTimes,
    //         the formula used is the following t = transmissionRate and
    //         c = contactedTimes, 1-(1-t)^c
    public void simulateSickPeople() {
        for (Person person : people) {
            int temp = person.getContactedTimes();
            double store;
            if (person.getState().equals("Alive")
                    && temp > 0) {
                store = 1 - pow((1 - transmissionRate), temp);
                double index = Math.random();
                if (store > index) {
                    person.setState("Sick");
                }
            }
        }
    }

    //EFFECTS: simulates the number of dead people based on
    //         the death rate
    public void simulateDeadPeople() {
        for (Person person : people) {
            if (person.getState().equals("Sick")) {
                double index = Math.random();
                if (deathRate > index) {
                    person.setState("Dead");
                }
            }
        }
    }

    public void simulateRecovery() {
        for (Person person : people) {
            if (person.getState().equals("Sick")) {
                double index = Math.random();
                if (recoveryRate > index) {
                    person.setState("Alive");
                }
            }
        }
    }

    //EFFECTS: Converts name of simulation, contact rate, transmission rate,
    //         death rate, day, and people into a JSON Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("contactRate", contactRate);
        json.put("transmissionRate", transmissionRate);
        json.put("deathRate", deathRate);
        json.put("recoveryRate", recoveryRate);
        json.put("day", day);
        json.put("people", peopleToJson());
        return json;
    }

    // EFFECTS: returns people in this workroom as a JSON array
    private JSONArray peopleToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Person p : people) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }


}
