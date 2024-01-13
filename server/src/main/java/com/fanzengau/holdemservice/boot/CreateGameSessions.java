package com.fanzengau.holdemservice.boot;
import com.fanzengau.holdemservice.models.GameSession;
import com.fanzengau.holdemservice.repositories.GameSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Order(1)
@Slf4j
public class CreateGameSessions implements CommandLineRunner {

    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Override
    public void run(String... args) throws Exception {
        if (gameSessionRepository.count() == 0) {
            GameSession gameSession = GameSession.builder().name("test game session").build();
            gameSessionRepository.save(gameSession);
            log.info(">>>> Created game session");
        }
    }
}