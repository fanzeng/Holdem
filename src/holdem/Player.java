package holdem;

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