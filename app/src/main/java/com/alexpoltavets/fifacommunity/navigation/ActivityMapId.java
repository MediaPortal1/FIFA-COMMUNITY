package com.alexpoltavets.fifacommunity.navigation;

import android.support.annotation.IdRes;
import android.util.SparseArray;

import com.alexpoltavets.fifacommunity.R;

public final class ActivityMapId {

    private static SparseArray<Class> classList=new SparseArray<Class>();

    static {
        classList.put(MenuItems.MAIN.getMenuId(),null);
    }


    public static Class getClassbyId(@IdRes int id){
     return classList.get(id);
    }

}
