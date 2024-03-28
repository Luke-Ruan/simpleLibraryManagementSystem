package com.luke.enums;

public enum RoleEnums {
    ADMIN("admin"),
    USER("user");
    private String value;

    RoleEnums(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
