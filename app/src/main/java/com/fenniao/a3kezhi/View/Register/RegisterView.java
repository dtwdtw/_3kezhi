package com.fenniao.a3kezhi.View.Register;

import android.content.Intent;

/**
 * Created by Administrator on 2017/6/2 0002.
 */

public interface RegisterView {
    void showToast(String msg);
    void toVerificationCodeActivity();
    void goBack(int resultCode,Intent data);
}
