package com.luke.action;

import com.luke.controller.OperationController;
import com.luke.entity.User;
import com.luke.utils.CurrentUserUtil;

import java.util.List;

public class ReturnAction implements ActionMethod {

    private OperationController operationController;

    public ReturnAction() {
        this.operationController = new OperationController();
    }

    @Override
    public void action(List<String> args) {
        Boolean aBoolean = operationController.returnBook(args.get(1), args.get(2));
        if (!aBoolean) {
            User currentUser = CurrentUserUtil.getCurrentUser();
            System.out.println("Sorry, book " + args.get(1) + " cannot be returned by user " + currentUser.getUserName());
        } else {
            System.out.println("Book " + args.get(1) + " successfully returned.");
        }
    }
}
