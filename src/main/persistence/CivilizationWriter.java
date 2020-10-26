package persistence;

import model.Civilization;
import org.json.JSONObject;



import java.io.*;
import java.util.List;


// Represents a class that writes data to JSON file

public class CivilizationWriter extends Writer {

    //EFFECTS: construct writer to write to destination file
    public CivilizationWriter(String destination) {
        super(destination);
    }


    //MODIFIES: this
    //EFFECTS: write JSON representation of civilization to file
    public void write(Civilization cv) {

        JSONObject json = cv.civilizationDetails();
        saveToFile(json.toString(TAB));

    }



}

