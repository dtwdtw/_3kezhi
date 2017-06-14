package com.fenniao.a3kezhi.View.VerificationCode;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fenniao.a3kezhi.Config;
import com.fenniao.a3kezhi.Presenter.VerificationCodePresener;
import com.fenniao.a3kezhi.R;
import com.fenniao.a3kezhi.View.BaseActivity;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class VerificationCodeActivity extends BaseActivity implements VerificationCodeView, View.OnFocusChangeListener {
    VerificationCodePresener verificationCodePresener =new VerificationCodePresener(this);
    EditText pwdEdit,virifyCodeEdit;
    TextView noteText,voiceVerifyText;
    Button getVerifyCodeBtn,subminBtn;
    CountDownTimer countDownTimer=new CountDownTimer(1000*65,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            getVerifyCodeBtn.setText("重新获取"+millisUntilFinished/1000);
        }

        @Override
        public void onFinish() {
            setEnableVerify(true);
        }
    };
    String phoneNum;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification_code_activity);
        enableBackButton();
        setMainTitle("设置密码");
        pwdEdit = (EditText) findViewById(R.id.pwd);
        virifyCodeEdit= (EditText) findViewById(R.id.verify_code);
        getVerifyCodeBtn= (Button) findViewById(R.id.get_verify_code);
        subminBtn= (Button) findViewById(R.id.submit_btn);
        noteText= (TextView) findViewById(R.id.note_text);
        voiceVerifyText= (TextView) findViewById(R.id.voice_verify);

        phoneNum=getIntent().getStringExtra(Config.Key_PhoneNum);
        pwdEdit.setOnFocusChangeListener(this);

        countDownTimer.start();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.pwd:
                noteText.setVisibility(hasFocus?View.VISIBLE:View.GONE);
                break;
        }
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.submit_btn:
                verificationCodePresener.verifyCode(this,phoneNum,pwdEdit.getText(),virifyCodeEdit.getText().toString());
                Log.v("dtw",phoneNum);
                break;
            case R.id.voice_verify:
                verificationCodePresener.getVoiceCode(this,phoneNum);
                break;
            //获取验证码
            case R.id.get_verify_code:
                showToast("验证码已发送，请稍后");
                setEnableVerify(false);
                countDownTimer.start();
                verificationCodePresener.getVerificationCode(this,phoneNum);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDownTimer.cancel();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goBack(int resultCode) {
        Intent intent=new Intent();
        intent.putExtra(Config.Key_PhoneNum,phoneNum);
        intent.putExtra(Config.Key_Password,pwdEdit.getText().toString());
        setResult(resultCode,intent);
        onBackPressed();
    }

    @Override
    public void setEnableVerify(boolean enable) {
        getVerifyCodeBtn.setEnabled(enable);
        voiceVerifyText.setEnabled(enable);
        if(enable){
            getVerifyCodeBtn.setText("获取验证码");
            voiceVerifyText.setTextColor(getResources().getColor(R.color.lightBlue));
        }else{
            voiceVerifyText.setTextColor(Color.DKGRAY);
        }
    }
}
