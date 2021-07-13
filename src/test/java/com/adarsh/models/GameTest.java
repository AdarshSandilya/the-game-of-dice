package com.adarsh.models;

import com.adarsh.AppConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayDeque;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameTest {
    @Mock
    LeaderBoard leaderBoard;

    @Test
    void play_should_increment_the_players_point() {
        Player player1 = new Player("Player-1");
        Player player2 = new Player("Player-2");
        Game game = new Game(new ArrayDeque<>(Arrays.asList(player1, player2)), 3, leaderBoard);

        Player currentPlayer = game.getCurrentPlayer();
        int currentPlayerPoints = currentPlayer.getScore();
        game.play(DiceValue.TWO);
        assertEquals(currentPlayerPoints + DiceValue.TWO.getValue(), currentPlayer.getScore());
    }

    @Test
    void play_should_change_the_current_player_if_player_got_other_than_six() {
        Player player1 = new Player("Player-1");
        Player player2 = new Player("Player-2");
        Game game = new Game(new ArrayDeque<>(Arrays.asList(player1, player2)), 3, leaderBoard);

        Player currentPlayer = game.getCurrentPlayer();
        System.out.println(currentPlayer);
        game.play(DiceValue.TWO);
        System.out.println(currentPlayer);

        assertNotEquals(currentPlayer, game.getCurrentPlayer());
    }


    @Test
    void play_should_not_change_the_player_if_player_got_six() {
        Player player1 = new Player("Player-1");
        Player player2 = new Player("Player-2");
        Game game = new Game(new ArrayDeque<>(Arrays.asList(player1, player2)), 13, leaderBoard);

        Player currentPlayer = game.getCurrentPlayer();
        game.play(DiceValue.SIX);
        assertEquals(game.getCurrentPlayer(), currentPlayer);
    }

    @Test
    void play_should_mark_penalty_if_player_got_consecutive_ones() {
        Player player1 = new Player("Player-1");
        Player player2 = new Player("Player-2");
        Game game = new Game(new ArrayDeque<>(Arrays.asList(player1, player2)), 3, leaderBoard);

        Player currentPlayer = game.getCurrentPlayer();
        currentPlayer.setLastRolledDiceValue(DiceValue.ONE);

        String message = game.play(DiceValue.ONE);
        assertEquals(AppConstants.Message.CONSECUTIVE_ONE_DICE_VALUE, message);
    }

    @Test
    void play_should_inform_leaderboard_if_player_has_completed_the_game() {
        Player player1 = new Player("Player-1");
        Player player2 = new Player("Player-2");
        Game game = new Game(new ArrayDeque<>(Arrays.asList(player1, player2)), 3, leaderBoard);

        Player currentPlayer = game.getCurrentPlayer();

        when(leaderBoard.getRankFor(currentPlayer)).thenReturn(1);

        String message = game.play(DiceValue.FIVE);

        verify(leaderBoard, times(1)).markCompletedFor(currentPlayer);
        assertEquals(currentPlayer.getName() + " you have completed the game with the rank 1", message);
    }

    @Test
    void hasGameEnded_should_return_true_if_game_has_completed() {
        Player player1 = new Player("Player-1");
        Player player2 = new Player("Player-2");
        Game game = new Game(new ArrayDeque<>(Arrays.asList(player1, player2)), 3, leaderBoard);

        game.play(DiceValue.FIVE);
        assertFalse(game.hasGameCompleted());

        game.play(DiceValue.SIX);
        assertTrue(game.hasGameCompleted());
    }
}