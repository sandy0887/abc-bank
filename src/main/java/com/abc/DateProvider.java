package com.abc;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Sandeep Srivastava
 *
 */
public class DateProvider {
    private static DateProvider instance = null;

    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }

    /**
     * 
     * @return Date
     */
    public Date now() {
        return Calendar.getInstance().getTime();
    }
}
