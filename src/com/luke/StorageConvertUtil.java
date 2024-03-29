package com.luke;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StorageConvertUtil {

    /**
     * 对象转成文本
     * @param list
     * @param testClass
     * @param path
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> boolean exportText(Collection<T> list, Class<T> testClass, String path) throws Exception {

        if (list == null || testClass == null || path == null || path.length() == 0) {
            return false;
        }

        FileWriter fileWriter = new FileWriter(path);
        if (list.size() == 0) {
            // 清空内容
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
            return true;
        }

        Field[] declaredFields = testClass.getDeclaredFields();

        BufferedWriter bw = new BufferedWriter(fileWriter);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < declaredFields.length; i++) {
            declaredFields[i].setAccessible(true);
            String filedName = declaredFields[i].getName();

            stringBuffer.append(filedName + "\t");
        }
        stringBuffer.append("\n");


        for (T t : list) {
            for (int i = 0; i < declaredFields.length; i++) {
                Object filed = declaredFields[i].get(t);
                //这是细节，如果该属性值为空，则改为null
                filed = filed == null ? "null" : filed;
                stringBuffer.append(filed.toString()).append("\t");
            }
            stringBuffer.append("\n");
        }

        bw.write(stringBuffer.toString());
        bw.close();
        return true;
    }


    /**
     * 文本转成对象
     * @param testClass
     * @param path
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> importText(Class<T> testClass, String path) throws Exception {
        if (testClass == null || path == null || path.length() == 0) {
            return null;
        }
        //作为返回的list
        ArrayList<T> list = new ArrayList<>();

        //创造节点流和缓冲流
        FileReader fileReader = new FileReader(path);
        BufferedReader br = new BufferedReader(fileReader);


        Field[] declaredFields = testClass.getDeclaredFields();
        int length = declaredFields.length;


        String line;
        int lineIndex = 0;

        while ((line = br.readLine()) != null) {
            String[] infos = line.split("[\t]+");
            //是第一行，则是属性信息行，更新属性数组，如果不是第一行则利用反射将构造对象
            if (lineIndex == 0) {
                for (int i = 0; i < length; i++) {
                    declaredFields[i] = testClass.getDeclaredField(infos[i]);
                    declaredFields[i].setAccessible(true);
                }
            } else {
                T t = testClass.newInstance();
                for (int i = 0; i < length; i++) {
                    declaredFields[i].set(t, infos[i]);
                }
                list.add(t);
            }
            //取出line
            lineIndex++;
        }
        br.close();

        return list;
    }

}
