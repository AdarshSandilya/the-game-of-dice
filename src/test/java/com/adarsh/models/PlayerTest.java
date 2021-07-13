package com.adarsh.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void incrementScore_should_add_dice_value_to_the_player_score() {
        Player player = new Player("name");
        assertEquals(0, player.getScore());

        player.setLastRolledDiceValue(DiceValue.FIVE);

        assertEquals(5, player.getScore());
    }

    @Test
    void isEligibleForPenalty_should_return_false_if_player_is_not_eligible_for_the_penalty() {
        Player player = new Player("name");
        assertFalse(player.isEligibleForPenalty());
    }

    @Test
    void isEligibleForPenalty_should_return_true_if_player_got_penalty() {
        Player player = new Player("name");
        player.markThePenalty();
        assertTrue(player.isEligibleForPenalty());
    }

    @Test
    void removePenalty_should_remove_the_penalty_from_the_player() {
        Player player = new Player("name");
        player.markThePenalty();
        assertTrue(player.isEligibleForPenalty());

        player.removePenalty();
        assertFalse(player.isEligibleForPenalty());
    }
}