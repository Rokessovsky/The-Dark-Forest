package ui;

import model.Civilization;
import model.Universe;
import persistence.CivilizationReader;
import persistence.CivilizationWriter;
import persistence.UniverseReader;
import persistence.UniverseWriter;
import ui.panels.*;
import ui.panels.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;

public class UserApp extends JFrame {
    private static final int UNIVERSE_STARTING_DIMENSION = 10;
    private static final String CIVILIZATION_STORE = "./data/JsonMyCivilization";
    private static final String UNIVERSE_STORE = "./data/JsonUniverse";
    //deleted


    private CivilizationWriter cvWriter;
    private CivilizationReader cvReader;
    private UniverseWriter uniWriter;
    private UniverseReader uniReader;
    //deleted
    private int roundNumber;
    private int resourcesWillGet;

    static final Dimension FRAME_DIMENSION = new Dimension(1200, 742);
    private Panel mainPanel;


    //UserApp
    //EFFECTS: initialise welcome panel
    public UserApp() {
        super("THE DARK FOREST");



        setLayout(new GridBagLayout());
        setPreferredSize(FRAME_DIMENSION);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Universe universe = new Universe("",10);
        Civilization myCivilization = new Civilization("",0,0,0,universe);
        mainPanel = new WelcomePanel(this, myCivilization, universe);

        add(mainPanel);
        addWindowListener(new SaveGame());
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

    }


    //MODIFIES: this
    //EFFECTS: Load saved game
    public void loadGame() {
        try {
            cvReader = new CivilizationReader(CIVILIZATION_STORE);
            uniReader = new UniverseReader(UNIVERSE_STORE);

            mainPanel.setCivilization(cvReader.read());
            mainPanel.setUniverse(uniReader.read());
            mainPanel.setRoundNumber(mainPanel.getCivilization().getRoundNumber());
            JFrame frame = new JFrame("Message From the Controller");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JOptionPane.showMessageDialog(frame,"Loaded civilization " + mainPanel.getCivilization().getName()
                    + " from " + CIVILIZATION_STORE + "and" + UNIVERSE_STORE);

            enterGameAfterLoad();

        } catch (IOException e) {
            System.out.println("Unable to read from file: " + CIVILIZATION_STORE + "or" + UNIVERSE_STORE);
        }
    }

    //EFFECTS: choose panel to enter after load the game data
    public void enterGameAfterLoad() {
        CivilizationPanel cp;
        Civilization myCivil = mainPanel.getCivilization();

        if (myCivil.getTechnology() < myCivil.getTechLevel1()) {
            cp = new PrimitiveCivilizationPanel(this,mainPanel.getCivilization(),mainPanel.getUniverse());
        } else if (myCivil.getTechnology() >= myCivil.getTechLevel1()
                && myCivil.getTechnology() < myCivil.getTechLevel2()) {
            cp = new Level1CivilizationPanel(this,mainPanel.getCivilization(),mainPanel.getUniverse());
        } else if (myCivil.getTechnology() >= myCivil.getTechLevel2()
                && myCivil.getTechnology() < myCivil.getTechLevel3()) {
            cp = new Level2CivilizationPanel(this,mainPanel.getCivilization(),mainPanel.getUniverse());
        } else {
            cp = new StarPluckerCivilizationPanel(this,mainPanel.getCivilization(),mainPanel.getUniverse());
        }

        cp.updatePanel();
        this.setContentPane(cp);
        this.pack();
    }




//    public void promptEnter(String s) {
//        System.out.println("-------------------------------------------------------------------------------------");
//        System.out.println(s);
//
//        Scanner input = new Scanner(System.in);
//        input.nextLine();
//    }








    //EFFECTS: customize size of universe
    public void customizeSize(int input1) {

        for (int i = 1; i <= input1; i++) {
            int max = 100;
            int min = 0;
            //create random int for the three parameters of other civilizations
            int randomInt1 = (int) (Math.random() * (max - min + 1) + min);
            int randomInt2 = (int) (Math.random() * (max - min + 1) + min);
            int randomInt3 = (int) (Math.random() * (max - min + 1) + min);

            //create a random 5-byte-long name for other civilizations
            byte[] array = new byte[5];
            new Random().nextBytes(array);
            String name = new String(array, StandardCharsets.UTF_8);

            Civilization civilization = new Civilization(name, randomInt1, randomInt2, randomInt3,
                    mainPanel.getUniverse());
            mainPanel.getUniverse().addCivilization(civilization);
        }
    }

//    //EFFECTS: In game intro and let user to set the name of it's own civilization.
//    public void gameIntroAndSetName() {
//        Scanner input1 = new Scanner(System.in);
//
//        System.out.println("In a small corner of nowhere,a tiny but extremely dense and hot gravitational singularity"
//                + " is shaking excitedly....");
//        System.out.println("And just in less than 10^-6 seconds, it EXPANDS!! Space,mass,energy,time emerges to all "
//                + "directions in a speed that nobody could imagine");
//        System.out.println("A universe with dimensionality of 10 is just created...");
//        System.out.println("...After millions of centuries, so
//        mewhere in the corner of the universe, a civilization is"
//                + "born. This is your civilization. Although it
//                is primitive,it could have infinite possibilities...");
//        System.out.println("-------------------------------------------------------------------------------------");
//        System.out.println(">>>>Please enter the name of your civilizations.");
//
//        String name = input1.nextLine();
//        myCivilization.setName(name);
//    }

//    //EFFECTS: the entry of each round of the game
//    public void gameRoundEntry() {
//        Scanner input = new Scanner(System.in);
//
//        System.out.println(">>>>Press 1 to start next round, press 2 to exit and save game");
//        int num = input.nextInt();
//        if (num == 1) {
//            newRound();
//        } else if (num == 2) {
//            exitAndSave();
//        }
//
//        newRound();
//
//    }

