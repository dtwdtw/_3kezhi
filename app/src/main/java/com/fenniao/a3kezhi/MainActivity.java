package com.fenniao.a3kezhi;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fenniao.a3kezhi.Model.JPushModel;
import com.fenniao.a3kezhi.View.BaseActivity;
import com.fenniao.a3kezhi.View.Zhanghu.ZhanghuFragment;
import com.fenniao.a3kezhi.View.Licai.LicaiFragment;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,RadioGroup.OnCheckedChangeListener{

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        JPushModel.getInstance(this);

        //<editor-fold desc="取消toolbar">
        if(!Config.ShowToolBar) {
            toolbar.setVisibility(View.GONE);
        }
        //</editor-fold>
        //<editor-fold desc="取消浮动按钮">
        if(Config.ShowFloatingActionButton) {
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }else{
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setVisibility(View.GONE);
        }
        //</editor-fold>
        //<editor-fold desc="取消侧边栏">
        if(Config.ShowDrawerLayout) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
        }else{
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
        //</editor-fold>

        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(MainActivity.this);
        ((RadioButton)findViewById(R.id.shouye)).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //载入菜单资源
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //菜单点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //左侧划出导航栏点击事件
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        Fragment fragment;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        switch(checkedId) {
            case R.id.shouye:
                fragment=fragmentManager.findFragmentByTag(Config.tagList[0]);
                if(fragment==null){
                    fragmentTransaction.add(R.id.fragment,new LicaiFragment(), Config.tagList[0]);
                    fragmentTransaction.commit();
                }
                showFragmentByTag(fragmentManager, Config.tagList[0]);
                break;
            case R.id.licai:
                fragment=fragmentManager.findFragmentByTag(Config.tagList[1]);
                if(fragment==null){
                    fragmentTransaction.add(R.id.fragment,new ZhanghuFragment(), Config.tagList[1]);
                    fragmentTransaction.commit();
                }
                showFragmentByTag(fragmentManager, Config.tagList[1]);
                break;
//            case R.id.wode:
//                fragment=fragmentManager.findFragmentByTag(Config.tagList[2]);
//                if(fragment==null){
//                    fragmentTransaction.add(R.id.fragment,new WodeFragment(),Config.tagList[2]);
//                    fragmentTransaction.commit();
//                }
//                showFragmentByTag(fragmentManager, Config.tagList[2]);
//                break;
//            case R.id.gengduo:
//                fragment=fragmentManager.findFragmentByTag(Config.tagList[3]);
//                if(fragment==null){
//                    fragmentTransaction.add(R.id.fragment,new GengduoFragment(),Config.tagList[3]);
//                    fragmentTransaction.commit();
//                }
//                showFragmentByTag(fragmentManager, Config.tagList[3]);
//                break;
        }
    }

    private void showFragmentByTag(FragmentManager fragmentManager,String tag){
        Fragment fragment;
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        for(int i = 0; i< Config.tagList.length; i++){
            fragment=fragmentManager.findFragmentByTag(Config.tagList[i]);
            if(fragment!=null){
                if(tag.equals(Config.tagList[i])){
                    fragmentTransaction.show(fragment);
                }else{
                    fragmentTransaction.hide(fragment);
                }
            }
        }
        fragmentTransaction.commit();
    }
}
