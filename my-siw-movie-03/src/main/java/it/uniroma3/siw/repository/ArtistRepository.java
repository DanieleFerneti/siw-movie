package it.uniroma3.siw.repository;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Artist;

public interface ArtistRepository extends CrudRepository<Artist,Long>{
	public boolean existsByNameAndUsernameAndData(String name, String username, LocalDate data);	
	
	
}
