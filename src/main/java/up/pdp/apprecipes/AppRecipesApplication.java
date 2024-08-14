package up.pdp.apprecipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class AppRecipesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppRecipesApplication.class, args);
    }

    @Bean
    public Random random() {
        return new Random();
    }
}
