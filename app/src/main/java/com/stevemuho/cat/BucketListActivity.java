package com.stevemuho.cat;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.stevemuho.cat.db.BucketListModel;
import com.stevemuho.cat.viewModels.AddBucketItemViewModel;

import java.util.Calendar;
import java.util.Date;

public class BucketListActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private Date date;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    private EditText bucketItemEdt;
    private EditText bucketDescEdt;

    private AddBucketItemViewModel addBucketItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bucketlist_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Add Bucket Item");

        bucketItemEdt = findViewById(R.id.bucketName);
        bucketDescEdt = findViewById(R.id.bucketDesc);

        calendar = Calendar.getInstance();
        addBucketItemViewModel = ViewModelProviders.of(this).get(AddBucketItemViewModel.class);

        datePickerDialog = new DatePickerDialog(this, BucketListActivity.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // we have used if else control statements to check for users input
                if (bucketItemEdt.getText() == null || bucketDescEdt.getText() == null || date == null)
                    Toast.makeText(BucketListActivity.this, "Missing fields", Toast.LENGTH_SHORT).show();
                else {
                    addBucketItemViewModel.addBorrow(new BucketListModel(
                            bucketItemEdt.getText().toString(),
                            bucketDescEdt.getText().toString(),
                            date
                    ));
                    finish();
                }
            }
        });


        findViewById(R.id.dateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = calendar.getTime();

    }
}
