package com.luke;

public class AddBookResult {
    private Boolean result;
    private Boolean newBook;
    private Long stock;

    public AddBookResult() {
    }

    public AddBookResult(Boolean result, Boolean newBook, Long stock) {
        this.result = result;
        this.newBook = newBook;
        this.stock = stock;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public void setNewBook(Boolean aNew) {
        newBook = aNew;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Boolean getResult() {
        return result;
    }

    public Boolean getNewBook() {
        return newBook;
    }

    public Long getStock() {
        return stock;
    }
}
