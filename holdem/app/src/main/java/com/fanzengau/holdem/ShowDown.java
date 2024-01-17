package com.fanzengau.holdem;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Stream;

class ShowDown {

    class ShowDownResult {

        Holdem.BEST_HAND_TYPE bestHandType;
        String bestHandTypeStr;
        CountResult countResult;
        int bestHandTypeInt;
        String[] candidateCards;
        Card[] kickers = new Card[]{};
        int value;
        Card[] bestHand = null;

        ShowDownResult(Holdem.BEST_HAND_TYPE bestHandType_, String[] candidateCards_, CountResult countResult_) {
            bestHandType = bestHandType_;
            candidateCards = candidateCards_;
            countResult = countResult_;

            switch (bestHandType) {
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
            Card c;
            int j;
            switch (bestHandType) {
                case ROYAL_FLUSH:
                    break;
                case STRAIGHT_FLUSH:
                    kickers = countResult.straightCards;
                    break;
                case FOUR_OF_A_KIND:
                    kickers = new Card[]{new Card(countResult.maxCountRank)};
                    break;
                case FULL_HOUSE:
                    kickers = new Card[2];
                    kickers[0] = new Card(countResult.maxCountRank);
                    for (int i = 14; i > 0; i--) {
                        if (countResult.rankCount[i % 13] >= 2 && i != countResult.maxCountRank) {
                            kickers[1] = new Card(i);
                            break;
                        }
                    }
                    break;
                case FLUSH:
                    Comparator<Card> cardComparator
                            = Comparator.comparingInt(Card::getRankForCompareAceHigh).reversed();
                    Arrays.sort(countResult.flushCards, cardComparator);
                    kickers = countResult.flushCards;
                    break;
                case STRAIGHT:
                    kickers = countResult.straightCards;
                    break;
                case THREE_OF_A_KIND:
                    kickers = new Card[3];
                    kickers[0] = new Card(countResult.maxCountRank);
                    int count = 1;
                    for (int i = 14; i > 0; i--) {
                        if (countResult.rankCount[i % 13] >= 1 && i != countResult.maxCountRank) {
                            kickers[count] = new Card(i + 52);
                            count++;
                            if (count == 3) break;
                        }
                    }
                    break;
                case TWO_PAIR:
                    kickers = new Card[3];
                    int[] pairRanks = new int[2];
                    j = 0;
                    for (int pairRank = 14; pairRank >= 2; pairRank--) {
                        if (countResult.rankCount[pairRank % 13] == 2) {
                            pairRanks[j] = pairRank;
                            System.out.println(j + " of two pair = " + pairRank);
                            kickers[j] = new Card(pairRank + 52);
                            j++;
                            if (j == 2) {
                                break;
                            }
                        }
                    }

                    for (int nextHigh = 14; nextHigh >= 2; nextHigh--) {
                        if (nextHigh == pairRanks[0] || nextHigh == pairRanks[1]) {
                            continue;
                        }
                        if (countResult.rankCount[nextHigh % 13] > 0) {
                            kickers[j] = new Card(nextHigh + 52);;
                            break;
                        }
                    }

                    break;
                case PAIR:
                    kickers = new Card[4];
                    kickers[0] = new Card(countResult.maxCountRank + 52);
                    j = 1;
                    for (int nextHigh = 14; nextHigh >= 2; nextHigh--) {
                        if (nextHigh % 13 == countResult.maxCountRank) {
                            continue;
                        }
                        if (countResult.rankCount[nextHigh % 13] > 0) {
                            kickers[j] = new Card(nextHigh + 52);
                            j++;
                            if (j > 3) {
                                break;
                            }
                        }
                    }
                    break;
                default: // high card
                    kickers = new Card[5];
                    for (int i = 0; i < 5; i++) {
                        kickers[i] = new Card(countResult.ranks[i] + 52);
                    }
            }
        }

        Card[] getKickers() {
            if (kickers.length == 0) {
                computeKickers();
            }
            return kickers;
        }

        int getValue() {
            int weight = 10000000; // ten to the power of seven
            value = bestHandTypeInt * weight;
            weight /= 100;
            if (bestHandType == bestHandType.ROYAL_FLUSH) {
                return value;
            }
            if (kickers == null) {
                computeKickers();
            }
            Card c;
            for (int i = 0; i < kickers.length; i++) {
                c = kickers[i];
                int rankValue = c.rankNum;
                if (rankValue == 0 || rankValue == 1) {
                    rankValue += 13;
                }
                System.out.println(kickers[i] + "=>" + rankValue * weight);
                value += rankValue * weight;
                weight /= 10;
            }
            System.out.println("value = " + value);
            return value;
        }

        void computeBestHand() {

        }

        Card[] getBestHand() {
            if (bestHand == null) {
                computeBestHand();
            }
            return bestHand;
        }

        public String toString() {
            return toString(0);
        }
        
        public String toString(int indentationLevel) {
            String indent = new String();
            for (int i = 0; i < indentationLevel; i++) {
                indent += "  ";
            }
            String s = indent + bestHandTypeStr + " with kickers: ";
            if (kickers.length == 0) {
                kickers = getKickers();
            }
            for (int i = 0; i < kickers.length; i++) {
                s += kickers[i];
                if (i < kickers.length - 1) {
                    s += ", ";
                }
            }
            return s;
        }
    }

