package com.stevemuho.cat.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import com.stevemuho.cat.util.DateConverter;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by muho on 12/7/17.
 */
@Dao
@TypeConverters(DateConverter.class)
public interface BucketListDao {

    @Query("select * from BucketListModel")
    LiveData<List<BucketListModel>> getAllBucketItems();

    @Query("select * from BucketListModel where id = :id")
    BucketListModel getItembyId(String id);

    @Insert(onConflict = REPLACE)
    void addBorrow(BucketListModel bucketListModel);

    @Delete
    void deleteBorrow(BucketListModel bucketListModel);

}
