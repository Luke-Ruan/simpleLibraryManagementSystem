package com.luke;


import java.util.List;

public class BorrowAction implements ActionMethod {

    private OperationController operationController;

    public BorrowAction() {
        this.operationController = new OperationController();
    }

    @Override
    public void action(List<String> args) {
        if (args.size() != 3) {
            System.out.println("Incorrect Command");
            return;
        }
        Boolean aBoolean = operationController.borrowBook(args.get(1), args.get(2));
        if (!aBoolean) {
            System.out.println("Sorry, book " + args.get(1) + " is not available");
        } else {
            System.out.println("Book " + args.get(1) + " successfully borrowed.");
        }
    }
}
