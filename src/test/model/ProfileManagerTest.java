package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileManagerTest {

    private ProfileManager pm;
    private Profile p1;
    private Profile p2;
    private Profile p3;

    @BeforeEach
    void runBefore() {
        pm = new ProfileManager();
        p1 = new Profile("joe", 1);
        p2 = new Profile("moe", 2);
        p3 = new Profile("chloe", 3);
    }

    @Test
    void addProfileTest() {
        pm.addProfile(p1);
        pm.addProfile(p2);
        pm.addProfile(p3);
        assertEquals(3, pm.getProfiles().size());
    }

    @Test
    void isEmptyTest() {
        assertTrue(pm.isEmpty());
        pm.addProfile(p1);
        assertFalse(pm.isEmpty());
    }

    @Test
    void setChallengerTest() {
        pm.addProfile(p1);
        pm.addProfile(p2);
        pm.addProfile(p3);
        pm.setChallenger("moe");
        assertEquals(p2, pm.getChallenger());
    }

}
