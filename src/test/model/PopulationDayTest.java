package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
        p1 = new Person("Alive", 1);
        p2 = new Person("Alive", 10);
        p3 = new Person("Alive", 20);
        p4 = new Person("Alive", 30);
        p5 = new Person("Alive", 30);
        d1 = new PopulationDay("Test",3.5, 0, 0,0);
        d2 = new PopulationDay("Test1",0.6, 1, 1,0);
        people = new ArrayList<>();

        people.add(p1);
        people.add(p2);
        people.add(p3);

    }

    @Test
    void testName() {
        assertEquals(d1.getName(), "Test");
        assertEquals(d2.getName(), "Test1");
    }

    @Test
    void testDay() {
        assertEquals(d1.getDay(), 0);
        d1.increaseDay();
        assertEquals(d1.getDay(), 1);
    }

    @Test
    void testCheckDeaths(){
        assertEquals(d1.getDeathRate(), 0);
        assertEquals(d2.getDeathRate(), 1);

        d1.addPeople(p1);
        assertFalse(d1.checkAllDead());
        p1.setState("Dead");
        assertTrue(d1.checkAllDead());
        p2.setState("Dead");
        d1.addPeople(p2);
        assertTrue(d1.checkAllDead());
        d1.addPeople(p3);
        assertFalse(d1.checkAllDead());
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
    void testAllDeathAndOnePerson() {
        p1.setState("Dead");
        p2.setState("Dead");
        p3.setState("Dead");
        d1.addGroupPeople(people);
        d1.simulateContactedAmount();
        assertEquals(d1.getTestMessage(), "AllDead");
        p1.setState("Alive");
        d1.simulateContactedAmount();
        assertEquals(d1.getTestMessage(), "OneAlive");
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
        d1.returnTotalContact();
        assertEquals(d1.getContactedAmount(),0);

        p1.addContactedTimes();
        p1.addContactedTimes();
        p2.addContactedTimes();
        p3.addContactedTimes();

        d1.addGroupPeople(people);
        d1.returnTotalContact();
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

    @Test
    void testSimulateDeadPeople() {
        p1.setState("Sick");
        p2.setState("Sick");

        d1.addGroupPeople(people);
        d1.simulateDeadPeople();
        assertEquals(d1.returnTotalDeadPopulation(), 0);

        d2.addGroupPeople(people);
        d2.simulateDeadPeople();
        assertEquals(d2.returnTotalDeadPopulation(), 2);
    }


}