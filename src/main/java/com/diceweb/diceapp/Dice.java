package com.diceweb.diceapp;

import java.time.Instant;
import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dices")
public class Dice {
    @Id
    private String id;

    private int rolledNumber;
    private int sizeOfTheDice;
    private Instant created;
    private Instant modified;
    private String message;

    public Dice() {
        this.created = Instant.now();
        this.modified = this.created;
        this.sizeOfTheDice = 0;
        this.rolledNumber = 0;
        this.message = "Empty roll";
    }

    public Dice(int sizeOfTheDice) {
        this.created = Instant.now();
        this.modified = this.created;
        this.sizeOfTheDice = sizeOfTheDice;
        this.rolledNumber = rollTheDice(sizeOfTheDice);
        this.message = createMessage(this.rolledNumber, sizeOfTheDice);
    }

    private int rollTheDice(int sizeOfTheDice) {
        return (new Random().nextInt(sizeOfTheDice) + 1);
    }

    private String createMessage(int rolledNumber, int sizeOfTheDice) {
        String msg = "No roll happened.";
        // Decide on a message for the roll
        if (rolledNumber == sizeOfTheDice) {
            msg = "You rolled the maximum number of " + sizeOfTheDice + ".";
        } else {
            msg = "You rolled the number " + rolledNumber + ".";
        }
        return msg;
    }

    public void updateTime(){
        this.modified = Instant.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRolledNumber() {
        return rolledNumber;
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

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
