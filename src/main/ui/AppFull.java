package ui;

import model.Event;
import model.EventLog;
import model.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Combines game and menu aspects into a JFrame
public class AppFull extends JFrame implements ActionListener, WindowListener {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;

    private GamePanel game;
    private MenuPanel menu;
    private ScorePanel scorePanel;
    private JTextField textFieldNRecent;

    private JButton play;
    private JButton addRecentButton;

    // Constructs the full application
    // effects: sets the size, initializes the play area and menu
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public AppFull() {

        play = new JButton("b1");
        play.addActionListener(this);
        play.setBounds(20, 300, 75, 50);
        play.setText("Play");
        play.setVisible(true);

        addRecentButton = new JButton("addRecent");
        addRecentButton.addActionListener(this);
        addRecentButton.setBounds(370, 440, 100, 30);
        addRecentButton.setText("Add Score");
        addRecentButton.setVisible(true);

        textFieldNRecent = new JTextField();
        textFieldNRecent.setBounds(470, 445,100,20);
        textFieldNRecent.setText("Type Name");
        textFieldNRecent.setVisible(true);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(WIDTH, HEIGHT);


        menu = new MenuPanel();
        menu.setVisible(true);
        game = new GamePanel(menu);
        game.setBounds(385, 20, 200, 400);
        game.setVisible(false);
        addTimer();
        scorePanel = new ScorePanel(game.getGameState());
        scorePanel.setBounds(375, 10, 220, 430);
        frame.getContentPane().setBackground(Color.ORANGE);
        frame.add(scorePanel);
        frame.add(play);
        frame.add(addRecentButton);
        frame.add(textFieldNRecent);
        frame.add(menu);
        frame.add(game);
        frame.addKeyListener(game);
        frame.addWindowListener(this);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play) {
            game.start();
            game.setVisible(true);
            game.requestFocus();
        }
        if (e.getSource() == addRecentButton) {
            textFieldNRecent.setVisible(true);
            Profile user = new Profile(textFieldNRecent.getText(), game.getScore());
            menu.getProfiles().addProfile(user);
            textFieldNRecent.setText("");
        }
    }

    // effects: starts the timer(tick) for the game
    public void addTimer() {
        Timer t = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                game.getGameState().update();
                game.repaint();
                scorePanel.update();
            }
        });

        t.start();
    }

    // methods declared for WindowListener only windowClosing is implemented
    // effects: prints out the logged actions once application is closed
    @Override
    public void windowClosing(WindowEvent e) {
        EventLog log = EventLog.getInstance();
        for (Event ev : log) {
            System.out.println(ev.toString());

        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
