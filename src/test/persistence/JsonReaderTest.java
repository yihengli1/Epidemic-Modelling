package persistence;

import model.Person;
import model.PopulationDay;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Based off of JsonSerializationDemo/CPSC 210
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PopulationDay pd = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPopulation.json");
        try {
            PopulationDay pd = reader.read();
            assertEquals("test", pd.getName());
            assertEquals(0.3, pd.getContactRate());
            assertEquals(0.1, pd.getDeathRate());
            assertEquals(0.2, pd.getTransmissionRate());
            assertEquals(1, pd.getDay());
            assertEquals(0.3, pd.getRecoveryRate());
            assertEquals(0, pd.getPeopleSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPopulation.json");
        try {
            PopulationDay pd = reader.read();
            assertEquals("test1", pd.getName());
            List<Person> people = pd.getPeople();
            assertEquals(2, people.size());
            checkPerson("Sick", 20, people.get(0));
            checkPerson("Dead", 20, people.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}