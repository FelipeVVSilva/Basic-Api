package com.felipeveiga.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felipeveiga.domain.Person;
import com.felipeveiga.domain.dto.PersonDTO;
import com.felipeveiga.domain.dto.PersonNewDTO;
import com.felipeveiga.services.PersonService;

@RestController
public class PersonResource {

	@Autowired
	private PersonService service;
	
	@GetMapping("/persons")
	public ResponseEntity<List<PersonDTO>> findAll(){
		List<Person> list = service.findAll();
		List<PersonDTO> listDto = list.stream().map(x -> new PersonDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping("/persons/{id}")
	public ResponseEntity<Person> findById(@PathVariable Long id){
		Person obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping("/persons")
	public ResponseEntity<Void> insert(@RequestBody PersonNewDTO objNewDto){
		Person obj = service.fromDto(objNewDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/persons/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody PersonDTO objDto){
		Person obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/persons/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
