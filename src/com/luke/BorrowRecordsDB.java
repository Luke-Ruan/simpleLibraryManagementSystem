package com.luke;

import java.util.Set;

public class BorrowRecordsDB {

    public static Boolean updateBorrowRecord() throws Exception {
        StorageConvertUtil.exportText(BasicDB.borrowRecordMap.values(), BorrowRecord.class, BasicDB.borrowRecordsPath);
        return true;
    }

    public static int getRecordByBookId(String id) {
        Set<String> strings = BasicDB.borrowRecordMap.keySet();
        if (strings.size() > 0) {
            for (String s : strings) {
                if (s.contains(id)) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
