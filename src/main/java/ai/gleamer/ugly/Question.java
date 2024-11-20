package ai.gleamer.ugly;

public class Question {

    private final String value;

    private final QuestionCategory category;

    public Question(String value, QuestionCategory category) {
        this.value = value;
        this.category = category;
    }

    public String getValue() {
        return value;
    }

    public QuestionCategory getCategory() {
        return category;
    }
}
