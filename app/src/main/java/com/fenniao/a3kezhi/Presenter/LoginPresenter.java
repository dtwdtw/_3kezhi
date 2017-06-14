package com.fenniao.a3kezhi.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.fenniao.a3kezhi.Service.JMessageService;
import com.fenniao.a3kezhi.Utils.SPUtil;
import com.fenniao.a3kezhi.View.Login.LoginView;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class LoginPresenter {
    Handler handler=new Handler();
    private LoginView loginView;
    public LoginPresenter(LoginView loginView){
        this.loginView=loginView;
    }
    public void login(final Context context, final String phoneNum, final String password){
        //极光message登录
        JMessageClient.init(context);
        JMessageClient.setNotificationMode(JMessageClient.NOTI_MODE_NO_NOTIFICATION);
        JMessageClient.login(phoneNum,password, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                Log.v("dtw", i + "  " + s);
                if(i==0){
                    SPUtil spUtil=new SPUtil(context);
                    spUtil.setUserName(phoneNum);
                    spUtil.setUserPassword(password);
                    context.startService(new Intent(context, JMessageService.class));
                }
                //用户不存在
                else if(i==801003) {
//                    JMessageClient.register(intent.getStringExtra("name"), intent.getStringExtra("password"), new BasicCallback() {
//                        @Override
//                        public void gotResult(int i, String s) {
//                            Log.v("dtw", i + "  " + s);
//                            if (i == 0) {
//                                JMessageClient.login(intent.getStringExtra("name"),intent.getStringExtra("password"), null);
//                            }
//                        }
//                    });
                }
                //用户名不合法
                else if(i==871303 ){

                }
                //密码错误
                else if(i==801004){

                }
            }
        });
    }
}
