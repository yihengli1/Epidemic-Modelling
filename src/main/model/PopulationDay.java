package model;

import java.util.ArrayList;

import static java.lang.Math.pow;

//Represents the state of population in 1 day of the simulation
public class PopulationDay {
    private ArrayList<Person> people;
    private int deathAmount;
    private int contactedAmount;
    private double contactRate;
    private double transmissionRate;
    private double deathRate;


    //REQUIRES: c > 0, d >= 0
    //EFFECTS: Creates a population with an empty arrayList of people that are
    //         either "sick" or "alive", a contactRate where it represents the amount
    //         of people someone will be expected to contact in a day, the transmission
    //         rate where t >= 0 && t <= 1, the amount of dead people already dead, the
    //         death rate where dr >= 0 && dr <= 1,the contactedAmount for the whole list
    public PopulationDay(double c, double t, double dr, int d) {
        this.people = new ArrayList<Person>();
        this.contactRate = c;
        this.transmissionRate = t;
        this.deathAmount = d;
        this.contactedAmount = 0;
        this.deathRate = dr;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public int getPeopleSize() {
        return people.size();
    }

    //REQUIRES: p.getState() != "dead"
    public void addPeople(Person p) {
        people.add(p);
    }

    //REQUIRES: people.getState() != "dead" for all elements in list
    //EFFECTS: Adds a group of people to people in our day
    public void addGroupPeople(ArrayList<Person> pl) {
        people.addAll(pl);
    }

    public int getDeathAmount() {
        return deathAmount;
    }

    public double getContactRate() {
        return contactRate;
    }

    public int getContactedAmount() {
        return contactedAmount;
    }

    public double getDeathRate() {
        return deathRate;
    }

    public void resetContactedAmount() {
        for (int i = 0; i < people.size(); i++) {
            people.get(i).resetContactedTimes();
        }
    }

    //EFFECTS: returns the total number of people in the arraylist
    //         that are sick
    public int returnTotalSickPopulation() {
        int temp = 0;
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getState() == "Sick") {
                temp++;
            }
        }
        return temp;
    }

    //EFFECTS: returns the total number of people in the arraylist
    //         that are Alive
    public int returnTotalAlivePopulation() {
        int temp = 0;
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getState() == "Alive") {
                temp++;
            }
        }
        return temp;
    }

    //EFFECTS: returns the total number of people in the arraylist
    //         that are Dead
    public int returnTotalDeadPopulation() {
        int temp = 0;
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getState() == "Dead") {
                temp++;
            }
        }
        return temp;
    }

    //MODIFIES: this
    //EFFECTS: Simulate the amount of contact that will happen in one day,
    //         where contactRate represents the amount of people one
    //         person will be expected to see each day,
    //         People can contact each other multiple times in 1 day, but,
    //         cannot contact one another
    public void simulateContactedAmount() {
        resetContactedAmount();
        for (int i = 0; i < getPeopleSize(); i++) {

            double temp1 = getContactRate() - (int) getContactRate();
            double temp2 = Math.random();

            int contactRate = temp2 <= temp1 ? (int) (getContactRate() + 1) : (int) getContactRate();

            for (int k = 0; k < contactRate; k++) {
                int index = (int) (Math.random() * getPeopleSize());
                if (index == i) {
                    contactRate++;
                } else {
                    people.get(i).contact(getPeople().get(index));
                }
            }
        }

        returnTotalContact();
    }

    //MODIFIES: this
    //EFFECTS: calculate total number of normal contact in list
    public void returnTotalContact() {
        int temp = 0;
        for (int i = 0; i < people.size(); i++) {
            temp += people.get(i).getContactedTimes();
        }
        contactedAmount = temp;
    }


    //MODIFIES: this
    //EFFECTS: Calculates the number of sick people that will be
    //         generated based on the transmissionRate and contactedTimes,
    //         the formula used is the following t = transmissionRate and
    //         c = contactedTimes, 1-(1-t)^c
    public void simulateSickPeople() {
        for (int i = 0; i < people.size(); i++) {
            int temp = people.get(i).getContactedTimes();
            double store = 0;
            if (people.get(i).getState() == "Alive"
                    && temp > 0) {
                store = 1 - pow((1 - transmissionRate), (double) temp);
                double index = Math.random();
                if (store > index) {
                    people.get(i).setState("Sick");
                }
            }
        }
    }

    public void simulateDeadPeople() {
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getState() == "Sick") {
                double index = Math.random();
                if (deathRate > index) {
                    people.get(i).setState("Dead");
                }
            }
        }
    }

}
