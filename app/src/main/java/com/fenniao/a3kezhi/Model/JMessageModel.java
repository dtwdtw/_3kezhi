package com.fenniao.a3kezhi.Model;

import android.content.Context;

import cn.jpush.im.android.api.JMessageClient;

/**
 * Created by Administrator on 2017/6/14 0014.
 */

public class JMessageModel {
    public JMessageModel(Context context){
        JMessageClient.init(context);
        JMessageClient.registerEventReceiver(this);
    }
}