    class CountResult {

        String[] candidateCards;
        int[] suitCount = new int[4];
        int[] rankCount = new int[13];
        int[] ranks = new int[7]; // In ranks of this class, A = 14, K = 13.
        int maxSuitCount = 0;
        int maxCountSuit;
        int maxRankCount = 0;
        int maxCountRank;
        int setCount = 0;
        int pairCount = 0;
        boolean isStraight;
        boolean isFlush;
        Card[] flushCards = null; // This will contain all cards of the
        // flushed suit, if there is a flushed suit.
        Card[] straightCards = null; // This will contain the longest
        // straight, if there is a straight.

        CountResult(String[] candidateCards_) {
            candidateCards = candidateCards_;
        }

        public String toString() {
            return toString(0);
        }
        public String toString(int indentationLevel) {
            String indent = new String();
            for (int i = 0; i < indentationLevel; i++) {
                indent += "  ";
            }
            String s = indent + "Candidate cards: ";
            for (int i = 0; i < candidateCards.length; i++) {
                s += candidateCards[i] + " ";
            }
            s += "\n" + indent + "isStraight = " + isStraight + "\n";
            s += indent + "isFlush = " + isFlush + "\n";

            for (int i = 0; i < 4; i++) {
                s += indent + "Suit " + i + " = " + suitCount[i] + "\n";
            }
            for (int i = 0; i < 13; i++) {
                s +=  indent + "Rank " + i + " = " + rankCount[i] + "\n";
            }
            s += indent + "MaxRankCount = " + maxRankCount + "\n";
            s += indent + "MaxSuitCount = " + maxSuitCount + "\n";
            s += indent + "ranks = \n" + indent;
            for (int i = 0; i < ranks.length; i++) {
                s += ranks[i];
                if (i < ranks.length-1) {
                    s += ", ";
                }
            }
            s += "\n";
            return s;
        }
    }

    String[] candidateCards = new String[7];

    ShowDownResult showDownResult;

    boolean isStraight(Card[] cards) {
        if (cards.length < 5) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (
                cards[i].getRankForCompareAceHigh() - cards[i + 1].getRankForCompareAceHigh() != 1
                && cards[i].getRankForCompareAceLow() - cards[i + 1].getRankForCompareAceLow() != 1
            ) return false;
        }
        return true;
    }

