package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {
    private Person p1;
    private Person p2;

    @BeforeEach
    void runBefore(){
        p1 = new Person(20);
        p2 = new Person(40);
    }

    @Test
    void testPersonConstructor(){
        assertEquals(p1.getAge(), 20);
        assertEquals(p2.getState(), "Alive");
        assertEquals(p2.getContactedTimes(), 0);
    }

    @Test

    void testSetters(){
        assertEquals(p1.getAge(), 20);
        p1.setAge(10);
        assertEquals(p1.getAge(), 10);

        assertEquals(p2.getState(), "Alive");
        p2.setState("Dead");
        assertEquals(p2.getState(), "Dead");
    }

    @Test
    void testAddContactedTimes(){
        assertEquals(p1.getContactedTimes(), 0);

        p1.addContactedTimes();

        assertEquals(p1.getContactedTimes(), 1);
    }

    @Test
    void testContact(){
        assertEquals(p1.getContactedTimes(), 0);
        assertEquals(p2.getContactedTimes(), 0);

        p1.contact(p2);

        assertEquals(p1.getContactedTimes(), 0);
        assertEquals(p2.getContactedTimes(), 0);

        p1.setState("Sick");

        p2.contact(p1);

        assertEquals(p1.getContactedTimes(), 0);
        assertEquals(p2.getContactedTimes(), 1);

        p2.setState("Sick");
        p1.contact(p2);

        assertEquals(p1.getContactedTimes(), 1);
        assertEquals(p2.getContactedTimes(), 2);
    }

}
