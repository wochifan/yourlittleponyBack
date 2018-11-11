package fr.dta.kenny.tp.yourlittlepony.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dta.kenny.tp.yourlittlepony.dao.RaceDAO;
import fr.dta.kenny.tp.yourlittlepony.exception.ResourceNotFoundException;
import fr.dta.kenny.tp.yourlittlepony.model.Race;

@RestController
@RequestMapping("/race")
public class RaceController {

	@Autowired
	RaceDAO raceDAO;
	
	@CrossOrigin(origins = "*")
	@GetMapping("/")
	public List<Race> getAll() {
		return raceDAO.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Race getById(@PathVariable("id") Long id) {
		Optional<Race> oRace = raceDAO.findById(id);
		if(oRace.isPresent()) {
			return oRace.get();
		}
		throw new ResourceNotFoundException( "Course not found" );
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		raceDAO.deleteById(id);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Race create(@RequestBody @Valid Race race) {
		return raceDAO.save(race);
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping("/{id}")
	public Race update(@RequestBody @Valid Race race) {
		return raceDAO.save(race);
	}
	
	
	
}
