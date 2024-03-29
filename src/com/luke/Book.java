package com.luke;

public class Book {
    private String id;
    private String bookName;
    private String writer;
    private String stock;

    public Book() {
    }

    public Book(String id, String bookName, String writer, String stock) {
        this.id = id;
        this.bookName = bookName;
        this.writer = writer;
        this.stock = stock;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getWriter() {
        return writer;
    }

    public String getStock() {
        return stock;
    }
}
