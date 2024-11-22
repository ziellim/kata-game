package ai.gleamer.ugly;

import java.util.LinkedList;
import java.util.List;

import static ai.gleamer.ugly.QuestionCategory.*;
import static ai.gleamer.ugly.QuestionCategory.ROCK;

public class QuestionInventory {

    private final List<Question> questions;

    public QuestionInventory(){
        questions = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            questions.addLast(new Question("Pop Question " + i, POP));
            questions.addLast(new Question("Science Question " + i, SCIENCE));
            questions.addLast(new Question("Sports Question " + i, SPORT));
            questions.addLast(new Question("Rock Question " + i, ROCK));
        }
    }

    public Question get(int index) {
        //
        return questions.remove(index);
    }

    // only for test
    public long countQuestions(QuestionCategory category) {
        return questions.stream().filter(question -> category == question.getCategory()).count();
    }
}
