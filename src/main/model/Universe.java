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





}
