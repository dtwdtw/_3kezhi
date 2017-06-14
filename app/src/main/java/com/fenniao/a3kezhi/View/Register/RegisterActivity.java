package com.fenniao.a3kezhi.View.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fenniao.a3kezhi.Config;
import com.fenniao.a3kezhi.Presenter.RegisterPresener;
import com.fenniao.a3kezhi.R;
import com.fenniao.a3kezhi.View.BaseActivity;
import com.fenniao.a3kezhi.View.VerificationCode.VerificationCodeActivity;
import com.fenniao.a3kezhi.View.WebView.WebViewActivity;

/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class RegisterActivity extends BaseActivity implements RegisterView, CompoundButton.OnCheckedChangeListener {
    EditText phoneNum, phoneOther;
    CheckBox agreementCheck;
    TextView agreementText;
    Button nextBtn;
    RegisterPresener registerPresener = new RegisterPresener(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        setMainTitle("注册");
        enableBackButton();

        phoneNum = (EditText) findViewById(R.id.pwd);
        phoneOther = (EditText) findViewById(R.id.phone_other);
        agreementCheck = (CheckBox) findViewById(R.id.agreement_checkbox);
        agreementText = (TextView) findViewById(R.id.agreement_text);
        nextBtn = (Button) findViewById(R.id.next_step);

        agreementCheck.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.agreement_checkbox:
                nextBtn.setEnabled(isChecked);
                break;
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.agreement_text:
                Intent intent = new Intent(this, WebViewActivity.class);
                intent.putExtra(Config.Key_WebUrl, Config.AgreementUrl);
                intent.putExtra(Config.Key_MainTitle, "3颗志用户协议");
                startActivity(intent);
                break;
            case R.id.next_step:
                registerPresener.getVerificationCode(this, phoneNum.getText(), phoneOther.getText());
                break;
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toVerificationCodeActivity() {
        Intent intent = new Intent(this, VerificationCodeActivity.class);
        intent.putExtra(Config.Key_PhoneNum, phoneNum.getText().toString());
        startActivityForResult(intent, Config.RequestCodeSubmitCode);
    }

    @Override
    public void goBack(int resultCode, Intent data) {
        setResult(resultCode,data);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Config.RequestCodeSubmitCode:
                registerPresener.onRegisted(resultCode, data);
                break;
        }
    }
}
