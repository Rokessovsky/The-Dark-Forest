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
    private UserApp app;

    private List<JButton> buttons;
    private JButton playMusic;

    private JLabel citation;
    private GridBagConstraints gridBagConstraints;
    private Clip clip;




    public WelcomePanel(UserApp app, Civilization myCivil, Universe universe) {
        super(app, myCivil, universe);
        this.app = app;
        this.buttons = new ArrayList<>();

        setAlignmentX(Component.RIGHT_ALIGNMENT);
        setAlignmentY(Component.TOP_ALIGNMENT);
        setPreferredSize(new Dimension(1200,741));
        setVisible(true);

        initialiseContents();
        initialiseInteraction();
        addToPanel();



    }


    @Override
    protected void initialiseContents() {

        JButton bt0 = new JButton("NEW GAME");
        buttons.add(bt0);
        JButton bt1 = new JButton("LOAD GAME");
        buttons.add(bt1);
        JButton bt2 = new JButton("TUTORIAL");
        buttons.add(bt2);
        JButton bt3 = new JButton("EXIT");
        buttons.add(bt3);

        citation = new JLabel("--inspired by Liu CiXi's Three Body Problem series--");
        citation.setFont(new Font("MonoSpaced",Font.BOLD,20));
        citation.setForeground(Color.white);

        initialisePanels();
        initialiseButtons();
    }

    //MODIFIES: this
    //EFFECTS: initialise BackToMainMenu buttons
    private void initialiseButtons() {
        for (int i = 0; i < panels.size(); i++) {
            buttons.get(i).addActionListener(new Navigator(app,panels.get(i),this));
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
        panels.add(new Level3CivilizationPanel(app,myCivil,universe));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;

        for (Panel p: panels) {
            JButton backToMain = new JButton("BACK TO MAIN MENU");
            buttons.add(backToMain);
            p.add(backToMain,gbc);
        }
    }

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

    @Override
    protected void initialiseInteraction() {

        //new game button -> NewGamePanel
        buttons.get(0).addActionListener(new Navigator(app,this,panels.get(0)));

        //load game button -> app.loadGame()
        buttons.get(1).addActionListener(e -> app.loadGame());

        //tutorial button -> TutorialPanel
        buttons.get(2).addActionListener(new Navigator(app,this,panels.get(1)));

        //Exit -> exit
        buttons.get(3).addActionListener(e -> System.exit(0));

    }



}
