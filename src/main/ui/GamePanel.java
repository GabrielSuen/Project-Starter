package ui;


import model.GameState;
import model.Piece;
import model.Platform;

import java.awt.*;

import javax.swing.*;
import java.awt.event.*;

// Represents the panel where the game will be played/displayed
public class GamePanel extends JPanel implements KeyListener {

    private GameState gameState;
    private JLabel gameOverLbl;
    private JLabel challengeResults;
    private MenuPanel menu;

    // Constructor holds the labels associated with the game
    public GamePanel(MenuPanel m) {
        menu = m;
        gameOverLbl = new JLabel();
        gameOverLbl.setText("GAME OVER");
        gameOverLbl.setHorizontalAlignment(JLabel.CENTER);
        gameOverLbl.setVerticalAlignment(JLabel.CENTER);
        gameOverLbl.setForeground(Color.WHITE);
        gameOverLbl.setVisible(false);

        challengeResults = new JLabel();
        challengeResults.setHorizontalAlignment(JLabel.CENTER);
        challengeResults.setVerticalAlignment(JLabel.BOTTOM);
        challengeResults.setForeground(Color.WHITE);
        challengeResults.setVisible(false);

        setSize(200, 400);
        setLayout(new BorderLayout());
        setBackground(Color.GRAY);
        gameState = new GameState();
        addKeyListener(this);
        add(challengeResults, BorderLayout.SOUTH);
        add(gameOverLbl,BorderLayout.CENTER);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
        if (gameState.isEndedGame()) {
            gameOverLbl.setVisible(true);
            showChallengeResults();
        }
    }

    // modifies: this
    // effects: alters the challenge results text to reflect the players performance
    private void showChallengeResults() {
        if (menu.getChallenger() != null) {
            if (gameState.getScore() > menu.getChallenger().getScore()) {
                challengeResults.setText("You beat " + menu.getChallenger().getName() + "!");
            } else {
                challengeResults.setText("You did not beat " + menu.getChallenger().getName());
            }
            challengeResults.setVisible(true);
        }
    }

    // effects: draws the piece and platform onto screen
    private void render(Graphics g) {
        drawPiece(gameState.getPiece(), g);
        drawPlatform(gameState.getPlatform(), g);
    }

    // effects: draws the piece onto screen
    private void drawPiece(Piece p, Graphics g) {
        Color c = Color.red;
        int px = p.getPosX();
        int py = p.getPosY();
        g.setColor(c);
        g.fillOval(px, py, 10, 10);
    }

    // effects: draws the platform onto screen
    private void drawPlatform(Platform pl, Graphics g) {
        Color c = Color.green;
        int px = pl.getPosX();
        int py = pl.getPosY();
        g.setColor(c);
        g.fillRect(px, py, 20, 5);
    }

    public int getScore() {
        return gameState.getScore();
    }

    public GameState getGameState() {
        return gameState;
    }

    // starts the game
    public void start() {
        gameOverLbl.setVisible(false);
        challengeResults.setVisible(false);
        gameState.startGame();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        gameState.controlPiece(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
