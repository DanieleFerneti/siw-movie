package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.repository.MovieRepository;
import it.uniroma3.siw.service.MovieService;


@Controller
public class MovieController {
	@Autowired
	private MovieService movieService;

	@Autowired
	private ArtistService artistService;

	@GetMapping("/movie/{id}")
	public String getMovie(@PathVariable("id") Long id, Model model) {
		model.addAttribute("movie", this.movieService.findById(id));//"movie", nome a cui attribuisco il risultato di findbyid e che segue il th:text ="${...} nel file movie.html 
		return "movie.html";
	}

	@GetMapping("/movie")
	public String showMovies(Model model) {
		model.addAttribute("movies", this.movieService.findAll());
		return "movies.html";
	}

	@GetMapping("/formNewMovie")
	public String formNewMovie(Model model) {
		model.addAttribute("movie", new Movie());
		return "formNewMovie.html";
	}


	@PostMapping("/movie")
	public String newMovie(@ModelAttribute("movie") Movie movie, Model model) {
		if (!movieService.existsByTitleAndYear(movie.getTitle(), movie.getYear())) {
			this.movieService.save(movie);
			// model.addAttribute("movie", movie);
			return "redirect:movie/" + movie.getId();
		} else {
			model.addAttribute("messaggioErrore", "Questo film esiste già");
			return "formNewMovie.html";
		}
	}

	@GetMapping("/formSearchMovies")
	public String formSearchMovies() {
		return "formSearchMovies.html";
	}

	@GetMapping("/formUpdateMovie/{id}")
	public String formUpdateMovie(@PathVariable("id") Long id, Model model) {
		model.addAttribute("movie", this.movieService.findById(id));
		return "formUpdateMovie.html";
	}

	@GetMapping("/manageMovies")
	public String manageMovies(Model model) {
		model.addAttribute("movies", this.movieService.findAll());
		return "manageMovies.html";
	}

	@GetMapping("/indexMovie")
	public String indexMovie() {
		return "indexMovie.html";
	}

	@PostMapping("/searchMovies")
	public String searchMovies(Model model, @RequestParam Integer year) {
		model.addAttribute("moviesByYear", this.movieService.findByYear(year));
		return "foundMovies.html";
	}

	@GetMapping("/addDirector/{id}")
	public String addDirector(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artists", this.artistService.findAll());
		model.addAttribute("movie", this.movieService.findById(id));
		return "director.html";
	}


	@GetMapping ("/setDirectorMovie/{idArtist}/{idMovie}")
	public String setDirector(@PathVariable("idArtist") Long idArtist, @PathVariable("idMovie") Long idMovie, Model model) {
		Movie movie = this.movieService.movieDirector(idMovie, idArtist, this.artistService);
		model.addAttribute("movie", movie);
		this.movieService.save(movie);
		return "formUpdateMovie.html";

	}

}

