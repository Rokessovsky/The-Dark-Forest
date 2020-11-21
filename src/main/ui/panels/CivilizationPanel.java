package ui.panels;

import ui.exceptions.EndGameException;
import model.Civilization;
import model.Universe;
import ui.UserApp;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public abstract class CivilizationPanel extends Panel {

    protected JLabel civilInfo;
    protected JTextField techText;
    protected JTextField cultureText;
    protected JTextField societyText;
    protected Border techBorder;
    protected Border societyBorder;
    protected Border cultureBorder;

    //Constructor
    public CivilizationPanel(UserApp app, Civilization myCivil, Universe universe) {
        super(app, myCivil, universe);


        initialiseContents();

        initialiseInteraction();

        addToPanel();

        updatePanel();




    }

    //MODIFIES: this
    //EFFECTS: adds next round button to the login panel
    private void addButtons() {
        JButton nextRound = new JButton("NEXT ROUND");
        buttons.add(nextRound);
        JButton backToMain = new JButton("BACK TO MAIN");
        buttons.add(backToMain);
    }

    //MODIFIES: this
    //EFFECTS: initialise elements
    @Override
    protected void initialiseContents() {
        addButtons();
        //Label
        civilInfo = new JLabel("Your civilization summary: \n"
                + " Technology level: " + myCivil.getTechnology()
                + "\n Society level: " + myCivil.getSociety()
                + "\n Culture level: " + myCivil.getCulture()
                + "\n Resources: " + myCivil.getResources());
        civilInfo.setFont(new Font("Serif", Font.PLAIN, 20));
        civilInfo.setForeground(new Color(7, 193, 185));

        //Border
        techBorder = BorderFactory.createTitledBorder("Technology");
        societyBorder = BorderFactory.createTitledBorder("Society");
        cultureBorder = BorderFactory.createTitledBorder("Culture");

        //TextField
        techText = new JTextField();
        techText.setPreferredSize(new Dimension(200, 50));
        techText.setFont(new Font("monoSpaces", Font.PLAIN, 40));
        techText.setBorder(techBorder);

        societyText = new JTextField();
        societyText.setPreferredSize(new Dimension(200, 50));
        societyText.setFont(new Font("monoSpaces", Font.PLAIN, 40));
        societyText.setBorder(societyBorder);

        cultureText = new JTextField();
        cultureText.setPreferredSize(new Dimension(200, 50));
        cultureText.setFont(new Font("monoSpaces", Font.PLAIN, 40));
        cultureText.setBorder(cultureBorder);


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
        add(techText, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));
        add(cultureText, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));
        add(societyText, new GridBagConstraints(2, 1, 1, 1, 1, 1,
                GridBagConstraints.WEST,
                GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));

    }

    // MODIFIES: this
    // EFFECTS: helper method, add labels to panel
    private void addLabelsToPanel() {
        add(civilInfo, new GridBagConstraints(
                0,
                0,
                1,
                1,
                1,
                1,
                GridBagConstraints.NORTH,
                GridBagConstraints.NONE,
                new Insets(0,
                        0,
                        0,
                        0),
                0,
                0));

    }

    // MODIFIES: this
    // EFFECTS: helper method, add buttons to panel
    private void addButtonsToPanel() {
        GridBagConstraints bgc = new GridBagConstraints();
        bgc.anchor = GridBagConstraints.PAGE_END;
        bgc.insets = new Insets(5, 5, 5, 5);
        bgc.gridx = 2;
        bgc.gridy = 3;
        add(buttons.get(0),bgc);
        bgc.gridx = 0;
        bgc.gridy = 3;
        add(buttons.get(1),bgc);


    }


    //EFFECTS: initialise interaction when button is pressed
    @Override
    protected void initialiseInteraction() {
        buttons.get(0).addActionListener(e -> {
            int techDevelopment = Integer.parseInt(techText.getText());
            int societyDevelopment = Integer.parseInt(societyText.getText());
            int cultureDevelopment = Integer.parseInt(cultureText.getText());

            roundAction(techDevelopment,societyDevelopment,cultureDevelopment);

        });

        buttons.get(1).addActionListener(e -> {
            app.saveGame();
            moveFromTo(new WelcomePanel(app,myCivil,universe));
        });

    }

    //EFFECTS: action for each round
    protected void roundAction(int t, int s, int c) {
        if (myCivil.getResources() >= t + s + c) {

            development(t, s, c);

            roundNumber++;
            myCivil.addRoundNumber();

            roundEndConclusion();

            try {
                roundStarter();
            } catch (EndGameException e) {
                new UserApp();
            }

        } else {
            JFrame frame = new JFrame("Warning");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JOptionPane.showMessageDialog(frame,"You don't have enough resources to make the development\n"
                    + "Please redistribute your resources[you only have " + myCivil.getResources() + " resources "
                    + "points]");
        }
    }



    //EFFECTS: conclusion for the civilization at the end of a round
    public void roundEndConclusion() {


        JFrame frame = new JFrame("Message from controller");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,"After development, your civilization: "
                + myCivil.getName() + "\nTechnology: "
                + myCivil.getTechnology() + "\nSociety: " + myCivil.getSociety() + "\nCulture: "
                + myCivil.getCulture() + "\nRemaining resources: " + myCivil.getResources());
    }



    //EFFECTS: user will be assigned certain resources and user can decide how to distribute them
    public void development(int tech, int society, int culture) {

        teachDevelop(tech);
        societyDevelop(society);
        cultureDevelop(culture);
    }

    public void cultureDevelop(int c) {
        if (myCivil.getCulture() + c <= myCivil.getDevelopmentLimits()) {
            for (int i = 1; i <= c; i++) {
                myCivil.addCulture();
            }
        } else {
            JFrame frame = new JFrame("Message from controller");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JOptionPane.showMessageDialog(frame,"Your culture has reached development limits: "
                    + myCivil.getDevelopmentLimits() + "points \n"
                    + "You've advanced to the highest level possible.");

            myCivil.setCulture(myCivil.getDevelopmentLimits());
        }
    }

    //EFFECTS: develop society
    public void societyDevelop(int s) {
        if (myCivil.getSociety() + s <= myCivil.getDevelopmentLimits()) {
            for (int i = 1; i <= s; i++) {
                myCivil.addSociety();
            }
        } else {
            JFrame frame = new JFrame("Message from controller");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JOptionPane.showMessageDialog(frame,"Your society has reached development limits: "
                    + myCivil.getDevelopmentLimits() + "points \n"
                    + "You've advanced to the highest level possible.");

            myCivil.setSociety(myCivil.getDevelopmentLimits());
        }
    }

    //EFFECTS: develop technology
    public void teachDevelop(int t) {
        if (myCivil.getTechnology() + t <= myCivil.getDevelopmentLimits()) {
            for (int i = 1; i <= t; i++) {
                myCivil.addTechnology();
            }
        } else {
            JFrame frame = new JFrame("Message from controller");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JOptionPane.showMessageDialog(frame,"Your technology has reached development limits: "
                    + myCivil.getDevelopmentLimits() + "points \n"
                    + "You've advanced to the highest level possible.");

            myCivil.setTechnology(myCivil.getDevelopmentLimits());
        }

    }




    //MODIFIES: panel
    //EFFECT: based on the parameters of civilization, navigate to different panels
    public void roundStarter() throws EndGameException {

        if (universe.getCivilizations().size() == 0) {
            winCondition1();
        } else if (myCivil.getTechnology() < myCivil.getTechLevel1()) {
            primitiveStart();
        } else if (myCivil.getTechnology() >= myCivil.getTechLevel1()
                && myCivil.getTechnology() < myCivil.getTechLevel2()) {
            level1Start();
        } else if (myCivil.getTechnology() >= myCivil.getTechLevel2()
                && myCivil.getTechnology() < myCivil.getTechLevel3()) {
            level2Start();

        } else if (myCivil.getTechnology() >= myCivil.getTechLevel3()) {
            starPluckerStart();
        }

        otherCivilDevelopment();

        updatePanel();
    }

    //EFFECTS: other civilizations develop
    public void otherCivilDevelopment() {
        for (Civilization i : universe.getCivilizations()) {
            i.addResources(6);
            i.addTechnology();
            i.addTechnology();
            i.addSociety();
            i.addSociety();
            i.addCulture();
            i.addCulture();
            i.addRoundNumber();
        }
    }

    //EFFECTS: round start action
    public void starPluckerStart() throws EndGameException {
        myCivil.addResources(15);
        moveFromTo(new StarPluckerCivilizationPanel(app,myCivil,
                universe));

        universeSafeStatement();

        starPluckerProactiveContact();

        starPluckerSelfExpose();
        techDegenerates();
        societyStagnation();

        resourcesConclude(10);



    }

    //EFFECTS: round start action
    public void level2Start() throws EndGameException {
        myCivil.addResources(15);
        moveFromTo(new Level2CivilizationPanel(app,myCivil,
                universe));

        techDegenerates();
        level2SelfExpose();
        societyStagnation();

        level2ProactiveContact();
        resourcesConclude(15);


    }

    //EFFECTS: round start action
    public void level1Start() throws EndGameException {
        myCivil.addResources(12);
        moveFromTo(new Level1CivilizationPanel(app,myCivil,
                universe));

        techDegenerates();
        level1SelfExpose();
        societyStagnation();

        level1ProactiveContact();
        darkForestWarning();


        resourcesConclude(12);


    }

    //EFFECTS: round start action
    public void primitiveStart() {
        myCivil.addResources(15);
        moveFromTo(new PrimitiveCivilizationPanel(app,myCivil,
                universe));

        techDegenerates();
        innerChaos();
        societyStagnation();

        resourcesConclude(15);


    }

    //EFFECTS: dialog to ask users add three fields
    public void resourcesConclude(int n) {
        resourcesWillGet = n;
        JFrame frame = new JFrame("Message from controller");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,"You obtain " + n + " resources, please distribute "
                + "them wisely");


    }

    //EFFECTS: helper, move to another panel
    public void moveFromTo(Panel to) {
        to.updatePanel();
        app.setContentPane(to);
        app.pack();
    }

    //EFFECTS: the user's civilization eliminates all other civilizations and win.
    public void winCondition1() {
        JFrame frame = new JFrame("Message");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,"Congratulation! You've successfully eliminated all "
                        + universe.getCivilizations().size() + " civilizations in the universe\nYour civilization will "
                        + "be safe"
                        + "in the next few thousands of centuries until some other civilizations born in the "
                        + "nebula...\n",
                "Message", JOptionPane.PLAIN_MESSAGE);

    }

    //EFFECTS: civilization's society degenerates,tech level will fall back to the same level as culture
    public void techDegenerates() {
        if (myCivil.getTechnology() - myCivil.getCulture() > 10) {
            JFrame frame = new JFrame("WARNING!!!!!");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JOptionPane.showMessageDialog(frame,"Ooops,your culture level is falling far behind your "
                    + "technology.\n The technology development needs support of all other academic areas.\n"
                    + "Your level of technology has exceeded the range that the public could understand and accept.\n"
                    + "As a punishment, your technology level will be deduced by 10");

            myCivil.setTechnology(myCivil.getTechnology() - 10);
        }
    }

    //EFFECTS: the civilization have inner chaos,user will be assigned less resources
    public void innerChaos() {
        if (myCivil.getTechnology() - myCivil.getSociety() > 10) {
            JFrame frame = new JFrame("WARNING!!!!!");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JOptionPane.showMessageDialog(frame,"Ooops,your society level is falling far behind your technology"
                    + " level.\n You used too many resources to development technology and ignored the livelihood.\n"
                    + "of your people. Your people are angry and start a riot \n"
                    + "As a punishment, your resources will be reduced by 10");

            myCivil.setResources(myCivil.getResources() - 10);

        }
    }

    //EFFECTS: tech stagnate, society and culture fall back to the level of tech
    public void societyStagnation() {
        if (myCivil.getSociety() - myCivil.getTechnology() > 10
                || myCivil.getCulture() - myCivil.getTechnology() > 10) {
            JFrame frame = new JFrame("WARNING!!!!!");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JOptionPane.showMessageDialog(frame,"Ooops, your technology level is falling far behind your "
                    + "society requirements.\n The development of society is not based on any substantial "
                    + "technological breakthrough\n Thus the bubble economy is very easily broken.\n"
                    + "As punishment,your society and culture level will degrade to your technological level"
            );

            myCivil.setSociety(myCivil.getTechnology());
            myCivil.setCulture(myCivil.getTechnology());

        }
    }


    //EFFECTS: my civilization is destroyed by star plucker
    public void destroyedByStarPlucker() throws EndGameException {
        JFrame frame = new JFrame("Message");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,"Your contact attempt reached to a star plucker,the most advanced "
                + "form of civilization.\nThey lunch attack immediately,using the cheapest yet the most effective way "
                + "of attack.\nA photoid with light speed strike the star in the center of your galaxy.\n This leads to"
                + "the planets' utter incineration,your whole galaxy falls into flames...");
        endGame();
    }


    //EFFECTS: end the game
    public void endGame() throws EndGameException {
        JFrame frame = new JFrame("Message");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JOptionPane.showMessageDialog(frame,"The game is end, your civilization " + myCivil.getName()
                + " has survived for " + myCivil.getRoundNumber() + " rounds");

        throw new EndGameException();

    }

    //EFFECTS: the contact with another civilization is invalid because the enemy tech level is below level2
    public void invalidContact() {
        JFrame frame = new JFrame("Message");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JOptionPane.showMessageDialog(frame,"There's no civilization on your broadcast direction.\n"
                + "Your contact attempt didn't reach to any civilizations");
    }

    //EFFECTS : proactively contact with other civilizations when my civilization is at level 1
    public void level1ProactiveContact() throws EndGameException {

        String[] options = {"Yes, try to contact","No, remain silent"};
        JFrame frame = new JFrame("Proactively contact?");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        int n = JOptionPane.showOptionDialog(frame,"You've developed to level 1 civilization.\n"
                        + "You've gained the ability of interstellar communication.\n "
                        + "Do you want to try to contact other civilizations?",
                "Proactively contact?",
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,
                options,null);
        if (n == 0) {
            level1Contact();
        }
    }


    //EFFECTS: passively contact with other civilizations when my civilization is at level 1
    public void level1SelfExpose() throws EndGameException {


        if (myCivil.getTechnology() - myCivil.getSociety() > 8) {
            JFrame frame = new JFrame("WARNING!!!!");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JOptionPane.showMessageDialog(frame,"Ooops, your society level fall too far behind your technology "
                    + "level.\n You spent too many resources on technology development and too less on developing your"
                    + " people's livelihood.\nOne of your citizens can't stand the miserable life in your civilization"
                    + "and send a message to the universe asking for help");

            level1Contact();
        }
    }

    //EFFECTS: process of contact with another civilization when the user civilization is at level 1
    public void level1Contact() throws EndGameException {
        int max = universe.getCivilizations().size() - 1;
        int min = 0;

        int randomInt = (int) (Math.random() * (max - min + 1) + min);
        Civilization enemy = universe.getCivilizations().get(randomInt);

        if (enemy.getTechnology() < enemy.getTechLevel2()) {
            invalidContact();
        } else if (enemy.getTechnology() >= enemy.getTechLevel2()
                && enemy.getTechnology() < enemy.getTechLevel3()
                && myCivil.getCulture() >= myCivil.getDarkForestKnownCulture()) {
            deterrenceSetUp();
        } else if (enemy.getTechnology() >= enemy.getTechLevel2()
                && enemy.getTechnology() < enemy.getTechLevel3()
                && myCivil.getCulture() < myCivil.getDarkForestKnownCulture()) {
            destroyedByLevel2Civil();
        } else if (enemy.getTechnology() >= enemy.getTechLevel3()) {
            destroyedByStarPlucker();
        }
    }



    //EFFECTS: the contact result in deterrence between the civilization
    public void deterrenceSetUp() {

        JFrame frame = new JFrame("Message");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,"Your contact attempt reached to a civilization that has much "
                + "higher technology.\n They are now sending sophons to block your tech development and star fleets to"
                + " eliminate you.\n It seems like your civilization has no chance of survival anymore...\n"
                + "But at this desperate moment, it's Logic,the scientist, again. Successfully set up a deterrence "
                + "between two civilizations.\n He threats to broadcast enemy's civilization's location to universe.\n"
                + "Apparently, the enemy is also afraid of the Dark Forest attack.\n They withdraw their sophons and"
                + " star fleets, your civilization is safe again");
    }

    //EFFECTS: my civilization is destroyed by level 2 civilization
    public void destroyedByLevel2Civil() throws EndGameException {
        JFrame frame = new JFrame("Message");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,"Your contact attempt reached to a civilization that has much "
                + "higher technology.\n They are now sending sophons to block your tech development and star fleets to"
                + " eliminate you.\n There no way you can escape from this......\n ...Two centuries later,their fleets "
                + "arrive,they wipe out all humans and occupy the planet");
        endGame();
    }

    //EFFECTS: warn user about the dark forest principle
    public void darkForestWarning() {
        if (myCivil.getSociety() >= myCivil.getDarkForestKnownCulture()) {
            JFrame frame = new JFrame("WARNING!!!!");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JOptionPane.showMessageDialog(frame,"WARNING!!!\n One of your scientist named Logic just proved the"
                    + " validity of Dark Forest Principle.\nSetting up connections with other civilizations is "
                    + "extremely dangerous!!!!!\n War between two civilizations could immediately occurs once the "
                    + "connections set up.\n This action might expose the location of your civilizations to the rest of"
                    + " universe.\n Your civilization could be immediately destroyed by super-civilization.\n");

        }
    }

    //EFFECTS: passively contact with other civilizations when my civilization is at level 2
    public void level2SelfExpose() throws EndGameException {
        if (myCivil.getTechnology() - myCivil.getSociety() > 8) {
            JFrame frame = new JFrame("WARNING!!!!");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JOptionPane.showMessageDialog(frame,"Ooops, your society level fall too far behind your technology "
                    + "level.\n You spent too many resources on technology development and too less on developing your"
                    + " people's livelihood.\nOne of your citizens can't stand the miserable life in your civilization"
                    + "and send a message to the universe asking for help");

            level2Contact();
        }
    }

    //EFFECTS: proactively contact with other civilizations when my civilization is at level 2
    public void level2ProactiveContact() throws EndGameException {
        String[] options = {"Yes, try to contact","No, remain silent"};
        JFrame frame = new JFrame("Proactively contact?");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        int n = JOptionPane.showOptionDialog(frame,"You've developed to level 2 civilization.\n"
                        + "You have star fleets now.\n You can try to contact other civilizations and destroy them.\n"
                        + "However, this will cost you 30 resources.\n Do you want to contact?",
                "Proactively contact?",
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,
                options,null);
        if (n == 0) {
            level2Contact();
        }
    }

    //EFFECTS: process of contact with another civilization when the user civilization is at level 2
    public void level2Contact() throws EndGameException {
        int max = universe.getCivilizations().size() - 1;
        int min = 0;

        int randomInt = (int) (Math.random() * (max - min + 1) + min);
        Civilization enemy = universe.getCivilizations().get(randomInt);

        if (enemy.getTechnology() < enemy.getTechLevel1()) {
            invalidContact();
        } else if (enemy.getTechnology() >= enemy.getTechLevel1()
                && enemy.getTechnology() < myCivil.getTechnology()) {
            attackAndWin(enemy);
        } else if (enemy.getTechnology() >= myCivil.getTechnology()
                && enemy.getTechnology() < enemy.getTechLevel3()) {
            attackAndLose(enemy);
        } else if (enemy.getTechnology() >= enemy.getTechLevel3()) {
            destroyedByStarPlucker();
        }
    }

    //EFFECTS: attack and eliminate another civilization
    public void attackAndWin(Civilization enemy) {
        if (myCivil.getResources() >= 30) {
            myCivil.setResources(myCivil.getResources() - 30);
            JFrame frame = new JFrame("Attack!!");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JOptionPane.showMessageDialog(frame,"First,you send few sophons to their galaxy to block their "
                    + "technology development.\nSo that the fleet you send right now could keep technology advantage "
                    + "after arriving.\nAnd then your magnificent Star fleet heads to enemy's galaxy...\n...5 centuries"
                    + "later,your fleets reach the destination and encounter enemy's defense.\n But they can't compete"
                    + "with you because their technology level is lower than yours.\n You just use one 'droplets' "
                    + "ship to destroy all of their lagging fleets.\n With no doubt, you eliminate the civilization "
                    + "and occupy the galaxy.\n Additionally, you gain 35 resource points");

            universe.destroyCivilization(enemy);
            myCivil.addResources(35);
        } else {
            JFrame frame = new JFrame("Message");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JOptionPane.showMessageDialog(frame,"There're no enough resources to launch attack.REQUIRE:30");
        }
    }

    //EFFECTS: attack and lose, might be eliminated if the enemy tech - my tech >= 10
    public void attackAndLose(Civilization enemy) throws EndGameException {
        if (myCivil.getResources() >= 30) {
            myCivil.setResources(myCivil.getResources() - 30);
            JFrame frame = new JFrame("Attack!!");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JOptionPane.showMessageDialog(frame,"First,you send few sophons to their galaxy to block their "
                    + "technology development.\nSo that the fleet you send right now could keep technology advantage "
                    + "after arriving.\nAnd then your magnificent Star fleet heads to enemy's galaxy...\n...5 centuries"
                    + "later,your fleets reach the destination and encounter enemy's defense.\nSadly, your fleets get "
                    + "ambushed and you surprising find out they have more advance technology.\nYour fleet fights "
                    + "bravely but the gap between technology is too hard to overturn.\n"
                    + "Your fleet gets annihilated completely...");



            if (enemy.getTechnology() - myCivil.getTechnology() >= 10) {
                getCounterAttack();
            } else {
                JFrame frame2 = new JFrame("Attack!!");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JOptionPane.showMessageDialog(frame2,"But your attack still hit them hard,they don't have "
                        + "enough resources to launch\ncounter attack. Your civilization is still safe for now");
            }

        }
    }

    //EFFECTS: follows attack and lose. Enemy launch counter attack and eliminate my civilization
    public void getCounterAttack() throws EndGameException {
        JFrame frame = new JFrame("Attack!!");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,"...After they destroyed all your fleets\n Enemy gathered all their"
                + " remaining force to launch a counter attack\n"
                + "...Two centuries later,their fleets arrive,they wipe out all humans and occupy the planet\n"
        );

        endGame();
    }


    //EFFECTS: civilization makes a safe statement
    public void universeSafeStatement() throws EndGameException {
        askWhetherMakeTheStatement();


    }

    public void askWhetherMakeTheStatement() throws EndGameException {
        String[] options = {"Yes, make the statement","No, keep conquer"};
        JFrame frame = new JFrame("Universal Safety Statement?");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        int n = JOptionPane.showOptionDialog(frame,"Your civilization has developed to Star Plucker Civilization\n"
                        + "You now obtain the technology of curvature-drive. You're strong enough to destroy most of "
                        + "civilizations in the universe.\n However, you have another choice. You could make a "
                        + "Safe Statement to announce you are permanently harmless.\n ",
                "Universal Safety Statement?",
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,
                options,null);

        if (n == 0) {
            processUniversalSafetyStatement();
        }
    }

    //EFFECTS: civilization makes universal safety statement
    public void processUniversalSafetyStatement() throws EndGameException {
        JFrame frame = new JFrame("Message");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,"You chose to make universe safe statement,yet you didn't even know "
                + "how cruel it could be\n The statement is not something like just a statement to other civilizations"
                + " in text.\n Who would believe it? Instead they would rather attack you than believing your words\n"
                + "The real way is to encircling your own civilization with black holes.\n Nothing could escape from "
                + "black hole, therefore your civilization is definitely harmless.\n The cause of this is obvious, \n"
                + "your civilization lost all the chances to explore the universe\n and has to live in a world where "
                + "light speed is only 16.7 km/s.\n But on the other hand, your civilization could live to the end of "
                + " the universe......");

        myCivil.setRoundNumber(999999);
        endGame();
    }

    //EFFECTS: passively contact with other civilizations when my civilization is at Star Plucker level
    public void starPluckerSelfExpose() throws EndGameException {
        if (myCivil.getTechnology() - myCivil.getSociety() > 8) {
            JFrame frame = new JFrame("Message");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JOptionPane.showMessageDialog(frame,"Ooops, your society level fall too far behind your technology "
                    + "level.\n You spent too many resources on technology development and too less on developing your"
                    + " people's livelihood.\nOne of your citizens can't stand the miserable life in your civilization"
                    + "and send a message to the universe asking for help");

            starPluckerContact();

        }
    }

    //EFFECTS: process of contact with another civilization when the user civilization is at star plucker level
    public void starPluckerContact() throws EndGameException {
        int max = universe.getCivilizations().size() - 1;
        int min = 0;

        int randomInt = (int) (Math.random() * (max - min + 1) + min);
        Civilization enemy = universe.getCivilizations().get(randomInt);

        if (enemy.getTechnology() < enemy.getTechLevel1()) {
            invalidContact();
        } else if (enemy.getTechnology() >= enemy.getTechLevel1()
                && enemy.getTechnology() < enemy.getTechLevel3()) {
            pluckAPhotoid(enemy);
        } else if (enemy.getTechnology() >= enemy.getTechLevel3()) {
            dimensionWar(enemy);
        }
    }

    //EFFECTS: attack an enemy using photoid
    public void pluckAPhotoid(Civilization enemy) {
        JFrame frame = new JFrame("Message");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,"You drive a wrap driven spaceship heading to the enemy civilization"
                + "in light speed\n Soon, you reach to the edge of their galaxy. They did't noticed you because your "
                + " ship is totally invisible.\n You take out a tiny photoid and aim to their star\n"
                + "Swiftly, you pluck the photoid out and then drive the ship away\n You don't need to check the"
                + " explosion. Because you know what will happen as you have already\n as you have already"
                + " done this boring job thousands of times...\n ...Behind you,the photoid precisely strike the star\n "
                + " the entire civilization is incinerated in the explosion of the star...  "
        );

        universe.destroyCivilization(enemy);
    }

    //EFFECTS: proactively contact with other civilizations when my civilization is at Star Plucker level
    public void starPluckerProactiveContact() throws EndGameException {
        String[] options = {"Yes, try to contact","No, remain silent"};
        JFrame frame = new JFrame("Proactively contact?");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        int n = JOptionPane.showOptionDialog(frame,"You've developed to Star Plucker civilization.\n"
                        + "You can try to contact other civilizations and destroy them by a Photoid.\n"
                        + "Photoid, the cheapest yet the most effective weapons. It can reach to light speed.\n "
                        + "And if it hits a star,the star will explode and destroy the whole solar system.\n"
                        + "It only cost you 1 resources to destroy a civilization.\n"
                        + "Do you want to contact?",
                "Proactively contact?",
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,
                options,null);


        if (n == 0) {
            modifyOwnDimensionality();
            starPluckerContact();
        }
    }

    //EFFECTS: let user to choose whether modify their own dimensionality or not
    public void modifyOwnDimensionality() {
        String[] options = {"Yes, transform","No, ignore"};
        JFrame frame = new JFrame("Transform?");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        int n = JOptionPane.showOptionDialog(frame,"However, if you encounter another Star Plucker civilization,"
                        + " brutal"
                        + " fight might occur\n The most basic physics laws could be used in this level of war like "
                        + " DIMENSIONALITY ATTACK.\n Any dimensionality attack (whether it's from or against you) will"
                        + " cause "
                        + "the dimensionality of the universe reduce by one.\n Civilization with higher dimensionality "
                        + "can't "
                        + " survive in universe with lower dimension.\n The only way to prevent this is to transform "
                        + "your whole"
                        + " civilization into a lower dimension first.\n This will make you sacrifice a lot\n"
                        + " Also, if the dimensionality of the universe is reduced to 0 "
                        + "the game is over\n It's your choice of whether transform your civilization",

                "Transform?",
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,
                options,null);



        if (n == 0) {
            selfDimensionDecrease();
        }
    }

    //EFFECTS: decrease the dimension of myCivil by one
    public void selfDimensionDecrease() {
        myCivil.setDimension(myCivil.getDimension() - 1);
        myCivil.setTechnology(myCivil.getTechnology() - 10);
        myCivil.setSociety(myCivil.getSociety() - 10);
        myCivil.setCulture(myCivil.getCulture() - 10);
        myCivil.setResources(0);
    }

    //EFFECTS: a dimension war in universe eliminate all low level civilizations and
    //         reduce the dimensionality of the universe by 1
    public void dimensionWar(Civilization enemy) throws EndGameException {
        JFrame frame = new JFrame("Message");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,"You encounter another Star Plucker, the largest cruelest war happens"
                + " immediately.\n Millions of super star fleets are firing at each others across the galaxies.\n"
                + " Countless photoids are flying through the universe striking stars.\n"
                + "The explosions of stars are like never ending fireworks.\n "
                + "Ton's of black holes are built to track enemies' fleets and photoids...\n "
                + "Finally, you shoots out a 'dual vector foil' and reduce the dimension of the whole universe by one\n"

        );

        universe.destroyCivilization(enemy);

        universeDimensionDecrease();

        if (universe.getDimension() > 1) {
            universe.dimensionalityReduction();
            JFrame frame2 = new JFrame("Message");
            frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JOptionPane.showMessageDialog(frame2,"All low level civilizations and your enemy are destroyed in "
                    + "lower dimension universe");
        } else {
            universeDestroyed();
        }

    }

    //EFFECTS: decrease the dimensionality of universe by 1 and kill all the lower level civilizations
    public void universeDimensionDecrease() {
        for (Civilization c : universe.getCivilizations()) {
            if (c.getTechnology() < c.getTechLevel3()) {
                universe.destroyCivilization(c);
            } else {
                c.setTechnology(c.getTechnology() - 10);
                c.setSociety(c.getSociety() - 10);
                c.setCulture(c.getCulture() - 10);
            }
        }
    }

    //EFFECTS: the dimension of universe falls to 0,game over
    public void universeDestroyed() throws EndGameException {
        JFrame frame = new JFrame("Message");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,"...Unfortunately, the dimension of the universe is reduced to 0 "
                + "after this dimensionality attack.\n The universe return to the form of gravitational "
                + "singularity,all civilizations are destroyed");
        endGame();

    }

    // EFFECTS: updates data display on the panel when active
    @Override
    public void updatePanel() {
        techText.setText("");
        societyText.setText("");
        cultureText.setText("");
        civilInfo.setText("Your civilization summary: \n"
                + " Technology level: " + myCivil.getTechnology()
                + "\n Culture level: " + myCivil.getCulture()
                + "\n Society level: " + myCivil.getSociety()
                + "\n Resources: " + myCivil.getResources());
    }


}
