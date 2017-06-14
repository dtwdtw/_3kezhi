package com.fenniao.a3kezhi.Presenter;

import android.os.Handler;

import com.fenniao.a3kezhi.Been.LicaiBean;
import com.fenniao.a3kezhi.Model.LicaiLoadListener;
import com.fenniao.a3kezhi.Model.LicaiLoadModel;
import com.fenniao.a3kezhi.View.Licai.LicaiView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/3 0003.
 */

public class LicaiPresenter {
    LicaiLoadModel licaiLoadModel;
    LicaiView licaiView;
    Handler handler=new Handler();
    public LicaiPresenter(LicaiView licaiView){
        this.licaiView = licaiView;
        licaiLoadModel =new LicaiLoadModel();
    }
    public void load(){
        licaiLoadModel.getGetLicaiBeen(new LicaiLoadListener() {
            @Override
            public void onSuccess(final LicaiBean licaiBean) {
                List<LicaiBean.BannerListBean> bannerList= licaiBean.getBannerList();
                final List<String> imgUrlList=new ArrayList<>();
                for (int i = 0; i < bannerList.size(); i++) {
                    imgUrlList.add(bannerList.get(i).getImgUrl());

                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        licaiView.setBannerUrls(imgUrlList);
                        licaiView.setLicaiMenuData(licaiBean.getMenuList());
                        licaiView.setLicaiRecycleData(licaiBean.getClassfied());
                    }
                });
            }

            @Override
            public void onFail() {

            }
        });
    }
}
