package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DaysTest {
    private Person p1;
    private Person p2;

    @BeforeEach
    void runBefore(){
        p1 = new Person();
        p2 = new Person();
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

        assertEquals(p1.getContactedTimes(), 1);
        assertEquals(p2.getContactedTimes(), 1);

        p2.contact(p1);

        assertEquals(p1.getContactedTimes(), 2);
        assertEquals(p2.getContactedTimes(), 2);

    }


}