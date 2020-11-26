package by.kashlyak.bot.traineeTGBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class TraineeTgBotApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(TraineeTgBotApplication.class, args);
	}

}
