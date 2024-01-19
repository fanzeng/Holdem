package com.fanzengau.holdemservice;

import com.fanzengau.holdem.HoldemState;
import com.fanzengau.holdem.Player;
import com.fanzengau.holdemservice.models.GameSession;
import com.fanzengau.holdemservice.repositories.GameSessionRepository;
import com.fanzengau.holdemservice.mappers.HoldemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.fanzengau.holdem.Holdem;

import java.util.Arrays;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class HoldemController {
    private static Player[] players;
    private static PlayerBet[] playerBets;

    @Autowired
    private HoldemMapper holdemMapper;
    @Autowired
    private GameSessionRepository gameSessionRepository;

    private Holdem getHoldemByGameSessionId(String gameSessionId) {
        log.info(">>>> gameSessionId: " + gameSessionId);
        var gameSession = gameSessionRepository.findById(gameSessionId);
        if (gameSession.isPresent()) {
            return holdemMapper.holdemDtoToHoldem(gameSession.get().getHoldemDto());
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SessionId Not Found");
        }
    }

    private void saveHoldemToGameSession(Holdem holdem, String gameSessionId) {
        var gameSession = gameSessionRepository.findById(gameSessionId).get();
        gameSession.setHoldemDto(holdemMapper.holdemToHoldemDto(holdem));
        var saved = gameSessionRepository.save(gameSession);
        log.info(">>>> Saved game session:" + saved.getId());
    }

    @GetMapping("/new-game")
    @CrossOrigin(origins = {"http://localhost:3000", "https://epicbeaver.netlify.app", "https://fanzengau.com"})
    public String newGame() {
        var holdem = new Holdem(2);
        GameSession gameSession = GameSession.builder()
            .name("shuffled new")
            .holdemDto(holdemMapper.holdemToHoldemDto(holdem))
            .build();
        var saved = gameSessionRepository.save(gameSession);
        log.info(">>>> Created game session:" + saved.getId());
        players = new Player[]{new Player(1000), new Player(1000)};
        playerBets = new PlayerBet[]{new PlayerBet("0", 0), new PlayerBet("1", 0)};
        return saved.getId();
    }

    @GetMapping("/players")
    @CrossOrigin(origins = {"http://localhost:3000", "https://epicbeaver.netlify.app", "https://fanzengau.com"})
    public Player[] players() {
        return players;
    }

    @GetMapping("/shuffle")
    @CrossOrigin(origins = {"http://localhost:3000", "https://epicbeaver.netlify.app", "https://fanzengau.com"})
    public String[] shuffle(
        @RequestParam(required = true) String gameSessionId
    ) {
        var holdem = new Holdem(2);
        holdem.shuffle();
        saveHoldemToGameSession(holdem, gameSessionId);
        var privateCards0 = holdem.getPlayerCard(Integer.parseInt("0"));
        var privateCards1 = holdem.getPlayerCard(Integer.parseInt("1"));
        return Stream.concat(Arrays.stream(privateCards0), Arrays.stream(privateCards1)).toArray(String[]::new);
    }

    @GetMapping("/get-player-card")
    @CrossOrigin(origins = {"http://localhost:3000", "https://epicbeaver.netlify.app", "https://fanzengau.com"})
    public String[] getPlayerCard(
        @RequestParam(required = true) String gameSessionId,
        @RequestParam(required = true, defaultValue = "0") String playerId
    ) {
        var holdem = getHoldemByGameSessionId(gameSessionId);
        holdem.dealCardForPlayer(Integer.parseInt(playerId));
        var privateCards = holdem.getPlayerCard(Integer.parseInt(playerId));
        saveHoldemToGameSession(holdem, gameSessionId);
        return privateCards;
    }

    @GetMapping("/get-flop")
    @CrossOrigin(origins = {"http://localhost:3000", "https://epicbeaver.netlify.app", "https://fanzengau.com"})
    public String[] getFlop(
        @RequestParam(required = true) String gameSessionId
    ) {
        var holdem = getHoldemByGameSessionId(gameSessionId);
        var flop = holdem.getFlop();
        System.out.println(flop[0] + "," + flop[1] + "," + flop[2]);
        saveHoldemToGameSession(holdem, gameSessionId);
        return flop;
    }

    @GetMapping("/get-turn")
    @CrossOrigin(origins = {"http://localhost:3000", "https://epicbeaver.netlify.app", "https://fanzengau.com"})
    public String[] getTurn(
        @RequestParam(required = true) String gameSessionId
    ) {
        var holdem = getHoldemByGameSessionId(gameSessionId);
        var turn = holdem.getTurn();
        saveHoldemToGameSession(holdem, gameSessionId);
        return new String[]{turn};
    }
    @GetMapping("/get-river")
    @CrossOrigin(origins = {"http://localhost:3000", "https://epicbeaver.netlify.app", "https://fanzengau.com"})
    public String[] getRiver(
        @RequestParam(required = true) String gameSessionId
    ) {
        var holdem = getHoldemByGameSessionId(gameSessionId);
        var river = holdem.getRiver();
        saveHoldemToGameSession(holdem, gameSessionId);
        return new String[]{river};
    }

    @GetMapping("/get-community-cards")
    @CrossOrigin(origins = {"http://localhost:3000", "https://epicbeaver.netlify.app", "https://fanzengau.com"})
    public String[] getCommunityCards(
        @RequestParam() String gameSessionId
    ) {
        var holdem = getHoldemByGameSessionId(gameSessionId);
        var flop = holdem.getFlop();
        var turn = holdem.getTurn();
        var river = holdem.getRiver();
        saveHoldemToGameSession(holdem, gameSessionId);
        return new String[]{flop[0], flop[1], flop[2], turn, river};
    }

    @GetMapping("/show-down")
    @CrossOrigin(origins = {"http://localhost:3000", "https://epicbeaver.netlify.app", "https://fanzengau.com"})
    public String[] showDown(
        @RequestParam() String gameSessionId
    ) {
        var holdem = getHoldemByGameSessionId(gameSessionId);
        var result = holdem.showDown();
        saveHoldemToGameSession(holdem, gameSessionId);
        return result;
    }

    @GetMapping("/get-holdem-state")
    @CrossOrigin(origins = {"http://localhost:3000", "https://epicbeaver.netlify.app", "https://fanzengau.com"})
    public HoldemState getHoldemState(
        @RequestParam() String gameSessionId
    ) {
        var holdem = getHoldemByGameSessionId(gameSessionId);
        return holdem.holdemState;
    }

    @PostMapping("/next-holdem-state")
    @CrossOrigin(origins = {"http://localhost:3000", "https://epicbeaver.netlify.app", "https://fanzengau.com"})
    public HoldemState nextHoldemState(
        @RequestParam() String gameSessionId
    ) {
        var holdem = getHoldemByGameSessionId(gameSessionId);
        var holdemState = holdem.holdemState;
        holdemState = holdemState.next(new int[]{playerBets[0].betValue, playerBets[1].betValue});
        holdem.holdemState = holdemState;
        saveHoldemToGameSession(holdem, gameSessionId);
        return holdemState;
    }

    @PostMapping("/player-bet")
    @CrossOrigin(origins = {"http://localhost:3000", "https://epicbeaver.netlify.app", "https://fanzengau.com"})
    public PlayerBet[] playerBet(
        @RequestParam() String gameSessionId,
        @RequestBody PlayerBet playerBet
    ) {
        var holdem = getHoldemByGameSessionId(gameSessionId);
        var holdemState = holdem.holdemState;
        var cardStage = holdemState.cardStage;
        this.playerBets[Integer.parseInt(playerBet.id)] = playerBet;
        players[Integer.parseInt(playerBet.id)].decrStack(playerBet.betValue);
        var hs = nextHoldemState(gameSessionId);
        if (hs.cardStage != cardStage) {
            playerBets[0].betValue = 0;
            playerBets[1].betValue = 0;
        }
        return playerBets;
    }
}