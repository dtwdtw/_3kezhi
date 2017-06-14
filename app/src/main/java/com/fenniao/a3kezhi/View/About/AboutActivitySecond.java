package com.fenniao.a3kezhi.View.About;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.fenniao.a3kezhi.Lib.BezierView;
import com.fenniao.a3kezhi.R;
import com.fenniao.a3kezhi.View.BaseActivity;

/**
 * Created by Administrator on 2017/5/27 0027.
 */

public class AboutActivitySecond extends BaseActivity {
    BezierView bezierView;
    EditText editText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity_second);
        bezierView= (BezierView) findViewById(R.id.bezier_view);
        editText= (EditText) findViewById(R.id.edit_text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(s)) {
                    bezierView.setRate(Integer.valueOf(s.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.add:
                bezierView.addPoint();
                break;
            case R.id.del:
                bezierView.delPoint();
                break;
            case R.id.start:
                bezierView.start();
                break;
            case R.id.stop:
                bezierView.stop();
                break;
        }
    }
}
