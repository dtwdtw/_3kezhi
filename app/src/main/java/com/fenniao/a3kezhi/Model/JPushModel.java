package com.fenniao.a3kezhi.Model;

import android.content.Context;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/6/7 0007.
 */

public class JPushModel {
    private JPushModel(){}
    private static JPushModel jPushModel;
    public static JPushModel getInstance(Context context){
        if(jPushModel==null){
            jPushModel=new JPushModel();
            //设置极光调试模式发布时关闭
            JPushInterface.setDebugMode(true);
            JPushInterface.init(context);
        }
        return jPushModel;
    }
}
