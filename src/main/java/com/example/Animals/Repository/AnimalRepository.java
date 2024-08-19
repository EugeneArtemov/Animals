package com.example.Animals.Repository;

import com.example.Animals.Entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByStatus(String status);
    void deleteById(Long id);
}
