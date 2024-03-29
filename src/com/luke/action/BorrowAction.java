package com.luke.action;


import com.luke.controller.OperationController;

import java.util.List;

public class BorrowAction implements ActionMethod {

    private OperationController operationController;

    public BorrowAction() {
        this.operationController = new OperationController();
    }

    @Override
    public void action(List<String> args) {
        operationController.borrowBook(args.get(1), args.get(2));
    }
}
