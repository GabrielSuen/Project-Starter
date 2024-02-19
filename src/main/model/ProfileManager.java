package model;

import java.util.List;
import java.util.ArrayList;

public class ProfileManager {

    private final List<Profile> profiles;
    private Profile challenger;

    // constructs a profile manager with no profiles
    public ProfileManager() {
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

    public Profile getChallenger() {
        return challenger;
    }
}
