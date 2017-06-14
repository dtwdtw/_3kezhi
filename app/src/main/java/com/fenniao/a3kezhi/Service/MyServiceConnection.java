package com.fenniao.a3kezhi.Service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.fenniao.a3kezhi.Presenter.ChartPresener;

import cn.jpush.im.android.api.JMessageClient;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class MyServiceConnection implements ServiceConnection {
    OnMessageListener onMessageListener;
    public MyServiceConnection(OnMessageListener onMessageListener){
        this.onMessageListener=onMessageListener;
    }
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        JMessageService.MyBinder myBinder=(JMessageService.MyBinder) service;
        myBinder.setOnMessageListener(onMessageListener);
        onMessageListener.onMessage(myBinder.getMessages());
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
