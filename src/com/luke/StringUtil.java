package com.luke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static String capitalize(String inputString) {
        char firstLetter = inputString.charAt(0);

        char capitalFirstLetter = Character.toUpperCase(firstLetter);

        return inputString.replace(inputString.charAt(0), capitalFirstLetter);
    }


    /**
     * 判断当前字符串是不是整数
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

}
