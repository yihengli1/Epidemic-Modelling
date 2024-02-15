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
        this.deathAmount = d;
        this.contactRate = c;
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
        int temp = 0;
        for (int i = 0; i < people.size(); i++) {
            temp += people.get(i).getContactedTimes();
        }
        return temp;
    }

    //MODIFIES: this
    //EFFECTS: Simulate the amount of contact that will happen in one day
    public void simulateContactedAmount() {
        for (int i = 0; i < getPeopleSize(); i++) {

            int temp = (int) getContactRate();
            double temp1 = getContactRate() - temp;
            double temp2 = Math.random();

            int contactRate = temp2 <= temp1 ? (int) (getContactRate() + 1) : (int) getContactRate();

            for (int k = 0; k < contactRate; k++) {
                int index = (int) (Math.random() * getPeopleSize());
                getPeople().get(i).contact(getPeople().get(index));
            }
        }
    }




}
