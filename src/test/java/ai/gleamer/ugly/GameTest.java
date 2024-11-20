package ai.gleamer.ugly;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static ai.gleamer.ugly.QuestionCategory.*;
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
            game.roll(7);
            // then
            assertThat(game.getPlayerCursor()).isEqualTo(0);
            assertThat(game.getPlayer(0).getQuestionCategory()).isEqualTo(ROCK);
            assertThat(game.isGettingOutOfPenaltyBox()).isFalse();
        }

        @Test
        void shouldAskQuestionWhenTheCurrentPlayerIsInPenaltyBoxAndTheRollNumberIsOdd() {
            // given
            var game = new Game();
            game.addNewPlayer("Player 1");
            game.addNewPlayer("Player 2");
            game.getPlayer(0).setInPenaltyBox(true);
            // when
            game.roll(5);
            // then
            assertThat(game.isGettingOutOfPenaltyBox()).isEqualTo(true);
            assertThat(game.getPlayer(0).getQuestionCategory()).isEqualTo(SCIENCE);
            assertThat(game.getScienceQuestions()).hasSize(49);
        }

        @Test
        void shouldNotAskQuestionWhenTheCurrentPlayerIsInPenaltyBoxAndTheRollNumberIsEven() {
            // given
            var game = new Game();
            game.addNewPlayer("Player 1");
            game.addNewPlayer("Player 2");
            game.getPlayer(0).setInPenaltyBox(true);
            // when
            game.roll(4);
            // then
            assertThat(game.isGettingOutOfPenaltyBox()).isEqualTo(false);
            assertThat(game.getPlayer(0).getQuestionCategory()).isEqualTo(POP);
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
            assertThat(game.getPlayer(0).getCoins()).isEqualTo(1);
            assertThat(game.getPlayerCursor()).isEqualTo(1);
            assertThat(result).isTrue();
        }

        @Test
        void shouldRewardTheCurrentPlayerWhenHeIsInPenaltyBoxAndGettingOutFromIt(){
            // given
            var game = new Game();
            game.addNewPlayer("Player 1");
            game.addNewPlayer("Player 2");
            game.getPlayer(0).setInPenaltyBox(true);
            game.setGettingOutOfPenaltyBox(true);
            // when
            var result = game.registerCorrectAnswer();
            // then
            assertThat(game.getPlayer(0).getCoins()).isEqualTo(1);
            assertThat(game.getPlayerCursor()).isEqualTo(1);
            assertThat(result).isTrue();
        }

        @Test
        void shouldNotRewardTheCurrentPlayerWhenHeIsInPenaltyBoxAndNotGettingOutFromIt(){
            // given
            var game = new Game();
            game.addNewPlayer("Player 1");
            game.addNewPlayer("Player 2");
            game.getPlayer(0).setInPenaltyBox(true);
            game.setGettingOutOfPenaltyBox(false);
            // when
            var result = game.registerCorrectAnswer();
            // then
            assertThat(game.getPlayer(0).getCoins()).isEqualTo(0);
            assertThat(game.getPlayerCursor()).isEqualTo(1);
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
            assertThat(game.getPlayer(0).isInPenaltyBox()).isTrue();
            assertThat(game.getPlayerCursor()).isEqualTo(1);
        }
    }

}