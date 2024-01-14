package com.fanzengau.holdemservice.models;
import com.fanzengau.holdem.Holdem;
import com.fanzengau.holdem.HoldemState;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@RedisHash

public class GameSession {
    @Id
    private String id;
    private String name;
    private Holdem holdem;
}

