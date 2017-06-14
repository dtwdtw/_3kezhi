package com.fenniao.a3kezhi.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.fenniao.a3kezhi.R;
import com.umeng.analytics.MobclickAgent;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        JPushInterface.onPause(this);
    }

    protected void enableBackButton(){
        findViewById(R.id.back_button).setVisibility(View.VISIBLE);
        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.super.onBackPressed();
            }
        });
    }

    protected void onSubtitleClick(View v){}

    protected void disenableBackButton(){
        findViewById(R.id.back_button).setVisibility(View.GONE);
    }

    protected void setMainTitle(String title){
        ((TextView)findViewById(R.id.main_title)).setText(title);
    }

    protected String getMainTitle(){
        return ((TextView)findViewById(R.id.main_title)).getText().toString();
    }

    protected void setSubtitle(String subtitle){
        ((TextView)findViewById(R.id.subtitle)).setText(subtitle);
        findViewById(R.id.subtitle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubtitleClick(v);
            }
        });
    }

    protected String getSubtitle(){
        return ((TextView)findViewById(R.id.subtitle)).getText().toString();
    }
}
