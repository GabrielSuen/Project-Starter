package ui;

import model.Profile;
import model.ProfileManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import persistence.JsonReader;
import persistence.JsonWriter;

public class TerminalMenu {

    private static final String JSON_STORE = "./data/profiles.json";
    private Scanner input;
    private ProfileManager profiles;
    private TerminalGame gameStarter;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // starts the menu ui
    @SuppressWarnings("methodlength")
    public void runMenu() throws IOException, InterruptedException {
        boolean keepGoing = true;
        String command;

        gameStarter = new TerminalGame();
        gameStarter.start();

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else if (command.equals("p")) {
                challenge();
                Profile c = getChallenger();
                gameStarter = new TerminalGame();
                gameStarter.start();
                if (getChallenger() != null) {
                    if (gameStarter.getScore() > c.getScore()) {
                        System.out.println("You beat your opponent");
                    } else {
                        System.out.println("You did not beat your opponent");
                    }
                } else {
                    System.out.println("Thank you for playing!");
                }
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
        System.exit(0);
    }


    // effects: initializes the list of profiles
    private void init() {
        profiles = new ProfileManager("New Profile Manager");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // effects: displays the input options available to a player
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add Most Recent Score");
        System.out.println("\tb -> Add Another Score");
        System.out.println("\tc -> Compare Scores");
        System.out.println("\tv -> View Leaderboard");
        System.out.println("\tx -> Set Challenger");
        System.out.println("\ts -> Save Profiles");
        System.out.println("\tl -> Load Profiles");
        System.out.println("\tp -> Play Again");
        System.out.println("\tq -> Quit");
    }

    // effects: does the appropriate command according to the input
    private void processCommand(String command) {
        if (command.equals("a")) {
            addMostRecentUser();
        } else if (command.equals("v")) {
            viewPlayers();
        } else if (command.equals("b")) {
            addUsersAndScore();
        } else if (command.equals("c")) {
            compareScores();
        } else if (command.equals("x")) {
            challenge();
        } else if (command.equals("s")) {
            saveProfiles();
        } else if (command.equals("l")) {
            loadProfiles();
        }
    }

    // effects: takes the names of two players and compares their scores
    private void compareScores() {
        System.out.println("Enter name of player to compare with");
        String selection1;
        selection1 = input.next();
        System.out.println("Enter name of player to compare with");
        String selection2;
        selection2 = input.next();

        makeComparison(selection1, selection2);

    }

    // effects: displays a different message depending on the scores of the profiles
    public void compareProfiles(Profile p1, Profile p2) {
        if (p2.getScore() < (p1.getScore() - 2)) {
            System.out.println("Player 2's got a ways to go!");
        } else if (p2.getScore() > (p1.getScore() + 2)) {
            System.out.println("Player 2's leagues ahead!");
        } else {
            System.out.println("They're neck and neck!");
        }
    }

    // effects: does the calculation for the comparison
    public void makeComparison(String n1, String n2) {
        Profile p1 = null;
        Profile p2 = null;
        for (Profile p : profiles.getProfiles()) {
            if (n1.equals(p.getName())) {
                p1 = p;
            }
            if (n2.equals(p.getName())) {
                p2 = p;
            }
        }

        compareProfiles(p1, p2);

    }

    // effects: shows a list of player profiles. prints empty if profiles is empty
    private void viewPlayers() {
        if (profiles.isEmpty()) {
            System.out.println("Empty");
        }
        viewProfiles();
    }

    // effects: prints out the name and score of each profile
    public void viewProfiles() {
        for (Profile p : profiles.getProfiles()) {
            System.out.println(p.showProfile());
        }
    }



    // option to add most recent score as a user profile
    private void addMostRecentUser() {
        System.out.println("Enter Name");
        String selection;
        selection = input.next();
        Profile user = new Profile(selection, gameStarter.getScore());
        profiles.addProfile(user);
    }

    // option to add a user with name and score
    private void addUsersAndScore() {
        System.out.println("Enter Name");
        String name;
        name = input.next();
        System.out.println("Enter Score");
        int score;
        score = Integer.parseInt(input.next());
        Profile user = new Profile(name, score);
        profiles.addProfile(user);
    }

    // option to challenge a player, prints a performance report based on the challengers score
    private void challenge() {
        System.out.println("Would you like to challenge a player? (Yes/No)");
        String selection;
        selection = input.next();
        if (selection.equals("yes") || selection.equals("Yes")) {
            System.out.println("Who would you like to challenge? (enter player name)");
            String name;
            name = input.next();
            profiles.setChallenger(name);
        }
    }

    private Profile getChallenger() {
        return profiles.getChallenger();
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

}
