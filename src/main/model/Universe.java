package model;

import java.util.ArrayList;
import java.util.List;

//present an universe with a dimension and a list civilizations
public class Universe {
    private int dimension;
    private ArrayList<Civilization> civilizations;

    public Universe(int dimension) {
        this.dimension = dimension;
        civilizations = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: add civilization to this universe
    public void addCivilization(Civilization civilization) {
        civilizations.add(civilization);
    }


    //MODIFIES: this
    //EFFECTS: Remove the civilization you destroyed from the universe
    public void destroyCivilization(Civilization civilization) {
        civilizations.remove(civilization);
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





}
