package backend;

import java.lang.reflect.Type;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 * The DataHandler class is responsible for handling the loading of questions from a JSON file by deserializing questions.json.
 */
public class DataHandler {
    public List<Category> Categories = new ArrayList<>();
    public DataHandler() {

    }

    /**
     * Loads questions from a JSON file located at /data/questions.json.
     *
     * @return a list of Category objects loaded from the JSON file, or null if an error occurs.
     */
    public List<Category> loadQuestions() {
        String path = "/data/questions.json";

        InputStream inputStream = DataHandler.class.getResourceAsStream(path);

        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            // Read each line from the file and append it to the content StringBuilder
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String jsonString = content.toString();

        // Create a Gson object for JSON deserialization
        Gson gson = new Gson();

        // Define the type for the deserialization
        final Type QUESTION_TYPE = new TypeToken<List<Category>>(){}.getType();

         // Deserialize the JSON string into a list of Category objects
        List<Category> data = gson.fromJson(jsonString, QUESTION_TYPE);

        Categories.addAll(data);
        return data;
    }

}