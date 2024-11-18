package ai.gleamer.ugly;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    @Nested
    class Roll {
        @Test
        void shouldAskQuestionWhenTheCurrentPlayerIsNotInPenaltyBox() {
            // given
            var game = new Game();
            game.addNewPlayer("Player 1");
            game.addNewPlayer("Player 2");
            // when
            game.roll(5);
            // then
            assertThat(game.getCurrentPlayer()).isEqualTo(0);
            assertThat(game.getPlaces()[0]).isEqualTo(5);
            assertThat(game.isGettingOutOfPenaltyBox()).isFalse();
        }

        @Test
        void shouldAskQuestionWhenTheCurrentPlayerIsInPenaltyBoxAndTheRollNumberIsOdd() {
            // given
            var game = new Game();
            game.addNewPlayer("Player 1");
            game.addNewPlayer("Player 2");
            game.getInPenaltyBox()[0] = true;
            // when
            game.roll(5);
            // then
            assertThat(game.isGettingOutOfPenaltyBox()).isEqualTo(true);
            assertThat(game.getPlaces()[0]).isEqualTo(5);
            assertThat(game.getScienceQuestions()).hasSize(49);
        }

        @Test
        void shouldNotAskQuestionWhenTheCurrentPlayerIsInPenaltyBoxAndTheRollNumberIsEven() {
            // given
            var game = new Game();
            game.addNewPlayer("Player 1");
            game.addNewPlayer("Player 2");
            game.getInPenaltyBox()[0] = true;
            // when
            game.roll(4);
            // then
            assertThat(game.isGettingOutOfPenaltyBox()).isEqualTo(false);
            assertThat(game.getPlaces()[0]).isEqualTo(0);
        }

    }

    @Nested
    class RegisterCorrectAnswer {

        @Test
        void shouldRewardTheCurrentPlayerWhenHeIsNotInPenaltyBox(){
            // given
            var game = new Game();
            game.addNewPlayer("Player 1");
            game.addNewPlayer("Player 2");
            // when
            var result = game.registerCorrectAnswer();
            // then
            assertThat(game.getPurses()[0]).isEqualTo(1);
            assertThat(game.getCurrentPlayer()).isEqualTo(1);
            assertThat(result).isTrue();
        }

        @Test
        void shouldRewardTheCurrentPlayerWhenHeIsInPenaltyBoxAndGettingOutFromIt(){
            // given
            var game = new Game();
            game.addNewPlayer("Player 1");
            game.addNewPlayer("Player 2");
            game.getInPenaltyBox()[0] = true;
            game.setGettingOutOfPenaltyBox(true);
            // when
            var result = game.registerCorrectAnswer();
            // then
            assertThat(game.getPurses()[0]).isEqualTo(1);
            assertThat(game.getCurrentPlayer()).isEqualTo(1);
            assertThat(result).isTrue();
        }

        @Test
        void shouldNotRewardTheCurrentPlayerWhenHeIsInPenaltyBoxAndNotGettingOutFromIt(){
            // given
            var game = new Game();
            game.addNewPlayer("Player 1");
            game.addNewPlayer("Player 2");
            game.getInPenaltyBox()[0] = true;
            game.setGettingOutOfPenaltyBox(false);
            // when
            var result = game.registerCorrectAnswer();
            // then
            assertThat(game.getPurses()[0]).isEqualTo(0);
            assertThat(game.getCurrentPlayer()).isEqualTo(1);
            assertThat(result).isTrue();
        }
    }

    @Nested
    class RegisterWrongAnswer {

        @Test
        void shouldMoveTheCurrentPlayerInPenaltyBoxAndSwitchToNext() {
            // given
            var game = new Game();
            game.addNewPlayer("Player 1");
            game.addNewPlayer("Player 2");
            // when
            game.registerWrongAnswer();
            // then
            assertThat(game.getInPenaltyBox()[0]).isTrue();
            assertThat(game.getCurrentPlayer()).isEqualTo(1);
        }
    }

}