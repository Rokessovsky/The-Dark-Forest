package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//present an universe with a dimension and a list civilizations
public class Universe {
    private String name;
    private int dimension;
    private ArrayList<Civilization> civilizations;

    //Constructor
    //EFFECTS: construct a universe with given name and dimension
    public Universe(String name,int dimension) {
        this.name = name;
        this.dimension = dimension;
        civilizations = new ArrayList<>();
    }

    //MODIFIES: json object.
    //EFFECTS: return a json object with all universe details
    public JSONObject universeDetails() {
        JSONObject uni = new JSONObject();
        uni.put("name of universe",name);
        uni.put("dimension",dimension);
        uni.put("civilizations", cvsToJson());
        return uni;
    }

    // EFFECTS: returns civilizations as a JSON array
    private JSONArray cvsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Civilization cv : civilizations) {
            jsonArray.put(cv.civilizationDetails());
        }

        return jsonArray;
    }

    //MODIFIES: this
    //EFFECTS: add civilization to this universe if it's not already in the list and return true,otherwise return false.
    public boolean addCivilization(Civilization civilization) {
        if (civilizations.contains(civilization)) {
            return false;
        }
        civilizations.add(civilization);
        return true;
    }


    //MODIFIES: this
    //EFFECTS: Remove the civilization you destroyed from the universe and return true if the
    //         civilization is in the list, otherwise return false.
    public boolean destroyCivilization(Civilization civilization) {
        return civilizations.remove(civilization);
    }


    //MODIFIES: this
    //EFFECTS: creates civilizations and put them in the universe
    public void setCivilizations(ArrayList<Civilization> civilizations) {
        this.civilizations = civilizations;
    }

    //EFFECTS: return the civilizations in the universe.
    public List<Civilization> getCivilizations() {
        return civilizations;
    }

    //MODIFIES: this
    //REQUIRES: dimension > 0
    //EFFECTS: reduce the dimension by one
    public void dimensionalityReduction() {
        this.dimension--;
    }

    //EFFECTS: return the dimension of the universe
    public int getDimension() {
        return dimension;
    }

    //EFFECTS: set the dimension
    public void setDimension(int num) {
        this.dimension = num;
    }

    //EFFECTS: get the name of the universe
    public String getName() {
        return name;
    }

    //EFFECTS: set the name of the universe
    public void setName(String s) {
        this.name = s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Universe universe = (Universe) o;
        return dimension == universe.dimension
                && Objects.equals(name, universe.name)
                && Objects.equals(civilizations, universe.civilizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dimension, civilizations);
    }
}
