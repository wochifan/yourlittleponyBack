package fr.dta.kenny.tp.yourlittlepony.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="race")
public class Race {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String location;
	
	@ManyToMany
	@JoinTable(name="race_pony", joinColumns=@JoinColumn(name="race_id"), inverseJoinColumns=@JoinColumn(name="pony_id")) 
	private List<Pony> ponies = new ArrayList<Pony>();
	
	@Column
	private Date date;
	
	public Race() {
		
	}
	
	public Race(String location, Date date) {
		this.location = location;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Pony> getPonies() {
		return ponies;
	}

	public void setPonies(List<Pony> ponies) {
		this.ponies = ponies;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
