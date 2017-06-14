package com.fenniao.a3kezhi.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;

import com.fenniao.a3kezhi.Been.MessageBean;
import com.fenniao.a3kezhi.SQL.DBHelper;
import com.fenniao.a3kezhi.SQL.MessageDao;
import com.fenniao.a3kezhi.Service.JMessageService;
import com.fenniao.a3kezhi.Service.MyServiceConnection;
import com.fenniao.a3kezhi.Service.OnMessageListener;
import com.fenniao.a3kezhi.Utils.SPUtil;
import com.fenniao.a3kezhi.View.Chart.ChartView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.Message;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class ChartPresener implements OnMessageListener{
    MyServiceConnection myServiceConnection;
    ChartView chartView;
    Handler handler=new Handler();
    public ChartPresener(ChartView chartView){
        this.chartView=chartView;
    }
    public void bindService(Context context){
        myServiceConnection=new MyServiceConnection(this);
        JMessageClient.enterSingleConversation("master");
        context.bindService(new Intent(context, JMessageService.class),myServiceConnection,context.BIND_AUTO_CREATE);
    }

    public void unBindService(Context context){
        JMessageClient.exitConversation();
        context.unbindService(myServiceConnection);
    }

    @Override
    public void onMessage(Message message) {
        chartView.onMessage(message);
    }

    @Override
    public void onMessage(List<Message> historyMessageList) {
        chartView.onMessage(historyMessageList);
    }

    public void backUpMessage(Context context,List<Message> messageList){
        new MessageDao(context).add(messageList);
    }

    public List<Message> getBackedMessage(Context context){
        return new MessageDao(context).getMessageBeanList("master");
    }
}
