package ui;

import model.Civilization;
import model.Universe;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.Scanner;

public class UserApp {
    private static final int UNIVERSE_STARTING_DIMENSION = 10;
    private Civilization myCivilization;
    private Universe universe;

    private int roundNumber;
    private int resourcesWillGet;

    //Welcome panel
    //EFFECTS: welcome panel, you can choose to start a new game,go to tutorial or exit.
    public void welcomePanel() {
        Scanner input = new Scanner(System.in);

        System.out.println("---------------THE DARK FOREST---------------");
        System.out.println("-----------------VERSION 1.0-----------------");
        System.out.println("----------------BY ROKESOVSKY----------------");
        System.out.println("Welcome to the strategy game THE DARK FOREST!!");
        System.out.println("If you want to start a new game, press 1."
                + "If you want to go through tutorial,press 2."
                + "If you want to exit,press 3.");
        System.out.println("----------------------------------------------");
        int i = input.nextInt();
        if (i == 1) {
            newGame();
        } else if (i == 2) {
            tutorial1();
        } else if (i == 3) {
            System.exit(0);
        }



    }

    //Start a tutorial,give a brief introduction of this game works
    public void tutorial1() {
        Scanner input = new Scanner(System.in);


        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("The Dark Forest is a strategic game. You will be given a primitive civilization in a ");
        System.out.println("universe.The goal is to SURVIVE as long as possible. Your civilization has three factors");
        System.out.println("TECHNOLOGY,SOCIETY,CULTURE.You will be assigned resources to develop these three factors.");
        System.out.println("How you chose to distribute the resources will determine the future of your civilization");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println(">>>>>>please press 1 to continue");
        int num = input.nextInt();
        if (num == 1) {
            tutorial2();
        }
    }

    //EFFECTS: continue tutorial follows tutorial1
    public void tutorial2() {
        Scanner input = new Scanner(System.in);

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("TECHNOLOGY:");
        System.out.println("Technology is the most important factor for your civilization development.It will");
        System.out.println("directly determine the level of your civilization. There are 4 levels in total.");
        System.out.println("Civilization with lower technology level has slim chance of winning when encounter other");
        System.out.println("civilizations with higher technology level. Moreover, if the gap between your technology");
        System.out.println("and two other factors is too big, it will also negatively impact your civilization.");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println(">>>>>>please press 1 to continue");


        int num = input.nextInt();
        if (num == 1) {
            tutorial3();
        }
    }

    //EFFECTS: continue tutorial follows tutorial2
    public void tutorial3() {
        Scanner input = new Scanner(System.in);

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("SOCIETY:");
        System.out.println("Society is the representation of the stability of the civilization. Any technology ");
        System.out.println("development requires a stable society environment. If the gap between the society level");
        System.out.println("and technology level is too big,inner chaos or some other unpredictable result will occur");
        System.out.println("You will also want a mature society so that some advanced technology can be accepted.");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println(">>>>>>please press 1 to continue");

        int num = input.nextInt();
        if (num == 1) {
            tutorial4();
        }
    }

    //EFFECTS: continue tutorial follows tutorial3
    public void tutorial4() {
        Scanner input = new Scanner(System.in);

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("CULTURE");
        System.out.println("Culture represent the level of the culture development including knowledge,belief,arts...");
        System.out.println("It's what make your civilization unique and colourful.It's also a crucial element for ");
        System.out.println("your civilization to realize the most important law in the universe which could help");
        System.out.println("you make wiser decision and increase the chance of survival.");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println(">>>>>>please press 1 to continue");

        int num = input.nextInt();
        if (num == 1) {
            tutorial5();
        }
    }

    //EFFECTS: continue tutorial follows tutorial4
    public void tutorial5() {
        Scanner input = new Scanner(System.in);

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("In this game, your civilization will be putted into a universe alongside with other");
        System.out.println("civilizations you customized. You can't interact with others until a certain level of");
        System.out.println("technology. After that level, you might encounter other civilizations and start a war");
        System.out.println("The goal is to eliminate all the threats and keep your civilization alive as long as ");
        System.out.println("possible. Have fun!");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println(">>>>>>please press 1 to end tutorial");

        int num = input.nextInt();
        if (num == 1) {
            welcomePanel();
        }

    }


