package holdem;
import java.util.Scanner;

public class HoldemState {
    int cardStage;
    int playerStage;  // Player0 always acts first
    int[] playerBets; // array of total bet amount of each single player
    boolean roundCompleted;
    HoldemState(int cardStage_, int betStage_, int[] playerBets_, boolean roundCompleted_) {
        cardStage = cardStage_;
        playerStage = betStage_;
        playerBets = playerBets_;
        roundCompleted = roundCompleted_;
    }

    public HoldemState next() {
        int nextBetStage;
        System.out.println("cardStage: " + cardStage + ", betStage: " + playerStage);
        System.out.println("Player 0's bet: " + playerBets[0] + ", Player 1's bet: " + playerBets[1]);
        System.out.println("Enter player" + playerStage + "'s next bet:");
        Scanner sc = new Scanner(System.in);
        playerBets[playerStage] = sc.nextInt();
        if (playerStage == 1) roundCompleted = true;
        if (playerBets[playerStage] < playerBets[1-playerStage]) {
            System.out.println("Player " + playerStage + " folded.");
            System.out.println("Game ended at cardStage " + cardStage);
            return new HoldemState(0, 0, new int[2], false);
        } else if (playerBets[playerStage] == playerBets[1 - playerStage] && roundCompleted) {
            cardStage += 1;
            if (cardStage > 3) {
                System.out.println("Show down.");
                return new HoldemState(0, 0, new int[2], false);
            } else {
                return new HoldemState(cardStage, 0, playerBets, false);
            }
        } else {
            return new HoldemState(cardStage, 1 - playerStage, playerBets, roundCompleted); 
        }
    }
    
    public static void main(String[] args) {
        HoldemState hs = new HoldemState(0, 0, new int[2], false);
        while(true) {
            hs = hs.next();
            System.out.println();
        }
    }
}
    