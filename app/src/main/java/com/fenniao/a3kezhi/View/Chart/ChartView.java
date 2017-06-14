package com.fenniao.a3kezhi.View.Chart;

import java.util.List;

import cn.jpush.im.android.api.model.Message;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public interface ChartView {
    void onMessage(Message message);
    void onMessage(List<Message> messageList);
}
