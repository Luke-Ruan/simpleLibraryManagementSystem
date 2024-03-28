package com.luke.entity;

public class Book {
    private String id;
    private String bookName;
    private String writer;
    private Integer stock;

    public Book() {
    }

    public Book(String id, String bookName, String writer, Integer stock) {
        this.id = id;
        this.bookName = bookName;
        this.writer = writer;
        this.stock = stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getBookName() {
        return bookName;
    }

    public String getWriter() {
        return writer;
    }

    public Integer getStock() {
        return stock;
    }
}
