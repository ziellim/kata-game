package ai.gleamer.ugly;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static ai.gleamer.ugly.QuestionCategory.*;

public class Game {

    private static final String ANSWER_WAS_CORRECT = "Answer was correct!!!!";
    private final List<String> players, popQuestions, scienceQuestions, sportsQuestions, rockQuestions;
    private final int[] places, purses;
    private final boolean[] inPenaltyBox;
    private int currentPlayer;
    private boolean isGettingOutOfPenaltyBox;

    public  Game(){
        players = new ArrayList<>();
        places = new int[6];
        purses = new int[6];
        inPenaltyBox  = new boolean[6];
        popQuestions = new LinkedList<>();
        scienceQuestions = new LinkedList<>();
        sportsQuestions = new LinkedList<>();
        rockQuestions = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast("Science Question " + i);
            sportsQuestions.addLast("Sports Question " + i);
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public boolean isPlayable() {
        return countPlayers() >= 2;
    }

    public void addNewPlayer(String playerName) {
        players.add(playerName);
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    public int countPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);
        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 == 0) {
                System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
                return;
            }
            isGettingOutOfPenaltyBox = true;
            System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
        }
        moveCurrentPlayer(roll);
        displayCurrentCategory();
        askQuestion();
    }

    public boolean registerCorrectAnswer() {
        if (inPenaltyBox[currentPlayer] && !isGettingOutOfPenaltyBox) {
            switchToNextPlayer();
            return true;
        } else {
            System.out.println(ANSWER_WAS_CORRECT);
            purses[currentPlayer]++;
            displayCoinsNumber();
            switchToNextPlayer();
            return didPlayerWin();
        }
    }

    public void registerWrongAnswer(){
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;
        switchToNextPlayer();
    }

    public int[] getPlaces() {
        return places;
    }

    public int[] getPurses() {
        return purses;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean[] getInPenaltyBox() {
        return inPenaltyBox;
    }

    public boolean isGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }

    public void setGettingOutOfPenaltyBox(boolean gettingOutOfPenaltyBox) {
        isGettingOutOfPenaltyBox = gettingOutOfPenaltyBox;
    }

    public List<String> getScienceQuestions() {
        return scienceQuestions;
    }

    private boolean didPlayerWin() {
        return purses[currentPlayer] != 6;
    }

    private void askQuestion() {
        var question = switch (currentCategory()){
            case POP -> popQuestions.removeFirst();
            case SCIENCE -> scienceQuestions.removeFirst();
            case SPORT -> sportsQuestions.removeFirst();
            case ROCK -> rockQuestions.removeFirst();
        };
        System.out.println(question);
    }

    private QuestionCategory currentCategory() {
        return switch (places[currentPlayer]) {
            case 0,4,8 -> POP;
            case 1,5,9 -> SCIENCE;
            case 2,6,10 -> SPORT;
            default -> ROCK;
        };
    }

    private void switchToNextPlayer(){
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    private void displayCoinsNumber() {
        System.out.println(players.get(currentPlayer)
                + " now has "
                + purses[currentPlayer]
                + " Gold Coins.");
    }

    private void displayCurrentCategory() {
        System.out.println("The category is " + currentCategory().getValue());
    }

    private void moveCurrentPlayer(int roll) {
        places[currentPlayer] = places[currentPlayer] + roll;
        if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
        System.out.println(players.get(currentPlayer)
                + "'s new location is "
                + places[currentPlayer]);
    }
}

