package com.fenniao.a3kezhi.View.About;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fenniao.a3kezhi.R;
import com.fenniao.a3kezhi.View.BaseActivity;
import com.jawnnypoo.physicslayout.PhysicsLinearLayout;

/**
 * Created by Administrator on 2017/5/27 0027.
 */

public class AboutActivity extends BaseActivity{
    PhysicsLinearLayout physicsLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);
        physicsLayout= (PhysicsLinearLayout) findViewById(R.id.physics_layout);
        physicsLayout.getPhysics().enableFling();
        Glide.with(this)
                .load("http://pic.qiantucdn.com/58pic/18/70/60/93c58PICafj_1024.jpg")
                .placeholder(R.drawable.ic_menu_gallery)
                .dontAnimate()
                .into((ImageView) physicsLayout.getChildAt(0));
        Glide.with(this)
                .load("http://pic.qiantucdn.com/58pic/18/70/60/93c58PICafj_1024.jpg")
                .placeholder(R.drawable.ic_menu_gallery)
                .dontAnimate()
                .into((ImageView) physicsLayout.getChildAt(1));
        Glide.with(this)
                .load("http://pic.qiantucdn.com/58pic/18/70/60/93c58PICafj_1024.jpg")
                .placeholder(R.drawable.ic_menu_gallery)
                .dontAnimate()
                .into((ImageView) physicsLayout.getChildAt(3));
//        Glide.with(this)
//                .load("http://pic.qiantucdn.com/58pic/18/70/60/93c58PICafj_1024.jpg")
//                .into((ImageView) physicsLayout.getChildAt(1));
    }
}
