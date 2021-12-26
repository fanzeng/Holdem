package holdem;
import java.util.Scanner;

public class HoldemState {
    public int cardStage;
    public int playerStage;  // Player0 always acts first
    int[] playerBets; // array of total bet amount of each single player
    boolean roundCompleted;
    HoldemState() {
        cardStage = 0;
        playerStage = 0;
        playerBets = new int[2];
        roundCompleted = false;
    }
    HoldemState(int cardStage_, int betStage_, int[] playerBets_, boolean roundCompleted_) {
        cardStage = cardStage_;
        playerStage = betStage_;
        playerBets = playerBets_;
        roundCompleted = roundCompleted_;
    }
    
    public int[] getPlayerBets() {
        return playerBets;
    }
    
    public HoldemState next(int[] playerBets_) {
        int nextBetStage;
        playerBets = playerBets_;
        System.out.println("cardStage: " + cardStage + ", betStage: " + playerStage);
        System.out.println("Player 0's bet: " + playerBets[0] + ", Player 1's bet: " + playerBets[1]);

        if (playerStage == 1) roundCompleted = true;
        if (playerBets[playerStage] < playerBets[1-playerStage]) {
            System.out.println("Player " + playerStage + " folded.");
            System.out.println("Game ended at cardStage " + cardStage);
            return new HoldemState();
        } else if (playerBets[playerStage] == playerBets[1 - playerStage] && roundCompleted) {
            cardStage += 1;
            if (cardStage > 3) {
                System.out.println("Show down.");
                return new HoldemState();
            } else {
                return new HoldemState(cardStage, 0, playerBets, false);
            }
        } else {
            return new HoldemState(cardStage, 1 - playerStage, playerBets, roundCompleted); 
        }
    }
    
    public static void main(String[] args) {
        HoldemState hs = new HoldemState();
        while(true) {
            System.out.println("cardStage: " + hs.cardStage + ", betStage: " + hs.playerStage);
            System.out.println("Enter player" + hs.playerStage + "'s next bet:");
            Scanner sc = new Scanner(System.in);
            int[] playerBets = hs.getPlayerBets();
            
            playerBets[hs.playerStage] = sc.nextInt();
            hs = hs.next(playerBets);
            System.out.println();
        }
    }
}
    