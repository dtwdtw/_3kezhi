package com.fenniao.a3kezhi.View.Licai;

import com.fenniao.a3kezhi.Been.LicaiBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/3 0003.
 */

public interface LicaiView {
    void setBannerUrls(List<String> imgUrls);
    void setLicaiMenuData(List<LicaiBean.MenuItemBean> menudata);
    void setLicaiRecycleData(List<LicaiBean.ClassfiedBean> recycleData);
    void toLoginActivity();
}
