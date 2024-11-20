package ai.gleamer.ugly;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameIntTest {

    @Test
    void testGame() {
        var game = new Game();
        assertThat(game.isPlayable()).isFalse();

        var livaiAdded = game.add("Livai");
        assertThat(livaiAdded).isTrue();
        assertThat(game.isPlayable()).isFalse();

        var added = game.add("Eren");
        assertThat(added).isTrue();
        assertThat(game.isPlayable()).isTrue();

        // Livai
        assertThat(game.currentPlayer).isZero();
        game.roll(7);
        assertThat(game.isGettingOutOfPenaltyBox).isFalse();
        var result = game.wasCorrectlyAnswered();
        assertThat(result).isTrue();
        assertThat(game.places[0]).isEqualTo(7);
        assertThat(game.places[1]).isEqualTo(0);
        assertThat(game.purses[0]).isEqualTo(1);
        assertThat(game.purses[1]).isEqualTo(0);
        assertThat(game.inPenaltyBox[0]).isFalse();
        assertThat(game.inPenaltyBox[1]).isFalse();

        // Eren
        assertThat(game.currentPlayer).isOne();
        game.roll(4);
        result = game.wrongAnswer();
        assertThat(result).isTrue();
        assertThat(game.isGettingOutOfPenaltyBox).isFalse();
        assertThat(game.places[0]).isEqualTo(7);
        assertThat(game.places[1]).isEqualTo(4);
        assertThat(game.purses[0]).isEqualTo(1);
        assertThat(game.purses[1]).isEqualTo(0);
        assertThat(game.inPenaltyBox[0]).isFalse();
        assertThat(game.inPenaltyBox[1]).isTrue();

        // Livai
        assertThat(game.currentPlayer).isZero();
        game.roll(8);
        result = game.wasCorrectlyAnswered();
        assertThat(result).isTrue();
        assertThat(game.isGettingOutOfPenaltyBox).isFalse();
        assertThat(game.places[0]).isEqualTo(3);
        assertThat(game.places[1]).isEqualTo(4);
        assertThat(game.purses[0]).isEqualTo(2);
        assertThat(game.purses[1]).isEqualTo(0);
        assertThat(game.inPenaltyBox[0]).isFalse();
        assertThat(game.inPenaltyBox[1]).isTrue();

        // Eren
        assertThat(game.currentPlayer).isOne();
        game.roll(6);
        assertThat(game.isGettingOutOfPenaltyBox).isFalse();
        assertThat(game.places[0]).isEqualTo(3);
        assertThat(game.places[1]).isEqualTo(4);
        assertThat(game.purses[0]).isEqualTo(2);
        assertThat(game.purses[1]).isEqualTo(0);
        assertThat(game.inPenaltyBox[0]).isFalse();
        assertThat(game.inPenaltyBox[1]).isTrue();

        // Eren
        assertThat(game.currentPlayer).isOne();
        game.roll(2);
        result = game.wrongAnswer();
        assertThat(result).isTrue();
        assertThat(game.isGettingOutOfPenaltyBox).isFalse();
        assertThat(game.places[0]).isEqualTo(3);
        assertThat(game.places[1]).isEqualTo(4);
        assertThat(game.purses[0]).isEqualTo(2);
        assertThat(game.purses[1]).isEqualTo(0);
        assertThat(game.inPenaltyBox[0]).isFalse();
        assertThat(game.inPenaltyBox[1]).isTrue();

        // Livai
        assertThat(game.currentPlayer).isZero();
        game.roll(6);
        result = game.wrongAnswer();
        assertThat(result).isTrue();
        assertThat(game.isGettingOutOfPenaltyBox).isFalse();
        assertThat(game.places[0]).isEqualTo(9);
        assertThat(game.places[1]).isEqualTo(4);
        assertThat(game.purses[0]).isEqualTo(2);
        assertThat(game.purses[1]).isEqualTo(0);
        assertThat(game.inPenaltyBox[0]).isTrue();
        assertThat(game.inPenaltyBox[1]).isTrue();

        // Eren
        assertThat(game.currentPlayer).isOne();
        game.roll(1);
        result = game.wasCorrectlyAnswered();
        assertThat(result).isTrue();
        assertThat(game.isGettingOutOfPenaltyBox).isTrue();
        assertThat(game.places[0]).isEqualTo(9);
        assertThat(game.places[1]).isEqualTo(5);
        assertThat(game.purses[0]).isEqualTo(2);
        assertThat(game.purses[1]).isEqualTo(1);
        assertThat(game.inPenaltyBox[0]).isTrue();
        assertThat(game.inPenaltyBox[1]).isTrue();
    }
}