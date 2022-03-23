package spring.ru.otus.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import spring.ru.otus.domain.Answer;
import spring.ru.otus.domain.Question;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class DataConnectorImpl implements DataConnector {
    @Value("${app.splitter}")
    private String SPLITTER;
    @Value("${app.lineSeparator}")
    private String LINE_SEPARATOR;
    @Value("${app.questionFileName}")
    private String questionFileName;
    @Value("${app.answerFileName}")
    private String answerFileName;

    @Override
    public List<Question> findQuestions() {
        List<Question> questions = new ArrayList<>();
        Resource resource = new ClassPathResource(questionFileName);
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            String csv = FileCopyUtils.copyToString(reader);
            for (String questionLine : csv.split(LINE_SEPARATOR)) {
                questions.add(stringToQuestion(questionLine));
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return questions;
    }

    private Question stringToQuestion(String line) {
        String[] fields = line.split(SPLITTER);
        return new Question(Integer.parseInt(fields[0]), fields[1], findAnswers(Integer.parseInt(fields[0])));
    }

    @Override
    public List<Answer> findAnswers(int questionId) {
        List<Answer> answers = new ArrayList<>();
        Resource resource = new ClassPathResource(answerFileName);
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            String csv = FileCopyUtils.copyToString(reader);
            for (String answerLine : csv.split(LINE_SEPARATOR)) {
                answers.add(stringToAnswer(answerLine));
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return answers.stream()
                .filter(f -> questionId == f.getQuestionId())
                .collect(Collectors.toList());
    }

    private Answer stringToAnswer(String line) {
        String[] fields = line.split(SPLITTER);
        return new Answer(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2]);
    }

    public void setQuestionFileName(String questionFileName) {
        this.questionFileName = questionFileName;
    }

    public void setAnswerFileName(String answerFileName) {
        this.answerFileName = answerFileName;
    }
}
