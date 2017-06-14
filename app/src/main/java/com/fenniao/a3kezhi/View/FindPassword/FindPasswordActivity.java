package com.fenniao.a3kezhi.View.FindPassword;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fenniao.a3kezhi.R;
import com.fenniao.a3kezhi.View.BaseActivity;

/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class FindPasswordActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_password_activity);
        setMainTitle("找回密码");
        enableBackButton();
    }
}
