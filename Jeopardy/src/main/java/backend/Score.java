package backend;

public class Score {
    public String name; 
    public int score; 
    public Score(String name, String score) { 
        this.name = name;
        this.score = Integer.valueOf(score);
    } 
    public Score(String name, int score) { 
        this.name = name;
        this.score = score;
    } 
} 
