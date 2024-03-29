package com.luke.service;

import com.luke.entity.User;

public interface UserService {

    /**
     * 根据userName找用户
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);

    /**
     * 用户注册
     * @param userName
     * @param pw
     * @param type
     */
    void registerUser(String userName, String pw, String type);

    /**
     * 校验当前账号密码是否登录成功
     * @param userName
     * @param pw
     * @return
     */
    boolean verifyUserLogin(String userName, String pw);

    /**
     * 校验当前角色是否存在
     * @param type
     * @return
     */
    boolean verifyRole(String type);
}
