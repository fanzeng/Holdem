package com.fanzengau.holdemservice.models;

import lombok.Data;

@Data
public class PlayerDto {
    int stack;
    String[] privateCards;
}
