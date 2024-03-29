package com.luke;


import java.util.List;

public class DeleteAction implements ActionMethod {

    private OperationController operationController;

    public DeleteAction() {
        this.operationController = new OperationController();
    }

    @Override
    public void action(List<String> args) {
        if (args.size() != 3) {
            System.out.println("Incorrect Command");
            return;
        }
        Boolean aBoolean = operationController.deleteBook(args.get(1), args.get(2));
        if (!aBoolean) {
            System.out.println("Cannot delete book " + args.get(1) + " because it is currently borrowed.");
        } else {
            System.out.println("Book " + args.get(1) + " by " + args.get(2) + " deleted successfully.");
        }
    }
}
