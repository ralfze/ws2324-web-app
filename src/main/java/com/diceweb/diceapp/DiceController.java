package com.diceweb.diceapp;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;

@RestController
public class DiceController{
    // mongoDB Repository
    @Autowired
	private DiceRepository diceRepository;

    @WithSpan
	@GetMapping("/dices")
	public ResponseEntity<List<Dice>> getAllDices() {
		List<Dice> diceList = (List<Dice>) diceRepository.findAll();
        if (!diceList.isEmpty()) {
            return ResponseEntity.ok(diceList);
        }
        return ResponseEntity.notFound().build();
	}

    /* CRUD Dice Methods */
    @WithSpan
	@GetMapping("/dices/{id}")
	public ResponseEntity<Dice> getDiceById(@PathVariable Long id) {
        Dice diceItem = diceRepository.findById(id).orElse(null);
        if (diceItem != null) {
            return ResponseEntity.ok(diceItem);
        }
        return ResponseEntity.notFound().build();
	}

    @PostMapping("/dices/{message}")
	public ResponseEntity<Dice> CreateDice(@PathVariable String message){
		Dice item = new Dice();
		item.setMessage(message);
		diceRepository.save(item);
		return ResponseEntity.status(HttpStatus.CREATED).body(item);
	}

    @PutMapping("/dices/{id}/{message}")
	ResponseEntity<Dice> UpdateDice(@PathVariable Long id, @PathVariable String message){
        Dice existingItem = diceRepository.findById(id).orElse(null);
        if (existingItem != null) {
            existingItem.setMessage(message);
            diceRepository.save(existingItem);
            return ResponseEntity.status(HttpStatus.OK).body(existingItem);
        }
        return ResponseEntity.notFound().build();
	}

    @DeleteMapping("/dices/{id}")
	ResponseEntity<Dice> DeleteDice(@PathVariable Long id){
        Dice item = diceRepository.findById(id).orElse(null);
        if (item != null) {
            diceRepository.delete(item);
            return ResponseEntity.status(HttpStatus.OK).body(item);
        }
        return ResponseEntity.notFound().build();
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


}
