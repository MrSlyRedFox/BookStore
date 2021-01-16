package ru.mrSlyRedFox.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ru.mrSlyRedFox.error.NotFoundException;
import ru.mrSlyRedFox.persist.model.Genre;
import ru.mrSlyRedFox.persist.repo.GenreRepository;

@Controller
public class GenreController {
    private static final Logger logger = LoggerFactory.getLogger(GenreController.class);

    private final GenreRepository genreRepository;

    @Autowired
    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping("/genres")
    public String adminGenresPage(Model model) {
        model.addAttribute("activePage", "Genres");
        model.addAttribute("genres", genreRepository.findAll());
        return "genres";
    }

    @GetMapping("/genre/create")
    public String adminGenreCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Genres");
        model.addAttribute("genre", new Genre());
        return "genre_form";
    }

    @GetMapping("/genre/{id}/edit")
    public String adminEditGenre(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Genres");
        model.addAttribute("genre", genreRepository.findById(id).orElseThrow(NotFoundException::new));
        return "genre_form";
    }

    @DeleteMapping("/genre/{id}/delete")
    public String adminDeleteGenre(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Genres");
        genreRepository.deleteById(id);
        return "redirect:/genres";
    }

    @PostMapping("/genre")
    public String adminUpsertGenre(Model model, RedirectAttributes redirectAttributes, Genre genre ) {

        model.addAttribute("activePage", "Genres");

        try {
            genreRepository.save(genre);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating brand", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (genre.getGenre_id() == null) {
                return "redirect:/genre/create";
            }
            return "redirect:/genre/" + genre.getGenre_id() + "/edit";
        }
        return "redirect:/genres";
    }
}
