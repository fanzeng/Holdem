package com.fanzengau.holdem;

public class Player {
    public int stack;
    String[] privateCard;
    public Player (int initialStack) {
        stack = initialStack;
    }

    public String[] getPrivateCard() {
        return privateCard;
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