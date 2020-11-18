package ui.panels;

import model.Civilization;
import model.Universe;
import ui.Navigator;
import ui.UserApp;

import javax.swing.*;
import java.awt.*;

public class NewGamePanel extends Panel {

    private JLabel title;
    private JLabel universeName;
    private JLabel universeSize;
    private JLabel myCivilName;

    private JTextField cosUniNameText;
    private JTextField cosCivilNameText;
    private JTextField cosUniSizeText;


    //Image source: https://www.esquire.com/entertainment/tv/a33861100/the-three-body-problem-netflix-series-plot-release-date-trailer-details/
    private static final String FILE_NAME = "./data/three-body-problem-new-dand-project-netflix.jpg";

    //Constructor
    public NewGamePanel(UserApp app, Civilization myCivil, Universe universe) {
        super(app, myCivil, universe);

        initialiseContents();

        initialiseInteraction();
        addToPanel();
    }


    //MODIFIES: this
    //EFFECTS: adds start game button to the login panel
    public void addButtons() {
        JButton startGame = new JButton("Start Game");
        buttons.add(startGame);
    }

    //MODIFIES: this
    //EFFECTS: initialise elements
    @Override
    protected void initialiseContents() {
        addButtons();

        //Labels
        title = new JLabel("GAME SETTINGS");
        title.setFont(new Font("Serif", Font.BOLD,55));
        title.setForeground(Color.YELLOW);
        universeName = new JLabel("Customize the name of your Universe");
        universeName.setFont(new Font("Serif",Font.BOLD,25));
        universeName.setForeground(Color.YELLOW);
        universeSize = new JLabel("Universe size (i.e the number of others civilizations)");
        universeSize.setFont(new Font("Serif", Font.BOLD,25));
        universeSize.setForeground(Color.YELLOW);
        myCivilName = new JLabel("Customize the name of your Civilization");
        myCivilName.setFont(new Font("Serif",Font.BOLD,25));
        myCivilName.setForeground(Color.YELLOW);

        //TextFields

        cosUniNameText = new JTextField();
        cosUniNameText.setPreferredSize(new Dimension(400,40));
        cosUniNameText.setFont(new Font("MonoSpaced",Font.PLAIN,30));
        cosCivilNameText = new JTextField();
        cosCivilNameText.setPreferredSize(new Dimension(400,40));
        cosCivilNameText.setFont(new Font("MonoSpaced",Font.PLAIN,30));
        cosUniSizeText = new JTextField();
        cosUniSizeText.setPreferredSize(new Dimension(400,40));
        cosUniSizeText.setFont(new Font("MonoSpaced",Font.PLAIN,30));

    }

    //MODIFIES: panel
    //EFFECTS: add elements to panel
    @Override
    protected void addToPanel() {
        addButtonsToPanel();
        addLabelsToPanel();
        addTextFieldToPanel();

    }

    // MODIFIES: this
    // EFFECTS: helper method, add text field to panel
    private void addTextFieldToPanel() {
        GridBagConstraints bgc = new GridBagConstraints();
        bgc.gridx = 0;
        bgc.gridy = 2;
        add(cosUniNameText,bgc);
        bgc.gridx = 1;
        bgc.gridy = 2;
        add(cosCivilNameText,bgc);
        bgc.gridx = 0;
        bgc.gridy = 4;
        add(cosUniSizeText,bgc);
    }

    // MODIFIES: this
    // EFFECTS: helper method, add labels to panel
    private void addLabelsToPanel() {
        GridBagConstraints bgc = new GridBagConstraints();
        bgc.anchor = GridBagConstraints.PAGE_START;
        bgc.insets = new Insets(10,10,10,10);
        bgc.gridx = 0;
        bgc.gridy = 0;
        add(title,bgc);

        bgc.anchor = GridBagConstraints.LINE_START;
        bgc.gridx = 0;
        bgc.gridy = 1;
        add(universeName,bgc);

        bgc.gridx = 1;
        bgc.gridy = 1;
        add(myCivilName,bgc);

        bgc.gridx = 0;
        bgc.gridy = 3;
        add(universeSize,bgc);
    }

    // MODIFIES: this
    // EFFECTS: helper method, add buttons to panel
    private void addButtonsToPanel() {
        GridBagConstraints bgc = new GridBagConstraints();
        bgc.anchor = GridBagConstraints.LAST_LINE_START;
        bgc.insets = new Insets(30,3,3,3);
        bgc.gridx = 0;
        bgc.gridy = 6;
        add(buttons.get(0),bgc);
    }


    //EFFECTS: initialise interaction when button is pressed
    @Override
    protected void initialiseInteraction() {
        buttons.get(0).addActionListener(e -> {
            String universeName = cosUniNameText.getText();
            String myCivilName = cosCivilNameText.getText();
            int universeSize = Integer.parseInt(cosUniSizeText.getText());

            enterGame(universeName,myCivilName,universeSize);
        });

    }



    //MODIFIES: this
    //EFFECTS : set up initial parameters of game and enter round starter
    private void enterGame(String universeName, String myCivilName, int universeSize) {
        universe.setName(universeName);
        universe.setDimension(10);
        app.customizeSize(universeSize);

        myCivil.setUniverse(universe);
        myCivil.setTechnology(0);
        myCivil.setCulture(0);
        myCivil.setSociety(0);
        myCivil.setResources(0);
        myCivil.setName(myCivilName);

        JFrame frame = new JFrame("Message From the Controller");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(frame, "...Somewhere in a corner of nowhere, a singular point with \n"
                + "infinitely large density and energy is shaking excitedly \n"
                + "...And in the blink of an eye, BANG!! it explodes!!!! \n"
                + "A universe is created. This is your universe, but not just yours"
                + "There are " + universeSize + " other civilizations with you"
                + "But for now, let's consider our own development first...");

        CivilizationPanel pcp = new PrimitiveCivilizationPanel(app,myCivil,universe);
        myCivil.addResources(15);
        pcp.updatePanel();
        app.setContentPane(pcp);
        app.pack();


    }



    //EFFECTS: update elements when needed
    @Override
    public void updatePanel() {
        cosUniNameText.setText("");
        cosCivilNameText.setText("");
        cosUniSizeText.setText("");
    }

    //MODIFIES: this
    //EFFECTS; set the background picture of the welcome panel.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon image = new ImageIcon(FILE_NAME);
        setBounds(0,0,1200,742);
        image.paintIcon(this,g,0,0);
    }



}
