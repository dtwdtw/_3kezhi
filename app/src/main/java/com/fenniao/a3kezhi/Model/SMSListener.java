package com.fenniao.a3kezhi.Model;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public interface SMSListener {
    void onCodeSended();
    void onCodeVerified(boolean success, String msg);
}
