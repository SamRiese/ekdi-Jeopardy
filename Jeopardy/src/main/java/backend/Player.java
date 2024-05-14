package backend;

public class Player {

  String name;
  int score;
  String lastAnswer;

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
