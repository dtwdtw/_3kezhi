package com.fenniao.a3kezhi.View.Zhanghu;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fenniao.a3kezhi.Been.ZhanghuItemBean;
import com.fenniao.a3kezhi.R;
import com.fenniao.a3kezhi.View.Chart.ChartActivity;
import com.fenniao.a3kezhi.View.Login.LoginActivity;
import com.fenniao.a3kezhi.View.OnRecycleViewItemClickListener;
import com.fenniao.a3kezhi.View.About.AboutActivity;
import com.fenniao.a3kezhi.View.About.AboutActivitySecond;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/2 0002.
 */

public class ZhanghuFragment extends Fragment implements OnRecycleViewItemClickListener, View.OnClickListener {
    RecyclerView recyclerView;
    ZhanghuRecycleListAdapter zhanghuRecycleListAdapter;
    List<ZhanghuItemBean> zhanghuItemBeanList=new ArrayList<>();
    Button tixianButton,chongzhiButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_zhanghu,container,false);
        view.findViewById(R.id.login).setOnClickListener(this);
        tixianButton= (Button) view.findViewById(R.id.tixian);
        chongzhiButton= (Button) view.findViewById(R.id.chongzhi);

        recyclerView= (RecyclerView) view.findViewById(R.id.zhanghu_recyclelist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadData(zhanghuItemBeanList);
        zhanghuRecycleListAdapter=new ZhanghuRecycleListAdapter(getActivity(),zhanghuItemBeanList);
        zhanghuRecycleListAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(zhanghuRecycleListAdapter);

        return view;
    }
    private void loadData(List<ZhanghuItemBean> zhanghuItemBeanList){
        ZhanghuItemBean zhanghuItemBean=new ZhanghuItemBean();
        zhanghuItemBean.setItemIconResID(R.drawable.ic_menu_camera);
        zhanghuItemBean.setItemName("我的投资");
        zhanghuItemBeanList.add(zhanghuItemBean);
        zhanghuItemBean=new ZhanghuItemBean();
        zhanghuItemBean.setItemIconResID(R.drawable.ic_menu_gallery);
        zhanghuItemBean.setItemName("我的投资");
        zhanghuItemBeanList.add(zhanghuItemBean);
        zhanghuItemBean=new ZhanghuItemBean();
        zhanghuItemBean.setItemIconResID(R.drawable.ic_menu_manage);
        zhanghuItemBean.setItemName("我的投资");
        zhanghuItemBeanList.add(zhanghuItemBean);
        zhanghuItemBean=new ZhanghuItemBean();
        zhanghuItemBean.setItemIconResID(R.drawable.ic_menu_send);
        zhanghuItemBean.setItemName("我的投资");
        zhanghuItemBeanList.add(zhanghuItemBean);
        zhanghuItemBean=new ZhanghuItemBean();
        zhanghuItemBean.setItemIconResID(R.drawable.ic_menu_share);
        zhanghuItemBean.setItemName("我的投资");
        zhanghuItemBeanList.add(zhanghuItemBean);
        zhanghuItemBean=new ZhanghuItemBean();
        zhanghuItemBean.setItemIconResID(R.drawable.ic_menu_slideshow);
        zhanghuItemBean.setItemName("我的投资");
        zhanghuItemBeanList.add(zhanghuItemBean);
    }

    @Override
    public void onRecycleViewItemClick(View view, String adapterClassName, int viewType, int listPosition, int itemPosition) {
        Log.v("dtw",""+itemPosition);
        if(ZhanghuRecycleListAdapter.class.getName().equals(adapterClassName)){
            switch (itemPosition){
                case 2:
                    startActivity(new Intent(getActivity(), ChartActivity.class));
                    break;
                case 3:
                    Dialog dialog=new Dialog(getActivity(),R.style.dialog);
                    dialog.setContentView(R.layout.loding_view);
                    dialog.show();
                    break;
                case 4:
                    startActivity(new Intent(getActivity(), AboutActivitySecond.class));
                    break;
                case 5:
                    startActivity(new Intent(getActivity(), AboutActivity.class));
                    break;
            }
        };
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }
}
