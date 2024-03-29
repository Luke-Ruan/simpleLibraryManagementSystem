package service;

import com.luke.CurrentUserUtil;
import com.luke.PermissionService;
import com.luke.SimplePermissionService;
import com.luke.User;

public class DeleteBookService {
    public static void main(String[] args) {
        CurrentUserUtil.refreshPermission(new User());
        PermissionService service = new SimplePermissionService();
        Boolean aBoolean = service.deleteBook("apple", "apple");
        System.out.println(aBoolean);
    }
}