    //EFFECTS: Start a new game
    public void newGame() {
        universe = new Universe(UNIVERSE_STARTING_DIMENSION);
        myCivilization = new Civilization("",0,0,0,universe);
        roundNumber = 1;

        customizeUniverse();
        gameIntroAndSetName();
        gameRoundEntry();
    }



    //EFFECTS: let user to customize the structure of the universe(i.e customize the number and parameters of other
    //         civilizations.
    public void customizeUniverse() {
        Scanner input = new Scanner(System.in);


        System.out.println("You need to customize your universe.It's your choice to determine the numbers of other "
                + "civilizations.");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println(">>>>Please type the number of the size of the universe down");
        int num = input.nextInt();
        for (int i = 1;i <= num;i++) {
            int max = 100;
            int min = 0;
            //create random int for the three parameters of other civilizations
            int randomInt1 = (int) (Math.random() * (max - min + 1) + min);
            int randomInt2 = (int) (Math.random() * (max - min + 1) + min);
            int randomInt3 = (int) (Math.random() * (max - min + 1) + min);

            //create a random 5-byte-long name for other civilizations
            byte[] array = new byte[5];
            new Random().nextBytes(array);
            String name = new String(array, Charset.forName("UTF_8"));

            Civilization civilization = new Civilization(name,randomInt1,randomInt2,randomInt3, universe);
            universe.addCivilization(civilization);
        }
    }

    //EFFECTS: In game intro and let user to set the name of it's own civilization.
    public void gameIntroAndSetName() {
        Scanner input1 = new Scanner(System.in);

        System.out.println("In a small corner of nowhere,a tiny but extremely dense and hot gravitational singularity"
                + " is shaking excitedly....");
        System.out.println("And just in less than 10^-6 seconds, it EXPANDS!! Space,mass,energy,time emerges to all "
                + "directions in a speed that nobody could imagine");
        System.out.println("A universe with dimensionality of 10 is just created...");
        System.out.println("...After millions of centuries, somewhere in the corner of the universe, a civilization is"
                + "born. This is your civilization. Although it is primitive,it could have infinite possibilities...");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println(">>>>Please enter the name of your civilizations.");

        String name = input1.nextLine();
        myCivilization.setName(name);
    }

    //EFFECTS: the entry of each round of the game
    public void gameRoundEntry() {

        otherCivilDevelopment();


        System.out.println("Your civilization: " + myCivilization.getName() + "||Technology: "
                + myCivilization.getTechnology() + "||Society: " + myCivilization.getSociety() + "||Culture: "
                + myCivilization.getCulture() + "||Resources: " + myCivilization.getResources()
                + "||Round: " + roundNumber);

        if (universe.getCivilizations().size() == 0) {
            winCondition1();
        } else if (myCivilization.getTechnology() < myCivilization.getTechLevel1()) {
            primitiveCivilization();
        } else if (myCivilization.getTechnology() >= myCivilization.getTechLevel1()
                && myCivilization.getTechnology() < myCivilization.getTechLevel2()) {
            level1Civilization();
        } else if (myCivilization.getTechnology() >= myCivilization.getTechLevel2()
                && myCivilization.getTechnology() < myCivilization.getTechLevel3()) {
            level2Civilization();
        } else if (myCivilization.getTechnology() >= myCivilization.getTechLevel3()) {
            starPluckerCivilization();
        }

    }

    //EFFECTS: other civilizations develop
    public void otherCivilDevelopment() {
        for (Civilization i : universe.getCivilizations()) {
            i.addResources(3);
            i.addTechnology();
            i.addSociety();
            i.addCulture();
        }
    }



