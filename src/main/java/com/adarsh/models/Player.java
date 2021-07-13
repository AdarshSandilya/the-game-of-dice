package com.adarsh.models;

import java.util.Objects;

public class Player {

    private DiceValue lastRolledDiceValue;
    private int score;
    private final String name;
    private boolean isPenaltyApplicable = false;

    public Player(String name) {
        this.name = name;
        this.score = 0;

    }

    private void incrementScore() {
        this.score +=  lastRolledDiceValue.getValue();
    }

    public DiceValue lastRolledDiceValue() {
        return lastRolledDiceValue;
    }

    public void setLastRolledDiceValue(DiceValue lastRolledDiceValue) {
        this.lastRolledDiceValue = lastRolledDiceValue;
        incrementScore();
    }

    public void markThePenalty() {
        this.isPenaltyApplicable = true;
    }

    public boolean isEligibleForPenalty() {
        return isPenaltyApplicable;
    }

    public void removePenalty() {
        this.isPenaltyApplicable = false;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return score == player.score && isPenaltyApplicable == player.isPenaltyApplicable && lastRolledDiceValue == player.lastRolledDiceValue && Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastRolledDiceValue, score, name, isPenaltyApplicable);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }
}
