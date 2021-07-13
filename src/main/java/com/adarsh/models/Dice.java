package com.adarsh.models;

import java.util.Random;

public class Dice {
    public DiceValue roll(){
        return DiceValue.values()[new Random().nextInt(DiceValue.values().length)];
    }
}
