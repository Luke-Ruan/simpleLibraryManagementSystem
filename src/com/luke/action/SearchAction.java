package com.luke.action;

import com.luke.controller.OperationController;
import com.luke.entity.Book;

import java.util.List;

public class SearchAction implements ActionMethod {

    private OperationController operationController;

    public SearchAction() {
        this.operationController = new OperationController();
    }

    @Override
    public void action(List<String> args) {
        List<Book> books = operationController.queryList(args.get(1), args.get(2));
        if (books == null || books.isEmpty()) {
            System.out.println("Sorry, no books found");
        } else {
            System.out.println("Book List:");
            for (Book b : books) {
                System.out.println(b.getBookName() + " - " + b.getWriter() + " - Inventory: " + b.getStock());
            }
        }
    }
}
