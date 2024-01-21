package com.fanzengau.holdem;

public class Player {
    public int stack;
    String[] privateCards;
    public Player (int stack) {
        this.stack = stack;
    }

    public String[] getprivateCards() {
        return privateCards;
    }

    public void setprivateCards(String[] privateCards)
    {
        this.privateCards = privateCards;
    }

    public int incrStack(int incr) {
        stack += incr;
        return stack;
    }
    public int decrStack(int decr) {
        stack -= decr;
        return stack;
    }
}