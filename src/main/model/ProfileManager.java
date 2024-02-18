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

    // adds the given profile to list
    public void addProfile(Profile profile) {
        profiles.add(profile);
    }


    public List<Profile> getProfiles() {
        return profiles;
    }

    public boolean isEmpty() {
        return profiles.isEmpty();
    }

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
