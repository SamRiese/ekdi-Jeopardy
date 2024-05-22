package backend;

/**
 * The Player class represents a player in the game, holds their name, score, and last answer.
 */
public class Player {

  String name;
  int score;
  String lastAnswer;

  /**
     * Constructs a new Player with the specified name.
     *
     * @param newName the name of the player
     */
  public Player(String newName) {
    name = newName;
    score = 0;
    lastAnswer = "";
  }

  public int getScore() {
    return score;
  }

  public String getLastAnswer() {
    return lastAnswer;
  }

  public String getName() {
    return name;
  }

  public void setLastAnswer(String answer) {
    lastAnswer = answer;
  }

  public void increaseScore(int newPoints) {
    score = score + newPoints;
  }
}
