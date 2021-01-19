package ru.mrSlyRedFox.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import ru.mrSlyRedFox.controllers.repr.BookRepr;


public class LineItem implements Serializable {

    private Long bookId;

    private BookRepr bookRepr;

    private Integer qty;

    private String color;

    private String material;

    public LineItem(BookRepr bookRepr, String color, String material) {
        this.bookId = bookRepr.getId();
        this.bookRepr = bookRepr;
        this.color = color;
        this.material = material;
    }

    public LineItem() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public BookRepr getBookRepr() {
        return bookRepr;
    }

    public void setBookRepr(BookRepr bookRepr) {
        this.bookRepr = bookRepr;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @JsonIgnore
    public BigDecimal getTotal() {
        return bookRepr.getPrice().multiply(new BigDecimal(qty));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return bookId.equals(lineItem.bookId) &&
                Objects.equals(color, lineItem.color) &&
                Objects.equals(material, lineItem.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, color, material);
    }
}
