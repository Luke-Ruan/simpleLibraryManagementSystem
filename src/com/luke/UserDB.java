package com.luke;

public class UserDB {

    public static void put(User user) {
        insert(user);
    }

    public static User get(String userName) {
        return BasicDB.userMap.get(userName);
    }

    public static Boolean insert(User user) {
        if (user != null && !BasicDB.userMap.containsKey(user.getUserName())) {
            // 只有当前user不存在，说明没创建过，才可以创建
            BasicDB.userMap.put(user.getUserName(), user);
            try {
                StorageConvertUtil.exportText(BasicDB.userMap.values(), User.class, BasicDB.userPath);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

}
