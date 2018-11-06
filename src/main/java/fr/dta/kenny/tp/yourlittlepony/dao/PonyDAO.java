package fr.dta.kenny.tp.yourlittlepony.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dta.kenny.tp.yourlittlepony.model.Pony;

@Repository
public interface PonyDAO extends JpaRepository<Pony, Long>{

}
