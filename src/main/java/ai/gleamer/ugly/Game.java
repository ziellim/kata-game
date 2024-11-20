package ai.gleamer.ugly;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {

    private static final String ANSWER_WAS_CORRECT = "Answer was correct!!!!";
    private static final int NUMBER_OF_PLAYER = 6;
    private final List<Player> players;
    private int playerCursor;
    private final List<String> popQuestions, scienceQuestions, sportsQuestions, rockQuestions;
    private boolean isGettingOutOfPenaltyBox;

    public  Game(){
        players = new ArrayList<>(NUMBER_OF_PLAYER);
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
        players.add(new Player(playerName));
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    public int countPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        var currentPlayer = getCurrentPlayer();
        System.out.println(currentPlayer.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);
        if (currentPlayer.isInPenaltyBox()) {
            if (roll % 2 == 0) {
                System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
                return;
            }
            isGettingOutOfPenaltyBox = true;
            System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
        }
        moveCurrentPlayer(roll);
        displayCurrentCategory();
        askQuestion();
    }

    public boolean registerCorrectAnswer() {
        var currentPlayer = getCurrentPlayer();
        if (currentPlayer.isInPenaltyBox() && !isGettingOutOfPenaltyBox) {
            switchToNextPlayer();
            return true;
        } else {
            System.out.println(ANSWER_WAS_CORRECT);
            currentPlayer.incrementCoins();
            displayCoinsNumber();
            switchToNextPlayer();
            return didPlayerWin();
        }
    }

    public void registerWrongAnswer(){
        System.out.println("Question was incorrectly answered");
        System.out.println(getCurrentPlayer().getName() + " was sent to the penalty box");
        getCurrentPlayer().setInPenaltyBox(true);
        switchToNextPlayer();
    }

    public Player getCurrentPlayer() {
        return players.get(playerCursor);
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public boolean isGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }

    public void setGettingOutOfPenaltyBox(boolean gettingOutOfPenaltyBox) {
        isGettingOutOfPenaltyBox = gettingOutOfPenaltyBox;
    }

    public int getPlayerCursor() {
        return playerCursor;
    }

    public List<String> getScienceQuestions() {
        return scienceQuestions;
    }

    private boolean didPlayerWin() {
        return getCurrentPlayer().getCoins() != NUMBER_OF_PLAYER;
    }

    private void askQuestion() {
        var question = switch (getCurrentPlayer().getQuestionCategory()){
            case POP -> popQuestions.removeFirst();
            case SCIENCE -> scienceQuestions.removeFirst();
            case SPORT -> sportsQuestions.removeFirst();
            case ROCK -> rockQuestions.removeFirst();
        };
        System.out.println(question);
    }

    private void switchToNextPlayer(){
        playerCursor++;
        if(playerCursor == countPlayers()){
            playerCursor = 0;
        }
    }

    private void displayCoinsNumber() {
        var currentPlayer = getCurrentPlayer();
        System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getCoins() + " Gold Coins.");
    }

    private void displayCurrentCategory() {
        System.out.println("The category is " + getCurrentPlayer().getQuestionCategory().getValue());
    }

    private void moveCurrentPlayer(int roll) {
        var currentPlayer = getCurrentPlayer();
        currentPlayer.setQuestionCategory(roll);
        System.out.println(currentPlayer.getName() + "'s new location is " + currentPlayer.getQuestionCategory());
    }
}

