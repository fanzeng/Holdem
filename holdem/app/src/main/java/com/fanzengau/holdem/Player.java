package com.fanzengau.holdem;

public class Player {
    public int stack;
    String[] privateCard;
    public Player (int stack) {
        this.stack = stack;
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