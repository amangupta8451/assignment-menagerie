package com.sarvikatech.model;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "id")
    private Pet pet;
    @Column(columnDefinition="date NOT NULL")
    private LocalDate date;
    @Column(columnDefinition=" varchar(15) DEFAULT NULL")
    private String type;
    @Column(columnDefinition=" varchar(255) DEFAULT NULL")
    private String remark;
    // Getters and setters
    public Long getId() {
        return id;
    }

    public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Event(Long id, Pet pet, LocalDate date, String type, String remark) {
		super();
		this.id = id;
		this.pet = pet;
		this.date = date;
		this.type = type;
		this.remark = remark;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}