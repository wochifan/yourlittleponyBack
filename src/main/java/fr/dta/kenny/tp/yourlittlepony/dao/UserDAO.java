package fr.dta.kenny.tp.yourlittlepony.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.dta.kenny.tp.yourlittlepony.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

	User findByUsername(String username);
}


