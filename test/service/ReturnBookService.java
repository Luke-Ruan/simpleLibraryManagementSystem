package service;

import com.luke.CurrentUserUtil;
import com.luke.PermissionService;
import com.luke.SimplePermissionService;
import com.luke.User;

public class ReturnBookService {
    public static void main(String[] args) {
        CurrentUserUtil.refreshPermission(new User("1", "tony", "user", "123"));
        PermissionService service = new SimplePermissionService();

        Boolean aBoolean = service.returnBook("apple", "apple");
        System.out.println(aBoolean);
    }
}
