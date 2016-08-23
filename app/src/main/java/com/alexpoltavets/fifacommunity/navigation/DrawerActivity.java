package com.alexpoltavets.fifacommunity.navigation;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;

import com.alexpoltavets.fifacommunity.R;


public abstract class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private int contentResId=-1;
    private int toolbarTitle=-1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();

    }

    private void initActivity() {
        setContentView(R.layout.navigation_activity);
        setContent();
        setToolbarTitle();
        ViewStub viewStub=(ViewStub)findViewById(R.id.navigation_viestub);
        if(contentResId==-1){
            throw new IllegalStateException("Layout resource dont set");
        }
        viewStub.setLayoutResource(contentResId);
        viewStub.inflate();
        initViews();
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawerview);
        toolbar = (Toolbar) findViewById(R.id.navigation_toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        setSupportActionBar(toolbar);
        drawerLayout.addDrawerListener(toggle);
        navigationView = (NavigationView) findViewById(R.id.navigation_drawerview);
        navigationView.setNavigationItemSelectedListener(this);
        if(toolbarTitle!=-1)    getSupportActionBar().setTitle(toolbarTitle);
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) return true;

        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        startActivityFromNavigation(item.getItemId());
        return false;
    }

    private boolean isDrawerOpen(){return drawerLayout.isDrawerOpen(GravityCompat.START);}

    private void closeDrawer(){drawerLayout.closeDrawer(GravityCompat.START);}

    private void startActivityFromNavigation(@IdRes int classId){
        Intent intent=new Intent(this,MenuItems.getActivityClassbyId(classId));
        closeDrawer();
        startActivity(intent);
    }

    protected void setContentResId(int contentResId) {
        this.contentResId = contentResId;
    }
    protected void setTitleString(@IdRes int title){
        this.toolbarTitle=title;
    }
    protected void setContent(){};
    protected void setToolbarTitle(){};
    protected void initViews(){};
}