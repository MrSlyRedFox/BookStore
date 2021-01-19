package ru.mrSlyRedFox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.mrSlyRedFox.service.BookService;
import ru.mrSlyRedFox.service.CartService;
import ru.mrSlyRedFox.service.model.LineItem;


@Controller
public class CartController {

    private final CartService cartService;

    private final BookService bookService;

    @Autowired
    public CartController(CartService cartService, BookService bookService) {
        this.cartService = cartService;
        this.bookService = bookService;
    }

    @RequestMapping("/cart")
    public String cartPage(Model model) {
        model.addAttribute("lineItems", cartService.getLineItems());
        model.addAttribute("subTotal", cartService.getSubTotal());
        return "cart";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public String updateCart(LineItem lineItem) {
        lineItem.setBookRepr(bookService.findById(lineItem.getBookId())
                .orElseThrow(IllegalArgumentException::new));
        cartService.updateCart(lineItem);
        return "redirect:/cart";
    }
}
