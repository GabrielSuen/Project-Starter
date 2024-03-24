package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GameFull extends JFrame implements KeyListener {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 400;

    private TerminalGame game;

    private JButton bt1;
    private JPanel buttonPanel;

    public GameFull() {

//        bt1 = new JButton("b1");
//        bt1.addActionListener(this);
//
//        buttonPanel = new JPanel();
//        //buttonPanel.setLayout(new GridLayout(1,1));
//        buttonPanel.setBackground(Color.red);
//        buttonPanel.setBounds(0, 0, 100, 100);
//        buttonPanel.add(bt1);
//        buttonPanel.setVisible(false);


        JFrame menu = new JFrame();
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setLayout(null);
        menu.setSize(WIDTH, HEIGHT);
        menu.addKeyListener(this);
        game = new TerminalGame();
        menu.add(game);
        //menu.add(bt1);
        menu.setVisible(true);
        game.addTimer();


    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == bt1) {
//            buttonPanel.setVisible(true);
//        }
//    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        game.getGameState().controlPiece(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

//    private class KeyHandler extends KeyAdapter {
//        @Override
//        public void keyPressed(KeyEvent e) {
//            game.getGameState().controlPiece(e.getKeyCode());
//        }
//    }


}
