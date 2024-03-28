package com.luke.mapper;

import com.luke.db.BasicDB;
import com.luke.entity.BorrowRecord;
import com.luke.utils.StorageConvertUtil;

import java.util.Set;

public class BorrowRecordsDB {

    public static Boolean updateBorrowRecord() throws Exception {
        StorageConvertUtil.exportText(BasicDB.borrowRecordMap.values(), BorrowRecord.class, BasicDB.borrowRecordsPath);
        return true;
    }

    public static int getRecordByBookId(String id) {
        Set<String> strings = BasicDB.borrowRecordMap.keySet();
        if (strings.size() > 0 && strings.contains(id)) {
            return 1;
        }
        return 0;
    }
}
