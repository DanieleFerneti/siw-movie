package it.uniroma3.siw.service;


import java.util.List;
import java.util.Optional;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;


	public Movie findById(Long id) {
		return movieRepository.findById(id).get();
	}

	public Iterable<Movie> findAll() {
		return movieRepository.findAll();
	}

	public boolean existsByTitleAndYear(String title, Integer year) {
		return movieRepository.existsByTitleAndYear(title, year);
	}

	public List<Movie> findByYear(Integer year) {
		return movieRepository.findByYear(year);
	}

	public Movie save(Movie movie) {
		return movieRepository.save(movie);
		
	}

	public Movie movieDirector(Long idMovie,Long idArtist,ArtistService artistService){
		Movie movie = this.findById(idMovie);
		Artist artist = artistService.findById(idArtist);
		movie.setDirector(artist);
		System.out.println(movie.getDirector());
		return movie;

	}
	
	
	
}
