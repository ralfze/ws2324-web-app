package com.diceweb.diceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.opentelemetry.instrumentation.annotations.WithSpan;

@SpringBootApplication
@RestController
@EnableMongoRepositories
public class DiceappApplication {

	// private ArrayList<Dice> diceList = new ArrayList<>();
	public static void main(String[] args) {
		SpringApplication.run(DiceappApplication.class, args);
	}

	@WithSpan
	@GetMapping("/")
	public String welcome() {
		String msg = "Welcome on the dice-web-app.";
		return msg;
	}

}
