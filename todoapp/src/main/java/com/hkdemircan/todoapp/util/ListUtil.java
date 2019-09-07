package com.hkdemircan.todoapp.util;

import java.util.List;

public class ListUtil {
    public static boolean isNullOrEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

}