    //EFFECTS: the user's civilization eliminates all other civilizations and win.
    public void winCondition1() {
        Scanner input = new Scanner(System.in);

        System.out.println("Congratulation!You've successfully eliminated all"
                + universe.getCivilizations().size() + "\n civilizations in the universe");
        System.out.println("Your civilization will be safe in the next few thousands of centuries until some other "
                + "civilizations born in the nebula.");
        System.out.println("Yes,indeed,you've killed uncountable numbers of people,you destroyed millions of planets");
        System.out.println("But this is only way to be absolutely safe in this universe without sacrificing anything "
                + "of your own civilizations.");
        System.out.println("The universe is like the cruelest and darkest Forest, civilizations are like hunters "
                + "trying their best to hide their location and eliminate any spotted enemies.");
        System.out.println("You're just the one who is lucky and strong enough to survive,there's nothing wrong.");
        System.out.println("Yet it's impossible for this kind of winning to happen in real life as there're too many"
                + "super-civilizations out there.");
        System.out.println("Someone will definitely locate earth someday and eliminate this thread...");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println(">>>>press 1 to return to the welcome panel");

        int num = input.nextInt();
        if (num == 1) {
            welcomePanel();
        }

    }

    //EFFECTS: the game process for primitive civilization
    public void primitiveCivilization() {
        Scanner input = new Scanner(System.in);
        resourcesWillGet = 15;


        techDegenerates();
        innerChaos();
        societyStagnation();

        development(resourcesWillGet);

        roundNumber++;

        System.out.println("------------------------------------------------------------");
        System.out.println(">>>>press 1 to end this round");
        int num = input.nextInt();
        if (num == 1) {
            gameRoundEntry();
        }
    }

    //EFFECTS: user will be assigned certain resources and user can decide how to distribute them
    public void development(int d) {

        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        Scanner input3 = new Scanner(System.in);

        myCivilization.addResources(d);

        System.out.println("You obtain " + d + " resources, please distribute "
                + "them wisely");

        teachDevelop(input1);
        societyDevelop(input2);
        cultureDevelop(input3);
    }

    public void cultureDevelop(Scanner input3) {
        System.out.println(">>>>Please enter the number of resources you want to put in culture development||"
                + "Available resources: " + myCivilization.getResources() + "||Technology: "
                + myCivilization.getTechnology() + "||Society: " + myCivilization.getSociety() + "||Culture: "
                + myCivilization.getCulture());
        int num3 = input3.nextInt();
        for (int i = 1; i <= num3; i++) {
            myCivilization.addCulture();
        }
    }

    //EFFECTS: develop society
    public void societyDevelop(Scanner input2) {
        System.out.println(">>>>Please enter the number of resources you want to put in society development||"
                + "Available resources: " + myCivilization.getResources() + "||Technology: "
                + myCivilization.getTechnology() + "||Society: " + myCivilization.getSociety() + "||Culture: "
                + myCivilization.getCulture());
        int num2 = input2.nextInt();
        for (int i = 1; i <= num2; i++) {
            myCivilization.addSociety();
        }
    }

    //EFFECTS: develop technology
    public void teachDevelop(Scanner input1) {
        System.out.println(">>>>Please enter the number of resources you want to put in technology development||"
                + "Available resources: " + myCivilization.getResources() + "||Technology: "
                + myCivilization.getTechnology() + "||Society: " + myCivilization.getSociety() + "||Culture: "
                + myCivilization.getCulture());
        int num1 = input1.nextInt();
        for (int i = 1; i <= num1; i++) {
            myCivilization.addTechnology();
        }
    }

    //EFFECTS: tech stagnate, society and culture fall back to the level of tech
    public void societyStagnation() {
        if (myCivilization.getSociety() - myCivilization.getTechnology() > 10
                || myCivilization.getCulture() - myCivilization.getTechnology() > 10) {
            System.out.println("Ooops, your technology level is falling far behind your society requirements");
            System.out.println("The development of society is not based on any substantial technological breakthrough");
            System.out.println("thus the bubble economy is very easily broken.");
            System.out.println("As punishment,your society and culture level will degrade to your technological level");
            System.out.println("-------------------------------------------------------------------------------------");

            myCivilization.setSociety(myCivilization.getTechnology());
            myCivilization.setCulture(myCivilization.getTechnology());
        }
    }

