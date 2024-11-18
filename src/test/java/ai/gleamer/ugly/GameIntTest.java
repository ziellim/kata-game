package ai.gleamer.ugly;

import org.junit.jupiter.api.Test;

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
        assertThat(game.getCurrentPlayer()).isZero();
        game.roll(7);
        assertThat(game.isGettingOutOfPenaltyBox()).isFalse();
        var result = game.registerCorrectAnswer();
        assertThat(result).isTrue();
        assertThat(game.getPlaces()[0]).isEqualTo(7);
        assertThat(game.getPlaces()[1]).isEqualTo(0);
        assertThat(game.getPurses()[0]).isEqualTo(1);
        assertThat(game.getPurses()[1]).isEqualTo(0);
        assertThat(game.getInPenaltyBox()[0]).isFalse();
        assertThat(game.getInPenaltyBox()[1]).isFalse();

        // Eren
        assertThat(game.getCurrentPlayer()).isOne();
        game.roll(4);
        game.registerWrongAnswer();
        assertThat(game.isGettingOutOfPenaltyBox()).isFalse();
        assertThat(game.getPlaces()[0]).isEqualTo(7);
        assertThat(game.getPlaces()[1]).isEqualTo(4);
        assertThat(game.getPurses()[0]).isEqualTo(1);
        assertThat(game.getPurses()[1]).isEqualTo(0);
        assertThat(game.getInPenaltyBox()[0]).isFalse();
        assertThat(game.getInPenaltyBox()[1]).isTrue();

        // Livai
        assertThat(game.getCurrentPlayer()).isZero();
        game.roll(8);
        result = game.registerCorrectAnswer();
        assertThat(result).isTrue();
        assertThat(game.isGettingOutOfPenaltyBox()).isFalse();
        assertThat(game.getPlaces()[0]).isEqualTo(3);
        assertThat(game.getPlaces()[1]).isEqualTo(4);
        assertThat(game.getPurses()[0]).isEqualTo(2);
        assertThat(game.getPurses()[1]).isEqualTo(0);
        assertThat(game.getInPenaltyBox()[0]).isFalse();
        assertThat(game.getInPenaltyBox()[1]).isTrue();

        // Eren
        assertThat(game.getCurrentPlayer()).isOne();
        game.roll(6);
        assertThat(game.isGettingOutOfPenaltyBox()).isFalse();
        assertThat(game.getPlaces()[0]).isEqualTo(3);
        assertThat(game.getPlaces()[1]).isEqualTo(4);
        assertThat(game.getPurses()[0]).isEqualTo(2);
        assertThat(game.getPurses()[1]).isEqualTo(0);
        assertThat(game.getInPenaltyBox()[0]).isFalse();
        assertThat(game.getInPenaltyBox()[1]).isTrue();

        // Eren
        assertThat(game.getCurrentPlayer()).isOne();
        game.roll(2);
        game.registerWrongAnswer();
        assertThat(result).isTrue();
        assertThat(game.isGettingOutOfPenaltyBox()).isFalse();
        assertThat(game.getPlaces()[0]).isEqualTo(3);
        assertThat(game.getPlaces()[1]).isEqualTo(4);
        assertThat(game.getPurses()[0]).isEqualTo(2);
        assertThat(game.getPurses()[1]).isEqualTo(0);
        assertThat(game.getInPenaltyBox()[0]).isFalse();
        assertThat(game.getInPenaltyBox()[1]).isTrue();

        // Livai
        assertThat(game.getCurrentPlayer()).isZero();
        game.roll(6);
        game.registerWrongAnswer();
        assertThat(result).isTrue();
        assertThat(game.isGettingOutOfPenaltyBox()).isFalse();
        assertThat(game.getPlaces()[0]).isEqualTo(9);
        assertThat(game.getPlaces()[1]).isEqualTo(4);
        assertThat(game.getPurses()[0]).isEqualTo(2);
        assertThat(game.getPurses()[1]).isEqualTo(0);
        assertThat(game.getInPenaltyBox()[0]).isTrue();
        assertThat(game.getInPenaltyBox()[1]).isTrue();

        // Eren
        assertThat(game.getCurrentPlayer()).isOne();
        game.roll(1);
        result = game.registerCorrectAnswer();
        assertThat(result).isTrue();
        assertThat(game.isGettingOutOfPenaltyBox()).isTrue();
        assertThat(game.getPlaces()[0]).isEqualTo(9);
        assertThat(game.getPlaces()[1]).isEqualTo(5);
        assertThat(game.getPurses()[0]).isEqualTo(2);
        assertThat(game.getPurses()[1]).isEqualTo(1);
        assertThat(game.getInPenaltyBox()[0]).isTrue();
        assertThat(game.getInPenaltyBox()[1]).isTrue();
    }
}
