package ui;

import model.Profile;
import model.ProfileManager;

import java.io.IOException;
import java.util.Scanner;

public class TerminalMenu {

    private Scanner input;
    private ProfileManager profiles;
    private TerminalGame gameStarter;

    // starts the program
    @SuppressWarnings("methodlength")
    public void runMenu() throws IOException, InterruptedException {
        boolean keepGoing = true;
        String command = null;

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


    // initializes the list of profiles
    private void init() {
        profiles = new ProfileManager();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add Most Recent Score");
        System.out.println("\tb -> Add Another Score");
        System.out.println("\tc -> Compare Scores");
        System.out.println("\tv -> View Leaderboard");
        System.out.println("\tx -> Set Challenger");
        System.out.println("\tp -> Play Again");
        System.out.println("\tq -> Quit");
    }

    private void processCommand(String command) {
        if (command.equals("a")) {
            addUsers();
        } else if (command.equals("v")) {
            viewPlayers();
        } else if (command.equals("b")) {
            addUsersAndScore();
        } else if (command.equals("c")) {
            compareScores();
        } else if (command.equals("x")) {
            challenge();
        }
    }

    private void compareScores() {
        System.out.println("Enter name of player to compare with");
        String selection1;
        selection1 = input.next();
        System.out.println("Enter name of player to compare with");
        String selection2;
        selection2 = input.next();

        makeComparison(selection1, selection2);

    }

    public void compareProfiles(Profile p1, Profile p2) {
        if (p2.getScore() < (p1.getScore() - 2)) {
            System.out.println("Player 2's got a ways to go!");
        } else if (p2.getScore() > (p1.getScore() + 2)) {
            System.out.println("Player 2's leagues ahead!");
        } else {
            System.out.println("They're neck and neck!");
        }
    }

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

    private void viewPlayers() {
        if (profiles.isEmpty()) {
            System.out.println("Empty");
        }
        viewProfiles();
    }

    public void viewProfiles() {
        for (Profile p : profiles.getProfiles()) {
            System.out.println(p.showProfile());
        }
    }



    private void addUsers() {
        System.out.println("Enter Name");
        String selection;
        selection = input.next();
        Profile user = new Profile(selection, gameStarter.getScore());
        profiles.addProfile(user);
    }

    private void addUsersAndScore() {
        System.out.println("Enter Name");
        String name = "";
        name = input.next();
        System.out.println("Enter Score");
        int score = 0;
        score = Integer.parseInt(input.next());
        Profile user = new Profile(name, score);
        profiles.addProfile(user);
    }

    private void challenge() {
        System.out.println("Would you like to challenge a player? (Yes/No)");
        String selection = "";
        selection = input.next();
        if (selection.equals("yes") || selection.equals("Yes")) {
            System.out.println("Who would you like to challenge? (enter player name)");
            String name = "";
            name = input.next();
            profiles.setChallenger(name);
        }
    }

    private Profile getChallenger() {
        return profiles.getChallenger();
    }

}
