package com.diceweb.diceapp;

import java.util.List;

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
public class DiceController {
	// mongoDB Repository
	@Autowired
	private DiceRepository diceRepository;

	@WithSpan
	@GetMapping("/dices")
	public ResponseEntity<List<Dice>> getAllDices() {
		List<Dice> diceList = diceRepository.findAll();
		if (!diceList.isEmpty()) {
			return ResponseEntity.ok(diceList);
		}
		return ResponseEntity.notFound().build();
	}

	/* CRUD Dice Methods */
	@WithSpan
	@GetMapping("/dices/{id}")
	public ResponseEntity<Dice> getDiceById(@PathVariable String id) {
		Dice diceItem = diceRepository.findById(id).orElse(null);
		if (diceItem != null) {
			return ResponseEntity.ok(diceItem);
		}
		return ResponseEntity.notFound().build();
	}

	@WithSpan
	@PostMapping("/dices")
	public ResponseEntity<Dice> CreateDice(
			@SpanAttribute("size") @RequestParam(required = false, defaultValue = "6") int size) {
		Dice item = new Dice(size);
		diceRepository.save(item);
		return ResponseEntity.status(HttpStatus.CREATED).body(item);
	}

	/*
	 * Create a custom message for a dice with id
	 */
	@WithSpan
	@PutMapping("/dices/{id}")
	ResponseEntity<Dice> UpdateDice(@PathVariable String id,
			@SpanAttribute("message") @RequestParam(required = false) String message) {
		Dice existingItem = diceRepository.findById(id).orElse(null);
		if (existingItem != null) {
			existingItem.setMessage(message);
			existingItem.updateTime();
			diceRepository.save(existingItem);
			return ResponseEntity.status(HttpStatus.OK).body(existingItem);
		}
		return ResponseEntity.notFound().build();
	}

	/*
	 * Create a path to reroll dices
	 */
	@WithSpan
	@PutMapping("/dices/{id}/reroll")
	ResponseEntity<Dice> UpdateDice(@PathVariable String id) {
		Dice existingItem = diceRepository.findById(id).orElse(null);
		if (existingItem != null) {
			existingItem.reroll();
			existingItem.updateTime();
			diceRepository.save(existingItem);
			return ResponseEntity.status(HttpStatus.OK).body(existingItem);
		}
		return ResponseEntity.notFound().build();
	}

	@WithSpan
	@DeleteMapping("/dices/{id}")
	ResponseEntity<Dice> DeleteDice(@PathVariable String id) {
		Dice item = diceRepository.findById(id).orElse(null);
		if (item != null) {
			diceRepository.delete(item);
			return ResponseEntity.status(HttpStatus.OK).body(item);
		}
		return ResponseEntity.notFound().build();
	}
}
