package com.stevemuho.cat;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.stevemuho.cat.adapters.RecyclerViewAdapter;
import com.stevemuho.cat.db.BucketListModel;
import com.stevemuho.cat.viewModels.BucketLIstViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    private BucketLIstViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private DividerItemDecoration dividerItemDecoration;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Bucket List");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //the user is taken to the next screen

               // Intent intent = new Intent(MainActivity.this,BucketListActivity.class);
                ////startActivity(intent);

                //startActivity( new Intent(MainActivity.this, BucketListActivity.class) );
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<BucketListModel>(), this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        dividerItemDecoration = new DividerItemDecoration(this,layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel = ViewModelProviders.of(this).get(BucketLIstViewModel.class);

        viewModel.getBucketItemsList().observe(MainActivity.this, new Observer<List<BucketListModel>>() {
            @Override
            public void onChanged(@Nullable List<BucketListModel> itemAndPeople) {
                recyclerViewAdapter.addItems(itemAndPeople);
            }
        });


    }

    @Override
    public boolean onLongClick(View view) {

        BucketListModel bucketListModel = (BucketListModel) view.getTag();
        viewModel.deleteItem(bucketListModel);
        return true;
    }
}
