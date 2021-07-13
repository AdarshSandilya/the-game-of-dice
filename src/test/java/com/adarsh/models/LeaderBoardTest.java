package com.adarsh.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LeaderBoardTest {

    @Test
    void getRankFor_should_return_rank_for_the_given_player() {
        Player player1 = new Player("Player-1");
        Player player2 = new Player("Player-2");
        LeaderBoard board = new LeaderBoard(new ArrayList<>(Arrays.asList(player1, player2)));

        board.markCompletedFor(player2);
        board.markCompletedFor(player1);

        assertEquals(1, board.getRankFor(player2));
        assertEquals(2, board.getRankFor(player1));
    }
}