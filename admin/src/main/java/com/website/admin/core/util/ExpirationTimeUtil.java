package com.website.admin.core.util;

import java.util.Calendar;
import java.util.Date;

public class ExpirationTimeUtil {
    public static Date calculateExpirationTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 24);
        return calendar.getTime();
    }
}
