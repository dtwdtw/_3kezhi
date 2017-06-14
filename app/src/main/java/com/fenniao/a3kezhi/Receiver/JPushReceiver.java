package com.fenniao.a3kezhi.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.fenniao.a3kezhi.MainActivity;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/6/7 0007.
 */

public class JPushReceiver extends BroadcastReceiver {
    /**
     * 文档地址： http://docs.jiguang.cn/jpush/client/Android/android_api/
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle= intent.getExtras();
        switch(intent.getAction()){
            //收到了自定义消息 Push 。
            case "cn.jpush.android.intent.MESSAGE_RECEIVED":
                //保存服务器推送下来的消息的标题。
                String title = bundle.getString(JPushInterface.EXTRA_TITLE);
                //保存服务器推送下来的消息内容。
                String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
                //保存服务器推送下来的附加字段。这是个 JSON 字符串。
                String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
                //唯一标识消息的 ID, 可用于上报统计等。
                String msgID = bundle.getString(JPushInterface.EXTRA_MSG_ID);
                break;
            //用户点击了通知。
            case "cn.jpush.android.intent.NOTIFICATION_OPENED":
//                context.startActivity(new Intent(context, MainActivity.class));
                break;
            //获取当前 JPush 服务的连接状态。
            case "cn.jpush.android.intent.CONNECTION":
                boolean connected = bundle.getBoolean(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                break;
        }
    }
}
