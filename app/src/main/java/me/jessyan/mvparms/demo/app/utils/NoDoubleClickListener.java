package me.jessyan.mvparms.demo.app.utils;

import android.view.View;

/**
 * Created by phenix on 2017/6/14.
 */

public  abstract class NoDoubleClickListener implements View.OnClickListener {
    //间隔时间
    public static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime=0;
    private int id =-1;

    @Override
    public void onClick(View v) {
        long currentTime=System.currentTimeMillis();
        int  lastId=v.getId();
        if(id!=lastId){
            //说明点击的不是同一个View
            id=lastId;
            lastClickTime=currentTime;
            onNoDoubleClick(v);
            return;
        } else{
            if(currentTime-lastClickTime>MIN_CLICK_DELAY_TIME){
                lastClickTime=currentTime;
                onNoDoubleClick(v);
            }
        }
    }
    protected abstract void onNoDoubleClick(View v);
}
