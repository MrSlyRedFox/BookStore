package ru.mrSlyRedFox.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import ru.mrSlyRedFox.controllers.repr.BookRepr;
import ru.mrSlyRedFox.service.model.LineItem;

public interface CartService extends Serializable {

    void addBookQty(BookRepr bookRepr, String color, String material, int qty);

    void removeBookQty(BookRepr bookRepr, String color, String material, int qty);

    void removeBook(BookRepr bookRepr, String color, String material);

    List<LineItem> getLineItems();

    BigDecimal getSubTotal();

    void updateCart(LineItem lineItem);
}
