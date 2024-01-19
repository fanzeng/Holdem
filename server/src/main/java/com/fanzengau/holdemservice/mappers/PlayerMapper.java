package com.fanzengau.holdemservice.mappers;

import com.fanzengau.holdem.Player;
import com.fanzengau.holdemservice.models.PlayerDto;
import org.mapstruct.factory.Mappers;

public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper( PlayerMapper.class );

    PlayerDto playerToPlayerDto(Player player);

    Player playerDtoToPlayer(PlayerDto playerDto);
}
