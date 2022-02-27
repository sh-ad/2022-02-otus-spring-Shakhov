package spring.ru.otus.domain;

public class Answer {
    private int id;
    private int question_id;
    private String text;

    public Answer(int id, int question_id, String text) {
        this.id = id;
        this.question_id = question_id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
