package com.luke;

import com.luke.BasicDB;
import com.luke.User;

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
        User user = currentUser.get();
        if (user == null) {
            return false;
        }
        Set<String> strings = BasicDB.rolePermission.get(user.getRole());
        return strings.contains(permission);
    }

    public static User getCurrentUser() {
        return currentUser.get();
    }
}
