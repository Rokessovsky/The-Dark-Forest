package persistence;

import model.Civilization;
import model.Universe;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class UniverseWriter extends Writer {

    //EFFECTS: construct writer to write to destination file
    public UniverseWriter(String destination) {
        super(destination);
    }

    //MODIFIES: this
    //EFFECTS: write JSON representation of civilization to file
    public void write(Universe universe) {
        JSONObject json = universe.universeDetails();
        saveToFile(json.toString(TAB));


    }
}
