package ui.panels;


import model.Civilization;
import model.Universe;
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

    static Civilization myCivil;
    static Universe universe;

    static final Dimension PANEL_DIMENSION = new Dimension(1200,742);

    public Panel(UserApp app, Civilization myCivil, Universe universe) {
        super(new GridBagLayout());
        Panel.myCivil = myCivil;
        Panel.universe = universe;

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


}
