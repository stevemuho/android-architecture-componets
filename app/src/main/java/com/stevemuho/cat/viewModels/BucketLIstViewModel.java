package com.stevemuho.cat.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.stevemuho.cat.db.AppDatabase;
import com.stevemuho.cat.db.BucketListModel;

import java.util.List;

/**
 * Created by muho on 12/7/17.
 */

public class BucketLIstViewModel extends AndroidViewModel {

    private final LiveData<List<BucketListModel>> bucketItemsList;

    private AppDatabase appDatabase;

    public BucketLIstViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        bucketItemsList = appDatabase.bucketItemModel().getAllBucketItems();
    }

    public LiveData<List<BucketListModel>> getBucketItemsList() {
        return bucketItemsList;
    }

    public void deleteItem(BucketListModel bucketListModel) {
        new deleteAsyncTask(appDatabase).execute(bucketListModel);
    }

    private static class deleteAsyncTask extends AsyncTask<BucketListModel, Void, Void> {

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final BucketListModel... params) {
            db.bucketItemModel().deleteBorrow(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }
}
