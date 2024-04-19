package it.uniroma3.siw.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.service.ArtistService;

@Controller
public class ArtistController {

	@Autowired
	private ArtistService artistService;

	@GetMapping("/artist/{id}")
	public String getArtist(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artist", this.artistService.findById(id));
		return "artist.html";
	}

	@GetMapping("/artist")
	public String showArtists(Model model) {		
		model.addAttribute("artists", this.artistService.findAll());
		return "artists.html";
	}

	@GetMapping("/formNewArtist")
	public String formNewArtist(Model model) {
		model.addAttribute("artist", new Artist());
		return "formNewArtist.html";
	}


	@PostMapping("/artist")
	public String newArtist(@ModelAttribute("artist") Artist artist, Model model) {
		if (!artistService.existsByNameAndUsernameAndData(artist.getName(), artist.getUsername(),artist.getData())) {
			this.artistService.save(artist);
			return "redirect:artist/"+ artist.getId();
		} else {
			model.addAttribute("messaggioErrore", "Questo artista esiste già");
			return "formNewArtist.html";
		}
	}
	
	
}
