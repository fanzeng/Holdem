package com.fanzengau.holdemservice.models;

import lombok.Data;

@Data
public class HoldemDto {
    int playerNum;
    String[] flop;
    String turn;
    String river;
    int pot;
    PlayerDto[] players;
    HoldemStateDto holdemState;
}
