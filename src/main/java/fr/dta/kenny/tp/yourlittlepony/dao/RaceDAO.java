package fr.dta.kenny.tp.yourlittlepony.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dta.kenny.tp.yourlittlepony.model.Race;

@Repository
public interface RaceDAO extends JpaRepository<Race, Long> {

}
