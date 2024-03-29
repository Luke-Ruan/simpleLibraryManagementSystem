package com.luke;

import java.util.List;

public class AddAction implements ActionMethod {

    private OperationController operationController;

    public AddAction() {
        this.operationController = new OperationController();
    }

    @Override
    public void action(List<String> args) {
        if (args.size() != 4) {
            System.out.println("Incorrect Command");
            return;
        }
        AddBookResult addBookResult = operationController.addBook(args.get(1), args.get(2), args.get(3));
        if (!addBookResult.getResult() && addBookResult.getStock() < 0) {
            // 添加失败
            System.out.println("Sorry, command or args incorrect");
            return;
        }
        if (!addBookResult.getResult()) {
            System.out.println("Sorry, book " + args.get(1) + " cannot be added");
            return;
        }
        if (addBookResult.getNewBook()) {
            System.out.println("Book " + args.get(1) + " by " + args.get(2) + " added successfully, inventory: " + addBookResult.getStock() + ".");
        } else {
            System.out.println("Book " + args.get(1) + " inventory successfully updated, new inventory: " + addBookResult.getStock() + ".");
        }
    }
}
