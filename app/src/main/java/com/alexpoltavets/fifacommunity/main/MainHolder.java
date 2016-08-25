package com.alexpoltavets.fifacommunity.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alexpoltavets.fifacommunity.R;

/**
 * Created by Alex Poltavets on 25.08.2016.
 */
public class MainHolder extends RecyclerView.ViewHolder {

    private TextView textView;

    public MainHolder(View itemView) {
        super(itemView);
        textView= (TextView) itemView.findViewById(R.id.main_holder_textview);
    }

    public TextView getTextView() {
        return textView;
    }
}
