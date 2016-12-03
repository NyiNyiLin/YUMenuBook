package com.nyi.yumenubook.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IN-3442 on 03-Dec-16.
 */

public class DateUtil {
    public static String getCurrentDate(){
        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String DateToStr = format.format(curDate);

        return DateToStr;
    }
}
