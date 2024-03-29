package com.luke.action;

import com.luke.controller.OperationController;

import java.util.List;

public class SearchAction implements ActionMethod {

    private OperationController operationController;

    public SearchAction() {
        this.operationController = new OperationController();
    }

    @Override
    public void action(List<String> args) {
        operationController.queryList(args.get(1), args.get(2));
    }
}
