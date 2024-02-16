package model;

import java.util.ArrayList;

//Represents the state of people in 1 day of the simulation
public class Days {
    private ArrayList<Person> people;
    private int deathAmount;
    private int contactedAmount;
    private double contactRate;

    public Days(double c, int d) {
        this.people = new ArrayList<Person>();
        this.contactRate = c;
        this.deathAmount = d;
        this.contactedAmount = 0;

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

    public void resetContactedAmount() {
        for (int i = 0; i < people.size(); i++) {
            people.get(i).resetContactedTimes();
        }
    }

    //MODIFIES: this
    //EFFECTS: Simulate the amount of contact that will happen in one day
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

        simulateTotalContact();
    }

    //MODIFIES: this
    //EFFECTS: calculate total number of contact in list
    public void simulateTotalContact() {
        int temp = 0;
        for (int i = 0; i < people.size(); i++) {
            temp += people.get(i).getContactedTimes();
        }
        contactedAmount = temp;
    }






}
