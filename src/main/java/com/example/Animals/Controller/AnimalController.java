package com.example.Animals.Controller;

import com.example.Animals.Dto.AnimalDto;
import com.example.Animals.Dto.AnimalHistoryDto;
import com.example.Animals.Service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/animals")
public class AnimalController {
    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public List<AnimalDto> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/history")
    public List<AnimalHistoryDto> getAnimalHistory() {
        return animalService.getAllAnimalHistory();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AnimalDto> updateAnimalStatus(@PathVariable Long id, @RequestBody
    Map<String, String> statusUpdate) {
        String newStatus = statusUpdate.get("status");
        AnimalDto updatedAnimal = animalService.updateAnimalStatus(id, newStatus);
        if (updatedAnimal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAnimal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalDto> getAnimalById(@PathVariable Long id) {
        AnimalDto animalDto = animalService.getAnimalById(id);
        return ResponseEntity.ok(animalDto);
    }


    @PostMapping
    public AnimalDto addAnimal(@RequestBody AnimalDto animalDto) {
        return animalService.addAnimal(animalDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAnimal(@PathVariable Long id, @RequestParam String reason, @RequestParam String description) {
        animalService.removeAnimal(id, reason, description);
        return ResponseEntity.noContent().build();
    }


}
