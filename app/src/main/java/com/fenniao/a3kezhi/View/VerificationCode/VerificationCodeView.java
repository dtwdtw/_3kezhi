package com.fenniao.a3kezhi.View.VerificationCode;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public interface VerificationCodeView {
    void showToast(String msg);
    void goBack(int resultCode);
    void setEnableVerify(boolean enable);
}
