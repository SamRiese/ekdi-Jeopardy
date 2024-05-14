package backend;

import java.lang.reflect.Type;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
        System.out.println("Loading questions...");
        path = System.getProperty("user.dir") + path;

        final Type QUESTION_TYPE = new TypeToken<List<Category>>() {
        }.getType();

        Gson gson = new Gson();

        try {
            JsonReader reader = new JsonReader(new FileReader(path));
            List<Category> data = gson.fromJson(reader, QUESTION_TYPE);

            for (Category c : data) {
                for (int i = 0; i < c.easy.size(); i++) {
                    System.out.println(c.easy.get(i).question);
                }
            }
            Categories.addAll(data);
            return data;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            return null;
        }

    }

}
