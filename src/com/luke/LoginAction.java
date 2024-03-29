package com.luke;


import java.util.List;

public class LoginAction implements ActionMethod{

    private UserController userController;

    public LoginAction() {
        this.userController = new UserController();
    }

    @Override
    public void action(List<String> args) {
        if (args.size() != 3) {
            System.out.println("Incorrect Command");
            return;
        }
        boolean login = userController.login(args.get(1), args.get(2));
        if (!login) {
            System.out.println("Login failed");
            return;
        }
        User currentUser = CurrentUserUtil.getCurrentUser();
        System.out.println(StringUtil.capitalize(currentUser.getRole()) + " " + args.get(1) + " successfully logged in.");
    }
}
