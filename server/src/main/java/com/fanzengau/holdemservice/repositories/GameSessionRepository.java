package com.fanzengau.holdemservice.repositories;
import com.fanzengau.holdemservice.models.GameSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GameSessionRepository extends CrudRepository<GameSession, String> {
}