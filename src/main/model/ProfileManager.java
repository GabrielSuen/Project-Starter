package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

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
    }


    public List<Profile> getProfiles() {
        return profiles;
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
            }
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
