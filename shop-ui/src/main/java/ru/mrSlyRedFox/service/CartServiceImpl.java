package ru.mrSlyRedFox.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import ru.mrSlyRedFox.controllers.repr.BookRepr;
import ru.mrSlyRedFox.service.model.LineItem;

@Service
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService {

    private static final long serialVersionUID = -9025621122549454991L;

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    private final Map<LineItem, Integer> lineItems;

    @PostConstruct
    public void post() {
        logger.info("Session bean post construct");
    }

    public CartServiceImpl() {
        this.lineItems = new HashMap<>();
    }

    @JsonCreator
    public CartServiceImpl(@JsonProperty("lineItems") List<LineItem> lineItems) {
        this.lineItems = lineItems.stream().collect(Collectors.toMap(li -> li, LineItem::getQty));
    }

    @Override
    public void addBookQty(BookRepr bookRepr, String color, String material, int qty) {
        LineItem lineItem = new LineItem(bookRepr, color, material);
        lineItems.put(lineItem, lineItems.getOrDefault(lineItem, 0) + qty);
    }

    @Override
    public void removeBookQty(BookRepr bookRepr, String color, String material, int qty) {
        LineItem lineItem = new LineItem(bookRepr, color, material);
        int currentQty = lineItems.getOrDefault(lineItem, 0);
        if (currentQty - qty > 0) {
            lineItems.put(lineItem, currentQty - qty);
        } else {
            lineItems.remove(lineItem);
        }
    }

    @Override
    public void removeBook(BookRepr bookRepr, String color, String material) {
        lineItems.remove(new LineItem(bookRepr, color, material));
    }

    @Override
    public List<LineItem> getLineItems() {
        lineItems.forEach(LineItem::setQty);
        return new ArrayList<>(lineItems.keySet());
    }

    @JsonIgnore
    @Override
    public BigDecimal getSubTotal() {
        lineItems.forEach(LineItem::setQty);
        return lineItems.keySet().stream()
                .map(LineItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void updateCart(LineItem lineItem) {
        lineItems.put(lineItem, lineItem.getQty());
    }
}