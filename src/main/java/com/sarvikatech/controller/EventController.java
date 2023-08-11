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
public class EventController {
	 @Autowired
	    private PetRepo petRepository;
	 @Autowired
	    private EventRepo eventRepository;
	

	 
	 @GetMapping("/pets/{id}")
	 public ResponseEntity<Pet> getPetEvents(
	         @PathVariable Long id,
	         @RequestParam(required = false) String sortKey,
	         @RequestParam(required = false) String sortOrder) {

	     Optional<Pet> petOptional = petRepository.findById(id);
	     if (petOptional.isPresent()) {
	         Pet pet = petOptional.get();

	         List<Event> events = eventRepository.findByPet(pet);
	         
	         if (sortKey != null && sortOrder != null) {
	             Sort sort = Sort.by(Direction.fromString(sortOrder), sortKey);
	             events.sort(Comparator.comparing(Event::getDate, Comparator.nullsLast(Comparator.naturalOrder())).reversed());
	         }

	         return ResponseEntity.ok(pet);
	     } else {
	         return ResponseEntity.notFound().build();
	     }
	 }
	 
	 @PostMapping("/pets/{id}")
	 public ResponseEntity addEvent(@PathVariable Long id, @RequestBody Event event) {
		
	     Optional<Pet> petOptional = petRepository.findById(id);
	     if (petOptional.isPresent()) {
	         event.setPet(petOptional.get());
	         Event savedEvent = eventRepository.save(event);
	         return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
	     } else {
	    	 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet with ID: "+id+" NOT FOUND");
	     }
	 }


}
