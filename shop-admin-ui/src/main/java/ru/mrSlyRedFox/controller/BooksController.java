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

import ru.mrSlyRedFox.controller.repr.BookRepr;
import ru.mrSlyRedFox.error.NotFoundException;
import ru.mrSlyRedFox.persist.repo.AuthorRepository;
import ru.mrSlyRedFox.persist.repo.GenreRepository;
import ru.mrSlyRedFox.service.BookService;

@Controller
public class BooksController {
    private static final Logger logger = LoggerFactory.getLogger(BooksController.class);

    private final BookService bookService;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Autowired
    public BooksController(BookService bookService, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookService = bookService;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping("/books")
    public String adminBooksPage(Model model) {
        model.addAttribute("activePage", "Books");
        model.addAttribute("books", bookService.findAll());
        return "books";
    }

    @GetMapping("/book/{id}/edit")
    public String adminEditBook(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Books");
        model.addAttribute("book", bookService.findById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        return "book_form";
    }

    @DeleteMapping("/book/{id}/delete")
    public String adminDeleteBook(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Books");
        bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/book/create")
    public String adminCreateBook(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Books");
        model.addAttribute("book", new BookRepr());
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        return "book_form";
    }

    @PostMapping("/book")
    public String adminUpsertBook(Model model, RedirectAttributes redirectAttributes, BookRepr book) {
        model.addAttribute("activePage", "Books");

        try {
            bookService.save(book);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating book", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (book.getBook_id() == null) {
                return "redirect:/book/create";
            }
            return "redirect:/book/" + book.getBook_id() + "/edit";
        }
        return "redirect:/books";
    }
}
