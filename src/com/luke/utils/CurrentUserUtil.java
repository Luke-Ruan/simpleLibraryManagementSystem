package com.luke.utils;

import com.luke.db.BasicDB;
import com.luke.entity.User;

import java.util.Set;

/**
 * 利用ThreadLocal来当做redis的缓存作用
 */
public class CurrentUserUtil {
    private static final ThreadLocal<User> currentUser = new ThreadLocal<>();

    public static void refreshPermission(User user) {
        currentUser.remove();
        currentUser.set(user);
    }

    /**
     * 判断当前用户是否有某个权限
     * @param permission
     * @return
     */
    public static Boolean hasPermission(String permission) {
        Set<String> strings = BasicDB.rolePermission.get(currentUser.get().getRole());
        return strings.contains(permission);
    }

    public static User getCurrentUser() {
        return currentUser.get();
    }
}
