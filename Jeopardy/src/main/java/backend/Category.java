package backend;

import java.util.List;

/**
 * The Category class holds lists of easy, medium, hard and expert questions. 
 * The questions.json file is strucutred accordingly as List of Categories.
 */
public class Category {
    public String name;
    public List<Question> easy;
    public List<Question> medium;
    public List<Question> hard;
    public List<Question> expert;
}
