package fr.dta.kenny.tp.yourlittlepony.service;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.dta.kenny.tp.yourlittlepony.dao.RoleDAO;
import fr.dta.kenny.tp.yourlittlepony.dao.UserDAO;
import fr.dta.kenny.tp.yourlittlepony.model.User;

import static java.util.Collections.emptyList;

@Service("userService")
public class UserService implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;
	 
	@Autowired
	private RoleDAO roleDAO;

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User applicationUser = userDAO.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }
	
	/*
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	*/
/*
	public User findUserByUsername(String username) {
		Optional<User> oUser = userDAO.findUserWithName(username);
		if (oUser.isPresent() ) {
			return oUser.get();
		}
		throw new ResourceNotFoundException( "User not found" );
	}
	*/
	
	/*
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Role userRole = roleDAO.findByRole("USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userDAO.save(user);
	}
	 
	    */
	    
}
