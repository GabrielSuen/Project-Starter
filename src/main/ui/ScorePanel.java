package ui;

import model.GameState;
import model.Profile;

import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScorePanel extends JPanel {

    private JLabel scoreLbl;
    private GameState game;

    // Represents the panel which in which the score is displayed
    public ScorePanel(GameState g, TerminalMenu m) {

        setLayout(new BorderLayout());
        game = g;
        scoreLbl = new JLabel();
        scoreLbl.setText("Score: " + game.getScore());
        scoreLbl.setHorizontalAlignment(JLabel.LEFT);
        scoreLbl.setVerticalAlignment(JLabel.BOTTOM);
        scoreLbl.setForeground(Color.WHITE);
        scoreLbl.setVisible(true);

        setBorder(BorderFactory.createLineBorder(Color.WHITE));
        setBackground(Color.BLACK);
        add(scoreLbl);
    }

    // modifies: this
    // effects: updates the score text to reflect the current players score
    public void update() {
        scoreLbl.setText("Score: " + game.getScore());
    }

}
