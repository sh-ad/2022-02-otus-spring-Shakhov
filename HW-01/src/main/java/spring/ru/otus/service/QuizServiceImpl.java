package spring.ru.otus.service;

import org.springframework.stereotype.Service;
import spring.ru.otus.dao.DataConnector;
import spring.ru.otus.domain.Question;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    private final DataConnector dao;

    public QuizServiceImpl(DataConnector dao) {
        this.dao = dao;
    }

    public List<Question> getQuestion() {
        return dao.findQuestions();
    }
}
