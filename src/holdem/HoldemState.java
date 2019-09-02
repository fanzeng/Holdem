package holdem;
import java.util.Scanner;

public class HoldemState {
    int cardStage;
    int betStage;
    boolean roundCompleted;
    int[] playerBet;
    HoldemState(int cardStage_, int betStage_, int[] playerBet_, boolean roundCompleted_) {
        cardStage = cardStage_;
        betStage = betStage_;
        playerBet = playerBet_;
        roundCompleted = roundCompleted_;
    }

    public HoldemState next() {
        int nextCardStage = cardStage;
        int nextBetStage;

        System.out.println("Player 0 bets: " + playerBet[0] + ", Player 1 bets: " + playerBet[1]);
        System.out.println("Enter player" + betStage + "'s next bet:");
        Scanner sc = new Scanner(System.in);
        playerBet[betStage] = sc.nextInt();
        if (playerBet[betStage] < playerBet[1-betStage]) {
            System.out.println("Player " + betStage + " folded.");
            return new HoldemState(nextCardStage, -1, playerBet, false);
        }
        if (betStage == 1) roundCompleted = true;
        if (playerBet[betStage] == playerBet[1-betStage] && roundCompleted) {
            nextBetStage = -1;
        } else {
            nextBetStage = 1 - betStage;
        }
        System.out.println("nextBetStage = " + nextBetStage);
        if (nextBetStage == -1) {
            nextCardStage = cardStage+1;
            roundCompleted = false;
            if (nextCardStage == 4) {
                return new HoldemState(0, 0, new int[2], false);
            }
            nextBetStage = 0;
        }
        System.out.println("nextCardStage = " + nextCardStage);

        return new HoldemState(nextCardStage, nextBetStage, playerBet, roundCompleted);
    }
    
    public static void main(String[] args) {
        HoldemState hs = new HoldemState(0, 0, new int[2], false);
        while(true) {
            System.out.println("cardStage = " + hs.cardStage + ", betStage = " + hs.betStage);
            HoldemState nextHoldemState = hs.next();
            if (nextHoldemState.betStage == -1) {
                System.out.println("Game ended at cardStage " + nextHoldemState.cardStage);
                hs = new HoldemState(0, 0, new int[2], false);
            } else {
                hs = nextHoldemState;
            }
            System.out.println();
            System.out.println();

        }
    }
}
    