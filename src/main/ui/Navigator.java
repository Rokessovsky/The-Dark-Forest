package ui;

import ui.UserApp;
import ui.panels.Panel;
import ui.panels.WelcomePanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//A class navigate you from one panel to another
public class Navigator implements ActionListener {
    private UserApp app;
    private ui.panels.Panel thisPanel;
    private ui.panels.Panel nextPanel;

    public Navigator(UserApp app, ui.panels.Panel thisPanel, Panel nextPanel) {
        this.app = app;
        this.thisPanel = thisPanel;
        this.nextPanel = nextPanel;
    }



    //EFFECT: action of the navigator
    @Override
    public void actionPerformed(ActionEvent e) {
        
        nextPanel.setVisible(true);
        app.setContentPane(nextPanel);
        thisPanel.setVisible(false);
        app.validate();
    }
}
