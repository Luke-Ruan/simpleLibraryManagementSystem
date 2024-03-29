package com.luke.service.impl;

import com.luke.db.BasicDB;
import com.luke.entity.User;
import com.luke.mapper.UserDB;
import com.luke.service.UserService;
import com.luke.utils.CurrentUserUtil;
import com.luke.utils.PasswordUtil;

import java.util.Set;
import java.util.UUID;

public class SimpleUserService implements UserService {

    @Override
    public User getUserByUserName(String userName) {
        // 假设userName是唯一key
        return UserDB.get(userName);
    }

    @Override
    public void registerUser(String userName, String pw, String type) {
        pw = PasswordUtil.encode(pw);
        User user = new User(UUID.randomUUID().toString(), userName, type.trim().toLowerCase(), pw);
        UserDB.put(user);
        CurrentUserUtil.refreshPermission(user);
    }

    @Override
    public boolean verifyUserLogin(String userName, String pw) {
        User userByUserName = getUserByUserName(userName);
        if (userByUserName != null) {
            pw = PasswordUtil.encode(pw);
            return pw.equals(userByUserName.getPw());
        }
        return false;
    }

    @Override
    public boolean verifyRole(String type) {
        Set<String> strings = BasicDB.rolePermission.keySet();
        return strings.contains(type.trim().toLowerCase());
    }
}
