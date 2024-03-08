package persistence;

import model.Profile;
import model.ProfileManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ProfileManager pm = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyProfile.json");
        try {
            ProfileManager pm = reader.read();
            assertEquals("New Profile Manager", pm.getName());
            assertEquals(0, pm.numProfiles());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralProfiles.json");
        try {
            ProfileManager pm = reader.read();
            assertEquals("New Profile Manager", pm.getName());
            List<Profile> profiles = pm.getProfilesJson();
            assertEquals(2, profiles.size());
            checkProfile("Joe", 1, profiles.get(0));
            checkProfile("Bill", 2, profiles.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}