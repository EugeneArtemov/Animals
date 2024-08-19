package com.example.Animals.Mapper;

import com.example.Animals.Dto.AnimalHistoryDto;

import com.example.Animals.Entity.AnimalHistory;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnimalHistoryMapper {
    AnimalHistoryDto toDto(AnimalHistory animalHistory);
    AnimalHistory toEntity(AnimalHistoryDto animalHistoryDto);


}
