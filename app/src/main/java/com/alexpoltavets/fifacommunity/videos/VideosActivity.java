package com.alexpoltavets.fifacommunity.videos;

import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alexpoltavets.fifacommunity.R;
import com.alexpoltavets.fifacommunity.navigation.DrawerActivity;

import de.hdodenhof.circleimageview.CircleImageView;


public class VideosActivity extends DrawerActivity implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void setContent() {
        setContentResId(R.layout.videos_activity);
    }

    @Override
    protected void setToolbarTitle() {
        setTitleString(R.string.title_videos);
    }

    @Override
    protected void setSelectedMenuId() {
        setSelectedMenuId("VIDEOS");
    }

    @Override
    protected void initViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.videos_channelslist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new ChannelsAdapter());
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.videos_swipetorefresh);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(this,"WORK",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        swipeRefreshLayout.setOnRefreshListener(null);
    }

    private class ChannelsAdapter extends RecyclerView.Adapter<ChannelHolder> {
        @Override
        public ChannelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ChannelHolder(LayoutInflater.from(getBaseContext()).inflate(R.layout.videos_channelitem, parent, false));
        }

        @Override
        public void onBindViewHolder(ChannelHolder holder, int position) {
            holder.setImageView(getResources().getDrawable(R.drawable.ava_test));
            holder.setItemPosition(position);
        }

        @Override
        public int getItemCount() {
            return 2;
        }

    }

    private class ChannelHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CircleImageView imageView;
        private int position;

        public ChannelHolder(View itemView) {
            super(itemView);
            imageView = (CircleImageView) itemView.findViewById(R.id.videos_channelsimageicon);
            itemView.setOnClickListener(this);
        }

        public void setImageView(Drawable drawable) {
            imageView.setImageDrawable(drawable);
        }

        public int getItemPosition() {
            return position;
        }

        public void setItemPosition(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
        }
    }

}
