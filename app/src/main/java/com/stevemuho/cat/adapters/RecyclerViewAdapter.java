package com.stevemuho.cat.adapters;

/**
 * Created by muho on 12/7/17.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.stevemuho.cat.R;
import com.stevemuho.cat.db.BucketListModel;

import java.text.SimpleDateFormat;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<BucketListModel> bucketListModelList;
    private View.OnLongClickListener longClickListener;
    private SimpleDateFormat dateFormat;

    public RecyclerViewAdapter(List<BucketListModel> bucketListModelList, View.OnLongClickListener longClickListener) {
        this.bucketListModelList = bucketListModelList;
        this.longClickListener = longClickListener;

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        BucketListModel bucketListModel = bucketListModelList.get(position);
        holder.itemTextView.setText(bucketListModel.getBucketName());
        holder.nameTextView.setText(bucketListModel.getBucketDesc());
        holder.dateTextView.setText(dateFormat.format(bucketListModel.getBucketDate()));
        holder.itemView.setTag(bucketListModel);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return bucketListModelList.size();
    }

    public void addItems(List<BucketListModel> bucketListModelList) {
        this.bucketListModelList = bucketListModelList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView itemTextView;
        private TextView nameTextView;
        private TextView dateTextView;

        RecyclerViewHolder(View view) {
            super(view);
            itemTextView = view.findViewById(R.id.itemTextView);
            nameTextView = view.findViewById(R.id.nameTextView);
            dateTextView = view.findViewById(R.id.dateTextView);
        }
    }
}