    //MODIFIES: JSon
    //EFFECTS: exit the program and save the game
    public void exitAndSave() {
        try {
            cvWriter = new CivilizationWriter(CIVILIZATION_STORE);
            uniWriter = new UniverseWriter(UNIVERSE_STORE);

            cvWriter.open();
            cvWriter.write(mainPanel.getCivilization());
            cvWriter.close();

            uniWriter.open();
            uniWriter.write(mainPanel.getUniverse());
            uniWriter.close();

            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //MODIFIES: JSon
    //EFFECTS: save the game
    public void saveGame() {
        try {
            cvWriter = new CivilizationWriter(CIVILIZATION_STORE);
            uniWriter = new UniverseWriter(UNIVERSE_STORE);

            cvWriter.open();
            cvWriter.write(mainPanel.getCivilization());
            cvWriter.close();

            uniWriter.open();
            uniWriter.write(mainPanel.getUniverse());
            uniWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



//    //MODIFIES: panel
//    //EFFECT: based on the parameters of civilization, navigate to different panels
//    public void roundStarter(Panel from) {
//        Civilization cv = mainPanel.getCivilization();
//
//
//
//        if (mainPanel.getUniverse().getCivilizations().size() == 0) {
//            winCondition1();
//        } else if (cv.getTechnology() < cv.getTechLevel1()) {
//            primitiveStart(from);
//        } else if (cv.getTechnology() >= cv.getTechLevel1()
//                && cv.getTechnology() < cv.getTechLevel2()) {
//            level1Start(from);
//        } else if (cv.getTechnology() >= cv.getTechLevel2()
//                && cv.getTechnology() < cv.getTechLevel3()) {
//            level2Start(from);
//
//        } else if (cv.getTechnology() >= cv.getTechLevel3()) {
//            starPluckerStart(from);
//        }
//    }
//
//    //EFFECTS: round start action
//    public void starPluckerStart(Panel from) {
//
//        moveFromTo(from,new StarPluckerCivilizationPanel(this,mainPanel.getCivilization(),
//                mainPanel.getUniverse()));
//        mainPanel.getCivilization().setResources(15);
//        resourcesConclude(10);
//
//    }
//
//    //EFFECTS: round start action
//    public void level2Start(Panel from) {
//
//        moveFromTo(from,new Level2CivilizationPanel(this,mainPanel.getCivilization(),
//                mainPanel.getUniverse()));
//        mainPanel.getCivilization().setResources(15);
//
//        resourcesConclude(15);
//    }
//
//    //EFFECTS: round start action
//    public void level1Start(Panel from) {
//
//        moveFromTo(from,new Level1CivilizationPanel(this,mainPanel.getCivilization(),
//                mainPanel.getUniverse()));
//        mainPanel.getCivilization().setResources(12);
//
//        resourcesConclude(12);
//    }
//
//    //EFFECTS: round start action
//    public void primitiveStart(Panel from) {
//
//        moveFromTo(from,new PrimitiveCivilizationPanel(this,mainPanel.getCivilization(),
//                mainPanel.getUniverse()));
//        mainPanel.getCivilization().setResources(15);
//        resourcesConclude(15);
//    }
//
//
//
//
//
//
//    //EFFECTS: dialog to ask users add three fields
//    public void resourcesConclude(int n) {
//        mainPanel.resourcesWillGet = n;
//        JFrame frame = new JFrame("Message from controller");
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        JOptionPane.showMessageDialog(frame,"You obtain " + n + " resources, please distribute "
//                + "them wisely");
//    }
//
//    //EFFECTS: helper, move from one panel to another
//    public void moveFromTo(Panel from, Panel to) {
//        to.setVisible(true);
//        setContentPane(to);
//        from.setVisible(false);
//        validate();
//    }





//    //EFFECTS: other civilizations develop
//    public void otherCivilDevelopment() {
//        for (Civilization i : mainPanel.getUniverse().getCivilizations()) {
//            i.addResources(3);
//            i.addTechnology();
//            i.addSociety();
//            i.addCulture();
//            i.addRoundNumber();
//        }
//    }

//    ///!!!!!!!!!!!!!!!!!!!!!!!!!! change to dialog
//    //EFFECTS: the user's civilization eliminates all other civilizations and win.
//    public void winCondition1() {
//        JFrame frame = new JFrame("Message");
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        JOptionPane.showMessageDialog(frame,"Congratulation! You've successfully eliminated all "
//  + universe.getCivilizations().size() + " civilizations in the universe\nYour civilization will be safe"
//                + "in the next few thousands of centuries until some other civilizations born in the nebula......\n",
//                "Message", JOptionPane.PLAIN_MESSAGE);
//
//    }


    // Reference: https://stackoverflow.com/questions/15198549/popup-for-jframe-close-button
    //EFFECTS: generate a popup window conforming to save the data or not when you close the window.
    private class SaveGame extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            int option = JOptionPane.showOptionDialog(
                    UserApp.this,
                    "Would you like to save the game after you exit?",
                    "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, null, null);
            if (option == JOptionPane.YES_OPTION) {
                exitAndSave();
                System.out.println("save game!");

            } else if (option == JOptionPane.NO_OPTION) {
                System.out.println("didn't save game!");
                System.exit(0);
            }
        }
    }
}


