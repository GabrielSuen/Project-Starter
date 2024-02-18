package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest {

    private Profile pr;

    @BeforeEach
    void runBefore() {
        pr = new Profile("joe", 0);
    }

    @Test
    void testShowProfile() {
        assertEquals("Player: joe Score: 0", pr.showProfile());
    }

}
