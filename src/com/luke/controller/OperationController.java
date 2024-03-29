package com.luke.controller;

import com.luke.entity.AddBookResult;
import com.luke.entity.Book;
import com.luke.enums.BookPermissionEnums;
import com.luke.service.PermissionService;
import com.luke.service.impl.SimplePermissionService;
import com.luke.utils.CurrentUserUtil;

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
        if (CurrentUserUtil.hasPermission(BookPermissionEnums.QUERY_LIST.getValue())) {
            return permissionService.queryBook(bookName, writer);
        }
        return null;
    }

    
    public Boolean borrowBook(String bookName, String writer) {
        if (bookName == null || writer == null) {
            return false;
        }
        if (CurrentUserUtil.hasPermission(BookPermissionEnums.BORROW_BOOK.getValue())) {
            return permissionService.borrowBook(bookName, writer);
        }
        return false;
    }

    
    public Boolean returnBook(String bookName, String writer) {
        if (bookName == null || writer == null) {
            return false;
        }
        if (CurrentUserUtil.hasPermission(BookPermissionEnums.RETURN_BOOK.getValue())) {
            return permissionService.returnBook(bookName, writer);
        }
        return false;
    }

    
    public AddBookResult addBook(String bookName, String writer, String count) {
        if (bookName == null || writer == null || count == null) {
            return new AddBookResult(false, false, -1L);
        }
        if (CurrentUserUtil.hasPermission(BookPermissionEnums.ADD_BOOK.getValue())) {
            return permissionService.addBook(bookName, writer, count);
        }
        return new AddBookResult(false, false, 0L);
    }

    public Boolean deleteBook(String bookName, String writer) {
        if (bookName == null || writer == null) {
            return false;
        }
        if (CurrentUserUtil.hasPermission(BookPermissionEnums.DELETE_BOOK.getValue())) {
            return permissionService.deleteBook(bookName, writer);
        }
        return false;
    }
}
