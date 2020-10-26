package persistence;

import model.Civilization;
import model.Universe;
import org.json.JSONObject;

import java.io.IOException;

public class CivilizationReader extends Reader {

    //EFFECTS: construct reader to read file from source file
    public CivilizationReader(String source) {
        super(source);
    }

    //EFFECTS: read civilization from file and return it;
    // throw IOException if an error occurs reading data from file
    public Civilization read() throws IOException {

        String civilizationData = readFile(source);
        JSONObject jsonObject = new JSONObject(civilizationData);
        return parseCivilization(jsonObject);
    }

    // EFFECTS: parses civilization from JSON object and return it
    private Civilization parseCivilization(JSONObject jsonObject) {
        String name = jsonObject.getString("Name of the civilization");
        int resources = jsonObject.getInt("Available resources");
        int roundNum = jsonObject.getInt("Round number");
        int tech = jsonObject.getInt("Technology");
        int society = jsonObject.getInt("Society");
        int culture = jsonObject.getInt("Culture");
        Universe universe = new Universe("solar",10);
        Civilization myCv = new Civilization(name,tech,society,culture,universe);
        myCv.setResources(resources);
        myCv.setRoundNumber(roundNum);

        return myCv;
    }
}
