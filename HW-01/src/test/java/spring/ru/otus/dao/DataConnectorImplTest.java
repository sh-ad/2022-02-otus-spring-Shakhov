package spring.ru.otus.dao;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.ru.otus.domain.Answer;
import spring.ru.otus.domain.Question;
import spring.ru.otus.service.QuizService;
import spring.ru.otus.service.QuizServiceImpl;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConnectorImpl.class)
public class DataConnectorImplTest extends TestCase {
    private static DataConnectorImpl dataConnector;
    private static QuizService quizService;

    @Before
    public void initialize() {
        dataConnector = new DataConnectorImpl();
        dataConnector.setAnswerFileName("answers_test.csv");
        dataConnector.setQuestionFileName("questions_test.csv");

        quizService = new QuizServiceImpl(dataConnector);
    }

    @Test
    public void testFindQuestions() {
        List<Question> questions = quizService.getQuestion();
        assertEquals("[Question: what?\n" +
                        "   answers:\n" +
                        "     first answer\n" +
                        "     second answer, Question: where?\n" +
                        "   answers:\n" +
                        "     third answer, Question: when?\n" +
                        "   answers:\n" +
                        "     ]",
                questions.toString()
        );
    }

    @Test
    public void testGetAnswers() {
        List<Answer> answers = dataConnector.findAnswers(1);
        assertEquals("[first answer, second answer]", answers.toString());

        answers = dataConnector.findAnswers(2);
        assertEquals("[third answer]", answers.toString());
    }
}