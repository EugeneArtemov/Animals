package com.example.Animals.Mapper;

import com.example.Animals.Dto.AnimalDto;
import com.example.Animals.Dto.AnimalHistoryDto;
import com.example.Animals.Entity.Animal;
import com.example.Animals.Entity.AnimalHistory;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
    AnimalDto toDto(Animal animal);
    Animal toEntity(AnimalDto animalDto);
}