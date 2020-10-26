package model;

import org.json.JSONObject;

//Represent a civilization with int technology, society and culture level,dimension,developLimit.And boolean value of
//is exposed to university,has obtained Dark Forest Principle knowledge,light speed drive technology,is harmful.
public class Civilization {
    private String name;
    private Universe universe;

    private int technology;
    private int society;
    private int culture;
    private int resources;



    private int dimension;
    private int developmentLimits;




    private boolean isExposed;
    private boolean isHarmful;


    private int darkForestKnownCulture;
    private int matureSociety;
    private int techLevel1;
    private int techLevel2;
    private int techLevel3;

    private int roundNumber;



    //constructor
    //REQUIRE: technology,society and culture have to be smaller or equal than developmentLimits
    //construct a civilization with certain technology,society and culture level.
    public Civilization(String name,int technology, int society, int culture,Universe universe) {
        this.name = name;
        this.universe = universe;

        this.technology = technology;
        this.society = society;
        this.culture = culture;

        this.resources = 0;
        this.roundNumber = 1;
        this.isExposed = false;
        this.isHarmful = true;

        this.dimension = universe.getDimension();
        this.developmentLimits = dimension * 10;

        darkForestKnownCulture = developmentLimits / 3;
        matureSociety = developmentLimits / 2;
        techLevel1 = developmentLimits / 4;
        techLevel2 = (developmentLimits / 4) * 2;
        techLevel3 = (developmentLimits / 4) * 3;





    }

    //MODIFIES: JSON object
    //EFFECTS: return a JSON object representing civilization with corresponding details
    public JSONObject civilizationDetails() {
        JSONObject cv = new JSONObject();

        cv.put("Name of the civilization",name);
        cv.put("Available resources", resources);
        cv.put("Round number",roundNumber);
        cv.put("Technology",technology);
        cv.put("Society",society);
        cv.put("Culture",culture);
        cv.put("Universe",universe);


        return cv;
    }

    //MODIFIES:this
    //REQUIRES: num >= 0
    //EFFECTS: add resources
    public void addResources(int num) {
        resources += num;
    }

    //MODIFIES:this
    //EFFECTS: add technology level by 1 and return true if there're still available development points
    //         and level hasn't reach the limits.Otherwise return false.
    public Boolean addTechnology() {
        if (resources > 0 && technology < developmentLimits) {
            technology++;
            resources--;
            return true;
        } else {
            return false;
        }
    }


    //MODIFIES:this
    //EFFECTS: add society level by 1 and return true if there're still available development points
    //         and level hasn't reach the limits.Otherwise return false.
    public Boolean addSociety() {
        if (resources > 0 && society < developmentLimits) {
            society++;
            resources--;
            return true;
        } else {
            return false;
        }
    }

    //MODIFIES:this
    //EFFECTS: add culture level by 1 and return true if there're still available development points
    //         and level hasn't reach the limits.Otherwise return false.
    public Boolean addCulture() {
        if (resources > 0 && culture < developmentLimits) {
            culture++;
            resources--;
            return true;
        } else {
            return false;
        }
    }

    //MODIFIES: this
    //EFFECTS: add round number by 1
    public void addRoundNumber() {
        roundNumber++;
    }



    //MODIFIES: this
    //EFFECTS: expose the civilization to the rest of the universe
    public void expose() {
        isExposed = true;
    }



    //MODIFIES: this
    //EFFECTS: the civilization makes a Safe Statement to the rest of the universe
    public void makeSafeStatement() {
        isHarmful = false;
    }

    //EFFECTS: get the round number
    public int getRoundNumber() {
        return this.roundNumber;
    }

    //MODIFIES: this
    //EFFECTS: set round number
    public void setRoundNumber(int n) {
        this.roundNumber = n;
    }



    //EFFECTS: get the remaining development points
    public int getResources() {
        return resources;
    }

