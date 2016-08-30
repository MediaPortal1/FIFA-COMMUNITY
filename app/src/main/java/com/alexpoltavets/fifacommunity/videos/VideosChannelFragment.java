package com.alexpoltavets.fifacommunity.videos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexpoltavets.fifacommunity.R;

/**
 * Created by Alex Poltavets on 27.08.2016.
 */
public class VideosChannelFragment extends android.support.v4.app.Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_holder,container,false);
        return view;
    }

    public static VideosChannelFragment newInstance(){
        Bundle args=new Bundle();
//        args.put
        VideosChannelFragment instance=new VideosChannelFragment();
        instance.setArguments(args);
        return instance;
    }

}
