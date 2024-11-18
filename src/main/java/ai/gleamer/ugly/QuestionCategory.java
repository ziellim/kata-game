package ai.gleamer.ugly;

public enum QuestionCategory {
    POP("Pop"), SCIENCE("Science"), SPORT("Sports"), ROCK("Rock");

    private final String value;

    QuestionCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
