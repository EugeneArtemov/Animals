package com.example.Animals.Service;

import com.example.Animals.Dto.AnimalDto;
import com.example.Animals.Dto.AnimalHistoryDto;
import com.example.Animals.Entity.Animal;
import com.example.Animals.Entity.AnimalHistory;
import com.example.Animals.Mapper.AnimalHistoryMapper;
import com.example.Animals.Mapper.AnimalMapper;
import com.example.Animals.Repository.AnimalHistoryRepository;
import com.example.Animals.Repository.AnimalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalHistoryRepository animalHistoryRepository;
    private final AnimalMapper animalMapper;
    private final AnimalHistoryMapper animalHistoryMapper;


    public AnimalService(AnimalRepository animalRepository, AnimalHistoryRepository animalHistoryRepository, AnimalMapper animalMapper,
                         AnimalHistoryMapper animalHistoryMapper) {
        this.animalRepository = animalRepository;
        this.animalHistoryRepository = animalHistoryRepository;
        this.animalMapper = animalMapper;
        this.animalHistoryMapper = animalHistoryMapper;
    }

    public List<AnimalDto> getAllAnimals() {
        return animalRepository.findAll().stream()
            .map(animalMapper::toDto)
            .collect(Collectors.toList());
    }

    public AnimalDto getAnimalById(Long id) {
        return animalRepository.findById(id)
            .map(animalMapper::toDto)
            .orElseThrow(() -> new EntityNotFoundException("Animal not found"));
    }

    public List<AnimalHistoryDto> getAllAnimalHistory() {
        return animalHistoryRepository.findAll().stream()
            .map(animalHistoryMapper::toDto)
            .collect(Collectors.toList());
    }

    public AnimalDto addAnimal(AnimalDto animalDto) {
        Animal animal = animalMapper.toEntity(animalDto);
        return animalMapper.toDto(animalRepository.save(animal));
    }

    public AnimalDto updateAnimalStatus(Long id, String newStatus) {
        Animal animal = animalRepository.findById(id).orElse(null);
        if (animal == null) {
            return null;
        }
        animal.setStatus(newStatus);
        Animal updatedAnimal = animalRepository.save(animal);
        return animalMapper.toDto(updatedAnimal);
    }

    @Transactional
    public void removeAnimal(Long id, String reason, String description) {
        Animal animal = animalRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Animal not found"));


        // Переносим запись в animal_history
        AnimalHistory history = new AnimalHistory();
        history.setAnimalId(animal.getId());
        history.setReasonForDisappearance(reason);
        history.setDescription(description);
        animalHistoryRepository.save(history);

        // Удалите запись из animals
        animalRepository.delete(animal);
    }
}
