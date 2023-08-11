package com.sarvikatech.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sarvikatech.model.Event;
import com.sarvikatech.model.Pet;
import com.sarvikatech.repo.EventRepo;
import com.sarvikatech.repo.PetRepo;

@RestController
public class PetController {
	 @Autowired
	    private PetRepo petRepository;
	 @Autowired
	    private EventRepo eventRepository;
	
	 @GetMapping("/pets")
	    public List<Pet> listPets(@RequestParam(required = false) String species) {
	        if (species != null) {
	            return petRepository.findBySpecies(species);
	        }
	        return petRepository.findAll();
	    }
	
	 @PostMapping("/pets")
	 public ResponseEntity addPet(@RequestBody Pet pet) {
		 if (petRepository.existsById(pet.getId())) {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pet with ID: "+pet.getId()+" already exists");
		    }
		    
		    try {
		        Pet savedPet = petRepository.save(pet);
		      //  logger.info("Added a new pet with ID: {}", savedPet.getId());
		        return ResponseEntity.status(HttpStatus.CREATED).body(savedPet);
		    } catch (Exception e) {
		     //   logger.error("Error adding a pet: {}", e.getMessage());
		    	 return ResponseEntity.badRequest().body("Validation error: " + e.getMessage());
		    }
	 }
	 
	 @PutMapping("/pets")
	 public ResponseEntity editPet( @RequestBody Pet pet) {
	     if (petRepository.existsById(pet.getId())) {
	         pet.setId(pet.getId());
	         Pet updatedPet = petRepository.save(pet);
	         return ResponseEntity.ok(updatedPet);
	     } else {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet with ID: "+pet.getId()+" NOT FOUND");
	     }
	 }

	 @DeleteMapping("/pet")
	 public ResponseEntity deletePet(@RequestParam Long id) {
	     Optional<Pet> optionalPet = petRepository.findById(id);
	     if (optionalPet.isPresent()) {
	         petRepository.deleteById(id);
	         //logger.info("Deleted pet with ID: {}", id);
	         return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Pet with ID: "+id+" Deleted");
	     } else {
	    	 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet with ID: "+id+" NOT FOUND");
	     }
	 }


}
