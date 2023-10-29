package com.diceweb.diceapp;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DiceappApplication {
	private ArrayList<Dice> diceList = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(DiceappApplication.class, args);
	}

	@GetMapping("/dice")
	public int rollTheDice(@RequestParam(required = false, defaultValue = "6") int size){
		Random random = new Random();
		String message;
		int number = (random.nextInt(size) + 1);
		// Decide on a message for the roll
		if(number == size){
			message = "You rolled the maximum number of " + size + ".";
		}else{
			message = "You rolled the number " + number + ".";
		}
		// Create Dice Object
		Dice dice = new Dice(size, number, message);
		diceList.add(dice);
		return number;
	}

	@GetMapping("/dice/list")
	public ArrayList<Dice> getAllRolls(){
		return diceList;
	}

	@GetMapping("/dice/list/{id}")
	public Dice getRollforId(@PathVariable int id){
		Dice returnableDice = null;
		for(Dice dice:diceList){
			if(dice.getId() == id){
				returnableDice = dice;
			}
		}
		return returnableDice;
	}
}
