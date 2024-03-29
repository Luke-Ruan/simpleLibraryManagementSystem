package com.luke.action;


import com.luke.controller.UserController;
import com.luke.entity.User;
import com.luke.enums.RoleEnums;
import com.luke.utils.CurrentUserUtil;
import com.luke.utils.StringUtil;

import java.util.List;

public class LoginAction implements ActionMethod{

    private UserController userController;

    public LoginAction() {
        this.userController = new UserController();
    }

    @Override
    public void action(List<String> args) {
        boolean login = userController.login(args.get(1), args.get(2));
        if (!login) {
            System.out.println("Login failed");
        }
        User currentUser = CurrentUserUtil.getCurrentUser();
        System.out.println(StringUtil.capitalize(currentUser.getRole()) + " " + args.get(1) + " successfully logged in.");
    }
}
