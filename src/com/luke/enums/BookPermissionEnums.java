package com.luke.enums;

public enum BookPermissionEnums {
    QUERY_LIST("list"),
    BORROW_BOOK("borrow"),
    RETURN_BOOK("return"),
    ADD_BOOK("add"),
    DELETE_BOOK("delete");
    private String value;

    BookPermissionEnums(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
