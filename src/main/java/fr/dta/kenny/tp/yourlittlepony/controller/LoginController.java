package fr.dta.kenny.tp.yourlittlepony.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@GetMapping("/login")
	public void showLoginPage() {
		
	}
	
}
