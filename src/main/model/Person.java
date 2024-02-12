package model;

//Represents each individual person and their current state
public class Person {
    private String state;

    public Person() {
        this.state = "Alive";
    }

    public String getState() {
        return state;
    }

    //REQUIRES: s to be either "Dead", "Sick", or "Alive"
    public void setState(String s) {
        this.state = s;
    }


}
