package ui.panels;

import model.Civilization;
import model.Universe;
import ui.UserApp;

import javax.swing.*;
import java.awt.*;


//Represent a star plucker civilization
public class StarPluckerCivilizationPanel extends CivilizationPanel {

    //Image Source: https://k.sina.cn/article_3019808433_b3fe9eb100101ti05.html
    private static final String FILE_NAME = "./data/starPlucker.jpeg";

    public StarPluckerCivilizationPanel(UserApp app, Civilization myCivil, Universe universe) {
        super(app, myCivil, universe);
        oneMoreButtons();
    }

    private void oneMoreButtons() {
        initialiseButton();
        addButton();
        initialiseButtonInteraction();

    }

    //EFFECTS: initialise the interaction of the button
    private void initialiseButtonInteraction() {
        buttons.get(2).addActionListener(e -> {
            universeOverview();
        });

    }

    //EFFECTS: an overview of civilizations in universe
    private void universeOverview() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(0,1));

        for (Civilization cv : universe.getCivilizations()) {
            JLabel label = new JLabel();
            label.setText("Name : " + cv.getName() + "||Technology : " + cv.getTechnology() + "||Society : "
                    + cv.getSociety() + "||Culture : " + cv.getCulture());
            panel.add(label);
        }
        JScrollPane scrollPane = new JScrollPane(panel);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.getContentPane().add(scrollPane);

        frame.pack();
        frame.setVisible(true);


    }

    //EFFECTS: initialise button
    private void initialiseButton() {
        JButton universeOverview = new JButton("Universe Overview");
        buttons.add(universeOverview);

    }

    //EFFECTS: add the buttons to the panel
    private void addButton() {
        GridBagConstraints bgc = new GridBagConstraints();
        bgc.anchor = GridBagConstraints.PAGE_END;
        bgc.insets = new Insets(5,10,5,10);
        bgc.gridx = 1;
        bgc.gridy = 3;
        add(buttons.get(2),bgc);

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
