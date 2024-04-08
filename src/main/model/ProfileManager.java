package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// Handles operations with player profiles
public class ProfileManager implements Writable {

    private final List<Profile> profiles;
    private Profile challenger;
    private String name;

    // constructs a profile manager with no profiles
    public ProfileManager(String name) {
        this.name = name;
        this.profiles = new ArrayList<>();
    }

    // effects: adds the given profile to list
    public void addProfile(Profile profile) {
        profiles.add(profile);
        EventLog.getInstance().logEvent(new Event("Profile Added."));
    }


    public List<Profile> getProfiles() {
        EventLog.getInstance().logEvent(new Event("Profiles Shown."));
        return profiles;
    }

    // gets the names of all profiles in profiles
    public List<String> getProfileNames() {
        List<String> names = new ArrayList<>();
        for (Profile p : profiles) {
            names.add(p.getName());
        }
        return names;
    }

    // effects: checks if the list of profiles is empty
    public boolean isEmpty() {
        return profiles.isEmpty();
    }

    // modifies: this
    // effects: sets the challenger to the corresponding string. null if none is found
    public void setChallenger(String name) {
        for (Profile p : profiles) {
            if (p.getName().equals(name)) {
                challenger = p;
                EventLog.getInstance().logEvent(new Event("Challenger Set."));
            }
        }
    }

    // effects: displays a different message depending on the scores of the profiles
    public String makeComparison(String n1, String n2) {
        Profile p1 = null;
        Profile p2 = null;
        for (Profile p : profiles) {
            if (n1.equals(p.getName())) {
                p1 = p;
            }
            if (n2.equals(p.getName())) {
                p2 = p;
            }
        }
        EventLog.getInstance().logEvent(new Event("ProfilesCompared."));
        if (p2.getScore() < (p1.getScore() - 2)) {
            //System.out.println("Player 2's got a ways to go!");
            return "Player 2's got a ways to go!";
        } else if (p2.getScore() > (p1.getScore() + 2)) {
            //System.out.println("Player 2's leagues ahead!");
            return "Player 2's leagues ahead!";
        } else {
            //System.out.println("They're neck and neck!");
            return "They're neck and neck!";
        }
    }

    // EFFECTS: returns an unmodifiable list of profiles in this workroom
    public List<Profile> getProfilesJson() {
        return Collections.unmodifiableList(profiles);
    }

    public int numProfiles() {
        return profiles.size();
    }

    public Profile getChallenger() {
        return challenger;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("profiles", profilesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray profilesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Profile p : profiles) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    public String getName() {
        return name;
    }
}
