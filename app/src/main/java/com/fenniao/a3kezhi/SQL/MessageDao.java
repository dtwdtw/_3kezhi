package com.fenniao.a3kezhi.SQL;

import android.content.Context;
import android.util.Log;

import com.fenniao.a3kezhi.Been.MessageBean;
import com.j256.ormlite.dao.Dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoListCallback;
import cn.jpush.im.android.api.callback.ProgressUpdateCallback;
import cn.jpush.im.android.api.content.CustomContent;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

/**
 * Created by Administrator on 2017/6/12 0012.
 */

public class MessageDao {
    Dao<MessageBean,Integer> messageDao;
    DBHelper dbHelper;
    public MessageDao(Context context){
        try {
            dbHelper=DBHelper.getInstance(context);
            messageDao=dbHelper.getDao(MessageBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Message message){

        try {
            messageDao.create(messageToMessageBean(message));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(List<Message> messageList){
        List<MessageBean> messageBeanList=new ArrayList<>();
        for(Message message:messageList){
            messageBeanList.add(messageToMessageBean(message));
        }
        try {
            messageDao.create(messageBeanList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.v("dtw",messageBeanList.get(0).getConversationName());
    }

    public void delete(Message message){
        try {
            messageDao.delete(messageToMessageBean(message));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Message message){
        try {
            messageDao.update(messageToMessageBean(message));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Message> getMessageBeanList(String conversationName){
        List<MessageBean> messageBeanList=new ArrayList<>();
        try {
            messageBeanList=messageDao.queryForEq("conversationName",conversationName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Message> messageList=new ArrayList<>();
        for(MessageBean messageBean:messageBeanList){
            messageList.add(messageBeanToMessage(messageBean));
        }
        return messageList;
    }

    public MessageBean messageToMessageBean(Message message){
        MessageBean messageBean=new MessageBean();
        switch (message.getContentType()) {
            case text:
                //处理文字消息
                TextContent textContent = (TextContent) message.getContent();
                messageBean.setType("text");
                messageBean.setValue(textContent.getText());
                messageBean.setConversationName(message.getFromUser().getUserName());
                messageBean.setDirect(message.getDirect().name());
                break;
            case image:
                //处理图片消息
                ImageContent imageContent = (ImageContent) message.getContent();
                imageContent.getLocalPath();//图片本地地址
                imageContent.getLocalThumbnailPath();//图片对应缩略图的本地地址
                messageBean.setType("image");
                messageBean.setValue(imageContent.getLocalPath());
                messageBean.setConversationName(message.getFromUser().getUserName());
                messageBean.setDirect(message.getDirect().name());
                break;
            case voice:
                //处理语音消息
                VoiceContent voiceContent = (VoiceContent) message.getContent();
                voiceContent.getLocalPath();//语音文件本地地址
                voiceContent.getDuration();//语音文件时长
                messageBean.setType("voice");
                messageBean.setValue(voiceContent.getLocalPath());
                messageBean.setConversationName(message.getFromUser().getUserName());
                messageBean.setDirect(message.getDirect().name());
                messageBean.setLongth(voiceContent.getDuration());
                break;
            case custom:
                //处理自定义消息
                CustomContent customContent = (CustomContent) message.getContent();
                messageBean.setType("custom");
                messageBean.setValue(customContent.getStringValue("custom_string"));
                messageBean.setConversationName(message.getFromUser().getUserName());
                messageBean.setDirect(message.getDirect().name());
                break;
        }
        return messageBean;
    }

    public Message messageBeanToMessage(MessageBean messageBean){
        Message message=null;
        switch (messageBean.getType()){
            case "text":
//                message=JMessageClient.getSingleConversation("master").createSendTextMessage(messageBean.getValue());
                message=JMessageClient.createSingleTextMessage("master",messageBean.getValue());
                break;
            case "image":
                try {
//                    message=JMessageClient.getSingleConversation("master").createSendImageMessage(new File(messageBean.getValue()));
                    message=JMessageClient.createSingleImageMessage("master",new File(messageBean.getValue()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "voice":
                try {
//                    message=JMessageClient.getSingleConversation("master").createSendVoiceMessage(new File(messageBean.getValue()),(int)messageBean.getLongth());
                    message=JMessageClient.createSingleVoiceMessage("master",new File(messageBean.getValue()),(int)messageBean.getLongth());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "custom":
                Map<String,String> customMap=new HashMap<>();
                customMap.put("custom_string",messageBean.getValue());
//                message=JMessageClient.getSingleConversation("master").createSendCustomMessage(customMap);
                message=JMessageClient.createSingleCustomMessage("master",customMap);
                break;
        }
        return message;
    }
}
