package com.luke;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, ActionMethod> actionMethodMap = new HashMap<>();
        actionMethodMap.put(ActionEnums.ADD.getValue(), new AddAction());
        actionMethodMap.put(ActionEnums.BORROW.getValue(), new BorrowAction());
        actionMethodMap.put(ActionEnums.DELETE.getValue(), new DeleteAction());
        actionMethodMap.put(ActionEnums.LOGIN.getValue(), new LoginAction());
        actionMethodMap.put(ActionEnums.REGISTER.getValue(), new RegisterAction());
        actionMethodMap.put(ActionEnums.RETURN.getValue(), new ReturnAction());
        actionMethodMap.put(ActionEnums.SEARCH.getValue(), new SearchAction());
        // write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Command-Line Library Management System");
        while (true) {
            String s = scanner.nextLine();
            List<String> result = analysisCommand(s);
            if (result != null) {
                ActionMethod actionMethod = actionMethodMap.get(result.get(0));
                if (actionMethod != null) {
                    actionMethod.action(result);
                } else {
                    System.out.println("Incorrect Command");
                }
            } else {
                System.out.println("Incorrect Command");
            }
        }
    }

    private static List<String> analysisCommand(String s) {
        if (s == null || s.trim().equals("")) {
            return null;
        }
        String[] split = s.split(" ");
        if (split.length > 10) {
            return null;
        }
        return new ArrayList<>(Arrays.asList(split));
    }
}
