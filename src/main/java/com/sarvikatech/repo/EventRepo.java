package com.sarvikatech.repo;

import java.util.List;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarvikatech.model.Event;
import com.sarvikatech.model.Pet;

public interface EventRepo extends JpaRepository<Event, Long> {

	//List<Event> findByPetIdOrderByDateDesc(Pet pet, Sort sort);

	List<Event> findByPetOrderByDateDesc(Pet pet);

	List<Event> findByPetOrderByDateDesc(Pet pet, Sort sort);

	List<Event> findByPet(Pet pet);

	List<Event> findByPetIdOrderByDateDesc(Pet pet);

}
