package model;

import org.json.JSONObject;
import persistence.Writable;

//Represents each individual person and their current state
public class Person implements Writable {
    private String state;
    private int age;
    private int contactedTimes;

    //REQUIRES: a > 0
    //EFFECTS: a person with a state of "Alive", "Sick", or "Dead", their age,
    //         the amount of times that they have been in sick contact with
    //         someone
    public Person(String s,int a) {
        this.state = s;
        this.age = a;
        this.contactedTimes = 0;
    }

    public String getState() {
        return state;
    }

    public int getAge() {
        return age;
    }

    //REQUIRES: i >= 0
    public void setAge(int i) {
        this.age = i;
    }

    //REQUIRES: s to be either "Dead", "Sick", or "Alive"
    public void setState(String s) {
        this.state = s;
    }

    public int getContactedTimes() {
        return contactedTimes;
    }

    public void resetContactedTimes() {
        this.contactedTimes = 0;
    }

    public void addContactedTimes() {
        this.contactedTimes++;
    }

    //REQUIRES: Person state to be not "Dead"
    //MODIFIES: this
    //EFFECTS: Makes contact with one other person
    public void contact(Person p) {
        if (getState() == "Sick") {
            p.addContactedTimes();
        }
        if (p.getState() == "Sick") {
            addContactedTimes();
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("state", state);
        json.put("age", age);
        return json;
    }
}
