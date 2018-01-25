package com.stevemuho.cat.util;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by muho on 12/7/17.
 */

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
