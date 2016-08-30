package com.alexpoltavets.fifacommunity.navigation;


import android.support.annotation.IdRes;

import com.alexpoltavets.fifacommunity.R;

public enum MenuItems {

    MAIN(R.id.menu_mainpage),
    VIDEOS(R.id.menu_videos);
    //

    private final int menuId;

    MenuItems(@IdRes int menu) {
        this.menuId = menu;
    }

    public int getMenuId() {
        return this.menuId;
    }

    public static Class getActivityClassbyId(@IdRes int menuId) {
        return ActivityMapId.getClassbyId(menuId);
    }

}
