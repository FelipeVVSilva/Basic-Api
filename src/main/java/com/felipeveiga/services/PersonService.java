package com.felipeveiga.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipeveiga.domain.Adress;
import com.felipeveiga.domain.Person;
import com.felipeveiga.domain.dto.PersonDTO;
import com.felipeveiga.domain.dto.PersonNewDTO;
import com.felipeveiga.repositories.PersonRepository;
import com.felipeveiga.services.exceptions.ObjectNotFoundException;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repo;
	
	public List<Person> findAll() {
		List<Person> list = repo.findAll();
		return list;
	}
	
	public Person findById(Long id) {
		Optional<Person> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found: " + id
				+ "Cause: " + obj.getClass().getName()));
	}
	
	@Transactional
	public Person insert(Person obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Person update(Person obj) {
		findById(obj.getId());
		Person newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Person newObj, Person obj) {
		newObj.setName(obj.getName());
		//newObj.setGender(obj.getGender());
		//newObj.setAdresses(obj.getAdresses());
	}
	
	public void delete(Long id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public Person fromDto(PersonDTO objDto) {
		return new Person(null, objDto.getName(), objDto.getGender());
	}
	
	public Person fromDto(PersonNewDTO objDto) {
		Person per = new Person(null, objDto.getName(), objDto.getGender());
		return per;
	}
}
