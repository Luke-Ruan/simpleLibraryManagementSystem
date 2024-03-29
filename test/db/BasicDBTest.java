package db;

import com.luke.BasicDB;
import com.luke.User;

public class BasicDBTest {
    public static void main(String[] args) {
        User alice = BasicDB.userMap.get("Alice");
        System.out.println(alice);
    }
}
