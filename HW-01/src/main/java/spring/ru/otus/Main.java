package spring.ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.ru.otus.domain.Question;
import spring.ru.otus.service.QuizService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuizService service = context.getBean(QuizService.class);
        List<Question> questions = service.getQuestion();
        questions.forEach(System.out::println);

        // Данная операция, в принципе не нужна.
        // Мы не работаем пока что с БД, а Spring Boot сделает закрытие за нас
        // Подробности - через пару занятий
        context.close();
    }
}
