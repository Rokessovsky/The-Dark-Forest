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
    private int matureSociety = 50;

    private int dimension;
    private int developmentLimits;


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
    public Civilization(String name,int technology, int society, int culture,Universe universe) {
        this.name = name;

        this.technology = technology;
        this.society = society;
        this.culture = culture;

        this.developmentPoints = 0;
        //dimension

        this.isExposed = false;
        this.isHarmful = true;

        this.dimension = universe.getDimension();
        this.developmentLimits = dimension * 10;




        this.hasLightSpeedTravel = technology >= techLevel3 && society >= matureSociety;
        this.hasDarkForestPrinciple = culture >= darkForestKnownCulture;
        this.hasSophons = technology >= techLevel2;
        this.hasStarFleet = technology >= techLevel1;
    }

    //MODIFIES:this
    //REQUIRES: num >= 0
    //EFFECTS: add development points
    public void addDevelopmentPoints(int num) {
        developmentPoints += num;
    }

    //MODIFIES:this
    //EFFECTS: add technology level by 1 and return true if there're still available development points
    //         and level hasn't reach the limits.Otherwise return false.
    public Boolean addTechnology() {
        if (developmentPoints > 0 && technology < developmentLimits) {
            technology++;
            developmentPoints--;
            return true;
        } else {
            return false;
        }
    }


    //MODIFIES:this
    //EFFECTS: add society level by 1 and return true if there're still available development points
    //         and level hasn't reach the limits.Otherwise return false.
    public Boolean addSociety() {
        if (developmentPoints > 0 && society < developmentLimits) {
            society++;
            developmentPoints--;
            return true;
        } else {
            return false;
        }
    }

    //MODIFIES:this
    //EFFECTS: add culture level by 1 and return true if there're still available development points
    //         and level hasn't reach the limits.Otherwise return false.
    public Boolean addCulture() {
        if (developmentPoints > 0 && culture < developmentLimits) {
            culture++;
            developmentPoints--;
            return true;
        } else {
            return false;
        }
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

    //REQUIRES: num >= 0
    //EFFECTS : set the DevelopmentPoints
    public void setDevelopmentPoints(int num) {
        this.developmentPoints = num;
    }

    //EFFECTS: get the technology level
    public int getTechnology() {
        return technology;
    }

    //REQUIRES: num >= 0
    //EFFECTS : set the technology level
    public void setTechnology(int num) {
        this.technology = num;
    }

    //EFFECTS: get the society level
    public int getSociety() {
        return society;
    }

    //REQUIRES: num >= 0
    //EFFECTS: set the society level
    public void setSociety(int num) {
        this.society = num;
    }

    //EFFECTS: get the culture level
    public int getCulture() {
        return culture;
    }

    //REQUIRES: num >= 0
    //EFFECTS: set the culture level
    public void setCulture(int num) {
        this.culture = num;
    }


    //EFFECTS: get the value of isExposed
    public boolean getIsExposed() {
        return isExposed;
    }

    //EFFECTS : set the value of isExposed
    public void setIsExposed(boolean i) {
        this.isExposed = i;
    }

    //EFFECTS: get the value of isHarmful
    public boolean getIsHarmful() {
        return isHarmful;
    }

    //EFFECTS : set the value of isHarmful
    public void setIsHarmful(boolean i) {
        this.isHarmful = i;
    }

    //EFFECTS: get the value of hasStarFleet
    public boolean getHasStarFleet() {
        return hasStarFleet;
    }

    //EFFECTS : set the value of hasStarFleet
    public void setHasStarFleet(boolean i) {
        this.hasStarFleet = i;
    }


    //EFFECTS: get the value of hasSophons
    public boolean getHasSophons() {
        return hasSophons;
    }

    //EFFECTS : set the value of hasSophons
    public void setHasSophons(boolean i) {
        this.hasSophons = i;
    }



    //EFFECTS: get the value of hasDarkForestPrinciple
    public boolean getHasDarkForestPrinciple() {
        return hasDarkForestPrinciple;
    }

    //EFFECTS : set the value of hasDarkForestPrinciple
    public void setDarkForestPrinciple(boolean i) {
        this.hasDarkForestPrinciple = i;
    }

    //EFFECTS: get the value of hasLightSpeedTravel
    public boolean getHasLightSpeedTravel() {
        return hasLightSpeedTravel;
    }

    //EFFECTS : set the value of hasLightSpeedTravel
    public void setLightSpeedTravel(boolean i) {
        this.hasLightSpeedTravel = i;
    }

    //EFFECTS: get development limits
    public int getDevelopmentLimits() {
        return developmentLimits;
    }

    //REQUIRES: num >= 0
    //EFFECTS: set development limits
    public void setDevelopmentLimits(int num) {
        this.developmentLimits = num;
    }

    //EFFECTS : get the name of the civilization
    public String getName() {
        return name;
    }

    //EFFECTS : set the name of the civilization
    public void setName(String name) {
        this.name = name;
    }


}
