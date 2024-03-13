package persistence;

import model.Person;
import model.PopulationDay;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads PopulationDay from JSON data stored in file
// Based off of JsonSerializationDemo/CPSC 210
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads PopulationDay from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PopulationDay read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePopulationDay(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses PopulationDay from JSON object and returns it
    private PopulationDay parsePopulationDay(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double contactRate = jsonObject.getDouble("contactRate");
        double transmissionRate = jsonObject.getDouble("transmissionRate");
        double deathRate = jsonObject.getDouble("deathRate");
        double recoveryRate = jsonObject.getDouble("recoveryRate");
        int day = jsonObject.getInt("day");

        PopulationDay pd = new PopulationDay(name, contactRate, transmissionRate, deathRate, recoveryRate, day);
        addPeople(pd, jsonObject);
        return pd;
    }

    // MODIFIES: pd
    // EFFECTS: parses people from JSON object and adds them to PopulationDay
    private void addPeople(PopulationDay pd, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("people");
        for (Object json : jsonArray) {
            JSONObject nextPerson = (JSONObject) json;
            addPerson(pd, nextPerson);
        }
    }

    // MODIFIES: pd
    // EFFECTS: parses thingy from JSON object and adds it to PopulationDay
    private void addPerson(PopulationDay pd, JSONObject jsonObject) {
        String state = jsonObject.getString("state");
        int age = jsonObject.getInt("age");
        Person person = new Person(state, age);
        pd.addPeople(person);
    }
}
