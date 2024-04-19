package it.uniroma3.siw.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.repository.ArtistRepository;

@Service
public class ArtistService {

	
	@Autowired
	private ArtistRepository artistRepository;
	
	
	public Artist findById(Long id) {
		return artistRepository.findById(id).get();
	}
	
	public Iterable<Artist> findAll(){
		return artistRepository.findAll();
	}
	
	
	
	public boolean existsByNameAndUsernameAndData(String name, String username, LocalDate data) {
		return artistRepository.existsByNameAndUsernameAndData(name, username, data);
	}
	
	public Artist save(Artist artist) {
		return artistRepository.save(artist);
	}
}
