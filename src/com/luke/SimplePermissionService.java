package com.luke;

import java.util.List;
import java.util.UUID;

public class SimplePermissionService implements PermissionService {
    @Override
    public Boolean borrowBook(String bookName, String writer) {
        // 借书要分两个步骤
        // 库存有就借
        Book book = BasicDB.bookMap.get(bookName + writer);
        Long stock = Long.parseLong(book.getStock());
        if (stock > 0) {
            // 借了要减库存
            book.setStock(String.valueOf(stock - 1));
            try {
                BookDB.updateBook();
            } catch (Exception e) {
                return false;
            }
            // 借书记录要更新
            String key = CurrentUserUtil.getCurrentUser().getId() + book.getId();
            BorrowRecord borrowRecord = BasicDB.borrowRecordMap.get(key);
            if (borrowRecord == null) {
                borrowRecord = new BorrowRecord();
                borrowRecord.setBookId(book.getId());
                borrowRecord.setId(UUID.randomUUID().toString());
                borrowRecord.setUserId(CurrentUserUtil.getCurrentUser().getId());
                borrowRecord.setCount("0");
                BasicDB.borrowRecordMap.put(key, borrowRecord);
            }
            borrowRecord.setCount(String.valueOf(Integer.parseInt(borrowRecord.getCount()) + 1));

            try {
                BorrowRecordsDB.updateBorrowRecord();
            } catch (Exception e) {
                // todo 这里需要做回滚操作，目前暂不考虑
                return false;
            }
            // 都成功了就返回true
            return true;
        }
        return false;
    }

    @Override
    public List<Book> queryBook(String bookName, String writer) {
        return BookDB.query(bookName, writer);
    }

    @Override
    public Boolean returnBook(String bookName, String writer) {
        // 检查这个书是不是当前用户借的
        Book book = BasicDB.bookMap.get(bookName + writer);
        if (book == null) {
            return false;
        }
        String key = CurrentUserUtil.getCurrentUser().getId() + book.getId();
        BorrowRecord borrowRecord = BasicDB.borrowRecordMap.get(key);
        if (borrowRecord != null) {
            // 是当前用户借的
            // 修改borrowRecord
            long count = Long.parseLong(borrowRecord.getCount()) - 1;
            // 如果小于0说明还清了，这里默认删除这个记录
            if (count <= 0) {
                BasicDB.borrowRecordMap.remove(key);
            } else {
                borrowRecord.setCount(String.valueOf(count));
            }
            try {
                BorrowRecordsDB.updateBorrowRecord();
            } catch (Exception e) {
                return false;
            }
            // 修改book
            book.setStock(String.valueOf(Integer.parseInt(book.getStock()) + 1));
            try {
                BookDB.updateBook();
            } catch (Exception e) {
                // todo 这里同样需要做回滚，目前不考虑
                return false;
            }
            // 正常处理之后返回true
            return true;
        }
        return false;
    }

    @Override
    public AddBookResult addBook(String bookName, String writer, String count) {
        AddBookResult addBookResult = new AddBookResult();
        Book book = BasicDB.bookMap.get(bookName + writer);
        addBookResult.setStock(Long.parseLong(count));
        if (book == null) {
            // 是新书
            addBookResult.setNewBook(true);
            book = new Book();
            book.setStock(count);
            book.setBookName(bookName);
            book.setId(UUID.randomUUID().toString());
            book.setWriter(writer);
            try {
                BookDB.addBook(book);
            } catch (Exception e) {
                addBookResult.setResult(false);
                return addBookResult;
            }
        } else {
            // 是旧书，直接更新库存即可
            addBookResult.setNewBook(false);
            book.setStock(String.valueOf(Integer.parseInt(book.getStock()) + Integer.parseInt(count)));
            addBookResult.setStock(Long.valueOf(book.getStock()));
            try {
                BookDB.updateBook();
            } catch (Exception e) {
                addBookResult.setResult(false);
                return addBookResult;
            }
        }
        addBookResult.setResult(true);
        return addBookResult;
    }

    @Override
    public Boolean deleteBook(String bookName, String writer) {
        // 如果当前有人再借，就不能删除这个书
        List<Book> query = BookDB.query(bookName, writer);
        // 一开始默认没有人借书
        Boolean flag = false;
        if (query.size() > 0) {
            for (Book b : query) {
                int count = BorrowRecordsDB.getRecordByBookId(b.getId());
                if (count > 0) {
                    flag = true;
                    break;
                }
            }
        }
        // flag==true,说明有人借书
        if (flag) {
            return false;
        }
        try {
            BookDB.deleteBook(bookName, writer);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
