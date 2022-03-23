package spring.ru.otus.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import spring.ru.otus.dao.DataConnector;
import spring.ru.otus.domain.Answer;
import spring.ru.otus.domain.Question;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class QuizServiceImpl implements QuizService {
    Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);
    private final DataConnector dao;

    public QuizServiceImpl(DataConnector dao) {
        this.dao = dao;
    }

    public List<Question> getQuestion() {
        return dao.findQuestions();
    }

    public void startQuiz(){
        System.out.println("Enter first and last name:");
        Scanner scanner = new Scanner(System.in);
        String nameSurname = scanner.nextLine();
        logger.debug(nameSurname);

        AtomicInteger score = new AtomicInteger();
        getQuestion().forEach(question -> {
            System.out.println(question.getText());
            String userAnswer = scanner.nextLine();
            List<String> answers = question.getAnswers().stream()
                    .map(Answer::getText)
                    .map(String::toLowerCase)
                    .toList();
            if(answers.contains(userAnswer.toLowerCase(Locale.ROOT))){
                score.getAndIncrement();
            }
        });
        System.out.println(nameSurname + " score:" + score);
    }
}
