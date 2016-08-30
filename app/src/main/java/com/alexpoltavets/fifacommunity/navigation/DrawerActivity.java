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
    private String selectedMenuName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isDrawerOpen())closeDrawer();
    }

    private void initActivity() {
        setContentView(R.layout.navigation_activity);
        setContent();
        setToolbarTitle();
        setSelectedMenuId();
        ViewStub viewStub=(ViewStub)findViewById(R.id.navigation_viestub);
        if(contentResId==-1){
            throw new IllegalStateException("Layout resource dont set");
        }
        viewStub.setLayoutResource(contentResId);
        viewStub.inflate();
        initViews();
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawerlayout);
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
        navigationView.getMenu().getItem(MenuItems.valueOf(selectedMenuName).ordinal()).setChecked(true);
        if(toolbarTitle!=-1) setTitle(toolbarTitle);
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

    @Override
    public void onBackPressed() {
        if(isDrawerOpen())closeDrawer();
        else super.onBackPressed();
    }

    private boolean isDrawerOpen(){return drawerLayout.isDrawerOpen(GravityCompat.START);}

    private void closeDrawer(){drawerLayout.closeDrawer(GravityCompat.START);}

    private void startActivityFromNavigation(@IdRes int classId){
        Intent intent=new Intent(this,MenuItems.getActivityClassbyId(classId));
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        closeDrawer();
        startActivity(intent);
    }
    protected final void setSelectedMenuId(String menuName){this.selectedMenuName=menuName;}
    protected final void setContentResId(int contentResId) {
        this.contentResId = contentResId;
    }
    protected final void setTitleString(int title){
        this.toolbarTitle=title;
    }
    protected abstract void setContent();
    protected abstract void setToolbarTitle();
    protected abstract void setSelectedMenuId();
    protected abstract void initViews();
}
