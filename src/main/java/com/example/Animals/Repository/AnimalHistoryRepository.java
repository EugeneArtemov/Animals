package com.example.Animals.Repository;

import com.example.Animals.Entity.AnimalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalHistoryRepository extends JpaRepository<AnimalHistory, Long> {
    void deleteByAnimalId(Long animalId);
}