    //MODIFIES: this
    //REQUIRES: num >= 0
    //EFFECTS : set the DevelopmentPoints
    public void setResources(int num) {
        this.resources = num;
    }

    //EFFECTS: get the technology level
    public int getTechnology() {
        return technology;
    }

    //MODIFIES: this
    //REQUIRES: num >= 0
    //EFFECTS : set the technology level
    public void setTechnology(int num) {
        this.technology = num;
    }

    //EFFECTS: get the society level
    public int getSociety() {
        return society;
    }

    //MODIFIES: this
    //REQUIRES: num >= 0
    //EFFECTS: set the society level
    public void setSociety(int num) {
        this.society = num;
    }

    //EFFECTS: get the culture level
    public int getCulture() {
        return culture;
    }

    //MODIFIES: this
    //REQUIRES: num >= 0
    //EFFECTS: set the culture level
    public void setCulture(int num) {
        this.culture = num;
    }


    //EFFECTS: get the value of isExposed
    public boolean getIsExposed() {
        return isExposed;
    }

    //MODIFIES: this
    //EFFECTS : set the value of isExposed
    public void setIsExposed(boolean i) {
        this.isExposed = i;
    }

    //EFFECTS: get the value of isHarmful
    public boolean getIsHarmful() {
        return isHarmful;
    }

    //MODIFIES: this
    //EFFECTS : set the value of isHarmful
    public void setIsHarmful(boolean i) {
        this.isHarmful = i;
    }



    //EFFECTS: get techLevel1
    public int getTechLevel1() {
        return techLevel1;
    }

    //MODIFIES: this
    //REQUIRES: num >=0
    //EFFECTS: set techLevel1
    public void setTechLevel1(int num) {
        this.techLevel1 = num;
    }

    //EFFECTS: get techLevel2
    public int getTechLevel2() {
        return techLevel2;
    }

    //MODIFIES: this
    //REQUIRES: num >=0
    //EFFECTS: set techLevel2
    public void setTechLevel2(int num) {
        this.techLevel2 = num;
    }

    //EFFECTS: get techLevel3
    public int getTechLevel3() {
        return techLevel3;
    }

    //MODIFIES: this
    //REQUIRES: num >=0
    //EFFECTS: set techLevel3
    public void setTechLevel3(int num) {
        this.techLevel3 = num;
    }

    //EFFECTS: get dimension
    public int getDimension() {
        return dimension;
    }

    //MODIFIES: this
    //REQUIRES: num >=0
    //EFFECTS: set dimension
    public void setDimension(int num) {
        this.dimension = num;
    }

    //EFFECTS: get darkForestKnownCulture
    public int getDarkForestKnownCulture() {
        return darkForestKnownCulture;
    }

    //MODIFIES: this
    //REQUIRES: num >=0
    //EFFECTS: set darkForestKnownCulture
    public void setDarkForestKnownCulture(int num) {
        this.darkForestKnownCulture = num;
    }

    //EFFECTS: get matureSociety
    public int getMatureSociety() {
        return matureSociety;
    }

    //MODIFIES: this
    //REQUIRES: num >=0
    //EFFECTS: set matureSociety
    public void setMatureSociety(int num) {
        this.matureSociety = num;
    }





    //EFFECTS: get development limits
    public int getDevelopmentLimits() {
        return developmentLimits;
    }

    //MODIFIES: this
    //REQUIRES: num >= 0
    //EFFECTS: set development limits
    public void setDevelopmentLimits(int num) {
        this.developmentLimits = num;
    }

    //EFFECTS : get the name of the civilization
    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECTS : set the name of the civilization
    public void setName(String name) {
        this.name = name;
    }

    //EFFECTS: get the universe of the civilization
    public Universe getUniverse() {
        return universe;
    }

    //MODIFIES: this
    //EFFECTS: set the universe of the civilization
    public void setUniverse(Universe universe) {
        this.universe = universe;
    }



}
