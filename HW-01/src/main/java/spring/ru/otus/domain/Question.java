package spring.ru.otus.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Question {
    private int id;
    private String text;
    private List<Answer> answers;

    public Question(int id, String text, List<Answer> answers) {
        this.id = id;
        this.text = text;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question: " +
                text + "\n" +
                "   answers:" + "\n     " +
                answers.stream()
                        .map(Answer::getText)
                .collect(Collectors.joining("\n     "));
    }
}
