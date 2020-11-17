package ui.panels;

import model.Civilization;
import model.Universe;
import ui.UserApp;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

//Represent a level 2 civilization
public class Level2CivilizationPanel extends CivilizationPanel {
    private static final String FILE_NAME = "./data/Level 2.jpg";

    public Level2CivilizationPanel(UserApp app, Civilization myCivil, Universe universe) {
        super(app, myCivil, universe);

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
