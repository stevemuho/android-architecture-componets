package com.stevemuho.cat.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.stevemuho.cat.util.DateConverter;

import java.util.Date;

/**
 * Created by muho on 12/7/17.
 */

@Entity
public class BucketListModel {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String bucketName;
    private String bucketDesc;
    @TypeConverters(DateConverter.class)
    private Date bucketDate;


    public BucketListModel(String bucketName, String bucketDesc, Date bucketDate) {
        this.bucketName = bucketName;
        this.bucketDesc = bucketDesc;
        this.bucketDate = bucketDate;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketDesc() {
        return bucketDesc;
    }

    public void setBucketDesc(String bucketDesc) {
        this.bucketDesc = bucketDesc;
    }

    public Date getBucketDate() {
        return bucketDate;
    }

    public void setBucketDate(Date bucketDate) {
        this.bucketDate = bucketDate;
    }
}
