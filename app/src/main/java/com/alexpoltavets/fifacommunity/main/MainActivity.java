package com.alexpoltavets.fifacommunity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alexpoltavets.fifacommunity.R;
import com.alexpoltavets.fifacommunity.login.LoginActivity;
import com.alexpoltavets.fifacommunity.navigation.DrawerActivity;
import com.alexpoltavets.fifacommunity.navigation.MenuItems;
import com.facebook.Profile;
import com.vk.sdk.VKSdk;

import java.util.Arrays;


public class MainActivity extends DrawerActivity{

    private RecyclerView recyclerView;

    @Override
    protected void setContent() {
        setContentResId(R.layout.main_activity);
    }
    @Override
    protected void setSelectedMenuId() {
        setSelectedMenuId("MAIN");
    }
    @Override
    protected void setToolbarTitle() {
        setTitleString(R.string.title_main);
    }

    @Override
    protected void initViews() {
        recyclerView= (RecyclerView) findViewById(R.id.main_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainAdapter(Arrays.asList(new String[]{"TEST1","TEST2","TEST3","TEST4"})));
    }

}
