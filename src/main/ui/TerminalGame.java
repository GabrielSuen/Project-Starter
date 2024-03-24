package ui;


import model.GameState;
import model.Piece;
import model.Platform;
import java.awt.Color;

import java.awt.Graphics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TerminalGame extends JPanel {

    private GameState gameState;

    public TerminalGame() {
        setSize(300, 400);
        setBackground(Color.GRAY);
        gameState = new GameState();
        //addKeyListener(new KeyHandler());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
        if (gameState.isEndedGame()) {
            System.exit(0);
        }
    }

    public void addTimer() {
        Timer t = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                    gameState.update();
                    repaint();
            }
        });

        t.start();
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

}
