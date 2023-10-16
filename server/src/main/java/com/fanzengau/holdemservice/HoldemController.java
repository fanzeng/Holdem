package com.fanzengau.holdemservice;

import com.fanzengau.holdem.HoldemState;
import com.fanzengau.holdem.Player;
import org.springframework.web.bind.annotation.*;

import com.fanzengau.holdem.Holdem;

import java.util.Arrays;
import java.util.stream.Stream;

@RestController
public class HoldemController {
    private static Holdem holdem;
    private static HoldemState holdemState;
    private static Player[] players;
    private static PlayerBet[] playerBets;

    @GetMapping("/")
    @CrossOrigin(origins = "http://localhost:3000")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/new-game")
    @CrossOrigin(origins = "http://localhost:3000")
    public Player[] newGame() {
        players = new Player[]{new Player(1999), new Player(2000)};
        playerBets = new PlayerBet[]{new PlayerBet("0", 0), new PlayerBet("1", 0)};
        return players;
    }

    @GetMapping("/players")
    @CrossOrigin(origins = "http://localhost:3000")
    public Player[] players() {
        return players;
    }

    @GetMapping("/shuffle")
    @CrossOrigin(origins = "http://localhost:3000")
    public String[] shuffle() {
        holdem = new Holdem(2);
        holdemState = new HoldemState();
        System.out.println("reset holdemState.");
        holdem.dealCardForPlayer(0);
        holdem.dealCardForPlayer(1);
        holdem.getFlop();
        holdem.dealTurn();
        holdem.dealRiver();
        var privateCards0 = holdem.getPlayerCard(Integer.parseInt("0"));
        var privateCards1 = holdem.getPlayerCard(Integer.parseInt("1"));

        return Stream.concat(Arrays.stream(privateCards0), Arrays.stream(privateCards1)).toArray(String[]::new);
    }

    @GetMapping("/get-player-card")
    @CrossOrigin(origins = "http://localhost:3000")
    public String[] getPlayerCard(@RequestParam(required = true, defaultValue = "0") String id) {
        holdem.dealCardForPlayer(Integer.parseInt(id));
        var privateCards = holdem.getPlayerCard(Integer.parseInt(id));
        System.out.println("private cards =" + privateCards);
        return privateCards;
    }

    @GetMapping("/get-flop")
    @CrossOrigin(origins = "http://localhost:3000")
    public String[] getFlop() {
        var flop = holdem.getFlop();
        System.out.println(flop[0] + "," + flop[1] + "," + flop[2]);
        return flop;
    }

    @GetMapping("/deal-turn")
    @CrossOrigin(origins = "http://localhost:3000")
    public String[] dealTurn() {
        var turn = holdem.dealTurn();
        return new String[]{turn};
    }
    @GetMapping("/deal-river")
    @CrossOrigin(origins = "http://localhost:3000")
    public String[] dealRiver() {
        var river = holdem.dealRiver();
        return new String[]{river};
    }

    @GetMapping("/get-community-cards")
    @CrossOrigin(origins = "http://localhost:3000")
    public String[] getCommunityCards() {
        var flop = holdem.getFlop();
        var turn = holdem.dealTurn();
        var river = holdem.dealRiver();
        return new String[]{flop[0], flop[1], flop[2], turn, river};
    }

    @GetMapping("/show-down")
    @CrossOrigin(origins = "http://localhost:3000")
    public String[] showDown() {
        return holdem.showDown();
    }

    @GetMapping("/get-holdem-state")
    @CrossOrigin(origins = "http://localhost:3000")
    public HoldemState getHoldemState() {
        return holdemState;
    }

    @PostMapping("/next-holdem-state")
    @CrossOrigin(origins = "http://localhost:3000")
    public HoldemState nextHoldemState() {
        holdemState = holdemState.next(new int[]{playerBets[0].betValue, playerBets[1].betValue});
        return holdemState;
    }

    @PostMapping("/player-bet")
    @CrossOrigin(origins = "http://localhost:3000")
    public PlayerBet[] playerBet(
            @RequestBody PlayerBet playerBet
    ) {
        var cardStage = holdemState.cardStage;
        this.playerBets[Integer.parseInt(playerBet.id)] = playerBet;
        players[Integer.parseInt(playerBet.id)].decrStack(playerBet.betValue);
        var hs = nextHoldemState();
        if (hs.cardStage != cardStage) {
            playerBets[0].betValue = 0;
            playerBets[1].betValue = 0;
        }
        return playerBets;
    }
}