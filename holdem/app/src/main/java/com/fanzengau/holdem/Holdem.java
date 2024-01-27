package com.fanzengau.holdem;

import java.util.ArrayList;
import java.util.List;

public class Holdem {
    enum BEST_HAND_TYPE {
        ROYAL_FLUSH, 
        STRAIGHT_FLUSH, 
        FOUR_OF_A_KIND,
        FULL_HOUSE, 
        FLUSH, 
        STRAIGHT, 
        THREE_OF_A_KIND, 
        TWO_PAIR, 
        PAIR, 
        HIGH_CARD
    };
    
    private Deck deck;
    private int posInDeck;
    int playerNum;
    public Player[] players;
    String[] flop = null;
    String turn = "";
    String river = "";
    public static final int initialStack = 1000;
    public int pot;
    List<Integer> winners;
    public HoldemState holdemState = new HoldemState();
    public Holdem(int playerNum) {
        this.playerNum = playerNum;
        deck = new Deck();
        deck.shuffle();
        posInDeck = 0;
        pot = 0;
        players = new Player[playerNum];
        for (int i = 0; i < playerNum; i++) {
            players[i] = new Player(initialStack);
            dealCardForPlayer(i);
        }
        winners = new ArrayList<>();
    }
    
    public void shuffle() {
        deck.shuffle();
        posInDeck = 0;
        pot = 0;
        for (int i = 0; i < playerNum; i++) {
            dealCardForPlayer(i);
        }
        flop = dealFlop();
        turn = dealTurn();
        river = dealRiver();
        winners = new ArrayList<>();
        holdemState = new HoldemState();
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    private String getCardAt(int i) {
        if (i < deck.getCards().length) {
            return deck.getCards()[i].toString();
        } else {
            return "i = " + i + " is invalid";
        }
    }

    private String dealCard() {
        System.out.println(getCardAt(posInDeck));
        return getCardAt(posInDeck++);
    }

    private void dealCardForPlayer(int playerID) {
        players[playerID].privateCards = new String[2];
        for (int i = 0; i < 2; i++) {
            players[playerID].privateCards[i] = dealCard();
        }
    }
    public String[] getPlayerCard(int playerID) {
        return players[playerID].privateCards;
    }

       
    private String[] dealFlop() {
        flop = new String[3];
        for (int i = 0; i < 3; i++) {
            flop[i] = dealCard();
        }
        return flop;
    }
    
    private String dealTurn() {
       turn = dealCard();
        return turn;
    }
    
    private String dealRiver() {
        river = dealCard();
        return river;
    }

    public String[] getFlop() {
        return flop;
    }

    public void setFlop(String[] flop) {
        this.flop = flop;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public String getRiver() {
        return river;
    }

    public void setRiver(String river) {
        this.river = river;
    }
    
    public String[] showDown() {
        String[] showDownResultString = new String[Holdem.this.players.length + 1]; // result for each player and an overall result
        int[] showDownResultValue = new int[Holdem.this.players.length];

        for (int i = 0; i < Holdem.this.players.length; i++) {
            Player player = Holdem.this.players[i];
            ShowDown showDown = new ShowDown();
            ShowDown.ShowDownResult showDownResult = showDown.getShowDownResult(
                player, new String[]{flop[0], flop[1], flop[2], turn, river}
            );
            showDownResultString[i] = "bestHand of Player " + (i+1) + ": " + showDownResult.toString();
            showDownResultValue[i] = showDownResult.getValue();
            System.out.println(showDownResultString[i]);
        }
        int maxShowDownResultValue = 0;
        for (int i = 0; i < Holdem.this.players.length; i++) {
            if (showDownResultValue[i] > maxShowDownResultValue) {
                maxShowDownResultValue = showDownResultValue[i];
            }
        }
        
        showDownResultString[showDownResultString.length-1] = "Player ";
        String winnerString = "[";
        winners = new ArrayList<>();

        for (int i = 0; i < Holdem.this.players.length; i++) {
            if (showDownResultValue[i] == maxShowDownResultValue) {
                if (!winners.isEmpty()) {
                    winnerString += ", ";
                }
                winnerString += Integer.toString(i+1);
                winners.add(i);
            } 
        }
        // TODO: current logic assume only 2 players
        if (holdemState.playerFolded[0]) {
            System.out.println("Player 1 folded. Pot goes to Player 2.");
            players[1].stack += pot;
        } else if (holdemState.playerFolded[1]) {
            System.out.println("Player 2 folded. Pot goes to Player 1.");
            players[0].stack += pot;
        } else {
            System.out.println("Distributing pot " + pot + " among " + winners.size() + " player(s).");
            float potShare = pot / winners.size();
            for (var winner : winners) {
                players[winner].stack += potShare;
            }
        }
        showDownResultString[showDownResultString.length-1] += winnerString + "] has the best hand.";
        System.out.println(showDownResultString[showDownResultString.length-1]);
        return showDownResultString;
    }

    public List<Integer> getWinners() {
        return winners;
    }
}
