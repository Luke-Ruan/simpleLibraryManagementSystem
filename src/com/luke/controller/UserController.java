package com.luke.controller;

import com.luke.entity.User;
import com.luke.service.UserService;
import com.luke.service.impl.SimpleUserService;
import com.luke.utils.CurrentUserUtil;

public class UserController {
    private UserService userService;

    public UserController() {
        this.userService = new SimpleUserService();
    }

    /**
     * 注册入口
     * @param type
     * @param userName
     * @param pw
     * @return
     */
    public boolean register(String type, String userName, String pw) {
        if (userName == null || pw == null || type == null) {
            return false;
        }
        if (!userService.verifyRole(type)) {
            return false;
        }
        User userByUserNameAndType = userService.getUserByUserName(userName);
        if (userByUserNameAndType == null) {
            userService.registerUser(userName, pw, type);
            return true;
        }
        return false;
    }

    /**
     * 登录入口
     * @param userName
     * @param pw
     * @return
     */
    public boolean login(String userName, String pw) {
        if (userName == null || pw == null) {
            return false;
        }
        boolean loginStatus = userService.verifyUserLogin(userName, pw);
        if (loginStatus) {
            // 需要先清除上一个用户的权限，然后更新成当前用户的权限
            CurrentUserUtil.refreshPermission(userService.getUserByUserName(userName));
        }
        return loginStatus;
    }
}
