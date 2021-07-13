package com.adarsh.models;

import com.adarsh.AppConstants;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private final LeaderBoard leaderBord;
    private final ArrayDeque<Player> players;
    private final int winningPoint;
    private Player currentPlayer;

    public Game(ArrayDeque<Player> players, int winningPoint, LeaderBoard leaderBord) {
        this.players = players;
        this.winningPoint = winningPoint;
        this.leaderBord = leaderBord;
        assignRandomCurrentPlayer();
    }

    private void assignRandomCurrentPlayer() {
        int index = new Random().nextInt(players.size());
        this.currentPlayer = new ArrayList<>(players).get(index);
        players.remove(this.currentPlayer);
        players.add(this.currentPlayer);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public String play(DiceValue rolledDiceValue){
        String message="";
        if(eligibleForPenalty(rolledDiceValue)) {
            currentPlayer.markThePenalty();
            message = AppConstants.Message.CONSECUTIVE_ONE_DICE_VALUE;
        }
        else if(rolledDiceValue.equals(DiceValue.SIX))
             message = AppConstants.Message.DICE_VALUE_SIX;
        currentPlayer.setLastRolledDiceValue(rolledDiceValue);
        if(hasPlayerCompletedTheGame()){
            players.removeLast();
            leaderBord.markCompletedFor(currentPlayer);
            int rank = leaderBord.getRankFor(currentPlayer);
            message =  currentPlayer.getName() + " you have completed the game with the rank "+ rank ;
        }
        setCurrentPlayer();
        return message;
    }

    public boolean hasGameCompleted() {
        return players.isEmpty();
    }

    private boolean hasPlayerCompletedTheGame() {
        return currentPlayer.getScore() >= winningPoint;
    }

    private void setCurrentPlayer() {
        if(hasGameCompleted())
            return;
        if(!hasPlayerCompletedTheGame() && currentPlayer.lastRolledDiceValue().equals(DiceValue.SIX))
            return;
        Player player = players.poll();
        players.add(player);
        if(player.isEligibleForPenalty()){
            player.removePenalty();
            setCurrentPlayer();
        }else
            this.currentPlayer = player;
    }

    private boolean eligibleForPenalty(DiceValue rolledDice) {
        if(currentPlayer.lastRolledDiceValue() == null)
            return false;
        System.out.println();
        return DiceValue.ONE.equals(rolledDice) && currentPlayer.lastRolledDiceValue().equals(DiceValue.ONE);
    }
}
