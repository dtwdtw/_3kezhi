package com.fenniao.a3kezhi.Model;

import com.fenniao.a3kezhi.Been.LicaiBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/3 0003.
 */

public class LicaiLoadModel {
    public void getGetLicaiBeen(final LicaiLoadListener licaiLoadListener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                LicaiBean licaiBean =new LicaiBean();
                List<LicaiBean.BannerListBean> bannerListBeenList=new ArrayList<LicaiBean.BannerListBean>();
                LicaiBean.BannerListBean bannerListBean=new LicaiBean.BannerListBean();
                bannerListBean.setImgUrl("http://pic.qiantucdn.com/58pic/18/70/60/93c58PICafj_1024.jpg");
                bannerListBeenList.add(bannerListBean);
                bannerListBean=new LicaiBean.BannerListBean();
                bannerListBean.setImgUrl("http://img07.tooopen.com/images/20170412/tooopen_sy_205630266491.jpg");
                bannerListBeenList.add(bannerListBean);
                bannerListBean=new LicaiBean.BannerListBean();
                bannerListBean.setImgUrl("http://img02.tooopen.com/images/20141231/sy_78327074576.jpg");
                bannerListBeenList.add(bannerListBean);
                bannerListBean=new LicaiBean.BannerListBean();
                bannerListBean.setImgUrl("http://img05.tooopen.com/images/20150202/sy_80219211654.jpg");
                bannerListBeenList.add(bannerListBean);

                List<LicaiBean.ClassfiedBean> classfiedBeenList=new ArrayList<LicaiBean.ClassfiedBean>();
                LicaiBean.ClassfiedBean classfiedBean=new LicaiBean.ClassfiedBean();

                List<LicaiBean.ClassfiedBean.ItemListBean> itemListBeanList=new ArrayList<LicaiBean.ClassfiedBean.ItemListBean>();
                for (int i = 0; i < 3; i++) {
                    LicaiBean.ClassfiedBean.ItemListBean itemListBean=new LicaiBean.ClassfiedBean.ItemListBean();
                    itemListBean.setDescripe("历史年化收益率");
                    itemListBean.setLongth("5");
                    itemListBean.setLongthDescripe("期限/月");
                    itemListBean.setName("学生贷");
                    itemListBean.setRateOfReturn(12);
                    itemListBeanList.add(itemListBean);
                }
                classfiedBean.setItemList(itemListBeanList);
                classfiedBean.setTitleName("新品");
                classfiedBean.setDescripe("新手产品");
                classfiedBeenList.add(classfiedBean);
                classfiedBeenList.add(classfiedBean);
                List<LicaiBean.MenuItemBean> menuListBeanItem =new ArrayList<LicaiBean.MenuItemBean>();
                for (int i = 0; i < 4; i++) {
                    LicaiBean.MenuItemBean menuItemBean =new LicaiBean.MenuItemBean();
                    menuItemBean.setName("新手指导");
                    menuListBeanItem.add(menuItemBean);
                }
                licaiBean.setMenuList(menuListBeanItem);
                licaiBean.setClassfied(classfiedBeenList);
                licaiBean.setBannerList(bannerListBeenList);
                licaiLoadListener.onSuccess(licaiBean);
            }
        }).start();
    }
}
