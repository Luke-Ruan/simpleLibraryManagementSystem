package com.luke.action;


import com.luke.controller.OperationController;

import java.util.List;

public class DeleteAction implements ActionMethod {

    private OperationController operationController;

    public DeleteAction() {
        this.operationController = new OperationController();
    }

    @Override
    public void action(List<String> args) {
        operationController.deleteBook(args.get(1), args.get(2));
    }
}
