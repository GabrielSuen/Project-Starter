package model;


public class Profile {

    private int score;
    private String name;

    public Profile(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String showProfile() {
        String viewable = "Player:" + name + " Score:" + score;
        return viewable;
    }


    // getters and setters
    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

}
