package com.fenniao.a3kezhi.Service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.text.method.CharacterPickerDialog;
import android.util.Log;

import com.fenniao.a3kezhi.Config;
import com.fenniao.a3kezhi.Model.NotificationModel;
import com.fenniao.a3kezhi.View.Chart.ChartActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.CustomContent;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.Message;

/**
 * Created by Administrator on 2017/6/8 0008.
 */

public class JMessageService extends Service {
    List<Message> messageList = new ArrayList<>();
    OnMessageListener onMessageListener;
    MyBinder myBinder = new MyBinder();
    final static ReentrantLock lock = new ReentrantLock();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        JMessageClient.registerEventReceiver(this);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public void onEventMainThread(MessageEvent event) {
        Message msg = event.getMessage();
        if (Config.isChartActivityOnTop && onMessageListener != null) {
            onMessageListener.onMessage(msg);
        } else {
            lock.lock();
            messageList.add(msg);
            lock.unlock();
            switch(msg.getContentType()){
                case text:
                    NotificationModel.notify(this,msg.getFromUser().getUserName(),((TextContent)msg.getContent()).getText());
                    break;
            }
        }
    }

    public void onEventMainThread(OfflineMessageEvent event) {
        Log.v("dtw","off title " +event.getConversation().getTitle());
        for (Message message : event.getOfflineMessageList()) {
            Log.v("dtw","off  "+ message.toString());
        }
        messageList.addAll(event.getOfflineMessageList());
    }

    //接收通知栏点击事件
    public void onEventMainThread(NotificationClickEvent event) {
        if (!Config.isChartActivityOnTop) {
            Intent intent = new Intent(this, ChartActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else if(onMessageListener != null){

        }
        Log.v("dtw", "JMessageService 通知栏点击事件");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("dtw","service destory");
        JMessageClient.logout();
        JMessageClient.unRegisterEventReceiver(this);
    }

    public class MyBinder extends Binder {
        public List<Message> getMessages() {
            List<Message> tempMessageList = new ArrayList<>();
            lock.lock();
                tempMessageList.addAll(messageList);
                messageList.clear();
            lock.unlock();
            return tempMessageList;
        }

        public void setOnMessageListener(OnMessageListener onMessageListener) {
            JMessageService.this.onMessageListener = onMessageListener;
        }
    }
}
