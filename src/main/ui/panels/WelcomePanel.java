package ui.panels;

import model.Civilization;
import model.Universe;
import ui.Navigator;
import ui.UserApp;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

//Represent the welcome panel
public class WelcomePanel extends Panel {

    //Image source:https://www.artstation.com/artwork/2EmZJ
    private static final String FILE_NAME = "./data/evgeniy-kiselov-dark-forest-wallpapers-12.jpg";


    private static final String MUSIC_ICON = ",/data/music.jpg";

    //MUSIC : DUST by Hans Zimmer
    //Sources : https://www.youtube.com/watch?v=-0_VuwvvS-k
    private static final String MUSIC = "./data/983316ffee0eaa3aab4ad7fdc85e6fcbb54e6840.wav "
            + "(online-audio-converter.com).wav";
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

        playSound(MUSIC);
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
        ImageIcon music = new ImageIcon(MUSIC_ICON);
        playMusic = new JButton("music play/stop");

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
        gridBagConstraints.gridy = 5;
        this.add(playMusic,gridBagConstraints);


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

        //Play Music -> control music
        playMusic.addActionListener(e -> controlMusic());

    }

    //MODIFIES: Clip
    //EFFECTS: when the music icon is clicked, the music will be played
    //         if the music is playing, then it will stop.
    private void controlMusic() {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            } else {
                clip.start();
            }
        }
    }


    //EFFECTS: play the background music continuously from data file
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
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
