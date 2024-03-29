package service;

import com.luke.*;

import java.util.List;

public class SearchBookService {
    public static void main(String[] args) {
        CurrentUserUtil.refreshPermission(new User("1", "tony", "user", "123"));
        PermissionService service = new SimplePermissionService();

        List<Book> books = service.queryBook("apple", "apple");
        System.out.println(books.size());

    }
}
