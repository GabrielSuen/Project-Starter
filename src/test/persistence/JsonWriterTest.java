package persistence;

import model.Profile;
import model.ProfileManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            ProfileManager pm = new ProfileManager("New Profile Manager");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ProfileManager pm = new ProfileManager("New Profile Manager");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyProfile.json");
            writer.open();
            writer.write(pm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyProfile.json");
            pm = reader.read();
            assertEquals("New Profile Manager", pm.getName());
            assertEquals(0, pm.numProfiles());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ProfileManager pm = new ProfileManager("New Profile Manager");
            pm.addProfile(new Profile("Joe", 1));
            pm.addProfile(new Profile("Bill", 2));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralProfiles.json");
            writer.open();
            writer.write(pm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralProfiles.json");
            pm = reader.read();
            assertEquals("New Profile Manager", pm.getName());
            List<Profile> thingies = pm.getProfilesJson();
            assertEquals(2, thingies.size());
            checkProfile("Joe", 1, thingies.get(0));
            checkProfile("Bill", 2, thingies.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}