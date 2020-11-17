package ui.panels;

import model.Civilization;
import model.Universe;
import ui.UserApp;

import javax.swing.*;
import java.awt.*;

//Represent a primitive civilization
public class PrimitiveCivilizationPanel extends CivilizationPanel {

    private static final String FILE_NAME = "./data/new-york-new-york-skyline-bg-1-graphic-art-print-on-wrapped-canvas."
            + "jpg";

    public PrimitiveCivilizationPanel(UserApp app, Civilization myCivil, Universe universe) {

        super(app, myCivil, universe);
    }

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
