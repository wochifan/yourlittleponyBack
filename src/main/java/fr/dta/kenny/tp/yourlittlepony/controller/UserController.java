package fr.dta.kenny.tp.yourlittlepony.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dta.kenny.tp.yourlittlepony.dao.UserDAO;
import fr.dta.kenny.tp.yourlittlepony.exception.ResourceNotFoundException;
import fr.dta.kenny.tp.yourlittlepony.model.User;

@RestController
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/")
	public List<User> getAll() {
		return userDAO.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public User getById(@PathVariable("id") Long id) {
		Optional<User> oUser = userDAO.findById(id);
		if(oUser.isPresent()) {
			return oUser.get();
		}
		throw new ResourceNotFoundException( "Pony not found" );
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		userDAO.deleteById(id);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/create")
	public void create(@RequestBody @Valid User user) {
		userDAO.save(user);
	}
	
    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }
}
