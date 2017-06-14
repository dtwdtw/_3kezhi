package com.fenniao.a3kezhi.Presenter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.fenniao.a3kezhi.Config;
import com.fenniao.a3kezhi.Model.SMSListener;
import com.fenniao.a3kezhi.Model.SMSModel;
import com.fenniao.a3kezhi.View.VerificationCode.VerificationCodeView;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class VerificationCodePresener implements SMSListener {
    Handler handler = new Handler();
    SMSModel smsModel;
    VerificationCodeView verificationCodeView;

    public VerificationCodePresener(VerificationCodeView verificationCodeView) {
        this.verificationCodeView = verificationCodeView;
    }

    public void getVoiceCode(Context context, String phoneNum) {
        smsModel = com.fenniao.a3kezhi.Model.SMSModel.getInstance(context);
        smsModel.setSmsListener(this);
        smsModel.sendVoiceCode("86", phoneNum);
    }

    public void getVerificationCode(Context context, String phoneNum) {
        smsModel = com.fenniao.a3kezhi.Model.SMSModel.getInstance(context);
        smsModel.setSmsListener(this);
        smsModel.getVerificationCode("86", phoneNum);
        Log.v("dtw","get code click");
    }

    public void verifyCode(Context context, String phoneNum, CharSequence password, String code) {
        Pattern pattern = Pattern.compile("^[^a-zA-Z0-9\\s]*$|^\\d*$|^[a-zA-Z]*$");
        if (!pattern.matcher(password).matches() && password.length() > 7) {
            smsModel = com.fenniao.a3kezhi.Model.SMSModel.getInstance(context);
            smsModel.setSmsListener(this);
            smsModel.subMitVerificationCode("86", phoneNum, code);
        } else {
            verificationCodeView.showToast("密码格式错误");
        }
    }

    @Override
    public void onCodeSended() {

    }

    @Override
    public void onCodeVerified(final boolean success, final String msg) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (success) {
                    verificationCodeView.showToast(msg);
                    verificationCodeView.goBack(Config.CodeSuccess);
                } else {
                    verificationCodeView.showToast(msg);
                }
            }
        });
    }


}
