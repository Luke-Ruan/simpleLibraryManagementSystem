package com.luke;

import java.util.ArrayList;
import java.util.List;


public class BookDB {
    public static List<Book> query(String bookName, String writer) {
        Book book = BasicDB.bookMap.get(bookName + writer);
        List<Book> res = new ArrayList<>();
        if (book != null) {
            res.add(book);
        }
        return res;
    }

    public static Boolean updateBook() throws Exception {
        StorageConvertUtil.exportText(BasicDB.bookMap.values(), Book.class, BasicDB.booksPath);
        return true;
    }

    public static void addBook(Book book) throws Exception {
        // todo 需要做回滚，目前暂不处理
        BasicDB.bookMap.put(book.getBookName() + book.getWriter(), book);
        updateBook();
    }

    public static void deleteBook(String bookName, String writer) throws Exception {
        // todo 需要做回滚，目前暂不处理
        BasicDB.bookMap.remove(bookName + writer);
        updateBook();
    }
}
