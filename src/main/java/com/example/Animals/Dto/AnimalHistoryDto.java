package com.example.Animals.Dto;

import lombok.Builder;

@Builder
public record AnimalHistoryDto(Long id, Long animalId, String reasonForDisappearance, String description) {

}
