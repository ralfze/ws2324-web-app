package com.diceweb.diceapp;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dices")
public class Dice {
    @Id
    private long id;

    private int rolledNumber;
    private int sizeOfTheDice;
    private Instant timeOfRoll;
    private String message;

    public Dice(){
        this.timeOfRoll = Instant.now();
        this.sizeOfTheDice = 0;
        this.rolledNumber = 0;
        this.message = "Empty roll";
    }

    public Dice(int sizeOfTheDice, int rolledNumber, String message){
        this.timeOfRoll = Instant.now();
        this.sizeOfTheDice = sizeOfTheDice;
        this.rolledNumber = rolledNumber;
        this.message = message;
    }

    public long getId(){
        return this.id;
    }

    public int getRolledNumber(){
        return this.rolledNumber;
    }

    public int getSizeOfDice(){
        return this.sizeOfTheDice;
    }

    public Instant getTimeOfRoll(){
        return this.timeOfRoll;
    }

    public String getMessage(){
        return this.message;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRolledNumber(int rolledNumber) {
        this.rolledNumber = rolledNumber;
    }

    public int getSizeOfTheDice() {
        return sizeOfTheDice;
    }

    public void setSizeOfTheDice(int sizeOfTheDice) {
        this.sizeOfTheDice = sizeOfTheDice;
    }

    public void setTimeOfRoll(Instant timeOfRoll) {
        this.timeOfRoll = timeOfRoll;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
