package com.example.Animals.Dto;

import lombok.Builder;

@Builder
public record AnimalDto(Long id, String name, String species, String breed, String status) {

}
