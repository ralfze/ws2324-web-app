package com.diceweb.diceapp;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiceRepository extends MongoRepository<Dice, String>{

}