    //EFFECTS: civilization's society degenerates,tech level will fall back to the same level as culture
    public void techDegenerates() {
        if (myCivilization.getTechnology() - myCivilization.getCulture() > 10) {
            System.out.println("Ooops,your culture level is falling far behind your technology");
            System.out.println("The technology development needs support of all other academic areas,");
            System.out.println("Your level of technology has exceeded the range that the public could understand and "
                    + "accept. Your people are not happy about it.");
            System.out.println("As a punishment, your technology level will be deduced by 10");
            System.out.println("-------------------------------------------------------------------------------------");

            myCivilization.setTechnology(myCivilization.getTechnology() - 10);
        }
    }

    //EFFECTS: the civilization have inner chaos,user will be assigned less resources
    public void innerChaos() {
        if (myCivilization.getTechnology() - myCivilization.getSociety() > 10) {

            System.out.println("Ooops,your society level is falling far behind your technology level.");
            System.out.println("You used too many resources to development technology and ignored the livelihood");
            System.out.println("of your people. Your people are angry and start a riot");
            System.out.println("As a punishment, your resources will be deduced by 15");
            System.out.println("-------------------------------------------------------------------------------------");

            myCivilization.setResources(myCivilization.getResources() - 15);
        }
    }


    /////
    //EFFECTS:game process for level 1 civilization
    public void level1Civilization() {

        Scanner input = new Scanner(System.in);
        resourcesWillGet = 12;
        myCivilization.setHasInterCivilCommunication(true);

        System.out.println("Your civilization has developed to Level 1 Civilization");
        System.out.println("You now have ability to setup connections with other civilizations");
        darkForestWarning();

        level1ProactiveContact();

        level1SelfExpose();
        techDegenerates();
        societyStagnation();

        development(resourcesWillGet);

        roundNumber++;

        System.out.println("------------------------------------------------------------");
        System.out.println(">>>>press 1 to end this round");
        int num = input.nextInt();
        if (num == 1) {
            gameRoundEntry();
        }

    }

    //EFFECTS: warn user about the dark forest principle
    public void darkForestWarning() {
        if (myCivilization.getSociety() >= myCivilization.getDarkForestKnownCulture()) {
            myCivilization.setDarkForestPrinciple(true);

            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("WARNING!!!!!");
            System.out.println("One of your scientist named Logic just proved the validity of Dark Forest Principle");
            System.out.println("setting up connections with other civilizations is extremely dangerous!!!!!");
            System.out.println("War between two civilizations will immediately occurs once the connections set up");
            System.out.println("This action might expose the location of your civilizations to the rest of universe");
            System.out.println("Your civilization could be immediately destroyed by super-civilization");
            System.out.println("-------------------------------------------------------------------------------------");

        }
    }

    //EFFECTS: passively contact with other civilizations when my civilization is at level 1
    public void level1SelfExpose() {
        if (myCivilization.getTechnology() - myCivilization.getSociety() > 8) {
            System.out.println("Ooops, your society level fall too far behind your technology level");
            System.out.println("You spent too many resources on technology development and too less on developing your"
                    + " people's livelihood.");
            System.out.println("One of your citizens can't stand the miserable life in your civilization and send a "
                    + "message to the universe asking for help");
            System.out.println("And it is received by a civilization......");

            level1Contact();

        }
    }

    //EFFECTS : proactively contact with other civilizations when my civilization is at level 1
    public void level1ProactiveContact() {
        Scanner input = new Scanner(System.in);

        System.out.println("*************************************************************");
        System.out.println("You can now try to contact with another civilization in the universe");
        System.out.println("*************************************************************");
        System.out.println(">>>> press 1 if you want to contact,press 2 to skip");
        int n = input.nextInt();
        if (n == 1) {
            level1Contact();
        }
    }

    //EFFECTS: process of contact with another civilization when the user civilization is at level 1
    public void level1Contact() {
        int max = universe.getCivilizations().size() - 1;
        int min = 0;

        int randomInt = (int) (Math.random() * (max - min + 1) + min);
        Civilization enemy = universe.getCivilizations().get(randomInt);

        if (enemy.getTechnology() < enemy.getTechLevel2()) {
            invalidContact();
        } else if (enemy.getTechnology() >= enemy.getTechLevel2()
                && enemy.getTechnology() < enemy.getTechLevel3()
                && myCivilization.getHasDarkForestPrinciple()) {
            deterrenceSetUp();
        } else if (enemy.getTechnology() >= enemy.getTechLevel2()
                && enemy.getTechnology() < enemy.getTechLevel3()
                && !myCivilization.getHasDarkForestPrinciple()) {
            destroyedByLevel2Civil();
        } else if (enemy.getTechnology() >= enemy.getTechLevel3()) {
            destroyedByStarPlucker();
        }
    }


