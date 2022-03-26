package spring.ru.otus.service;

import spring.ru.otus.dao.DataConnector;
import spring.ru.otus.domain.Question;

import java.util.List;

public class QuizServiceImpl implements QuizService {
    private final DataConnector dao;

    public QuizServiceImpl(DataConnector dao) {
        this.dao = dao;
    }

    public List<Question> getQuestion() {
        return dao.findQuestions();
    }
}
