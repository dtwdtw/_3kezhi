package com.fenniao.a3kezhi.Model;

import com.fenniao.a3kezhi.Been.LicaiBean;

/**
 * Created by Administrator on 2017/5/3 0003.
 */

public interface LicaiLoadListener {
    void onSuccess(LicaiBean licaiBean);
    void onFail();
}
