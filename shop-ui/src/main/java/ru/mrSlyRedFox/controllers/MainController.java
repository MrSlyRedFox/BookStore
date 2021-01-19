package ru.mrSlyRedFox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.mrSlyRedFox.service.BookService;

@Controller
public class MainController {

    private final BookService bookService;

    @Autowired
    public MainController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping({"/", "/index", "/books"})
    public String indexPage(Model model) {
        model.addAttribute("books", bookService.findAllAndSplitBooksBy(3));
        return "books";
    }

    @RequestMapping("/book_details/{id}")
    public String bookDetailPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.findById(id).orElseThrow(IllegalArgumentException::new));
        return "book_details";
    }
}
