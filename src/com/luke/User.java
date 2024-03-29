package com.luke;

public class User {
    private String id;
    private String userName;
    private String role;
    private String pw;

    public User() {
    }

    public User(String id, String userName, String role, String pw) {
        this.id = id;
        this.userName = userName;
        this.role = role;
        this.pw = pw;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getRole() {
        return role;
    }

    public String getPw() {
        return pw;
    }
}
