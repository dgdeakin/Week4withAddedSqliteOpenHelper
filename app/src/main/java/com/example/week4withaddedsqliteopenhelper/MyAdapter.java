package com.example.week4withaddedsqliteopenhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
//    String[] data;
    List<NameDataModel> nameList;
    NameListFragment fragment;

    public MyAdapter(Context context, List<NameDataModel> nameList, NameListFragment fragment) {
        this.context = context;
        this.nameList = nameList;
        this.fragment = fragment;
    }

    //    public MyAdapter(Context context, String[] data) {
//        this.context = context;
//        this.data = data;
//    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
//        holder.getTextView().setText(data[position]);
        NameDataModel name = nameList.get(position);
//        holder.getTextView().setText(nameList.get(position).getNameText());
        holder.getTextView().setText(name.getNameText());
        holder.textView.setOnClickListener(v -> fragment.onItemClicked(name));
    }

    @Override
    public int getItemCount() {
//        return data.length;
        return nameList.size();
    }

    public void updateList(List<NameDataModel> newList){
        nameList = newList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
