package com.fenniao.a3kezhi.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.fenniao.a3kezhi.Config;
import com.fenniao.a3kezhi.Model.SMSListener;
import com.fenniao.a3kezhi.Model.SMSModel;
import com.fenniao.a3kezhi.View.Register.RegisterView;

import java.util.regex.Pattern;

import cn.smssdk.EventHandler;

/**
 * Created by Administrator on 2017/6/2 0002.
 */

public class RegisterPresener extends EventHandler implements SMSListener {
    Handler handler = new Handler();
    RegisterView registerView;
    SMSModel smsModel;

    public RegisterPresener(RegisterView registerView) {
        this.registerView = registerView;
    }

    public void getVerificationCode(Context context, CharSequence phoneNum, CharSequence phoneOther) {
        Pattern patten = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
        if (patten.matcher(phoneNum).matches() && (TextUtils.isEmpty(phoneOther) || patten.matcher(phoneOther).matches())) {
            smsModel = com.fenniao.a3kezhi.Model.SMSModel.getInstance(context);
            smsModel.setSmsListener(this);
            smsModel.getVerificationCode("86", phoneNum.toString());
            registerView.showToast("code send success");
            registerView.toVerificationCodeActivity();
        } else {
            registerView.showToast("请输入正确的手机号码");
        }
    }

    @Override
    public void onCodeSended() {
    }

    @Override
    public void onCodeVerified(boolean success, String msg) {
    }

    public void onRegisted(int resultCode,Intent data){
        switch (resultCode){
            case Config.CodeSuccess:
                registerView.goBack(Config.CodeSuccess,data);
                break;
        }
    }
}