    //EFFECTS: the contact with another civilization is invalid because the enemy tech level is below level2
    public void invalidContact() {
        System.out.println("There's no civilization on your broadcast direction."
                + "Your contact attempt didn't reach to any civilizations");
    }

    //EFFECTS: the contact result in deterrence between the civilization
    public void deterrenceSetUp() {
        System.out.println("Your contact attempt reached to a civilization that has much higher technology");
        System.out.println("They are now sending sophons to block your tech development and star fleets to eliminate "
                + "you");
        System.out.println("It seems like your civilization has no chance of survival anymore...... ");
        System.out.println("But at this desperate moment, it's Logic,the scientist, again. Successfully set up a "
                + "deterrence between two civilizations");
        System.out.println("He threats to broadcast enemy's civilization's location to the universe");
        System.out.println("Apparently, the enemy is also afraid of the Dark Forest attack");
        System.out.println("They withdraw their sophons and star fleets, your civilization is safe again");
    }

    //EFFECTS: my civilization is destroyed by level 2 civilization
    public void destroyedByLevel2Civil() {
        System.out.println("Your contact attempt reached to a civilization that has much higher technology");
        System.out.println("They are now sending sophons to block your tech development and star fleets to eliminate "
                + "you");
        System.out.println("There no way you can escape from this......");
        System.out.println("...Two centuries later,their fleets arrive,they wipe out all humans and occupy the planet");
        endGame();
    }

    //EFFECTS: my civilization is destroyed by star plucker
    public void destroyedByStarPlucker() {
        System.out.println("Your contact attempt reached to a star plucker, the most advanced form of civilization.");
        System.out.println("They lunch attack immediately,using the cheapest yet the most effective way of attack");
        System.out.println("A photoid with light speed strike the star in the center of your civilization's galaxy");
        System.out.println("This leads to the planets' utter incineration,your whole galaxy falls into flames...");
        endGame();
    }

    //EFFECTS: end the game
    public void endGame() {
        Scanner input = new Scanner(System.in);

        System.out.println("The game is end, your civilization " + myCivilization.getName()
                + " has survived for " + roundNumber + " rounds");
        System.out.println("-----------------------------------------------------------------");
        System.out.println(">>>>please press 1 to return to welcome panel");

        int num = input.nextInt();
        if (num == 1) {
            welcomePanel();
        }

    }




    /////
    //EFFECTS: game process for level 2 civilization
    public void level2Civilization() {
        Scanner input = new Scanner(System.in);
        resourcesWillGet = 15;
        myCivilization.setHasStarFleet(true);

        System.out.println("Your civilization has developed to Level 2 Civilization");
        System.out.println("You now have ability to attack other civilizations and occupy other civilization");

        level2ProactiveContact();

        level2SelfExpose();
        techDegenerates();
        societyStagnation();

        development(resourcesWillGet);

        roundNumber++;

        System.out.println("------------------------------------------------------------");
        System.out.println(">>>>press 1 to end this round");
        int num = input.nextInt();
        if (num == 1) {
            gameRoundEntry();
        }

    }

    //EFFECTS: proactively contact with other civilizations when my civilization is at level 2
    public void level2ProactiveContact() {
        Scanner input = new Scanner(System.in);

        System.out.println("*************************************************************");
        System.out.println("You can now try to contact with and attack another civilization in the universe.");
        System.out.println("Attacking other civilization requires 30 resources");
        System.out.println("But if you successfully occupy another civilization, you'll gain resources");
        System.out.println("*************************************************************");
        System.out.println(">>>> press 1 if you want to contact and attack ,press 2 to skip");
        int n = input.nextInt();
        if (n == 1) {
            level2Contact();
        }
    }

