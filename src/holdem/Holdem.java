package holdem;

import java.util.Random;
import java.util.Arrays;

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
    class Deck{
        public Card[] cards;
        class Card {
            int value;
            String rank;
            int rankNum;
            String suit;
            int suitNum;
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
    
    public class Player {
        int stack;
        String[] privateCard;
        Player (int initialStack) {
            stack = initialStack;
        }

        public String[] getPrivateCard() {
            return privateCard;
        }
        public int incrStack(int incr) {
            stack += incr;
            return stack;
        }
    }

    private final Deck deck;
    int posInDeck;
    int playerNum;
    public Player[] players = null;
    String[] board = null;
    String[] flop = null;
    String turn = "";
    String river = "";
    public int pot;
    
    public Holdem(int playerNum_) {
        int initialStack = 1000;
        playerNum = playerNum_;
        deck = new Deck();
        deck.shuffle();
        posInDeck = 0;
        board = new String[5];
        players = new Player[playerNum];
        for (int i = 0; i < playerNum; i++) {
            Player player = new Player(initialStack);
            players[i] = player;
        }
        pot = 0;
    }
    
    public void shuffle() {
        deck.shuffle();
        posInDeck = 0;
        for (int i = 0; i < playerNum; i++) {
            players[i].privateCard = null;
        }
        flop = null;
        turn = "";
        river = "";
        board = new String[5];
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

    public void dealCardForPlayer(int playerID) {
        if (players[playerID].privateCard == null) {
            players[playerID].privateCard = new String[2];
            for (int i = 0; i < 2; i++) {
                players[playerID].privateCard[i] = dealCard();
            }
        }
    }
    public String[] getPlayerCard(int playerID) {
        return players[playerID].privateCard;
    }

       
    public String[] getFlop() {
        if (flop == null) {
            flop = new String[3];
            for (int i = 0; i < 3; i++) {
                flop[i] = dealCard();
            }
        }
        System.arraycopy(flop, 0, board, 0, flop.length);
        return flop;
    }
    
    public String dealTurn() {
        if (turn.isEmpty()) {
           turn = dealCard();
        }
        board[3] = turn;
        return turn;
    }
    
    public String dealRiver() {
        if (river.isEmpty()) {
           river = dealCard();
        }
        board[4] = river;
        return river;
    }
    
    public void showDown() {
        for (int i = 0; i < Holdem.this.players.length; i++) {
            Holdem.Player player = Holdem.this.players[i];
            System.out.println("bestHand of Player " + i + ":");
            ShowDown showDown = new ShowDown();
            showDown.getshowDownResult(player);
        }
    }
    
    class ShowDown {
        class ShowDownResult {
            BEST_HAND_TYPE bestHandType;
            Holdem.Deck.Card[] bestHand;
        }
        String[] candidateCards = new String[7];
        int[] suitCount = new int[4];
        int[] rankCount = new int[13];
        int[] ranks = new int[7];
        String[] pickedCards = null;
        int maxSuitCount = 0;
        int maxCountSuit;
        int maxRankCount = 0;
        int maxCountRank;
        
        void countCards() {
            Arrays.fill(ranks, 14);
            for (int i  = 0; i < candidateCards.length; i++) {
                String card = candidateCards[i];
                Holdem.Deck deck = Holdem.this.new Deck();
                Holdem.Deck.Card c = deck.new Card(card);
                int rank = c.rankNum;
                if (rank == 0) rank = 13;
                if (rank == 1) rank = 14;
                suitCount[c.suitNum]++;
                rankCount[c.rankNum]++;
                // insertion sort the cards by rank
                ranks[i] = rank;
                for (int j = i; j > 0; j--) {
                    if (ranks[j] < ranks[j-1]) {
                        ranks[j] = ranks[j-1];
                        ranks[j-1] = rank;
                    }
                }

            }
            for (int i = 0; i < 4; i++) {
                System.out.println("Suit " + i + "=" + suitCount[i]);
            }
            for (int i = 0; i < 13; i++) {
                System.out.println("Rank " + i + "=" + rankCount[i]);
            }
        }
        
        boolean checkStraight(String[] candidateCards) {
            return false;
        }
        
        boolean checkRoyalFlush() {
            if (!checkFlush()) {
                return false;
            } else {
                Arrays.sort(pickedCards);
                if (Integer.parseInt(pickedCards[0]) != 14) {
                    return false;
                }
            }
            return true;
        }
        
        boolean checkStraightFlush() {
            if (maxCountSuit < 5) {
                return false;
            } else {
                checkStraight(candidateCards);
            }
            return false;
        }

        boolean checkFourOfAKind() {
            return maxRankCount == 4;
        }
        
        boolean checkFullHouse() {
            return false;
        }

        boolean checkFlush() {
            return maxSuitCount  == 5;
        }
        boolean checkStraight() {
            return false;
        }

        boolean checkThreeOfAKind() {
            return maxRankCount == 3;
        }
        boolean checkTwoPair() {
            return false;
        }

        boolean checkPair() {
            return maxRankCount == 2;
        }
            
        ShowDownResult getshowDownResult(Holdem.Player player) {
                String[] privateCard = player.getPrivateCard();
                String[] board = Holdem.this.board;
                System.arraycopy(board, 0, candidateCards, 0, board.length);
                candidateCards[5] = privateCard[0];
                candidateCards[6] = privateCard[1];
            
            ShowDownResult res = new ShowDownResult();
            countCards();

            for (int i = 0; i < 4; i++) {
                if (suitCount[i] > maxSuitCount) {
                    maxSuitCount= suitCount[i];
                    maxCountSuit = i;
                }
            }
            
            for (int i = 0; i < 13; i++) {
                if (rankCount[i] > maxRankCount) {
                    maxRankCount = rankCount[i];
                    maxCountRank = i;
                }
            }
            System.out.println("MaxRankCount=" + maxRankCount);
            System.out.println("MaxSuitCount=" + maxSuitCount);
            System.out.println("ranks=");
            for (int rank : ranks) {
                System.out.println(rank + ", ");
            }
            
            if (checkRoyalFlush()) {
                System.out.println("RoyalFlush");
            } else if (checkStraightFlush()) {
                System.out.println("StraightFlush");
            } else if (checkFourOfAKind()) {
                System.out.println("FourOfAKind");
            } else if (checkFullHouse()) {
                System.out.println("FullHouse");
            } else if (checkFlush()) {
                System.out.println("Flush");
            } else if (checkStraight()) {
                System.out.println("Straight");
            } else if (checkThreeOfAKind()) {
                System.out.println("ThreeOfAKind");
            } else if (checkTwoPair()) {
                System.out.println("TwoPair");
            } else if (checkPair()) {
                System.out.println("Pair");
            } else {
                System.out.println("HighCard");
            }
            
      
            return res;
        }
    }

}
