package model;

//Represent a civilization with int technology, society and culture level,dimension,developLimit.And boolean value of
//is exposed to university,has obtained Dark Forest Principle knowledge and light speed drive technology,is harmful.
public class Civilization {
    private String name;

    private int technology;
    private int society;
    private int culture;
    private int developmentPoints;

    private int darkForestKnownCulture = 15;
    private int matureSociety;

    private int dimension;
    private int developmentLimits = dimension * 10;


    private boolean isExposed;
    private boolean isHarmful;
    private boolean hasStarFleet;
    private boolean hasSophons;
    private boolean hasDarkForestPrinciple;
    private boolean hasLightSpeedTravel;
    private int techLevel1 = developmentLimits / 4;
    private int techLevel2 = (developmentLimits / 4) * 2;
    private int techLevel3 = (developmentLimits / 4) * 3;

    //constructor
    //REQUIRE: technology,society and culture have to be smaller or equal than developmentLimits
    //construct a civilization with certain technology,society and culture level.
    public Civilization(String name,int technology, int society, int culture) {
        this.name = name;

        this.technology = technology;
        this.society = society;
        this.culture = culture;

        this.developmentPoints = 0;
        //dimension

        this.isExposed = false;
        this.isHarmful = true;


        this.hasLightSpeedTravel = technology >= techLevel3 && society >= matureSociety;
        this.hasDarkForestPrinciple = culture >= darkForestKnownCulture;
        this.hasSophons = technology >= techLevel2;
        this.hasStarFleet = technology >= techLevel1;
    }

    //MODIFIES:this
    //EFFECTS: add development points
    public void addDevelopmentPoints(int num) {
        developmentPoints += num;
    }

    //MODIFIES:this
    //EFFECTS: add technology level by 1 if there're still available development points
    public void addTechnology() {
        technology++;
    }


    //MODIFIES:this
    //EFFECTS: add society level by 1 if there're still available development points
    public void addSociety() {
        society++;
    }

    //MODIFIES:this
    //EFFECTS: add culture level by 1 if there're still available development points
    public void addCulture() {
        culture++;
    }

    //MODIFIES: this
    //EFFECTS: expose the civilization to the rest of the universe
    public void expose() {
        isExposed = true;
    }

    //MODIFIES: this
    //EFFECTS: the Dark Forest Principle is obtained by the civilization
    public void obtainDarkForestPrinciple() {
        hasDarkForestPrinciple = true;
    }

    //MODIFIES: this
    //EFFECTS: the Light Speed Travel technology is obtained by the civilization
    public void obtainLightSpeedTravel() {
        hasLightSpeedTravel = true;
    }

    //MODIFIES: this
    //EFFECTS: the civilization makes a Safe Statement to the rest of the universe
    public void makeSafeStatement() {
        isHarmful = false;
    }

    //EFFECTS: get the remaining development points
    public int getDevelopmentPoints() {
        return developmentPoints;
    }

    //EFFECTS: get the technology level
    public int getTechnology() {
        return technology;
    }

    //EFFECTS: get the society level
    public int getSociety() {
        return society;
    }

    //EFFECTS: get the culture level
    public int getCulture() {
        return culture;
    }

    //EFFECTS: get the dimension of the culture
    public int getDimension() {
        return dimension;
    }

    //EFFECTS: get the value of isExposed
    public boolean getIsExposed() {
        return isExposed;
    }

    //EFFECTS: get the value of isHarmful
    public boolean getIsHarmful() {
        return isHarmful;
    }

    //EFFECTS: get the value of hasSophons
    public boolean getHasSophons() {
        return hasSophons;
    }

    //EFFECTS: get the value of hasDarkForestPrinciple
    public boolean getHasDarkForestPrinciple() {
        return hasDarkForestPrinciple;
    }

    //EFFECTS: get the value of hasLightSpeedTravel
    public boolean getHasLightSpeedTravel() {
        return hasLightSpeedTravel;
    }








}