    //EFFECTS: passively contact with other civilizations when my civilization is at level 2
    public void level2SelfExpose() {
        if (myCivilization.getTechnology() - myCivilization.getSociety() > 8) {
            System.out.println("Ooops, your society level fall too far behind your technology level");
            System.out.println("You spent too many resources on technology development and too less on developing your"
                    + " people's livelihood.");
            System.out.println("One of your citizens can't stand the miserable life in your civilization and send a "
                    + "message to the universe asking for help");
            System.out.println("And it is received by a civilization......");

            System.out.println("*************************************************************");

            level2Contact();
        }
    }

    //EFFECTS: process of contact with another civilization when the user civilization is at level 2
    public void level2Contact() {
        int max = universe.getCivilizations().size() - 1;
        int min = 0;

        int randomInt = (int) (Math.random() * (max - min + 1) + min);
        Civilization enemy = universe.getCivilizations().get(randomInt);

        if (enemy.getTechnology() < enemy.getTechLevel1()) {
            invalidContact();
        } else if (enemy.getTechnology() >= enemy.getTechLevel1()
                && enemy.getTechnology() < myCivilization.getTechnology()) {
            attackAndWin(enemy);
        } else if (enemy.getTechnology() >= myCivilization.getTechnology()
                && enemy.getTechnology() < enemy.getTechLevel3()) {
            attackAndLose(enemy);
        } else if (enemy.getTechnology() >= enemy.getTechLevel3()) {
            destroyedByStarPlucker();
        }
    }

    //EFFECTS: attack and eliminate another civilization
    public void attackAndWin(Civilization enemy) {
        if (myCivilization.getResources() >= 30) {
            myCivilization.setResources(myCivilization.getResources() - 30);

            System.out.println("First,you send few sophons to their galaxy to block their technology development");
            System.out.println("so that the fleet you send right now could keep technology advantage after arriving");
            System.out.println("And then your magnificent Star fleet heads to enemy's galaxy...");
            System.out.println("...5 centuries later, your fleets reach the destination and encounter enemy's defense");
            System.out.println("But they can't compete with you because their technology level is lower than yours");
            System.out.println("You just use one 'droplets' ship to destroy all of their lagging fleets");
            System.out.println("with no doubt, you eliminate the civilization and occupy the galaxy");
            System.out.println("Additionally, you gain 30 resource points");

            universe.destroyCivilization(enemy);
            myCivilization.addResources(30);
        } else {
            System.out.println("There're no enough resources to launch attack.REQUIRE:30");
        }
    }

    //EFFECTS: attack and lose, might be eliminated if the enemy tech - my tech >= 10
    public void attackAndLose(Civilization enemy) {
        if (myCivilization.getResources() >= 30) {
            myCivilization.setResources(myCivilization.getResources() - 30);

            System.out.println("First,you send few sophons to their galaxy to block their technology development");
            System.out.println("so that the fleet you send right now could keep technology advantage after arriving");
            System.out.println("And then your magnificent Star fleet heads to enemy's galaxy...");
            System.out.println("...5 centuries later, your fleets reach the destination and encounter enemy's defense");
            System.out.println("Sadly, your fleets get ambushed and you surprising find out they have more advance"
                    + "technology");
            System.out.println("Your fleet fights bravely but the gap between technology is too hard to overturn");
            System.out.println("Your fleet gets annihilated completely...");

            if (enemy.getTechnology() - myCivilization.getTechnology() >= 10) {
                getCounterAttack();
            } else {
                System.out.println("But your attack still hit them hard,they don't have enough resources to launch "
                        + "counter attack. Your civilization is still safe for now");
            }

        }
    }

    //EFFECTS: follows attack and lose. Enemy launch counter attack and eliminate my civilization
    public void getCounterAttack() {
        System.out.println("...After they destroyed all your fleet, enemy gathered all their remaining force to launch"
                + " a counter attack.");
        System.out.println("They are now sending sophons to block your tech development and star fleets to eliminate "
                + "you");
        System.out.println("There no way you can win this......");
        System.out.println("...Two centuries later,their fleets arrive,they wipe out all humans and occupy the planet");
        endGame();
    }



    /////
    //EFFECTS: game process of Star Plucker civilization
    public void starPluckerCivilization() {
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);

        resourcesWillGet = 10;
        myCivilization.setLightSpeedTravel(true);

