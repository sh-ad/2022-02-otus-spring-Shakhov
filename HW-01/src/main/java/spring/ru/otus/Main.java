package spring.ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.ru.otus.config.AppConfig;
import spring.ru.otus.domain.Question;
import spring.ru.otus.service.QuizService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        QuizService service = context.getBean(QuizService.class);
        List<Question> questions = service.getQuestion();
        questions.forEach(q -> logger.debug(q.toString()));

        // Данная операция, в принципе не нужна.
        // Мы не работаем пока что с БД, а Spring Boot сделает закрытие за нас
        // Подробности - через пару занятий
        context.close();
    }
}
