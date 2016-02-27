package de.fewi.wcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebsiteChangeCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsiteChangeCheckerApplication.class, args);
	}

	
}
