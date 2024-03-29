package com.luke.action;


import com.luke.controller.UserController;

import java.util.List;

public class RegisterAction implements ActionMethod {

    private UserController userController;

    public RegisterAction() {
        this.userController = new UserController();
    }

    @Override
    public void action(List<String> args) {
        userController.register(args.get(1), args.get(2), args.get(3));
    }
}
