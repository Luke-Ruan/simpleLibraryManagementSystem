package com.luke;

public enum ActionEnums {
    ADD("add"),
    BORROW("borrow"),
    DELETE("delete"),
    LOGIN("login"),
    REGISTER("register"),
    RETURN("return"),
    SEARCH("search");
    private String value;

    ActionEnums(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
