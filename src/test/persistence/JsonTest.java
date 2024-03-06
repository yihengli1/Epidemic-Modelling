package persistence;

import model.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Based off of JsonSerializationDemo/CPSC 210
public class JsonTest {
    protected void checkPerson(String state, int age, Person person) {
        assertEquals(state, person.getState());
        assertEquals(age, person.getAge());
    }
}

