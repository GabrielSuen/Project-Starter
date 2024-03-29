package model;


import org.json.JSONObject;
import persistence.Writable;

// represents a player, has a score and name
public class Profile implements Writable {

    private final int score;
    private final String name;

    public Profile(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // effects: shows the name and score of the profile
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("score", score);
        return json;
    }

}
