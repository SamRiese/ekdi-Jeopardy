package backend;

import java.lang.reflect.Type;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class DataHandler {
    public List<Category> Categories = new ArrayList<>();
    private String path = "/Jeopardy/src/main/java/backend/data/questions.json";

    public DataHandler() {

    }

    public List<Category> loadQuestions() {
        String fileName = "/data/questions.json"; 
        
        // Get the questions using getResourceAsStream
        InputStream inputStream = DataHandler.class.getResourceAsStream(fileName); 
        System.out.println("Loading questions...");

        final Type QUESTION_TYPE = new TypeToken<List<Category>>() {
        }.getType();

        Gson gson = new Gson();

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            List<Category> data = gson.fromJson(content.toString(), QUESTION_TYPE);

            for (Category c : data) {
                for (int i = 0; i < c.easy.size(); i++) {
                    System.out.println(c.easy.get(i).question);
                }
            }
            Categories.addAll(data);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}