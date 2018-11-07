package fr.dta.kenny.tp.yourlittlepony.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dta.kenny.tp.yourlittlepony.dao.PonyDAO;
import fr.dta.kenny.tp.yourlittlepony.exception.ResourceNotFoundException;
import fr.dta.kenny.tp.yourlittlepony.model.Pony;

@RestController
@RequestMapping("/pony")
public class PonyController {
	
	@Autowired
	PonyDAO ponyDAO;
	
	@CrossOrigin(origins = "*")
	@GetMapping("/")
	public List<Pony> getAll() {
		return ponyDAO.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Pony getById(@PathVariable("id") Long id) {
		Optional<Pony> oPony = ponyDAO.findById(id);
		if(oPony.isPresent()) {
			return oPony.get();
		}
		throw new ResourceNotFoundException( "Pony not found" );
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		ponyDAO.deleteById(id);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/create")
	public void create(@RequestBody @Valid Pony pony) {
		ponyDAO.save(pony);
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping("/{id}")
	public Pony update(@PathVariable("id") Long id, @RequestBody @Valid Pony pony) {
		Optional<Pony> ponyToChange = ponyDAO.findById(id);
		if (ponyToChange.isPresent()) {
			ponyToChange.get().setName(pony.getName());
			ponyToChange.get().setColor(pony.getColor());
			ponyToChange.get().setAge(pony.getAge());
			ponyToChange.get().setWeight(pony.getWeight());
			
			Pony updatePony = ponyDAO.save(ponyToChange.get());
			return updatePony;
		}
		throw new ResourceNotFoundException( "Pony not found" );

	}
}
