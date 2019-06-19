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
    
    public String[] showDown() {
        String[] showDownResultString = new String[Holdem.this.players.length];
        for (int i = 0; i < Holdem.this.players.length; i++) {
            Holdem.Player player = Holdem.this.players[i];
            ShowDown showDown = new ShowDown();
            ShowDown.ShowDownResult showDownResult = showDown.getshowDownResult(player);
            showDownResultString[i] = "bestHand of Player " + (i+1) + ":" + showDownResult.toString();
            System.out.println(showDownResultString[i]);
        }
        return showDownResultString;
    }
    
    class ShowDown {
        class ShowDownResult {
            BEST_HAND_TYPE bestHandType;
            String bestHandTypeStr;
            CountResult countResult;
            int bestHandTypeInt;
            String[] candidateCards;
            String[] kickers = null;
            Holdem.Deck.Card[] bestHand = null;
            
            ShowDownResult(BEST_HAND_TYPE bestHandType_, String[] candidateCards_, CountResult countResult_) {
                bestHandType = bestHandType_;
                candidateCards = candidateCards_;
                countResult = countResult_;

                switch(bestHandType) {
                    case ROYAL_FLUSH:
                        bestHandTypeStr = "RoyalFlush";
                        bestHandTypeInt = 7;
                        break;
                    case STRAIGHT_FLUSH:
                        bestHandTypeStr = "StraightFlush";
                        bestHandTypeInt = 6;
                        break;
                    case FOUR_OF_A_KIND:
                        bestHandTypeStr = "FourOfAKind";
                        bestHandTypeInt = 6;
                        break;
                    case FULL_HOUSE:
                        bestHandTypeStr = "FullHouse";
                        bestHandTypeInt = 7;
                        break;
                    case FLUSH:
                        bestHandTypeStr = "Flush";
                        bestHandTypeInt = 5;
                        break;
                    case STRAIGHT:
                        bestHandTypeStr = "Straight";
                        bestHandTypeInt = 4;
                        break;
                    case THREE_OF_A_KIND:
                        bestHandTypeStr = "ThreeOfAKind";
                        bestHandTypeInt = 3;
                        break;
                    case TWO_PAIR:
                        bestHandTypeStr = "TwoPair";
                        bestHandTypeInt = 2;
                        break;
                    case PAIR:
                        bestHandTypeStr = "Pair";
                        bestHandTypeInt = 1;
                        break;
                    default:
                        bestHandTypeStr = "HighCard";
                        bestHandTypeInt = 0;
                }

            }
            void computeKickers() {
                Holdem.Deck deck = Holdem.this.new Deck();
                Holdem.Deck.Card c = deck.new Card(countResult.ranks[0]);
                int j;
                switch(bestHandType) {
                    case ROYAL_FLUSH:
                        kickers = new String[1];
                        kickers[0] = ("Royal Flush is unbeatable");
                        break;
                    case STRAIGHT_FLUSH:
                        kickers = countResult.straightCards;
                        break;
                    case FOUR_OF_A_KIND:
                        kickers = new String[2];
                        c = deck.new Card(countResult.maxCountRank);
                        kickers[0] = c.rank;
                        for (int nextHigh = 14; nextHigh >= 2; nextHigh--) {
                            if (nextHigh == countResult.maxCountRank) continue;
                            if (countResult.rankCount[nextHigh%13] > 0) {
                                c = deck.new Card(nextHigh);
                                kickers[1] = c.rank;
                                break;
                            }
                        }
                        break;
                    case FULL_HOUSE:
                        kickers = new String[2];
                        c = deck.new Card(countResult.maxCountRank);
                        kickers[0] = c.rank;
                        for (int nextHigh = 14; nextHigh >= 2; nextHigh--) {
                            if (nextHigh == countResult.maxCountRank) continue;
                            if (countResult.rankCount[nextHigh%13] > 1) {
                                c = deck.new Card(nextHigh);
                                kickers[1] = c.rank;
                                break;
                            }
                        }
                        break;
                    case FLUSH:
                        kickers = countResult.flushCards;
                        break;
                    case STRAIGHT:
                        kickers = countResult.straightCards;
                        break;
                    case THREE_OF_A_KIND:
                        kickers = new String[3];
                        c = deck.new Card(countResult.maxCountRank);
                        kickers[0] = c.rank;
                        j = 1;
                        for (int nextHigh = 14; nextHigh >= 2; nextHigh--) {
                            if (nextHigh == countResult.maxCountRank) continue;
                            if (countResult.rankCount[nextHigh%13] > 0) {
                                c = deck.new Card(nextHigh);
                                kickers[j] = c.rank;
                                j++;
                                if (j > 2) break;
                            }
                        }
                        break;
                    case TWO_PAIR:
                        kickers = new String[3];
                        int[] pairRanks = new int[3];
                        j = 0;
                        for (int pairRank = 14; pairRank >= 2; pairRank--) {
                            if (countResult.rankCount[pairRank%13] == 2) {
                                pairRanks[j] = pairRank;
                                j++;
                                if (j > 2) break;
                            }
                        }
                        if (j == 3) {
                            Arrays.sort(pairRanks);
//                            kickers[0] = pairRanks[2];
                        }
//                        kickers[0] = pairRanks[0];
//                        kickers[1] = pairRanks[1];
                        break;
                    case PAIR:
                        kickers = new String[4];
                        c = deck.new Card(countResult.maxCountRank);
                        kickers[0] = c.rank;
                        j = 1;
                        for (int nextHigh = 14; nextHigh >= 2; nextHigh--) {
                            if (nextHigh == countResult.maxCountRank) continue;
                            if (countResult.rankCount[nextHigh%13] > 0) {
                                c = deck.new Card(nextHigh);
                                kickers[j] = c.rank;
                                j++;
                                if (j > 3) break;
                            }
                        }
                        break;
                    default:
                        kickers = new String[5];
                        for (int i = 0; i < 5; i++) {
                            c = deck.new Card(countResult.ranks[i]);
                            kickers[i] = c.rank;
                        }
                }
            }
            
            String[] getKickers() {
                if (kickers == null) {
                    computeKickers();
                }
                return kickers;
            }
            
            void computeBestHand () {
                
            }
            
            Holdem.Deck.Card[] getBestHand() {
                if (bestHand == null) {
                    computeBestHand();
                }
                return bestHand;
            }
            public String toString() {
                String s = bestHandTypeStr + " with kickers: ";
                if (kickers == null) {
                    computeKickers();
                }
                for (String k : kickers) {
                    s += k + ", ";
                }
                
                return s;
            }
        }
        
        class CountResult {
            String[] candidateCards;
            int[] suitCount = new int[4];
            int[] rankCount = new int[13];
            int[] ranks = new int[7]; // In ranks of this class, A = 14, K = 13.
            String[] pickedCards = null;
            int maxSuitCount = 0;
            int maxCountSuit;
            int maxRankCount = 0;
            int maxCountRank;
            int setCount = 0;
            int pairCount = 0;
            boolean isStraight;
            boolean isFlush;
            String[] flushCards = null; // This will contain all cards of the 
            // flushed suit, if there is a flushed suit.
            String[] straightCards = null; // This will contain the longest 
            // straight, if there is a flushed suit.
            
            CountResult (String[] candidateCards_) {
                candidateCards = candidateCards_;
            }
            
            public String toString() {
                String s = "isStraight = " + isStraight + "\n";
                s += "isFlush = " + isFlush + "\n";

                for (int i = 0; i < 4; i++) {
                    s += "Suit " + i + " = " + suitCount[i] + "\n";
                }
                for (int i = 0; i < 13; i++) {
                    s += "Rank " + i + " = " + rankCount[i] + "\n";
                }
                
                s += "MaxRankCount = " + maxRankCount + "\n";
                s += "MaxSuitCount = " + maxSuitCount + "\n";
                s += "ranks = \n";
                
                for (int rank : ranks) {
                    System.out.println(rank + ", ");
                }
                return s;
            }
        }
        
        String[] candidateCards = new String[7];

        ShowDownResult showDownResult;
        
        CountResult countCards(String[] cards) {
            CountResult countResult = new CountResult(candidateCards);
            int[] ranks = new int[7];
            Arrays.fill(ranks, -1);
            Holdem.Deck deck = Holdem.this.new Deck();

            for (int i  = 0; i < cards.length; i++) {
                String card = cards[i];
                Holdem.Deck.Card c = deck.new Card(card);
                int rank = c.rankNum;
                if (rank == 0) rank = 13;
                if (rank == 1) rank = 14;
                countResult.suitCount[c.suitNum]++;
                countResult.rankCount[c.rankNum]++;
                // insertion sort the cards by rank from high to low
                ranks[i] = rank;
                for (int j = i; j > 0; j--) {
                    if (ranks[j] > ranks[j-1]) {
                        ranks[j] = ranks[j-1];
                        ranks[j-1] = rank;
                    }
                }
                countResult.ranks = ranks;
            }
            
            for (int i = 0; i < 13; i++) {
                if (countResult.rankCount[i] == 3) {
                    countResult.setCount++;
                } else if (countResult.rankCount[i] == 2 ) {
                    countResult.pairCount++;
                }
            }
            
            // check for straight
            int straightLength = 0;
            countResult.straightCards = new String[7];
            Holdem.Deck.Card c = deck.new Card(countResult.ranks[0]);
            countResult.straightCards[0] = c.rank;

            for (int i = 1; i < countResult.ranks.length; i++) {
                
                if (countResult.ranks[i-1] - countResult.ranks[i] == 1 || 
                        (straightLength == 4 && countResult.ranks[i-1] == 2 
                        && countResult.ranks[i] == 14)) {

                    straightLength++;
                    c = deck.new Card(countResult.ranks[i]);
                    countResult.straightCards[straightLength] = c.rank;

                } else {
                    straightLength = 0;
                    countResult.straightCards = new String[7];
                    c = deck.new Card(countResult.ranks[i]);
                    countResult.straightCards[0] = c.rank;
                }
            }
            countResult.isStraight = (straightLength >= 5);
            

            // Check for Flush
            for (int i = 0; i < 4; i++) {
                if (countResult.suitCount[i] > countResult.maxSuitCount) {
                    countResult.maxSuitCount= countResult.suitCount[i];
                    countResult.maxCountSuit = i;
                }
            }
            
            if (countResult.maxSuitCount >= 5) {
                countResult.isFlush = true;
                countResult.flushCards = new String[countResult.maxSuitCount];
                for (int i = 0; i < countResult.maxSuitCount; i++) {
                    String card = candidateCards[i];
                    c = deck.new Card(card);
                    if (c.suitNum == countResult.maxCountSuit) {
                        countResult.flushCards[i] = card;
                    }
                }
            } else {
                countResult.isFlush = false;
            }
            
            for (int i = 0; i < 13; i++) {
                if (countResult.rankCount[i] > countResult.maxRankCount) {
                    countResult.maxRankCount = countResult.rankCount[i];
                    countResult.maxCountRank = i;
                }
            }
            
            System.out.println("countResult = \n" + countResult);
            return countResult;
        }
        
        // These checking functions should be run in order high to low.
        // 

        
        boolean checkRoyalFlush(CountResult countResult) {
            if (!checkStraightFlush(countResult)) {
                return false;
            } else if (countResult.straightCards[-1].equals("A")) {
                    return true;
            }
            return false;
        }
        
        boolean checkStraightFlush(CountResult countResult) {
            if (!countResult.isFlush) {
                return false;
            } else {
                CountResult flushCountResult = 
                        new CountResult(countResult.flushCards);
                if (flushCountResult.isStraight) {
                    return true;
                }
            }
            return false;
        }

        boolean checkFourOfAKind(CountResult countResult) {
            return countResult.maxRankCount == 4;
        }
        
        boolean checkFullHouse(CountResult countResult) {
            return (countResult.setCount == 2 || 
                    (countResult.setCount == 1 && countResult.pairCount > 0));
        }

        boolean checkFlush(CountResult countResult) {
            return countResult.maxSuitCount >= 5;
        }
        boolean checkStraight(CountResult countResult) {
            return countResult.isStraight;
        }

        boolean checkThreeOfAKind(CountResult countResult) {
            return countResult.setCount > 0;
        }
        boolean checkTwoPair(CountResult countResult) {
            return countResult.pairCount >= 2;
        }

        boolean checkPair(CountResult countResult) {
            return countResult.pairCount >= 1;
        }
            
        ShowDownResult getshowDownResult(Holdem.Player player) {
                String[] privateCard = player.getPrivateCard();
                String[] board = Holdem.this.board;
                System.arraycopy(board, 0, candidateCards, 0, board.length);
                candidateCards[5] = privateCard[0];
                candidateCards[6] = privateCard[1];
            
            CountResult countResult = countCards(candidateCards);
            BEST_HAND_TYPE bestHandType;
            if (checkRoyalFlush(countResult)) {
                bestHandType = BEST_HAND_TYPE.ROYAL_FLUSH;
            } else if (checkStraightFlush(countResult)) {
                bestHandType = BEST_HAND_TYPE.STRAIGHT_FLUSH;
            } else if (checkFourOfAKind(countResult)) {
                bestHandType = BEST_HAND_TYPE.FOUR_OF_A_KIND;
            } else if (checkFullHouse(countResult)) {
                bestHandType = BEST_HAND_TYPE.FULL_HOUSE;
            } else if (checkFlush(countResult)) {
                bestHandType = BEST_HAND_TYPE.FLUSH;
            } else if (checkStraight(countResult)) {
                bestHandType = BEST_HAND_TYPE.STRAIGHT;
            } else if (checkThreeOfAKind(countResult)) {
                bestHandType = BEST_HAND_TYPE.THREE_OF_A_KIND;
            } else if (checkTwoPair(countResult)) {
                bestHandType = BEST_HAND_TYPE.TWO_PAIR;
            } else if (checkPair(countResult)) {
                bestHandType = BEST_HAND_TYPE.PAIR;
            } else {
                bestHandType = BEST_HAND_TYPE.HIGH_CARD;
            }
            
            ShowDownResult res = new ShowDownResult(bestHandType, candidateCards, countResult);
            System.out.println("showDownResult = " + res);

            return res;
        }
    }

}
