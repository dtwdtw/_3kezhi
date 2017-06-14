package com.fenniao.a3kezhi.View.Login;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.fenniao.a3kezhi.Config;
import com.fenniao.a3kezhi.Presenter.LoginPresenter;
import com.fenniao.a3kezhi.R;
import com.fenniao.a3kezhi.Service.JMessageService;
import com.fenniao.a3kezhi.View.BaseActivity;
import com.fenniao.a3kezhi.View.FindPassword.FindPasswordActivity;
import com.fenniao.a3kezhi.View.Register.RegisterActivity;

/**
 * Created by Administrator on 2017/5/3 0003.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener,LoginView{
    LoginPresenter loginPresenter=new LoginPresenter(this);
    EditText userName, password;
    Button login;
    FrameLayout register;
    Drawable drawable;
    ImageView hideIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        enableBackButton();
        setMainTitle("登录");
        setSubtitle("忘记密码");
        userName = (EditText) findViewById(R.id.user_name);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (FrameLayout) findViewById(R.id.register);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        drawable = getResources().getDrawable(R.drawable.ic_menu_gallery);
        hideIcon = (ImageView) findViewById(R.id.hide_img);

        userName.setOnTouchListener(new OnClearTouch());
        password.setOnTouchListener(new OnClearTouch());
        hideIcon.setOnClickListener(this);
        userName.addTextChangedListener(new ShowClearIcon(userName));
        password.addTextChangedListener(new ShowClearIcon(password));
    }

    @Override
    protected void onSubtitleClick(View v) {
        super.onSubtitleClick(v);
        startActivity(new Intent(this, FindPasswordActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.password:
                break;
            case R.id.hide_img:
                if (password.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    password.setSelection(password.getText().length());
                } else {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password.setSelection(password.getText().length());
                }
                break;
            case R.id.register:
                startActivityForResult(new Intent(this, RegisterActivity.class), Config.RequestCodeRegiste);
                break;
            case R.id.login:
                loginPresenter.login(this,userName.getText().toString(),password.getText().toString());
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Config.RequestCodeRegiste:
                if(resultCode==Config.CodeSuccess){
                    Log.v("dtw",data.getStringExtra(Config.Key_PhoneNum)+"  "+data.getStringExtra(Config.Key_Password));
                    loginPresenter.login(this,data.getStringExtra(Config.Key_PhoneNum),data.getStringExtra(Config.Key_Password));
                }else if(resultCode==Config.CodeFail){
                    Log.v("dtw","fail");
                }
                break;
        }
    }

    class OnClearTouch implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    Drawable drawableRight = ((EditText) v).getCompoundDrawables()[2];
                    if (drawableRight != null && event.getX() >
                            ((EditText) v).getWidth() - ((EditText) v).getCompoundPaddingRight() &&
                            event.getX() < ((EditText) v).getWidth() - ((EditText) v).getPaddingRight() + drawable.getBounds().width()) {
                        ((EditText) v).setText("");
                    }
                    break;
            }
            return false;
        }
    }

    class ShowClearIcon implements TextWatcher {
        EditText editText;

        public ShowClearIcon(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (start == 0 && count > 0) {
                editText.setCompoundDrawablesWithIntrinsicBounds(editText.getCompoundDrawables()[0], null, drawable, null);
            } else if (s.length() == 0) {
                editText.setCompoundDrawablesWithIntrinsicBounds(editText.getCompoundDrawables()[0], null, null, null);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
