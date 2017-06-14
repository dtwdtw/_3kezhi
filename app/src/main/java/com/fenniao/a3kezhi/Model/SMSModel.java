package com.fenniao.a3kezhi.Model;

import android.content.Context;
import android.util.Log;

import com.fenniao.a3kezhi.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class SMSModel extends EventHandler {
    private SMSModel() {
    }

    private static SMSModel instance;
    private SMSListener smsListener;

    public static SMSModel getInstance(Context context) {
        if (instance == null) {
            instance = new SMSModel();
            SMSSDK.initSDK(context, "1e59ae03ebc57", "11ba043513de71434215b44129f6225a");
        }
        return instance;
    }

    public void setSmsListener(SMSListener smsListener) {
        SMSSDK.registerEventHandler(this);
        this.smsListener = smsListener;
    }

    public void sendVoiceCode(String countryCode,String phoneNum){
        SMSSDK.getVoiceVerifyCode(countryCode,phoneNum);
    }

    private void unRegisterEventHandler() {
        SMSSDK.unregisterEventHandler(this);
    }

    public void getSupportedCountries() {
        SMSSDK.getSupportedCountries();
    }

    public void getVerificationCode(String countryCode, String phoneNum) {
        SMSSDK.getVerificationCode(countryCode, phoneNum);
    }

    public void subMitVerificationCode(String countryCode, String phoneNum, String code) {
        SMSSDK.submitVerificationCode(countryCode, phoneNum, code);
    }

    @Override
    public void afterEvent(int event, int result, Object data) {
        super.afterEvent(event, result, data);
        if(result==SMSSDK.RESULT_ERROR){
            Throwable throwable = (Throwable) data;
            JSONObject object = null;
            try {
                object = new JSONObject(throwable.getMessage());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String des = object.optString("detail");//错误描述
            int status = object.optInt("status");//错误代码
            Log.v("dtw",""+des);
            switch (status){
                case Config.VerifiedCodeError:
                    smsListener.onCodeVerified(false,"验证码错误");
                    break;
                case Config.OutOfCount:
                    smsListener.onCodeVerified(false,"验证过于频繁，请使用语音验证码");
                    break;
            }
        }else if (result == SMSSDK.RESULT_COMPLETE) {
            //回调完成
            if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                //提交验证码成功
                    Set<Map.Entry<String, Object>> countrySet = ((HashMap<String, Object>) data).entrySet();
                    for (Map.Entry<String, Object> countyrEntry : countrySet) {
                        Log.v("dtw", "countrykey " + countyrEntry.getKey());
                        Log.v("dtw", "countryvalue " + countyrEntry.getValue().toString());
                    }

                smsListener.onCodeVerified(true,"注册成功");
                Log.v("dtw", "校验成功");
            } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                //获取验证码成功
                smsListener.onCodeSended();
                Log.v("dtw", "获取验证码成功");
            } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                //返回支持发送验证码的国家列表
                for (HashMap<String, Object> counry : (ArrayList<HashMap<String, Object>>) data) {
                    Set<Map.Entry<String, Object>> countrySet = counry.entrySet();
                    for (Map.Entry<String, Object> countyrEntry : countrySet) {
                        Log.v("dtw", "countrykey " + countyrEntry.getKey());
                        Log.v("dtw", "countryvalue " + countyrEntry.getValue().toString());
                    }
                }
            }
        } else {
            ((Throwable) data).printStackTrace();
        }
    }
}
