package holdem;

import java.util.Random;

public class Holdem {

    class Deck{
        public Card[] cards;
        class Card {
            int value;
            public Card(int i) {
                value = i;
            }
            public String toString() {
                String suit;
                String rank;
                switch (value / 13) {
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
                switch (value % 13) {
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
                return rank + suit;
            }
        }
        public Deck () {
            cards = new Card[52];
            for (int i = 0; i < 52; i++) {
                cards[i] = new Card(i + 1);
            }
        }
        public void shuffle() {
            Random rand = new Random(System.currentTimeMillis());
            for (int i = cards.length - 1; i > 0; i--) {
                swap(cards, i, rand.nextInt(i + 1));
            }
        }
        private void swap(Card[] arr, int i, int j) {
            Card tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    private final Deck deck;
    int posInDeck;
    String[] playerCard1 = null;
    String[] playerCard2 = null;
    String[] flop = null;
    String turn = "";
    String river = "";
    
    public Holdem() {
        deck = new Deck();
        deck.shuffle();
        posInDeck = 0;
    }
    
    public void shuffle() {
        deck.shuffle();
        posInDeck = 0;
        playerCard1 = null;
        playerCard2 = null;
        flop = null;
        turn = "";
        river = "";
    }
    
    private String getCardAt(int i) {
        if (i < deck.cards.length) {
            return deck.cards[i].toString();
        } else {
            return "i = " + i + " is invalid";
        }
    }

    private String dealCard() {
        System.out.println(getCardAt(posInDeck));
        return getCardAt(posInDeck++);
    }

    public String[] getPlayerCard1() {
        if (playerCard1 == null) {
            playerCard1 = new String[2];
            for (int i = 0; i < 2; i++) {
                playerCard1[i] = dealCard();
            }
        }
        return playerCard1;
    }

    public String[] getPlayerCard2() {
        if (playerCard2 == null) {
            playerCard2 = new String[2];
            for (int i = 0; i < 2; i++) {
                playerCard2[i] = dealCard();
            }
        }
        return playerCard2;
    }
        
    public String[] getFlop() {
        if (flop == null) {
            flop = new String[3];
            for (int i = 0; i < 3; i++) {
                flop[i] = dealCard();
            }
        }
        return flop;
    }
    
    public String getTurn() {
        if (turn.isEmpty()) {
           turn = dealCard();
        }
        return turn;
    }
    
    public String getRiver() {
        if (river.isEmpty()) {
           river = dealCard();
        }
        return river;
    }
    
    class showDown {
        class showDownresult {
            String bestHandType;
            Holdem.Deck.Card[] bestHand;
        }
        Holdem.Deck.Card[] candidateCards[];
        showDown() {
        
        }
    }

    public Integer player1Stack = 1000;
    public Integer player2Stack = 1000;
    public Integer pot = 0;
}
