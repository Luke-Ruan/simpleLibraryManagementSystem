package com.luke;

import com.luke.AddBookResult;
import com.luke.Book;

import java.util.List;

public interface PermissionService {
    Boolean borrowBook(String bookName, String writer);

    List<Book> queryBook(String bookName, String writer);

    Boolean returnBook(String bookName, String writer);

    AddBookResult addBook(String bookName, String writer, String count);

    Boolean deleteBook(String bookName, String writer);
}
