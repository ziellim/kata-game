package ai.gleamer.ugly;

import static ai.gleamer.ugly.QuestionCategory.*;
import static ai.gleamer.ugly.QuestionCategory.ROCK;

public class Player {
    private String name;

    private int questionCategory;

    private int coins;

    private boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public QuestionCategory getQuestionCategory() {
        return switch (questionCategory) {
            case 0,4,8 -> POP;
            case 1,5,9 -> SCIENCE;
            case 2,6,10 -> SPORT;
            default -> ROCK;
        };
    }

    public void setQuestionCategory(int roll) {
        questionCategory = questionCategory + roll;
        if (questionCategory > 11) {
            questionCategory = questionCategory - 12;
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
