package com.fanzengau.holdemservice.mappers;

import com.fanzengau.holdem.HoldemState;
import com.fanzengau.holdem.Player;
import com.fanzengau.holdemservice.models.HoldemStateDto;
import com.fanzengau.holdemservice.models.PlayerDto;
import org.mapstruct.factory.Mappers;

public interface HoldemStateMapper {
    HoldemStateMapper INSTANCE = Mappers.getMapper( HoldemStateMapper.class );

    HoldemStateDto holdemStateToHoldemStateDto(HoldemState holdemState);

    HoldemState HoldemStateDtoToHoldemState(HoldemStateDto holdemStateDto);
}
