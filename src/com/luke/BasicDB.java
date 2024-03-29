package com.luke;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BasicDB {
    // key是userName
    public static Map<String, User> userMap = new HashMap<>();
    // key是role，set是permissionName集合
    public static Map<String, Set<String>> rolePermission = new HashMap<>();
    // key是userId+bookId
    public static Map<String, BorrowRecord> borrowRecordMap = new HashMap<>();
    // key=bookName+writer
    public static Map<String, Book> bookMap = new HashMap<>();

    public static String userPath = null;
    public static String booksPath = null;
    public static String borrowRecordsPath = null;

    static {
        // 先判断是否有user文件
        userPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath() + "/user.txt";
        booksPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath() + "/books.txt";
        borrowRecordsPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath() + "/borrow_records.txt";
        List<User> users = verifyFile(User.class, userPath);
        if (users != null && users.size() > 0) {
            // 默认key重复了就舍弃新key
            userMap = users.stream().collect(Collectors.toMap(User::getUserName, user -> user, (k1, k2) -> k1));
        }
        List<Book> books = verifyFile(Book.class, booksPath);
        if (books != null && books.size() > 0) {
            bookMap = books.stream().collect(Collectors.toMap(book -> book.getBookName() + book.getWriter(), book -> book, (k1, k2) -> k1));
        }
        List<BorrowRecord> borrowRecords = verifyFile(BorrowRecord.class, borrowRecordsPath);
        if (borrowRecords != null && borrowRecords.size() > 0) {
            borrowRecordMap = borrowRecords.stream().collect(Collectors.toMap(borrowRecord -> borrowRecord.getUserId() + borrowRecord.getBookId(), borrowRecord -> borrowRecord, (k1, k2) -> k1));
        }
        Set<String> adminPermission = new HashSet<>();
        adminPermission.add(ActionEnums.ADD.getValue());
        adminPermission.add(ActionEnums.DELETE.getValue());
        adminPermission.add(ActionEnums.SEARCH.getValue());
        rolePermission.put(RoleEnums.ADMIN.getValue(), adminPermission);
        Set<String> userPermission = new HashSet<>();
        userPermission.add(ActionEnums.BORROW.getValue());
        userPermission.add(ActionEnums.RETURN.getValue());
        userPermission.add(ActionEnums.SEARCH.getValue());
        rolePermission.put(RoleEnums.USER.getValue(), userPermission);
    }

    private static <T> List<T> verifyFile(Class<T> targetClass, String path) {
        File file = new File(path);
        // 如果文件不存在，就创建文件
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else {
            // 如果文件存在，就读文件内容初始化userMap
            try {
                return StorageConvertUtil.importText(targetClass, path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
