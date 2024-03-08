package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest {

    private Profile pr;
    private Profile pr2;

    @BeforeEach
    void runBefore() {
        pr = new Profile("joe", 0);
        pr2 = new Profile("moe", 100);
    }

    @Test
    void testShowProfile() {
        assertEquals("Player: joe Score: 0", pr.showProfile());
    }

    @Test
    void testGetScore() {
        assertEquals(0, pr.getScore());
    }

    @Test
    void testGetScoreAdded() {
        assertEquals(100, pr2.getScore());
    }

}
