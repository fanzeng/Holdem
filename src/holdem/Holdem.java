package holdem;

import java.util.Random;

public class Holdem {
    public int[] cards = new int[52];

    public Holdem() {
    }

    public void shuffle() {
        Random rand = new Random();
        for (int i = cards.length - 1; i > 0; i--) {
            swap(cards, i, rand.nextInt(i + 1));
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public String toString(int Card) {
        String Suit;
        String Value;

        switch (Card / 13) {
            case 0:
                Suit = "S";
                break;
            case 1:
                Suit = "H";
                break;
            case 2:
                Suit = "C";
                break;
            case 3:
                Suit = "D";
                break;
            default:
                Suit = "D";
        }
        switch (Card % 13) {
            case 10:
                Value = "T";
                break;
            case 11:
                Value = "J";
                break;
            case 12:
                Value = "Q";
                break;
            case 0:
                Value = "K";
                break;
            case 1:
                Value = "A";
                break;
            default:
                Value = Integer.toString(Card % 13);
        }
        return Suit + Value;
    }
    public boolean player1Show = false;
    public boolean player2Show = false;
    public Integer player1Stack = 1000;
    public Integer player2Stack = 1000;
    public Integer pot = 0;
}