    CountResult countCards() {
        CountResult countResult = new CountResult(candidateCards);
        int[] ranks = new int[7];
        Arrays.fill(ranks, -1);
        for (int i = 0; i < candidateCards.length; i++) {
            String card = candidateCards[i];
            Card c = new Card(card);
            int rank = c.rankNum;
            if (rank == 0) { // K
                rank = 13;
            }
            if (rank == 1) { // A
                rank = 14;
            }
            countResult.suitCount[c.suitNum]++;
            countResult.rankCount[c.rankNum]++;
            // insertion sort the cards by rank from high to low
            ranks[i] = rank;
            for (int j = i; j > 0; j--) {
                if (ranks[j] > ranks[j - 1]) {
                    ranks[j] = ranks[j - 1];
                    ranks[j - 1] = rank;
                }
            }
            countResult.ranks = ranks;
        }

        for (int i = 0; i < 13; i++) {
            if (countResult.rankCount[i] == 3) {
                countResult.setCount++;
            } else if (countResult.rankCount[i] == 2) {
                countResult.pairCount++;
            }
        }

        // check for straight
        Comparator<Card> cardComparatorAceHigh
                = Comparator.comparingInt(Card::getRankForCompareAceHigh).reversed();

        Comparator<Card> cardComparatorAceLow
                = Comparator.comparingInt(Card::getRankForCompareAceLow).reversed();

        var cardsAceHigh = Stream.of(candidateCards).map(c -> new Card(c)).toArray(Card[]::new);
        var cardsAceLow = Stream.of(candidateCards).map(c -> new Card(c)).toArray(Card[]::new);

        Arrays.sort(cardsAceHigh, cardComparatorAceHigh);
        Arrays.sort(cardsAceLow, cardComparatorAceLow);

        for (int j = 0; j < 3; j++) {
            var candidateStraightCardsAceHigh = Arrays.stream(cardsAceHigh, j, j + 5).toArray(Card[]::new);
            var candidateStraightCardsAceLow = Arrays.stream(cardsAceLow, j, j + 5).toArray(Card[]::new);
            if (isStraight(candidateStraightCardsAceHigh)) {
                countResult.isStraight = true;
                countResult.straightCards = candidateStraightCardsAceHigh;
                break;
            }
            if (isStraight(candidateStraightCardsAceLow)) {
                countResult.isStraight = true;
                countResult.straightCards = candidateStraightCardsAceLow;
                break;
            }
        }

        // Check for Flush
        for (int i = 0; i < 4; i++) {
            if (countResult.suitCount[i] > countResult.maxSuitCount) {
                countResult.maxSuitCount = countResult.suitCount[i];
                countResult.maxCountSuit = i;
            }
        }
        if (countResult.maxSuitCount >= 5) {
            countResult.isFlush = true;
            countResult.flushCards = new Card[countResult.maxSuitCount];
            int flushCount = 0;
            for (int i = 0; i < candidateCards.length; i++) {
                String card = candidateCards[i];
                Card c = new Card(card);
                if (c.suitNum == countResult.maxCountSuit) {
                    countResult.flushCards[flushCount] = c;
                    flushCount++;
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
        System.out.println("countResult: \n" + countResult.toString(1));
        return countResult;
    }

    // These checking functions should be run in order high to low.
    // 
    boolean checkRoyalFlush(CountResult countResult) {
        if (!checkStraightFlush(countResult)) {
            return false;
        }
        return countResult.straightCards[0].rank.equals("A");
    }

    boolean checkStraightFlush(CountResult countResult) {
        return countResult.isStraight && countResult.isFlush;
    }

    boolean checkFourOfAKind(CountResult countResult) {
        return countResult.maxRankCount == 4;
    }

    boolean checkFullHouse(CountResult countResult) {
        return countResult.setCount == 2 || (countResult.setCount == 1 && countResult.pairCount > 0);
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

    ShowDownResult getShowDownResult(Player player, String[] board) {
        String[] privateCard = player.getPrivateCard();
        System.arraycopy(board, 0, candidateCards, 0, board.length);
        candidateCards[5] = privateCard[0];
        candidateCards[6] = privateCard[1];

        CountResult countResult = countCards();
        Holdem.BEST_HAND_TYPE bestHandType;
        if (checkRoyalFlush(countResult)) {
            bestHandType = Holdem.BEST_HAND_TYPE.ROYAL_FLUSH;
        } else if (checkStraightFlush(countResult)) {
            bestHandType = Holdem.BEST_HAND_TYPE.STRAIGHT_FLUSH;
        } else if (checkFourOfAKind(countResult)) {
            bestHandType = Holdem.BEST_HAND_TYPE.FOUR_OF_A_KIND;
        } else if (checkFullHouse(countResult)) {
            bestHandType = Holdem.BEST_HAND_TYPE.FULL_HOUSE;
        } else if (checkFlush(countResult)) {
            bestHandType = Holdem.BEST_HAND_TYPE.FLUSH;
        } else if (checkStraight(countResult)) {
            bestHandType = Holdem.BEST_HAND_TYPE.STRAIGHT;
        } else if (checkThreeOfAKind(countResult)) {
            bestHandType = Holdem.BEST_HAND_TYPE.THREE_OF_A_KIND;
        } else if (checkTwoPair(countResult)) {
            bestHandType = Holdem.BEST_HAND_TYPE.TWO_PAIR;
        } else if (checkPair(countResult)) {
            bestHandType = Holdem.BEST_HAND_TYPE.PAIR;
        } else {
            bestHandType = Holdem.BEST_HAND_TYPE.HIGH_CARD;
        }

        ShowDownResult res = new ShowDownResult(bestHandType, candidateCards, countResult);
        System.out.println("showDownResult: " + res);
        return res;
    }
}
