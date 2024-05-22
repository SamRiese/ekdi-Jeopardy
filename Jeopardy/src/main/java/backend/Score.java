package backend;

/**
 * The Score class represents a player's score in a game, consists of their name and score value.
 */
public class Score {
    public String name; 
    public int score;

    /**
     * Constructs a Score object with a player's name and score as a string.
     * The score string is stripped of leading and trailing whitespace and converted to an integer.
     *
     * @param name the name of the player
     * @param score the score of the player as a string
     */
    public Score(String name, String score) { 
        this.name = name;
        this.score = Integer.valueOf(score.strip());
    } 

    /**
     * Constructs a Score object with a player's name and score as an integer.
     *
     * @param name the name of the player
     * @param score the score of the player as an integer
     */
    public Score(String name, int score) { 
        this.name = name;
        this.score = score;
    } 
} 
