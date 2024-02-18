package model;


public class Profile {

    private final int score;
    private final String name;

    public Profile(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // shows the name and score of the profile
    public String showProfile() {
        return "Player: " + name + " Score: " + score;
    }


    // getters and setters
    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

}
