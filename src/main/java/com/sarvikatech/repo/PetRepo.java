package com.sarvikatech.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarvikatech.model.Pet;

public interface PetRepo extends JpaRepository<Pet, Long> {

	List<Pet> findBySpecies(String species);

}
