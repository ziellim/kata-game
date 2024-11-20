package ai.gleamer.ugly;

import org.junit.jupiter.api.Test;

import static ai.gleamer.ugly.QuestionCategory.*;
import static org.assertj.core.api.Assertions.assertThat;

public class GameIntTest {

    @Test
    void testGame() {
        var game = new Game();
        assertThat(game.isPlayable()).isFalse();

        game.addNewPlayer("Player 1");
        assertThat(game.isPlayable()).isFalse();

        game.addNewPlayer("Player 2");
        assertThat(game.isPlayable()).isTrue();

        // Player 1
        assertThat(game.getPlayerCursor()).isZero();
        game.roll(7);
        assertThat(game.getPlayer(0).isInPenaltyBox()).isFalse();
        game.registerCorrectAnswer();
        assertThat(game.getPlayer(0).getRandomQuestionCursor()).isEqualTo(7);
        assertThat(game.getPlayer(1).getRandomQuestionCursor()).isEqualTo(0);
        assertThat(game.getPlayer(0).getCoins()).isEqualTo(1);
        assertThat(game.getPlayer(1).getCoins()).isEqualTo(0);
        assertThat(game.getPlayer(0).isInPenaltyBox()).isFalse();
        assertThat(game.getPlayer(1).isInPenaltyBox()).isFalse();

        // Player 2
        assertThat(game.getPlayerCursor()).isOne();
        game.roll(4);
        assertThat(game.getPlayer(0).isInPenaltyBox()).isFalse();
        game.registerWrongAnswer();
        assertThat(game.getPlayer(0).getRandomQuestionCursor()).isEqualTo(7);
        assertThat(game.getPlayer(1).getRandomQuestionCursor()).isEqualTo(4);
        assertThat(game.getPlayer(0).getCoins()).isEqualTo(1);
        assertThat(game.getPlayer(1).getCoins()).isEqualTo(0);
        assertThat(game.getPlayer(0).isInPenaltyBox()).isFalse();
        assertThat(game.getPlayer(1).isInPenaltyBox()).isTrue();

        // Player 1
        assertThat(game.getPlayerCursor()).isZero();
        game.roll(8);
        assertThat(game.getPlayer(0).isInPenaltyBox()).isFalse();
        game.registerCorrectAnswer();
        assertThat(game.getPlayer(0).isInPenaltyBox()).isFalse();
        assertThat(game.getPlayer(0).getRandomQuestionCursor()).isEqualTo(3);
        assertThat(game.getPlayer(1).getRandomQuestionCursor()).isEqualTo(4);
        assertThat(game.getPlayer(0).getCoins()).isEqualTo(2);
        assertThat(game.getPlayer(1).getCoins()).isEqualTo(0);
        assertThat(game.getPlayer(0).isInPenaltyBox()).isFalse();
        assertThat(game.getPlayer(1).isInPenaltyBox()).isTrue();

        // Player 2
        assertThat(game.getPlayerCursor()).isOne();
        game.roll(6);
        assertThat(game.getPlayer(1).isInPenaltyBox()).isTrue();
        assertThat(game.getPlayer(0).getRandomQuestionCursor()).isEqualTo(3);
        assertThat(game.getPlayer(1).getRandomQuestionCursor()).isEqualTo(4);
        assertThat(game.getPlayer(0).getCoins()).isEqualTo(2);
        assertThat(game.getPlayer(1).getCoins()).isEqualTo(0);
        assertThat(game.getPlayer(0).isInPenaltyBox()).isFalse();
        assertThat(game.getPlayer(1).isInPenaltyBox()).isTrue();

        // Player 2
        assertThat(game.getPlayerCursor()).isOne();
        game.roll(2);
        assertThat(game.getPlayer(0).isInPenaltyBox()).isFalse();
        assertThat(game.getPlayer(0).getRandomQuestionCursor()).isEqualTo(3);
        assertThat(game.getPlayer(1).getRandomQuestionCursor()).isEqualTo(4);
        assertThat(game.getPlayer(0).getCoins()).isEqualTo(2);
        assertThat(game.getPlayer(1).getCoins()).isEqualTo(0);
        assertThat(game.getPlayer(0).isInPenaltyBox()).isFalse();
        assertThat(game.getPlayer(1).isInPenaltyBox()).isTrue();

        // Player 2
        assertThat(game.getPlayerCursor()).isOne();
        game.roll(1);
        game.registerCorrectAnswer();
        assertThat(game.getPlayer(1).isInPenaltyBox()).isFalse();
        assertThat(game.getPlayer(0).getRandomQuestionCursor()).isEqualTo(3);
        assertThat(game.getPlayer(1).getRandomQuestionCursor()).isEqualTo(5);
        assertThat(game.getPlayer(0).getCoins()).isEqualTo(2);
        assertThat(game.getPlayer(1).getCoins()).isEqualTo(1);
        assertThat(game.getPlayer(0).isInPenaltyBox()).isFalse();

        // Player 1
        assertThat(game.getPlayerCursor()).isZero();
        game.roll(6);
        game.registerWrongAnswer();
        assertThat(game.getPlayer(0).isInPenaltyBox()).isTrue();
        assertThat(game.getPlayer(0).getRandomQuestionCursor()).isEqualTo(9);
        assertThat(game.getPlayer(1).getRandomQuestionCursor()).isEqualTo(5);
        assertThat(game.getPlayer(0).getCoins()).isEqualTo(2);
        assertThat(game.getPlayer(1).getCoins()).isEqualTo(1);
        assertThat(game.getPlayer(0).isInPenaltyBox()).isTrue();

        // Player 2
        assertThat(game.getPlayerCursor()).isOne();
        game.roll(1);
        game.registerCorrectAnswer();
        assertThat(game.getPlayer(1).isInPenaltyBox()).isFalse();
        assertThat(game.getPlayer(0).getRandomQuestionCursor()).isEqualTo(9);
        assertThat(game.getPlayer(1).getRandomQuestionCursor()).isEqualTo(6);
        assertThat(game.getPlayer(0).getCoins()).isEqualTo(2);
        assertThat(game.getPlayer(1).getCoins()).isEqualTo(2);
        assertThat(game.getPlayer(0).isInPenaltyBox()).isTrue();
        assertThat(game.getPlayer(1).isInPenaltyBox()).isFalse();
    }
}
