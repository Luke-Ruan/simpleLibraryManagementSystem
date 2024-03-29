package com.luke.action;

import com.luke.controller.OperationController;

import java.util.List;

public class ReturnAction implements ActionMethod {

    private OperationController operationController;

    public ReturnAction() {
        this.operationController = new OperationController();
    }

    @Override
    public void action(List<String> args) {
        operationController.returnBook(args.get(1), args.get(2));
    }
}
