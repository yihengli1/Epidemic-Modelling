package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PopulationDayTest {
    private Person p1;
    private Person p2;
    private Person p3;
    private Person p4;
    private Person p5;
    private PopulationDay d1;
    private PopulationDay d2;
    private ArrayList<Person> people;


    @BeforeEach
    void runBefore(){
        p1 = new Person(1);
        p2 = new Person(10);
        p3 = new Person(20);
        p4 = new Person(30);
        p5 = new Person(30);
        d1 = new PopulationDay(3.5, 0, 0.5, 10);
        d2 = new PopulationDay(0.6, 1, 1, 15);
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
    void testReturnTotalSickPopulation() {
        assertEquals(d1.returnTotalSickPopulation(), 0);

        p1.setState("Sick");
        p3.setState("Sick");

        d1.addGroupPeople(people);

        assertEquals(d1.returnTotalSickPopulation(), 2);
    }

    @Test
    void testReturnTotalAlivePopulation() {
        assertEquals(d1.returnTotalAlivePopulation(), 0);

        p1.setState("Sick");

        d1.addGroupPeople(people);

        assertEquals(d1.returnTotalAlivePopulation(), 2);
    }

    @Test
    void testSimulateContactedAmount(){
        p1.setState("Sick");
        p2.setState("Sick");
        d1.addGroupPeople(people);
        d1.simulateContactedAmount();

        assertTrue(d1.getContactedAmount() >= 9 && d1.getContactedAmount() <= 20);

        d1.addPeople(p4);
        d1.simulateContactedAmount();

        assertTrue(d1.getContactedAmount() >= 6 && d1.getContactedAmount() <= 24);

        p1.setState("Alive");
        d2.addGroupPeople(people);
        d2.simulateContactedAmount();

        assertTrue(d2.getContactedAmount() >= 0 && d2.getContactedAmount() <= 3);

        p4.setState("Sick");
        p5.setState("Sick");
        d2.addPeople(p4);
        d2.addPeople(p5);

        assertTrue(d2.getContactedAmount() >= 0 && d2.getContactedAmount() <= 8);
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

    @Test
    void SimulateSickPeople() {
        p2.setState("Sick");
        p3.setState("Alive");
        p3.addContactedTimes();

        d2.addGroupPeople(people);
        d2.simulateSickPeople();
        assertEquals(d2.returnTotalAlivePopulation(),1);

        p1.addContactedTimes();
        d1.addGroupPeople(people);
        d1.simulateSickPeople();
        assertEquals(d1.returnTotalAlivePopulation(),1);
    }


}