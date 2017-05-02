package ui;

import model.CFGame;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by yuwang on 2017-05-01.
 */
public class ScorePanel extends JPanel implements Observer {
    private static final String FISH_TXT = "Fish Caught: ";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;
    private CFGame game;
    private JLabel fishLbl;


    // Constructs a score panel
    // effects: sets the background colour and draws the initial labels;
    //          updates this with the game whose score is to be displayed
    public ScorePanel(CFGame g) {
        game = g;
        setBackground(new Color(180, 180, 180));
        fishLbl = new JLabel(FISH_TXT + game.getNumFishCaught());
        fishLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(fishLbl);
        add(Box.createHorizontalStrut(10));
    }

    // Updates the score panel
    // modifies: this
    // effects:  updates number of invaders shot and number of missiles
    //           remaining to reflect current state of game
    @Override
    public void update(Observable subject, Object event) {
        // Score panel will be updated for any kind of event
        fishLbl.setText(FISH_TXT + game.getNumFishCaught());
        repaint();
    }
}
