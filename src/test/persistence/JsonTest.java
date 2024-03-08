package persistence;

import model.Profile;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkProfile(String name, int score, Profile profile) {
        assertEquals(name, profile.getName());
        assertEquals(score, profile.getScore());
    }
}
