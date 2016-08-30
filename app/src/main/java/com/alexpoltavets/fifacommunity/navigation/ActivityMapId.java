package com.alexpoltavets.fifacommunity.navigation;

import android.support.annotation.IdRes;
import android.util.SparseArray;

import com.alexpoltavets.fifacommunity.main.MainActivity;
import com.alexpoltavets.fifacommunity.videos.VideosActivity;

public final class ActivityMapId {

    private static SparseArray<Class> classList=new SparseArray<Class>();

    static {
        classList.put(MenuItems.MAIN.getMenuId(), MainActivity.class);
        classList.put(MenuItems.VIDEOS.getMenuId(), VideosActivity.class);
    }


    public static Class getClassbyId(@IdRes int id){
     return classList.get(id);
    }

}
