package com.adarsh.models;

import java.util.*;

public class LeaderBoard {
    private final ArrayList<Player> playersCompletedTheGame;
    private final ArrayList<Player> players;

    public LeaderBoard(ArrayList<Player> players) {
        this.players = players;
        playersCompletedTheGame = new ArrayList<>(players.size());
    }

    public void markCompletedFor(Player player) {
        playersCompletedTheGame.add(player);
        players.remove(player);
    }

    public String getTheCurrentRankTable(){
        players.sort(Comparator.comparingInt(Player::getScore));
        int counter=1;
        StringBuilder rankTable = new StringBuilder("Player  Points  Rank").append(System.lineSeparator());
        for (Player player: playersCompletedTheGame) {
            rankTable.append(player.getName()).append("  ").append(player.getScore()).append("  ").append(counter).append(System.lineSeparator());
            counter++;
        }
        for (int i = players.size()-1; i >=0 ; i--) {
            Player player = players.get(i);
            rankTable.append(player.getName()).append("  ").append(player.getScore()).append("  ").append(counter).append(System.lineSeparator());;
        }
        return rankTable.toString();
    }

    public int getRankFor(Player player) {
        return 1 + playersCompletedTheGame.indexOf(player);
    }
}
