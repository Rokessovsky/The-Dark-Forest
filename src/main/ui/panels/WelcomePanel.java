package ui.panels;

import model.Civilization;
import model.Universe;
import ui.Navigator;
import ui.UserApp;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//Represent the welcome panel
public class WelcomePanel extends Panel {
    private static final String FILE_NAME = "./data/evgeniy-kiselov-dark-forest-wallpapers-12.jpg";
    private UserApp app;

    private List<JButton> backBts;
    private JButton playMusic;

    private JLabel citation;
    private GridBagConstraints gridBagConstraints;
    private Clip clip;




    public WelcomePanel(UserApp app, Civilization myCivil, Universe universe) {
        super(app, myCivil, universe);
        this.app = app;
        this.backBts = new ArrayList<>();

        setAlignmentX(Component.RIGHT_ALIGNMENT);
        setAlignmentY(Component.TOP_ALIGNMENT);
        setPreferredSize(new Dimension(1200,742));
        setVisible(true);

        initialiseContents();
        initialiseInteraction();
        addToPanel();



    }

    //MODIFIES: this
    //EFFECTS: initialise elements
    @Override
    protected void initialiseContents() {

        JButton bt0 = new JButton("NEW GAME");
        buttons.add(bt0);
        JButton bt1 = new JButton("LOAD GAME");
        buttons.add(bt1);
        JButton bt2 = new JButton("TUTORIAL");
        buttons.add(bt2);
        JButton bt3 = new JButton("EXIT AND SAVE");
        buttons.add(bt3);

        citation = new JLabel("--inspired by Liu CiXin's Three Body Problem series--");
        citation.setFont(new Font("MonoSpaced",Font.BOLD,20));
        citation.setForeground(Color.white);

        initialisePanels();
        initialiseButtons();
    }

    //MODIFIES: this
    //EFFECTS: initialise BackToMainMenu buttons
    private void initialiseButtons() {
        for (int i = 0; i < panels.size(); i++) {
            backBts.get(i).addActionListener(new Navigator(app,panels.get(i),this));
        }
    }

    //MODIFIES: this
    //EFFECTS: initialise panels
    private void initialisePanels() {
        panels.add(new NewGamePanel(app,myCivil,universe));
        panels.add(new TutorialPanel(app,myCivil,universe));
        panels.add(new PrimitiveCivilizationPanel(app,myCivil,universe));
        panels.add(new Level1CivilizationPanel(app,myCivil,universe));
        panels.add(new Level2CivilizationPanel(app,myCivil,universe));
        panels.add(new StarPluckerCivilizationPanel(app,myCivil,universe));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3,3,10,3);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.gridx = 3;
        gbc.gridy = 6;

        for (Panel p: panels) {
            JButton backToMain = new JButton("BACK TO MAIN MENU");
            backBts.add(backToMain);
            p.add(backToMain,gbc);
        }
    }

    //MODIFIES: panel
    //EFFECTS: add elements to panel
    @Override
    protected void addToPanel() {
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(20,550,250,550);
        gridBagConstraints.gridy = 0;
        this.add(citation,gridBagConstraints);

        gridBagConstraints.insets = new Insets(20,550,5,550);
        int y = 1;
        for (JButton bt: buttons) {
            gridBagConstraints.gridy = y;
            this.add(bt,gridBagConstraints);
            y++;
        }


    }

    //EFFECTS: initialise interaction when button is pressed
    @Override
    protected void initialiseInteraction() {

        //new game button -> NewGamePanel
        buttons.get(0).addActionListener(new Navigator(app,this,panels.get(0)));

        //load game button -> app.loadGame()
        buttons.get(1).addActionListener(e -> app.loadGame());

        //tutorial button -> TutorialPanel
        buttons.get(2).addActionListener(new Navigator(app,this,panels.get(1)));

        //Exit -> exit
        buttons.get(3).addActionListener(e -> app.exitAndSave());

    }

    //EFFECTS: update components on panel when needed
    @Override
    public void updatePanel() {
        //
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
