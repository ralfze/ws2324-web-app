package com.diceweb.diceapp;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dices")
public class Dice {
    @Id
    private Long id;

    private int rolledNumber;
    private int sizeOfTheDice;
    private Instant created;
    private Instant modified;
    private String message;

    public Dice(){
        this.created = Instant.now();
        this.modified = this.created;
        this.sizeOfTheDice = 0;
        this.rolledNumber = 0;
        this.message = "Empty roll";
    }

    public Dice(int sizeOfTheDice, int rolledNumber, String message){
        this.created = Instant.now();
        this.modified = this.created;
        this.sizeOfTheDice = sizeOfTheDice;
        this.rolledNumber = rolledNumber;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
