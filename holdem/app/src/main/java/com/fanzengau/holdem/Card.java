package com.fanzengau.holdem;

public class Card {
    int value;
    String rank;
    int rankNum;
    String suit;
    int suitNum;
    
    public Card() {} // TODO: Should return invalid card
    
    public Card(int i) {
        value = i;
        suitNum = value / 13;
        switch (suitNum) {
            case 0:
                suit = "S";
                break;
            case 1:
                suit = "H";
                break;
            case 2:
                suit = "C";
                break;
            case 3:
                suit = "D";
                break;
            default:
                suit = "D";
        }
        rankNum = value % 13;
        switch (rankNum) {
            case 10:
                rank = "T";
                break;
            case 11:
                rank = "J";
                break;
            case 12:
                rank = "Q";
                break;
            case 0:
                rank = "K";
                break;
            case 1:
                rank = "A";
                break;
            default:
                rank = Integer.toString(value % 13);
        }
    }

    public Card(String cardString) {
        rank = cardString.substring(0,1);
        switch (rank) {
            case "T":
                rankNum = 10;
                break;
            case "J":
                rankNum = 11;
                break;
            case "Q":
                rankNum = 12;
                break;
            case "K":
                rankNum = 0;
                break;
            case "A":
                rankNum = 1;
                break;
            default:
                rankNum = Integer.parseInt(rank);
        }
        suit = cardString.substring(1);
        switch (suit) {
            case "S":
                suitNum = 0;
                break;
            case "H":
                suitNum = 1;
                break;
            case "C":
                suitNum = 2;
                break;
            case "D":
                suitNum = 3;
                break;
        }
        value = suitNum*13 + rankNum;
    }

    public String toString() {
        return rank + suit;
    }
}