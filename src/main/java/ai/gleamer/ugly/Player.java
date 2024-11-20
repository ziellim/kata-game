package ai.gleamer.ugly;

public class Player {
    private String name;

    private int randomQuestionCursor;

    private int coins;

    private boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getRandomQuestionCursor() {
        return randomQuestionCursor;
    }

    public void setRandomQuestionCursor(int roll) {
        randomQuestionCursor = randomQuestionCursor + roll;
        if (randomQuestionCursor > 11) {
            randomQuestionCursor = randomQuestionCursor - 12;
        }
    }

    public int getCoins() {
        return coins;
    }

    public void incrementCoins() {
        coins++;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }
}
