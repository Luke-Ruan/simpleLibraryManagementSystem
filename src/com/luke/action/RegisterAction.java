package com.luke.action;


import com.luke.controller.UserController;
import com.luke.entity.User;
import com.luke.utils.CurrentUserUtil;
import com.luke.utils.StringUtil;

import java.util.List;

public class RegisterAction implements ActionMethod {

    private UserController userController;

    public RegisterAction() {
        this.userController = new UserController();
    }

    @Override
    public void action(List<String> args) {
        boolean register = userController.register(args.get(1), args.get(2), args.get(3));
        if (!register) {
            System.out.println("Register failed");
        } else {
            User currentUser = CurrentUserUtil.getCurrentUser();
            System.out.println(StringUtil.capitalize(currentUser.getRole()) + " " + currentUser.getUserName() + " successfully logged in.");
        }
    }
}
