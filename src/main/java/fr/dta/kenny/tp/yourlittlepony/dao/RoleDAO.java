package fr.dta.kenny.tp.yourlittlepony.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dta.kenny.tp.yourlittlepony.model.Role;

public interface RoleDAO extends JpaRepository<Role, Long> {
	Role findByRole(String role);
}
