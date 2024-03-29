package com.luke.action;


import com.luke.controller.UserController;

import java.util.List;

public class LoginAction implements ActionMethod{

    private UserController userController;

    public LoginAction() {
        this.userController = new UserController();
    }

    @Override
    public void action(List<String> args) {
        userController.login(args.get(1), args.get(2));
    }
}
