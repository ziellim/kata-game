package ai.gleamer.ugly;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static ai.gleamer.ugly.QuestionCategory.*;

public class Game {

    private static final String ANSWER_WAS_CORRECT = "Answer was correct!!!!";
    private static final int MAXIMUM_NUMBER_OF_PLAYER = 6;
    private final List<Player> players;
    private int playerCursor;
    private final List<Question> questions;

    public  Game(){
        players = new ArrayList<>(MAXIMUM_NUMBER_OF_PLAYER);
        questions = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            questions.addLast(new Question("Pop Question " + i, POP));
            questions.addLast(new Question("Science Question " + i, SCIENCE));
            questions.addLast(new Question("Sports Question " + i, SPORT));
            questions.addLast(new Question("Rock Question " + i, ROCK));
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
                return;
            }
            currentPlayer.setInPenaltyBox(false);
            System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
        }
        moveCurrentPlayer(roll);
        var question = questions.remove(getCurrentPlayer().getRandomQuestionCursor());
        System.out.println("The category is " + question.getCategory().getValue());
        System.out.println(question.getValue());
    }

    public void registerCorrectAnswer() {
        var currentPlayer = getCurrentPlayer();
        if (!currentPlayer.isInPenaltyBox()) {
            System.out.println(ANSWER_WAS_CORRECT);
            currentPlayer.incrementCoins();
            displayCoinsNumber();
        }
        switchToNextPlayer();
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

    public int getPlayerCursor() {
        return playerCursor;
    }

    // only for test
    public long countScienceQuestions() {
        return questions.stream().filter(question -> SCIENCE == question.getCategory()).count();
    }

    private boolean didPlayerWin() {
        return getCurrentPlayer().getCoins() != MAXIMUM_NUMBER_OF_PLAYER;
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

    private void moveCurrentPlayer(int roll) {
        var currentPlayer = getCurrentPlayer();
        currentPlayer.setRandomQuestionCursor(roll);
        System.out.println(currentPlayer.getName() + "'s new location is " + currentPlayer.getRandomQuestionCursor());
    }
}