        System.out.println("Your civilization has developed to Star Plucker Civilization");
        System.out.println("You now obtain the technology of curvature-drive.You can now easily eliminate any "
                + "civilization that has tech level star plucker level");
        System.out.println("The only threats you will face is other Star Pluckers. The conflicts between two star"
                + " pluckers could be very brutal");
        System.out.println("However, you have another choice. You could make a Safe Statement to the universe to prove"
                + " your civilization is harmless");
        System.out.println("This is your choice. >>>press 1 to make safe statement , press 2 to skip");
        int n1 = input1.nextInt();
        if (n1 == 1) {
            universeSafeStatement();
        } else if (n1 == 2) {
            starPluckerContinuePlay(input2);
        }

    }

    public void starPluckerContinuePlay(Scanner input2) {
        starPluckerProactiveContact();

        starPluckerSelfExpose();

        techDegenerates();
        societyStagnation();

        development(resourcesWillGet);

        roundNumber++;

        System.out.println("------------------------------------------------------------");
        System.out.println(">>>>press 1 to end this round");
        int num = input2.nextInt();
        if (num == 1) {
            gameRoundEntry();
        }
    }

    //EFFECTS: civilization makes a safe statement
    public void universeSafeStatement() {
        System.out.println("You chose to make universe safe statement,yet you didn't even know how cruel it could be");
        System.out.println("The statement is not something like just a statement to other civilizations in text");
        System.out.println("It's not that easy. Imagine if you receive a text from another unknown civilization "
                + "saying they're safe, will you believe it?");
        System.out.println("The real universe statement is to make others believe your civilization is completely "
                + "harmless when they observe your galaxy.");
        System.out.println("The only way to accomplish that is to encircling your own civilization with black holes");
        System.out.println("Nothing could escape from black hole, therefore your civilization is definitely harmless");
        System.out.println("The cause of this is obvious,your civilization lost all the chances to explore the universe"
                + "and has to live in a world where light speed is only 16.7 km/s");
        System.out.println("However,this is not exactly a bad thing. Your civilization survives until the end of"
                + " universe.Your people peacefully live their lives forever......");

        roundNumber = 99999;
        endGame();

    }

    //EFFECTS: proactively contact with other civilizations when my civilization is at Star Plucker level
    public void starPluckerProactiveContact() {
        Scanner input = new Scanner(System.in);

        System.out.println("*************************************************************");
        System.out.println("You can now try to contact with and attack another civilization in the universe.");
        System.out.println("Attacking civilizations with tech lower than Star Plucker level only requires 1 resource");
        System.out.println("You just need to pluck a tiny photoid with speed of light to strike their star. The rest of"
                + " the galaxy will be incinerated due to explosion of the star");
        System.out.println("And you won't gain any resources because there'll be nothing left in than galaxy except "
                + "ashes. ");
        System.out.println("*************************************************************");
        System.out.println(">>>> press 1 if you want to contact and attack ,press 2 to skip");

        int n = input.nextInt();
        if (n == 1) {
            modifyOwnDimensionality();
            starPluckerContact();
        }
    }

    //EFFECTS: let user to choose whether modify their own dimensionality or not
    public void modifyOwnDimensionality() {
        Scanner input = new Scanner(System.in);
        System.out.println("*************************************************************");

        System.out.println("However, if you encounter another Star Plucker civilization, brutal fight might occur");
        System.out.println("The most basic physics laws could be used in this level of war like dimensionality attack");
        System.out.println("Any dimensionality attack (whether it's from or against you) will cause the dimensionality"
                + " of the universe reduce by one");
        System.out.println("Civilization with higher dimensionality can't survive in universe with lower dimension ");
        System.out.println("The only way to prevent this is to transform your whole civilization into a lower dimension"
                + " before the attack");
        System.out.println("Doing this will cause the upper limits for tech,society and culture and three factors "
                + "themselves to drop by 10 and will clear all your resources");
        System.out.println("If the dimensionality of the universe is reduced to 0 the game is over");
        System.out.println("It's your choice of whether transform your civilization");
        System.out.println(">>>> press 1 if you want transformation, press 2 to skip");
        System.out.println("*************************************************************");

        int num = input.nextInt();
        if (num == 1) {
            myCivilization.setDimension(myCivilization.getDimension() - 1);
            myCivilization.setTechnology(myCivilization.getTechnology() - 10);
            myCivilization.setSociety(myCivilization.getSociety() - 10);
            myCivilization.setCulture(myCivilization.getCulture() - 10);
            myCivilization.setResources(0);
        }


    }

    //EFFECTS: passively contact with other civilizations when my civilization is at Star Plucker level
    public void starPluckerSelfExpose() {
        if (myCivilization.getTechnology() - myCivilization.getSociety() > 8) {
            System.out.println("Ooops, your society level fall too far behind your technology level");
            System.out.println("You spent too many resources on technology development and too less on developing your"
                    + " people's livelihood.");
            System.out.println("One of your citizens can't stand the miserable life in your civilization and send a "
                    + "message to the universe asking for help");
            System.out.println("And it is received by a civilization......");
            System.out.println("*************************************************************");

            starPluckerContact();

        }
    }



    //EFFECTS: process of contact with another civilization when the user civilization is at star plucker level
    public void starPluckerContact() {
        int max = universe.getCivilizations().size() - 1;
        int min = 0;

        int randomInt = (int) (Math.random() * (max - min + 1) + min);
        Civilization enemy = universe.getCivilizations().get(randomInt);

        if (enemy.getTechnology() < enemy.getTechLevel1()) {
            invalidContact();
        } else if (enemy.getTechnology() >= enemy.getTechLevel1()
                && enemy.getTechnology() < enemy.getTechLevel3()) {
            pluckAPhotoid(enemy);
        } else if (enemy.getTechnology() >= enemy.getTechLevel3()) {
            dimensionWar(enemy);
        }
    }

    //EFFECTS: a dimension war in universe eliminate all low level civilizations and
    //         reduce the dimensionality of the universe by 1
    public void dimensionWar(Civilization enemy) {
        System.out.println("You encounter another Star Plucker, the largest cruelest war happens immediately");
        System.out.println("Millions of super star fleets are firing at each others across the galaxies");
        System.out.println("Countless photoids are flying through the universe, striking stars.The explosions of stars"
                + " are like never ending fireworks");
        System.out.println("Ton's of black holes are built to track enemies' fleets and photoids...");
        System.out.println("Finally, you shoots out a 'dual vector foil' and reduce the dimension of the"
                + " whole universe by one...");

        universe.destroyCivilization(enemy);

        for (Civilization c : universe.getCivilizations()) {
            if (c.getTechnology() < c.getTechLevel3()) {
                universe.destroyCivilization(c);
            } else {
                c.setTechnology(c.getTechnology() - 10);
                c.setSociety(c.getSociety() - 10);
                c.setCulture(c.getCulture() - 10);
            }
        }

        if (universe.getDimension() > 1) {
            universe.dimensionalityReduction();
            System.out.println("All low level civilizations and your enemy are destroyed in lower dimension universe");
        } else {
            universeDestroyed();
        }

    }

    //EFFECTS: the dimension of universe falls to 0,game over
    public void universeDestroyed() {
        System.out.println("*************************************************************");
        System.out.println("...Unfortunately, the dimension of the universe is reduced to 0 after this dimensionality"
                + "attack");
        System.out.println("The universe return to the form of gravitational singularity,all civilizations are "
                + "destroyed");
        endGame();

    }

    //EFFECTS: attack an enemy using photoid
    public void pluckAPhotoid(Civilization enemy) {
        System.out.println("You drive a wrap driven spaceship heading to the enemy civilization in light speed...");
        System.out.println("Soon, you reach to the edge of their galaxy. They did't noticed you because your ship is "
                + "totally invisible.");
        System.out.println("You take out a ting photoid and aim to that star in the center of their galaxy");
        System.out.println("Swiftly, you pluck the photoid out and then drive the ship away");
        System.out.println("You don't need to check the explosion.Because you know what will happen as you have already"
                + "done this boring job thousands of times...");
        System.out.println("...Behind you,the photoid precisely strike the star and the entire civilization is "
                + "incinerated in the explosion of the star... ");

        universe.destroyCivilization(enemy);
    }


}
