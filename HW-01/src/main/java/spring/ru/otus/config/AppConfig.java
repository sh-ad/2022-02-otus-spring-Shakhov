package spring.ru.otus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = "spring.ru.otus")
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
