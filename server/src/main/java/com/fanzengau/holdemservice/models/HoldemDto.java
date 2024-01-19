package com.fanzengau.holdemservice.models;

import lombok.Data;

@Data
public class HoldemDto {
    String[] flop;
    String turn;
    String river;
    PlayerDto[] players;
    HoldemStateDto holdemState;
}
