package persistence;

import model.Civilization;
import model.Universe;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class UniverseReader extends Reader {

    //EFFECTS: construct reader to read file from source file
    public UniverseReader(String source) {
        super(source);
    }

    //EFFECTS: read universe from file and return it;
    // throw IOException if an error occurs reading data from file
    public Universe read() throws IOException {

        String universeData = readFile(source);
        JSONObject jsonObject = new JSONObject(universeData);
        return parseUniverse(jsonObject);
    }

    //EFFECTS: parse universe from JSON object and return it
    private Universe parseUniverse(JSONObject jsonObject) {
        int dm = jsonObject.getInt("dimension");
        String name = jsonObject.getString("name of universe");
        Universe universe = new Universe(name,dm);
        addCvs(universe, jsonObject);
        return universe;
    }

    // MODIFIES: universe
    // EFFECTS: parses civilizations from JSON object and adds them to universe
    private void addCvs(Universe universe, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("civilizations");
        for (Object json : jsonArray) {
            JSONObject nextCv = (JSONObject) json;
            addCv(universe, nextCv);
        }
    }

    // MODIFIES: universe
    // EFFECTS: parses civilization from JSON object and adds it to universe
    private void addCv(Universe universe, JSONObject jsonObject) {
        String name = jsonObject.getString("Name of the civilization");
        int resources = jsonObject.getInt("Available resources");
        int roundNum = jsonObject.getInt("Round number");
        int tech = jsonObject.getInt("Technology");
        int society = jsonObject.getInt("Society");
        int culture = jsonObject.getInt("Culture");
        Civilization cv = new Civilization(name,tech,society,culture,universe);
        cv.setResources(resources);
        cv.setRoundNumber(roundNum);

        universe.addCivilization(cv);
    }



}
