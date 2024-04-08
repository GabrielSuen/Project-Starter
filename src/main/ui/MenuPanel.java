package ui;

import model.Profile;
import model.ProfileManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the game menu ui
public class MenuPanel extends JPanel implements ActionListener {

    private static final String JSON_STORE = "./data/profiles.json";
    private ProfileManager profiles;
    private ArrayList<String> namesAlreadyAdded;
    private ArrayList<String> namesAlreadyChallenged;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JButton addProfileButton;
    private JButton submit;
    private JButton viewProfilesButton;
    private JButton save;
    private JButton load;
    private JButton setChallenger;
    private JButton compare;
    private JButton submit1;

    private JComboBox dropDown;
    private JComboBox compare1;
    private JComboBox compare2;

    private JTextField textFieldN;
    private JTextField textFieldS;

    private JLabel comparison;

    private JPanel leaderboard;

    // Constructor, has all buttons and other visual aspects of the menu
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public MenuPanel() {

        leaderboard = new JPanel();
        leaderboard.setLayout(null);
        leaderboard.setBackground(Color.WHITE);
        leaderboard.setBounds(125, 80, 100, 200);
        leaderboard.setVisible(false);

        addProfileButton = new JButton("addP");
        addProfileButton.addActionListener(this);
        addProfileButton.setBounds(10, 10, 100, 30);
        addProfileButton.setText("Add Profile");
        addProfileButton.setVisible(true);

        submit = new JButton("Submit");
        submit.addActionListener(this);
        submit.setBounds(250, 10, 70, 30);
        submit.setText("Add");
        submit.setVisible(false);

        viewProfilesButton = new JButton("profiles");
        viewProfilesButton.addActionListener(this);
        viewProfilesButton.setBounds(10, 40, 100, 30);
        viewProfilesButton.setText("Players");
        viewProfilesButton.setVisible(true);

        save = new JButton("save");
        save.addActionListener(this);
        save.setBounds(10, 70, 100, 30);
        save.setText("Save");
        save.setVisible(true);

        load = new JButton("load");
        load.addActionListener(this);
        load.setBounds(10, 100, 100, 30);
        load.setText("Load");
        load.setVisible(true);

        setChallenger = new JButton("setChallenger");
        setChallenger.addActionListener(this);
        setChallenger.setBounds(10, 130, 100, 30);
        setChallenger.setText("Challenge");
        setChallenger.setVisible(true);

        namesAlreadyAdded = new ArrayList<String>();
        dropDown = new JComboBox();
        dropDown.setBounds(120, 130, 100, 30);
        dropDown.addActionListener(this);
        dropDown.setVisible(false);

        namesAlreadyChallenged = new ArrayList<String>();
        compare = new JButton("compare");
        compare.addActionListener(this);
        compare.setBounds(10, 160, 100, 30);
        compare.setText("Compare");
        compare.setVisible(true);

        compare1 = new JComboBox();
        compare1.setBounds(120, 160, 100, 30);
        compare1.addActionListener(this);
        compare1.setVisible(false);

        compare2 = new JComboBox();
        compare2.setBounds(120, 190, 100, 30);
        compare2.addActionListener(this);
        compare2.setVisible(false);

        submit1 = new JButton("Submit1");
        submit1.addActionListener(this);
        submit1.setBounds(250, 160, 70, 30);
        submit1.setText("Submit");
        submit1.setVisible(false);

        comparison = new JLabel();
        comparison.setBounds(120, 210, 200, 30);
        comparison.setForeground(Color.WHITE);
        comparison.setVisible(false);

        textFieldN = new JTextField();
        textFieldN.setBounds(120, 10,120,30);
        textFieldN.setText("Name");
        textFieldN.setVisible(false);

        textFieldS = new JTextField();
        textFieldS.setBounds(120, 45,120,30);
        textFieldS.setText("Score");
        textFieldS.setVisible(false);

        init();
        setSize(720, 500);
        setBackground(Color.BLACK);
        setLayout(null);
        this.add(leaderboard);
        this.add(addProfileButton);
        this.add(submit);
        this.add(save);
        this.add(load);
        this.add(viewProfilesButton);
        this.add(textFieldN);
        this.add(textFieldS);
        this.add(setChallenger);
        this.add(dropDown);
        this.add(compare);
        this.add(compare1);
        this.add(compare2);
        this.add(submit1);
        this.add(comparison);

    }

    // effects: initializes the list of profiles
    private void init() {
        profiles = new ProfileManager("New Profile Manager");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // modifies: this
    // effects: displays the leaderboard as a JPanel with a list of names with scores
    private void showLeaderboard() {
        int y = 0;
        for (Profile p: profiles.getProfiles()) {
            JLabel temp = new JLabel();
            temp.setText(p.getName() + ":" + p.getScore());
            temp.setBounds(25, y, 50, 30);
            leaderboard.add(temp);
            y += 12;
        }
    }

    // EFFECTS: saves the profile manager to file
    private void saveProfiles() {
        try {
            jsonWriter.open();
            jsonWriter.write(profiles);
            jsonWriter.close();
            System.out.println("Saved " + profiles.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads profile manager from file
    private void loadProfiles() {
        try {
            profiles = jsonReader.read();
            System.out.println("Loaded " + profiles.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    // effects: handles each buttons function
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addProfileButton) {
            textFieldN.setVisible(true);
            textFieldS.setVisible(true);
            submit.setVisible(true);
        }
        if (e.getSource() == submit) {
            try {
                Profile user = new Profile(textFieldN.getText(), Integer.parseInt(textFieldS.getText()));
                profiles.addProfile(user);
            } catch (NumberFormatException exception) {
                textFieldS.setBackground(Color.RED);
            }
            textFieldS.setText("Score");
            textFieldN.setText("Name");
            leaderboard.setVisible(false);
        }
        if (e.getSource() == viewProfilesButton) {
            showLeaderboard();
            leaderboard.setVisible(true);
        }
        if (e.getSource() == save) {
            saveProfiles();
        }
        if (e.getSource() == load) {
            loadProfiles();
        }
        if (e.getSource() == setChallenger) {
            ArrayList<String> names = (ArrayList<String>) profiles.getProfileNames();
            for (String n : names) {
                if (!namesAlreadyAdded.contains(n)) {
                    dropDown.addItem(n);
                    namesAlreadyAdded.add(n);
                }
            }
            dropDown.setVisible(true);
        }
        if (e.getSource() == dropDown) {
            profiles.setChallenger((String) dropDown.getSelectedItem());
            System.out.println(getChallenger().getName());
        }
        if (e.getSource() == compare) {
            ArrayList<String> names = (ArrayList<String>) profiles.getProfileNames();
            for (String n : names) {
                if (!namesAlreadyChallenged.contains(n)) {
                    compare1.addItem(n);
                    compare2.addItem(n);
                    namesAlreadyChallenged.add(n);
                }
            }
            compare1.setVisible(true);
            compare2.setVisible(true);
            submit1.setVisible(true);
        }
        if (e.getSource() == submit1) {
            String name1 = (String) compare1.getSelectedItem();
            String name2 = (String) compare2.getSelectedItem();
            if (name1 != null && name2 != null) {
                String resultText = profiles.makeComparison(name1, name2);
                comparison.setText(resultText);
                comparison.setVisible(true);
            }
        }
    }

    public ProfileManager getProfiles() {
        return profiles;
    }

    public Profile getChallenger() {
        return profiles.getChallenger();
    }
}
