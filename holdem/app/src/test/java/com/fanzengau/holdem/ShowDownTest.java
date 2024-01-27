package com.fanzengau.holdem;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.fanzengau.holdem.Holdem.BEST_HAND_TYPE.FOUR_OF_A_KIND;
import static com.fanzengau.holdem.Holdem.BEST_HAND_TYPE.FULL_HOUSE;
import static com.fanzengau.holdem.Holdem.BEST_HAND_TYPE.HIGH_CARD;
import static com.fanzengau.holdem.Holdem.BEST_HAND_TYPE.PAIR;
import static com.fanzengau.holdem.Holdem.BEST_HAND_TYPE.ROYAL_FLUSH;
import static com.fanzengau.holdem.Holdem.BEST_HAND_TYPE.STRAIGHT;
import static com.fanzengau.holdem.Holdem.BEST_HAND_TYPE.STRAIGHT_FLUSH;
import static com.fanzengau.holdem.Holdem.BEST_HAND_TYPE.THREE_OF_A_KIND;
import static com.fanzengau.holdem.Holdem.BEST_HAND_TYPE.TWO_PAIR;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.fanzengau.holdem.Holdem.BEST_HAND_TYPE.FLUSH;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShowDownTest {

    void testShowDownResult(
        String[] privateCards, String[] board, Holdem.BEST_HAND_TYPE expectedBestHandType, Card[] expectedKickers
    ) {
        var showDown = new ShowDown();
        var player = new Player(0);
        player.privateCards = privateCards;
        var showDownResult = showDown.getShowDownResult(player, board);
        assertEquals(expectedBestHandType, showDownResult.bestHandType);
        assertTrue(Arrays.equals(expectedKickers, showDownResult.getKickers()));
    }

    @Test
    void getShowDownResultRoyalFlush() {
        var privateCards = new String[]{"QD", "KD"};
        var board = new String[]{"2D", "TD", "JD", "AC", "AD"};
        var expectedBestHandType = ROYAL_FLUSH;
        var expectedKickers = new Card[]{};
        testShowDownResult(privateCards, board, expectedBestHandType, expectedKickers);
    }

    @Test
    void getShowDownResultStraightFlush() {
        var privateCards = new String[]{"JC", "6C"};
        var board = new String[]{"2C", "TC", "7C", "8C", "9C"};
        var expectedBestHandType = STRAIGHT_FLUSH;
        var expectedKickers = new Card[]{
            new Card("JC"), new Card("TC"),
            new Card("9C"), new Card("8C"), new Card("7C")
        };
        testShowDownResult(privateCards, board, expectedBestHandType, expectedKickers);
    }

    @Test
    void getShowDownResultFourOfAKind() {
        var privateCards = new String[]{"9S", "AS"};
        var board = new String[]{"9H", "9C", "9D", "AH", "AC"};
        var expectedBestHandType = FOUR_OF_A_KIND;
        var expectedKickers = new Card[]{
            new Card("9S")
        };
        testShowDownResult(privateCards, board, expectedBestHandType, expectedKickers);
    }

    @Test
    void getShowDownResultFullHouse() {
        var privateCards = new String[]{"6S", "3D"};
        var board = new String[]{"2H", "6H", "3H", "3S", "2S"};
        var expectedBestHandType = FULL_HOUSE;
        var expectedKickers = new Card[]{
            new Card("3S"), new Card("6S"),
        };
        testShowDownResult(privateCards, board, expectedBestHandType, expectedKickers);
    }

    @Test
    void getShowDownResultFlush() {
        var privateCards = new String[]{"4H", "KH"};
        var board = new String[]{"2H", "TH", "5H", "4C", "7C"};
        var expectedBestHandType = FLUSH;
        var expectedKickers = new Card[]{
            new Card("KH"), new Card("TH"),
            new Card("5H"), new Card("4H"), new Card("2H")
        };
        testShowDownResult(privateCards, board, expectedBestHandType, expectedKickers);
    }

    @Test
    void getShowDownResultStraightAceHigh() {
        var privateCards = new String[]{"AS", "KS"};
        var board = new String[]{"TH", "QC", "JD", "4C", "7C"};
        var expectedBestHandType = STRAIGHT;
        var expectedKickers = new Card[]{
                new Card("AS"), new Card("KS"),
                new Card("QC"), new Card("JD"), new Card("TH")
        };
        testShowDownResult(privateCards, board, expectedBestHandType, expectedKickers);
    }

    @Test
    void getShowDownResultStraightAceLow() {
        var privateCards = new String[]{"AS", "KS"};
        var board = new String[]{"2H", "3C", "5D", "4C", "7C"};
        var expectedBestHandType = STRAIGHT;
        var expectedKickers = new Card[]{
                new Card("5D"), new Card("4C"),
                new Card("3C"), new Card("2H"), new Card("AS")
        };
        testShowDownResult(privateCards, board, expectedBestHandType, expectedKickers);
    }

    @Test
    void getShowDownResultThreeOfAKind() {
        var privateCards = new String[]{"6S", "6D"};
        var board = new String[]{"2H", "3C", "6H", "4C", "7C"};
        var expectedBestHandType = THREE_OF_A_KIND;
        var expectedKickers = new Card[]{
            new Card("6S"), new Card("7*"), new Card("4*")
        };
        testShowDownResult(privateCards, board, expectedBestHandType, expectedKickers);
    }

//    @Test
//    void getShowDownResultThreeOfAKind2() {
//        var privateCards = new String[]{"5C", "4C"};
//        var board = new String[]{"2C", "3S", "KS", "KH", "KD"};
//        var expectedBestHandType = THREE_OF_A_KIND;
//        var expectedKickers = new Card[]{
//            new Card("KS"), new Card("5*"), new Card("4*")
//        };
//        testShowDownResult(privateCards, board, expectedBestHandType, expectedKickers);
//    }

    @Test
    void getShowDownResultTwoPair() {
        var privateCards = new String[]{"AS", "QH"};
        var board = new String[]{"2H", "3C", "QD", "4C", "2C"};
        var expectedBestHandType = TWO_PAIR;
        var expectedKickers = new Card[]{
            new Card("Q*"), new Card("2*"), new Card("A*")
        };
        testShowDownResult(privateCards, board, expectedBestHandType, expectedKickers);
    }

    @Test
    void getShowDownResultPair() {
        var privateCards = new String[]{"AS", "KH"};
        var board = new String[]{"2H", "3C", "QD", "4C", "2C"};
        var expectedBestHandType = PAIR;
        var expectedKickers = new Card[]{
            new Card("2*"), new Card("A*"),
            new Card("K*"), new Card("Q*")
        };
        testShowDownResult(privateCards, board, expectedBestHandType, expectedKickers);
    }

    @Test
    void getShowDownResultHighCard() {
        var privateCards = new String[]{"AS", "KS"};
        var board = new String[]{"2H", "3C", "5D", "6C", "7C"};
        var expectedBestHandType = HIGH_CARD;
        var expectedKickers = new Card[]{
            new Card("A*"), new Card("K*"),
            new Card("7*"), new Card("6*"), new Card("5*")
        };
        testShowDownResult(privateCards, board, expectedBestHandType, expectedKickers);
    }
}