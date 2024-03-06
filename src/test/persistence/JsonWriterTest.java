package persistence;

import model.Person;
import model.PopulationDay;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Based off of JsonSerializationDemo/CPSC 210
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            PopulationDay pd = new PopulationDay("test1", 0.2, 0.3, 0.1, 1);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testWriterEmptyWorkroom() {
        try {
            PopulationDay pd = new PopulationDay("test2", 1, 1, 1, 2);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPopulation.json");
            writer.open();
            writer.write(pd);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPopulation.json");
            pd = reader.read();
            assertEquals("test2", pd.getName());
            assertEquals(0, pd.getPeopleSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            PopulationDay pd = new PopulationDay("test3", 0.5, 0.5, 0.5, 3);
            pd.addPeople(new Person("Dead", 10));
            pd.addPeople(new Person("Alive", 20));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPopulation.json");
            writer.open();
            writer.write(pd);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPopulation.json");
            pd = reader.read();
            assertEquals("test3", pd.getName());
            List<Person> people = pd.getPeople();
            assertEquals(2, people.size());
            checkPerson("Dead", 10, people.get(0));
            checkPerson("Alive", 20, people.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}