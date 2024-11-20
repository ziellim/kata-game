package ai.gleamer.ugly;

import org.junit.jupiter.api.Test;

import static ai.gleamer.ugly.QuestionCategory.*;
import static org.assertj.core.api.Assertions.assertThat;

public class GameIntTest {

    @Test
    void testGame() {
        var game = new Game();
        assertThat(game.isPlayable()).isFalse();

        game.addNewPlayer("Livai");
        assertThat(game.isPlayable()).isFalse();

        game.addNewPlayer("Eren");
        assertThat(game.isPlayable()).isTrue();

        // Livai
        assertThat(game.getPlayerCursor()).isZero();
        game.roll(7);
        assertThat(game.isGettingOutOfPenaltyBox()).isFalse();
        var result = game.registerCorrectAnswer();
        assertThat(result).isTrue();
        assertThat(game.getPlayer(0).getQuestionCategory()).isEqualTo(ROCK);
        assertThat(game.getPlayer(1).getQuestionCategory()).isEqualTo(POP);
        assertThat(game.getPlayer(0).getCoins()).isEqualTo(1);
        assertThat(game.getPlayer(1).getCoins()).isEqualTo(0);
        assertThat(game.getPlayer(0).isInPenaltyBox()).isFalse();
        assertThat(game.getPlayer(1).isInPenaltyBox()).isFalse();

        // Eren
        assertThat(game.getPlayerCursor()).isOne();
        game.roll(4);
        game.registerWrongAnswer();
        assertThat(game.isGettingOutOfPenaltyBox()).isFalse();
        assertThat(game.getPlayer(0).getQuestionCategory()).isEqualTo(ROCK);
        assertThat(game.getPlayer(1).getQuestionCategory()).isEqualTo(POP);
        assertThat(game.getPlayer(0).getCoins()).isEqualTo(1);
        assertThat(game.getPlayer(1).getCoins()).isEqualTo(0);
        assertThat(game.getPlayer(0).isInPenaltyBox()).isFalse();
        assertThat(game.getPlayer(1).isInPenaltyBox()).isTrue();

        // Livai
        assertThat(game.getPlayerCursor()).isZero();
        game.roll(8);
        result = game.registerCorrectAnswer();
        assertThat(result).isTrue();
        assertThat(game.isGettingOutOfPenaltyBox()).isFalse();
        assertThat(game.getPlayer(0).getQuestionCategory()).isEqualTo(ROCK);
        assertThat(game.getPlayer(1).getQuestionCategory()).isEqualTo(POP);
        assertThat(game.getPlayer(0).getCoins()).isEqualTo(2);
        assertThat(game.getPlayer(1).getCoins()).isEqualTo(0);
        assertThat(game.getPlayer(0).isInPenaltyBox()).isFalse();
        assertThat(game.getPlayer(1).isInPenaltyBox()).isTrue();

        // Eren
        assertThat(game.getPlayerCursor()).isOne();
        game.roll(6);
        assertThat(game.isGettingOutOfPenaltyBox()).isFalse();
        assertThat(game.getPlayer(0).getQuestionCategory()).isEqualTo(ROCK);
        assertThat(game.getPlayer(1).getQuestionCategory()).isEqualTo(POP);
        assertThat(game.getPlayer(0).getCoins()).isEqualTo(2);
        assertThat(game.getPlayer(1).getCoins()).isEqualTo(0);
        assertThat(game.getPlayer(0).isInPenaltyBox()).isFalse();
        assertThat(game.getPlayer(1).isInPenaltyBox()).isTrue();

        // Eren
        assertThat(game.getPlayerCursor()).isOne();
        game.roll(2);
        game.registerWrongAnswer();
        assertThat(result).isTrue();
        assertThat(game.isGettingOutOfPenaltyBox()).isFalse();
        assertThat(game.getPlayer(0).getQuestionCategory()).isEqualTo(ROCK);
        assertThat(game.getPlayer(1).getQuestionCategory()).isEqualTo(POP);
        assertThat(game.getPlayer(0).getCoins()).isEqualTo(2);
        assertThat(game.getPlayer(1).getCoins()).isEqualTo(0);
        assertThat(game.getPlayer(0).isInPenaltyBox()).isFalse();
        assertThat(game.getPlayer(1).isInPenaltyBox()).isTrue();

        // Livai
        assertThat(game.getPlayerCursor()).isZero();
        game.roll(6);
        game.registerWrongAnswer();
        assertThat(result).isTrue();
        assertThat(game.isGettingOutOfPenaltyBox()).isFalse();
        assertThat(game.getPlayer(0).getQuestionCategory()).isEqualTo(SCIENCE);
        assertThat(game.getPlayer(1).getQuestionCategory()).isEqualTo(POP);
        assertThat(game.getPlayer(0).getCoins()).isEqualTo(2);
        assertThat(game.getPlayer(1).getCoins()).isEqualTo(0);
        assertThat(game.getPlayer(0).isInPenaltyBox()).isTrue();
        assertThat(game.getPlayer(1).isInPenaltyBox()).isTrue();

        // Eren
        assertThat(game.getPlayerCursor()).isOne();
        game.roll(1);
        result = game.registerCorrectAnswer();
        assertThat(result).isTrue();
        assertThat(game.isGettingOutOfPenaltyBox()).isTrue();
        assertThat(game.getPlayer(0).getQuestionCategory()).isEqualTo(SCIENCE);
        assertThat(game.getPlayer(1).getQuestionCategory()).isEqualTo(SCIENCE);
        assertThat(game.getPlayer(0).getCoins()).isEqualTo(2);
        assertThat(game.getPlayer(1).getCoins()).isEqualTo(1);
        assertThat(game.getPlayer(0).isInPenaltyBox()).isTrue();
        assertThat(game.getPlayer(1).isInPenaltyBox()).isTrue();
    }
}
