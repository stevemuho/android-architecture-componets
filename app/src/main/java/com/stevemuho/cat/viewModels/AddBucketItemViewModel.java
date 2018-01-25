package com.stevemuho.cat.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

import com.stevemuho.cat.db.AppDatabase;
import com.stevemuho.cat.db.BucketListModel;

/**
 * Created by muho on 12/7/17.
 */

public class AddBucketItemViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddBucketItemViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

    }

    public void addBorrow(final BucketListModel bucketListModel) {

        new addAsyncTask(appDatabase).execute(bucketListModel);
    }

    private static class addAsyncTask extends AsyncTask<BucketListModel, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final BucketListModel... params) {
            db.bucketItemModel().addBorrow(params[0]);
            return null;
        }

    }
}