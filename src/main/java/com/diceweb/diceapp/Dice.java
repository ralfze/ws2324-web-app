package com.diceweb.diceapp;

import java.time.Instant;

public class Dice {
    private static int counter = 0;
    private int id;
    private int rolledNumber;
    private int sizeOfTheDice;
    private Instant timeOfRoll;
    private String message;

    public Dice(){
        this.id = counter;
        counter++;
        this.timeOfRoll = Instant.now();
        this.sizeOfTheDice = 0;
        this.rolledNumber = 0;
        this.message = "Empty roll";
    }

    public Dice(int sizeOfTheDice, int rolledNumber, String message){
        this.id = counter;
        counter++;
        this.timeOfRoll = Instant.now();
        this.sizeOfTheDice = sizeOfTheDice;
        this.rolledNumber = rolledNumber;
        this.message = message;
    }

    public int getId(){
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
}
