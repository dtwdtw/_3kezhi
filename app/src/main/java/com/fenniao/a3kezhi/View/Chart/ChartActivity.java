package com.fenniao.a3kezhi.View.Chart;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.fenniao.a3kezhi.Config;
import com.fenniao.a3kezhi.Presenter.ChartPresener;
import com.fenniao.a3kezhi.R;
import com.fenniao.a3kezhi.Service.JMessageService;
import com.fenniao.a3kezhi.Service.OnMessageListener;
import com.fenniao.a3kezhi.Utils.SPUtil;
import com.fenniao.a3kezhi.View.BaseActivity;
import com.fenniao.a3kezhi.View.OnRecycleViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

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

public class ChartActivity extends BaseActivity implements ChartView ,OnRecycleViewItemClickListener{
    ChartPresener chartPresener = new ChartPresener(this);
    RecyclerView chartRecycleView;
    ChartRecycleListAdapter chartRecycleListAdapter;
    List<Message> chartMessage=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_activity);
        enableBackButton();
        setMainTitle("客服");
        chartRecycleView= (RecyclerView) findViewById(R.id.chart_recycleview);
        chartRecycleView.setLayoutManager(new LinearLayoutManager(this));

        chartRecycleListAdapter=new ChartRecycleListAdapter(this,chartMessage);
        chartRecycleListAdapter.setOnRecycleViewItemClickListener(this);
        chartRecycleView.setAdapter(chartRecycleListAdapter);

        chartPresener.bindService(this);

    }


    @Override
    public void onMessage(Message message) {
        switch (message.getContentType()) {
            case text:
                //处理文字消息
                TextContent textContent = (TextContent) message.getContent();
                textContent.getText();
                Log.v("dtw", textContent.getText());
                break;
            case image:
                //处理图片消息
                ImageContent imageContent = (ImageContent) message.getContent();
                imageContent.getLocalPath();//图片本地地址
                imageContent.getLocalThumbnailPath();//图片对应缩略图的本地地址
                Log.v("dtw", "图片");
                break;
            case voice:
                //处理语音消息
                VoiceContent voiceContent = (VoiceContent) message.getContent();
                voiceContent.getLocalPath();//语音文件本地地址
                voiceContent.getDuration();//语音文件时长
                Log.v("dtw", "语音");
                break;
            case custom:
                //处理自定义消息
                CustomContent customContent = (CustomContent) message.getContent();
                customContent.getNumberValue("custom_num"); //获取自定义的值
                customContent.getBooleanValue("custom_boolean");
                customContent.getStringValue("custom_string");
                Log.v("dtw", "自定义");
                break;
        }
        chartMessage.add(message);
        chartRecycleListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMessage(List<Message> messageList) {
        chartMessage.addAll(messageList);
        chartRecycleListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Config.isChartActivityOnTop = true;
        chartMessage.addAll(chartPresener.getBackedMessage(this));
        Log.v("dtw",chartPresener.getBackedMessage(this).toString());
        chartRecycleListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Config.isChartActivityOnTop = false;
        chartPresener.backUpMessage(this,chartMessage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        chartPresener.unBindService(this);
    }

    @Override
    public void onRecycleViewItemClick(View view, String adapterClassName, int viewType, int listPosition, int itemPosition) {
        Log.v("dtw","click "+itemPosition);
    }
}
