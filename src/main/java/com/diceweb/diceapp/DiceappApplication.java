package com.diceweb.diceapp;

// import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;

@SpringBootApplication
@RestController
@EnableMongoRepositories
public class DiceappApplication {
	@Autowired
	private DiceRepository diceRepository;

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

	/*
	 * @WithSpan
	 * 
	 * @GetMapping("/dices/roll")
	 * public int rollTheDice(@SpanAttribute("size") @RequestParam(required = false,
	 * defaultValue = "6") int size){
	 * Random random = new Random();
	 * String message;
	 * int number = (random.nextInt(size) + 1);
	 * // Decide on a message for the roll
	 * if(number == size){
	 * message = "You rolled the maximum number of " + size + ".";
	 * }else{
	 * message = "You rolled the number " + number + ".";
	 * }
	 * // Create Dice Object
	 * Dice dice = new Dice(size, number, message);
	 * diceList.add(dice);
	 * return number;
	 * }
	 * 
	 * @WithSpan
	 * 
	 * @GetMapping("/dices")
	 * public ArrayList<Dice> getAllRolls(){
	 * return diceList;
	 * }
	 * 
	 * @WithSpan
	 * 
	 * @GetMapping("/dices/{id}")
	 * public Dice getRollforId(@SpanAttribute("id") @PathVariable int id){
	 * Dice returnableDice = null;
	 * for(Dice dice:diceList){
	 * if(dice.getId() == id){
	 * returnableDice = dice;
	 * }
	 * }
	 * return returnableDice;
	 * }
	 */
	@WithSpan
	@GetMapping
	public int rollTheDice(@SpanAttribute("size") @RequestParam(required = false, defaultValue = "6") int size) {
		Random random = new Random();
		String message;
		int number = (random.nextInt(size) + 1);
		// Decide on a message for the roll
		if (number == size) {
			message = "You rolled the maximum number of " + size + ".";
		} else {
			message = "You rolled the number " + number + ".";
		}
		// Create Dice Object
		Dice dice = new Dice(size, number, message);
		// diceList.add(dice);
		diceRepository.save(dice);
		return number;
	}

	@WithSpan
	@GetMapping("/dices")
	public List<Dice> getAllRolls() {
		return (List<Dice>) diceRepository.findAll();
	}
}
