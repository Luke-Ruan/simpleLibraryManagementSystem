package com.luke;

import java.util.List;

public class OperationController {

    private PermissionService permissionService;

    public OperationController() {
        this.permissionService = new SimplePermissionService();
    }

    public List<Book> queryList(String bookName, String writer) {
        if (bookName == null || writer == null) {
            return null;
        }
        // 鉴权
        if (CurrentUserUtil.hasPermission(ActionEnums.SEARCH.getValue())) {
            return permissionService.queryBook(bookName, writer);
        }
        return null;
    }

    
    public Boolean borrowBook(String bookName, String writer) {
        if (bookName == null || writer == null) {
            return false;
        }
        if (CurrentUserUtil.hasPermission(ActionEnums.BORROW.getValue())) {
            return permissionService.borrowBook(bookName, writer);
        }
        return false;
    }

    
    public Boolean returnBook(String bookName, String writer) {
        if (bookName == null || writer == null) {
            return false;
        }
        if (CurrentUserUtil.hasPermission(ActionEnums.RETURN.getValue())) {
            return permissionService.returnBook(bookName, writer);
        }
        return false;
    }

    
    public AddBookResult addBook(String bookName, String writer, String count) {
        if (bookName == null || writer == null || count == null || !StringUtil.isNumeric(count)) {

            return new AddBookResult(false, false, -1L);
        }
        if (CurrentUserUtil.hasPermission(ActionEnums.ADD.getValue())) {
            return permissionService.addBook(bookName, writer, count);
        }
        return new AddBookResult(false, false, 0L);
    }

    public Boolean deleteBook(String bookName, String writer) {
        if (bookName == null || writer == null) {
            return false;
        }
        if (CurrentUserUtil.hasPermission(ActionEnums.DELETE.getValue())) {
            return permissionService.deleteBook(bookName, writer);
        }
        return false;
    }
}
