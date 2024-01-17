package com.fanzengau.holdem;

import org.junit.jupiter.api.Test;

import static com.fanzengau.holdem.Holdem.BEST_HAND_TYPE.STRAIGHT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.fanzengau.holdem.Holdem.BEST_HAND_TYPE.FLUSH;

class ShowDownTest {

    @Test
    void getShowDownResultFlush() {
        var showDown = new ShowDown();
        var player = new Player(0);
        player.privateCard = new String[]{"4H", "KH"};
        var board = new String[]{"2H", "TH", "5H", "4C", "7C"};
        var expectedBestHandType = FLUSH;
        var expectedKickers = new Card[]{
            new Card("KH"), new Card("TH"),
            new Card("5H"), new Card("4H"), new Card("3H")
        };
        var showDownResult = showDown.getShowDownResult(player, board);
        assertEquals(expectedBestHandType, showDownResult.bestHandType);
        assertEquals(expectedKickers, showDownResult.kickers);
    }

    @Test
    void getShowDownResultStraight() {
        var showDown = new ShowDown();
        var player = new Player(0);
        player.privateCard = new String[]{"AS", "KS"};
        var board = new String[]{"TH", "QC", "JD", "4C", "7C"};
        var expectedBestHandType = STRAIGHT;
        var expectedKickers = new Card[]{
                new Card("AS"), new Card("KS"),
                new Card("QC"), new Card("JD"), new Card("TH")
        };
        var showDownResult = showDown.getShowDownResult(player, board);
        assertEquals(expectedBestHandType, showDownResult.bestHandType);
        assertEquals(expectedKickers, showDownResult.kickers);
    }
}