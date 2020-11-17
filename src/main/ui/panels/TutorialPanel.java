package ui.panels;

import model.Civilization;
import model.Universe;
import ui.UserApp;

import javax.swing.*;
import java.awt.*;

//Represent a tutorial panel
public class TutorialPanel extends Panel {

    private static final String FILE_NAME = "./data/tutorial.jpg";

    public TutorialPanel(UserApp app, Civilization myCivil, Universe universe) {
        super(app, myCivil, universe);
        initialiseContents();
        initialiseInteraction();
        addToPanel();
    }

    //MODIFIES: this
    //EFFECTS: adds next round button to the login panel
    public void addButtons() {
        JButton nextRound = new JButton("Start");
        buttons.add(nextRound);
    }


    //MODIFIES: this
    //EFFECTS: initialise elements
    @Override
    protected void initialiseContents() {
        addButtons();

    }

    //MODIFIES: this
    //EFFECTS: add buttons to the panel
    @Override
    protected void addToPanel() {
        GridBagConstraints bgc = new GridBagConstraints();
        bgc.anchor = GridBagConstraints.CENTER;
        bgc.insets = new Insets(50,50,50,50);
        add(buttons.get(0),bgc);

    }

    //EFFECTS: initialise interaction for when the button is clicked
    @Override
    protected void initialiseInteraction() {
        buttons.get(0).addActionListener(e -> {
            tutorial1();
        });

    }

    //Start a tutorial,give a brief introduction of this game works
    public void tutorial1() {


        JFrame frame = new JFrame("Tutorial 1");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,"The Dark Forest is a strategic game.\n "
                + "You will be given a primitive civilization in a universe at the beginning.\n"
                + "The goal is to SURVIVE as long as possible with other civilizations in the universe.\n"
                + "Your civilization has three factors : TECHNOLOGY,SOCIETY,CULTURE\n"
                + "You will be assigned resources to develop these three factors.\n"
                + "How you chose to distribute the resources will determine the future of your civilization.\n");

        tutorial2();
    }

    //EFFECTS: continue tutorial follows tutorial1
    public void tutorial2() {
        JFrame frame = new JFrame("Tutorial 2");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,"TECHNOLOGY :\n "
                + "Technology is the most important factor for your civilization development.\n"
                + "It will directly determine the level of your civilization. There are 4 levels in total.\n"
                + "Civilization with lower technology level has slim chance of winning when encounter others\n"
                + "Moreover, if the gap between your technology and other two factors is too big, \n"
                + "it will also negatively impact your civilization.\n");

        tutorial3();

    }

    //EFFECTS: continue tutorial follows tutorial2
    public void tutorial3() {


        JFrame frame = new JFrame("Tutorial 3");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,"SOCIETY :\n "
                + "Society is the representation of the stability of the civilization.\n"
                + "Any civilization development requires a stable society environment.\n"
                + "If the gap between the society level and other two factors is too big,\n"
                + "inner chaos or some other unpredictable result will occur\n");

        tutorial4();
    }

    //EFFECTS: continue tutorial follows tutorial3
    public void tutorial4() {

        JFrame frame = new JFrame("Tutorial 4");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,"CULTURE :\n "
                + "Culture represent the level of the culture development including knowledge,belief,arts...\n"
                + "It's what make your civilization unique and colourful.\n"
                + "It's also a key element for your civilization to realize the most important law in the universe\n"
                + "which could help you make wiser decision and increase the chance of survival.\n");

        tutorial5();
    }

    //EFFECTS: continue tutorial follows tutorial4
    public void tutorial5() {


        JFrame frame = new JFrame("Tutorial 5");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,"In this game, your civilization will be putted into a universe "
                + "alongside with other civilizations you customized.\n "
                + "But you can't interact with others until a certain level of technology.\n"
                + "After that level, you might encounter other civilizations and start a war.\n"
                + "The goal is to eliminate all the threats and keep your civilization alive as long as possible.\n"
                + "Have fun!\n");

    }



    //EFFECTS: update elements on Panel when needed
    @Override
    public void updatePanel() {
        //
    }

    //MODIFIED: this
    //EFFECTS: set the background image
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon imageIcon = new ImageIcon(FILE_NAME);
        Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(1200, 742,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);
        imageIcon.paintIcon(this,g,0,0);
    }


}
