package com.luke.action;

import com.luke.controller.OperationController;
import com.luke.entity.AddBookResult;

import java.util.List;

public class AddAction implements ActionMethod {

    private OperationController operationController;

    public AddAction() {
        this.operationController = new OperationController();
    }

    @Override
    public void action(List<String> args) {
        AddBookResult addBookResult = operationController.addBook(args.get(1), args.get(2), args.get(3));
        if (!addBookResult.getResult()) {
            // 添加失败
            System.out.println("Sorry, book #{bookName} cannot be");
        }
    }
}
