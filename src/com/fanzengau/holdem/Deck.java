package com.fanzengau.holdem;

import java.util.Random;

public class Deck{
    public Card[] cards;

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