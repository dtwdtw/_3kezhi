package com.fenniao.a3kezhi.View.Licai;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.fenniao.a3kezhi.Been.LicaiBean;
import com.fenniao.a3kezhi.Presenter.LicaiPresenter;
import com.fenniao.a3kezhi.R;
import com.fenniao.a3kezhi.View.GlideImageLoader;
import com.fenniao.a3kezhi.View.Login.LoginActivity;
import com.fenniao.a3kezhi.View.OnRecycleViewItemClickListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Administrator on 2017/5/2 0002.
 */

public class LicaiFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate,LicaiView,OnRecycleViewItemClickListener, OnBannerListener, View.OnClickListener {
    private BGARefreshLayout mBGARefreshLayout;
    private Banner banner;
    private RecyclerView menu;
    private RecyclerView licaiRecycleList;
    private LicaiPresenter licaiPresenter =new LicaiPresenter(this);
    private FrameLayout virtualToolbar;
    private View headView;
    private RadioButton allKind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_licai,container,false);
        headView=inflater.inflate(R.layout.licai_headview,container,false);
//        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        headView.setLayoutParams(layoutParams);

        virtualToolbar = (FrameLayout) view.findViewById(R.id.virtual_toolbar);

        mBGARefreshLayout= (BGARefreshLayout) view.findViewById(R.id.bgarl);
        mBGARefreshLayout.setDelegate(this);
        mBGARefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity(),true));
        mBGARefreshLayout.setIsShowLoadingMoreView(true);

        banner = (Banner) headView.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setDelayTime(3000);
        banner.setOnBannerListener(this);

        allKind= (RadioButton) headView.findViewById(R.id.all_kind);
        allKind.setOnClickListener(this);

        menu= (RecyclerView) headView.findViewById(R.id.licai_menu);

        licaiRecycleList = (RecyclerView) view.findViewById(R.id.licai_recyclelist);
        licaiRecycleList.setLayoutManager(new LinearLayoutManager(getActivity()));

        licaiPresenter.load();
        return view;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        licaiPresenter.load();
        Log.v("dtw","lode");
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        Log.v("dtw","lodingMore");
        return false;
    }

    @Override
    public void setBannerUrls(List<String> imgUrls) {
        banner.setImages(imgUrls);
        banner.setImageLoader(new GlideImageLoader());
        banner.start();
        mBGARefreshLayout.endRefreshing();
    }

    @Override
    public void setLicaiMenuData(List<LicaiBean.MenuItemBean> menudata) {
        LicaiMenuAdapter shouyeMenuAdapter=new LicaiMenuAdapter(getActivity(),menudata);
        shouyeMenuAdapter.OnRecycleViewItemClickListener(this);
        menu.setAdapter(shouyeMenuAdapter);
        menu.setLayoutManager(new GridLayoutManager(getActivity(),4));
    }

    @Override
    public void setLicaiRecycleData(List<LicaiBean.ClassfiedBean> recycleData) {
        LicaiRecycleListAdapter licaiRecycleListAdapter =new LicaiRecycleListAdapter(getActivity(),recycleData);
        licaiRecycleListAdapter.setOnRecycleViewItemClickListener(this);
        licaiRecycleListAdapter.setHeadView(headView);
        licaiRecycleList.setAdapter(licaiRecycleListAdapter);
        licaiRecycleList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(-200<=headView.getTop()&&headView.getTop()<=0){
                    virtualToolbar.setAlpha(Math.abs((float)headView.getTop()/200f));
                }
            }
        });
    }


    @Override
    public void toLoginActivity() {
        startActivity(new Intent(getActivity(),LoginActivity.class));
    }

    @Override
    public void onRecycleViewItemClick(View view, String adapterClassName,int viewType, int listPosition, int itemPosition) {
        if(LicaiMenuAdapter.class.getName().equals(adapterClassName)){
            Log.v("dtw",adapterClassName+itemPosition);
        }else if(LicaiRecycleListAdapter.class.getName().equals(adapterClassName)){
            Log.v("dtw",adapterClassName+" viewtype:" +viewType+" listposition:"+listPosition+" itemposition:"+itemPosition );
        }
    }

    @Override
    public void OnBannerClick(int position) {
        Log.v("dtw","bannerclick:"+position);
    }

    @Override
    public void onClick(View v) {
        Log.v("dtw","分类 Click");
    }
}
