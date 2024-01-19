package com.fanzengau.holdemservice.mappers;

import com.fanzengau.holdem.Holdem;
import com.fanzengau.holdemservice.models.HoldemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface HoldemMapper {
    HoldemMapper INSTANCE = Mappers.getMapper( HoldemMapper.class );

    HoldemDto holdemToHoldemDto(Holdem holdem);

    Holdem holdemDtoToHoldem(HoldemDto holdemDto);
}
