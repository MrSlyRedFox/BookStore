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

import ru.mrSlyRedFox.persist.model.Author;
import ru.mrSlyRedFox.persist.repo.AuthorRepository;

@Controller
public class AuthorController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    private final AuthorRepository authorRepository;


    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @GetMapping("/authors")
    public String adminAuthorsPage(Model model) {
        model.addAttribute("activePage", "Authors");
        model.addAttribute("authors", authorRepository.findAll());
        return "authors";
    }

    @GetMapping("/author/create")
    public String adminAuthorCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Authors");
        model.addAttribute("author", new Author());
        return "author_form";
    }

    @GetMapping("/author/{id}/edit")
    public String adminEditAuthor(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Authors");
        model.addAttribute("author", authorRepository.findById(id).orElseThrow(IllegalStateException::new));
        return "author_form";
    }

    @DeleteMapping("/author/{id}/delete")
    public String adminDeleteAuthor(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Authors");
        authorRepository.deleteById(id);
        return "redirect:/authors";
    }

    @PostMapping("/author")
    public String adminUpsertAuthor(Model model, RedirectAttributes redirectAttributes, Author author) {
        model.addAttribute("activePage", "Authors");

        try {
            authorRepository.save(author);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating category", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (author.getAuthor_id() == null) {
                return "redirect:/author/create";
            }
            return "redirect:/author/" + author.getAuthor_id() + "/edit";
        }
        return "redirect:/authors";
    }
}
