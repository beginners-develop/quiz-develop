package bku.beginners.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class BeginnersTeamDevelopApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeginnersTeamDevelopApplication.class, args);
    }

}
