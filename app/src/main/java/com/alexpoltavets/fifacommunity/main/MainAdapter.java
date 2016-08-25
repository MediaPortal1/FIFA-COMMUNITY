package com.alexpoltavets.fifacommunity.main;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alexpoltavets.fifacommunity.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainHolder>{

    private List<String> items;

    public MainAdapter(List<String> arrayitems) {
        super();
        this.items=arrayitems;
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_holder,parent,false));
    }

    @Override
    public void onBindViewHolder(MainHolder holder, int position) {
        holder.getTextView().setText(items.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
