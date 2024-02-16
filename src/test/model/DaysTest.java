package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DaysTest {
    private Person p1;
    private Person p2;
    private Person p3;
    private Person p4;
    private Person p5;
    private Days d1;
    private Days d2;
    private ArrayList<Person> people;


    @BeforeEach
    void runBefore(){
        p1 = new Person(1);
        p2 = new Person(10);
        p3 = new Person(20);
        p4 = new Person(30);
        p5 = new Person(30);
        d1 = new Days(3.5, 10);
        d2 = new Days(0.6, 15);
        people = new ArrayList<Person>();

        people.add(p1);
        people.add(p2);
        people.add(p3);

    }

    @Test
    void testGetDeathAmount(){
        assertEquals(d1.getDeathAmount(), 10);
        assertEquals(d2.getDeathAmount(), 15);
    }

    @Test
    void testAddGroupPeople(){
        assertEquals(d1.getPeopleSize(),0);
        d1.addGroupPeople(people);
        assertEquals(d1.getPeopleSize(),3);
    }

    @Test
    void testSimulateContactedAmount(){
        d1.addGroupPeople(people);
        d1.simulateContactedAmount();

        assertTrue(d1.getContactedAmount() >= 18 && d1.getContactedAmount() <= 24);

        d1.addPeople(p4);
        d1.simulateContactedAmount();

        assertTrue(d1.getContactedAmount() >= 24 && d1.getContactedAmount() <= 32);


        d2.addGroupPeople(people);
        d2.simulateContactedAmount();

        assertTrue(d2.getContactedAmount() >= 0 && d2.getContactedAmount() <= 6);

        d2.addPeople(p4);
        d2.addPeople(p5);

        assertTrue(d2.getContactedAmount() >= 0 && d2.getContactedAmount() <= 10);
    }

    @Test
    void testSimulateTotalContact(){
        d1.simulateTotalContact();
        assertEquals(d1.getContactedAmount(),0);

        p1.addContactedTimes();
        p1.addContactedTimes();
        p2.addContactedTimes();
        p3.addContactedTimes();

        d1.addGroupPeople(people);
        d1.simulateTotalContact();
        assertEquals(d1.getContactedAmount(),4);
    }


}