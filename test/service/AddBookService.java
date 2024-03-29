package service;

import com.luke.AddBookResult;
import com.luke.PermissionService;
import com.luke.SimplePermissionService;

public class AddBookService {
    public static void main(String[] args) {
        PermissionService service = new SimplePermissionService();
        AddBookResult addBookResult = service.addBook("apple", "apple", "12");
        System.out.println(addBookResult.getResult());

    }
}
