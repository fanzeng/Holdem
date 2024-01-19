package com.fanzengau.holdemservice.models;

import com.fanzengau.holdem.HoldemState;
import lombok.Data;

@Data
public class HoldemStateDto {
    public HoldemState.CARD_STAGE cardStage;
    public int playerStage;
    int[] playerBets;
    boolean roundCompleted;
}
