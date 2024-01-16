package com.fanzengau.holdem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.fanzengau.holdem.Holdem.BEST_HAND_TYPE.FLUSH;

class ShowDownTest {

    @Test
    void getShowDownResult() {
        var showDown = new ShowDown();
        var player = new Player(0);
        player.privateCard = new String[]{"4H", "7H"};
        var board = new String[]{"2H", "3H", "5H", "4C", "7C"};
        var showDownResult = showDown.getShowDownResult(player, board);
        assertEquals(FLUSH, showDownResult.bestHandType);
    }
}