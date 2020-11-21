package ui.panels;

import jdk.nashorn.internal.scripts.JO;
import model.Civilization;
import model.Universe;
import ui.UserApp;

import javax.swing.*;
import java.awt.*;



//Represent a level 1 civilization
public class Level1CivilizationPanel extends CivilizationPanel {

    //Image Source: https://www.pinterest.ca/pin/406520303848209690/
    private static final String FILE_NAME = "./data/level 1.jpg";

    public Level1CivilizationPanel(UserApp app, Civilization myCivil, Universe universe) {
        super(app, myCivil, universe);

    }

    //MODIFIED: this,g
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
