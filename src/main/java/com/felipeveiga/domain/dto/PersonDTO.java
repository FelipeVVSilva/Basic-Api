package com.felipeveiga.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.felipeveiga.domain.Adress;
import com.felipeveiga.domain.Person;

public class PersonDTO {

	private Long id;
	private String name;
	private String gender;
	private List<Adress> adresses = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<Adress> getAdresses() {
		return adresses;
	}
	public void setAdresses(List<Adress> adresses) {
		this.adresses = adresses;
	}
	
	public PersonDTO() {
	
	}
	public PersonDTO(Person obj) {
		super();
		this.name = obj.getName();
		this.gender = obj.getGender();
	}
}
