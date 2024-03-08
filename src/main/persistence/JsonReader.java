package persistence;

import model.Profile;
import model.ProfileManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads profile manager from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ProfileManager read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseProfileManager(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private ProfileManager parseProfileManager(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ProfileManager pm = new ProfileManager(name);
        addSaves(pm, jsonObject);
        return pm;
    }

    // MODIFIES: pm
    // EFFECTS: parses thingies from JSON object and adds them to profile manager
    private void addSaves(ProfileManager pm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("profiles");
        for (Object json : jsonArray) {
            JSONObject nextSave = (JSONObject) json;
            addSave(pm, nextSave);
        }
    }

    // MODIFIES: pm
    // EFFECTS: parses thingy from JSON object and adds it to profile manager
    private void addSave(ProfileManager pm, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int score = jsonObject.getInt("score");
        Profile profile = new Profile(name, score);
        pm.addProfile(profile);
    }
}
