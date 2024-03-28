package db;

import com.luke.db.BasicDB;
import com.luke.entity.User;

public class BasicDBTest {
    public static void main(String[] args) {
        User alice = BasicDB.userMap.get("Alice");
        System.out.println(alice);
    }
}
