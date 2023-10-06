package com.fanzengau.holdem;
import java.util.Scanner;

public class HoldemState {
    public enum CARD_STAGE {
        PRE_FLOP, 
        FLOP, 
        TURN,
        RIVER, 
        SHOW_DOWN, 
    };
    int cardStageToInt(CARD_STAGE cardStage) {
        switch(cardStage) {
            case PRE_FLOP:
                return 2;
            case FLOP:
                return 3;
            case TURN:
                return 4;
            case RIVER:
                return 5;
            case SHOW_DOWN:
                return 6;
            default:
                return -1;
        }
    }
    CARD_STAGE intToCardStage(int cardStageInt) {
        System.out.println("cardStageInt=" + cardStageInt);
        switch(cardStageInt) {
            case 2:
                return CARD_STAGE.PRE_FLOP;
            case 3:
                return CARD_STAGE.FLOP;
            case 4:
                return CARD_STAGE.TURN;
            case 5:
                return CARD_STAGE.RIVER;
            case 6:
                return CARD_STAGE.SHOW_DOWN;
            default:
                return null;
        }
    }
    public CARD_STAGE cardStage;
    public int playerStage;  // Player0 always acts first
    int[] playerBets; // array of total bet amount of each single player
    boolean roundCompleted;
    HoldemState() {
        cardStage = CARD_STAGE.PRE_FLOP;
        playerStage = 0;
        playerBets = new int[2];
        roundCompleted = false;
    }
    HoldemState(CARD_STAGE cardStage_, int playerStage_, int[] playerBets_, boolean roundCompleted_) {
        cardStage = cardStage_;
        playerStage = playerStage_;
        playerBets = playerBets_;
        roundCompleted = roundCompleted_;
    }
    
    public int[] getPlayerBets() {
        return playerBets;
    }
    
    public HoldemState next(int[] playerBets_) {
        if (cardStageToInt(cardStage) == cardStageToInt(CARD_STAGE.SHOW_DOWN)) {
            System.out.println("Start new round.");
            return new HoldemState();
        }
        int nextBetStage;
        playerBets = playerBets_;
        System.out.println("cardStage: " + cardStage + ", betStage: " + playerStage);
        System.out.println("Player 0's bet: " + playerBets[0] + ", Player 1's bet: " + playerBets[1]);

        if (playerStage == 1) roundCompleted = true;
        if (playerBets[playerStage] < playerBets[1-playerStage]) {
            System.out.println("Player " + playerStage + " folded.");
            System.out.println("Game ended at cardStage " + cardStage);
            return new HoldemState(CARD_STAGE.SHOW_DOWN, 0, playerBets, true);
        } else if (playerBets[playerStage] == playerBets[1 - playerStage] && roundCompleted) {
            System.out.println("Round completed.");
            cardStage = intToCardStage(cardStageToInt(cardStage) + 1);
            if (cardStageToInt(cardStage) == cardStageToInt(CARD_STAGE.SHOW_DOWN)) {
                System.out.println("Show down.");
                return new HoldemState(cardStage, 0, playerBets, true);
            } else {
                System.out.println("Card stage: " + cardStage);
                return new HoldemState(cardStage, 0, playerBets, false);
            }
        } else {
            return new HoldemState(cardStage, 1 - playerStage, playerBets, roundCompleted); 
        }
    }
    
    public static void main(String[] args) {
        HoldemState hs = new HoldemState();
        int[] playerBets = hs.getPlayerBets();
        while(true) {
            System.out.println("cardStage: " + hs.cardStage + ", betStage: " + hs.playerStage);
            if (hs.cardStage != CARD_STAGE.SHOW_DOWN) {
                System.out.println("Enter player" + hs.playerStage + "'s next bet:");
                Scanner sc = new Scanner(System.in);
                playerBets = hs.getPlayerBets();
                playerBets[hs.playerStage] = sc.nextInt();
            }
            hs = hs.next(playerBets);
            System.out.println();
        }
    }
}
    