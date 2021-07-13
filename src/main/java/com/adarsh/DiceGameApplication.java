package com.adarsh;

import com.adarsh.models.*;

import java.util.*;

public class DiceGameApplication {
    public static void main(String[] args) {
        int noOfPlayers  = Integer.parseInt(args[0]);
        int winningPoint  = Integer.parseInt(args[1]);
        ArrayDeque<Player> players = createPlayers(noOfPlayers);

        LeaderBoard leaderBoard = new LeaderBoard(new ArrayList<>(players));
        Game game = new Game(players, winningPoint, leaderBoard);
        String userInput;
        Scanner in = new Scanner(System.in);
        System.out.println("Press r and enter to roll the dice");

        while(!game.hasGameCompleted()){
            System.out.printf("%s its your turn %s", game.getCurrentPlayer().getName(), System.lineSeparator());

            userInput = in.next();

            if ("r".equals(userInput)) {
                DiceValue rolledDice = new Dice().roll();
                System.out.printf("you have got dice value %s %s", rolledDice.getValue(), System.lineSeparator());

                String message = game.play(rolledDice);
                String theCurrentRankTable = leaderBoard.getTheCurrentRankTable();
                System.out.println("Current Point Table ----" + System.lineSeparator());
                System.out.println(theCurrentRankTable);
                if (!message.isEmpty())
                    System.out.println(message);

                if(game.hasGameCompleted())
                    System.out.println(AppConstants.Message.GAME_HAS_COMPLETED);
            } else {
                System.out.println("Invalid choice. Read the options carefully...");
            }
        }
    }

    private static ArrayDeque<Player> createPlayers(int noOfPlayers) {
        ArrayDeque<Player> players = new ArrayDeque<>();
        for (int i = 1; i <= noOfPlayers ; i++) {
            String playerName = "Player-" + i;
            Player player = new Player(playerName);
            players.add(player);
        }
        return players;
    }
}
