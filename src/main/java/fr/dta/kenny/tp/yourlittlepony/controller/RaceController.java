package fr.dta.kenny.tp.yourlittlepony.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import fr.dta.kenny.tp.yourlittlepony.model.Pony;
import fr.dta.kenny.tp.yourlittlepony.model.Race;

@RestController
@RequestMapping("/race")
public class RaceController {

	@Autowired
	RaceDAO raceDAO;
	
	@GetMapping("/")
	public List<Race> getAll() {
		return raceDAO.findAll();
	}
	
	@GetMapping("/{id}")
	public Race getById(@PathVariable("id") Long id) {
		Optional<Race> oRace = raceDAO.findById(id);
		if(oRace.isPresent()) {
			return oRace.get();
		}
		throw new ResourceNotFoundException( "Course not found" );
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		raceDAO.deleteById(id);
	}
	
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody @Valid Race race) {
		raceDAO.save(race);
	}
	
	@PutMapping("/{id}")
	public Race update(@PathVariable("id") Long id, @RequestBody @Valid Race race) {
		Optional<Race> raceToChange = raceDAO.findById(id);
		if (raceToChange.isPresent()) {
			raceToChange.get().setLocation(race.getLocation());
			raceToChange.get().setDate(race.getDate());
			raceToChange.get().setPonies(race.getPonies());
			
			Race updateRace = raceDAO.save(raceToChange.get());
			return updateRace;
		}
		throw new ResourceNotFoundException( "Course not found" );

	}
	
	
	
}
