package ui.panels;


import model.Civilization;
import model.Universe;
import ui.Navigator;
import ui.UserApp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//General app panel.
public abstract class Panel extends JPanel {

    protected UserApp app;
    protected List<JButton> buttons;
    protected List<Panel> panels;

    protected Civilization myCivil;
    protected Universe universe;
    public int roundNumber;
    public int resourcesWillGet;

    static final Dimension PANEL_DIMENSION = new Dimension(1200,742);

    public Panel(UserApp app, Civilization myCivil, Universe universe) {
        super(new GridBagLayout());
        this.myCivil = myCivil;
        this.universe = universe;

        this.app = app;
        this.buttons = new ArrayList<>();
        this.panels = new ArrayList<>();

        setPreferredSize(PANEL_DIMENSION);
        setAlignmentX(Component.RIGHT_ALIGNMENT);
        setAlignmentY(Component.TOP_ALIGNMENT);
        setVisible(true);
    }

    // MODIFIES: this, UserApp app
    // EFFECTS: initializes contents
    protected abstract void initialiseContents();

    // MODIFIES: this
    // EFFECTS: adds contents to this panel
    protected abstract void addToPanel();

    // MODIFIES: this, UserApp app
    // EFFECTS: assigns according panel to each button
    protected abstract void initialiseInteraction();


    //EFFECTS: return the civilization
    public Civilization getCivilization() {
        return this.myCivil;
    }


    //EFFECTS: return the universe
    public Universe getUniverse() {
        return this.universe;
    }

    //MODIFIES: this
    //EFFECTS: set the civilization
    public void setCivilization(Civilization civil) {
        this.myCivil = civil;
    }

    //MODIFIES: this
    //EFFECTS: set universe
    public void setUniverse(Universe uni) {
        this.universe = uni;
    }

    //EFFECTS: get the round number
    public int getRoundNumber() {
        return roundNumber;
    }

    //MODIFIES: this
    //EFFECTS: set the round number
    public void setRoundNumber(int n) {
        this.roundNumber = n;
    }

    // EFFECTS: updates data display on the panel when active
    public abstract void updatePanel();







}
