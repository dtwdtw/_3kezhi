package com.fenniao.a3kezhi.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.fenniao.a3kezhi.Config;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class SPUtil {
    SharedPreferences sharedPreferences;
    public SPUtil(Context context){
        sharedPreferences=context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
    }
    public String getUserName(){
        return sharedPreferences.getString(Config.Key_SP_UserName,"");
    }
    public String getUserPassword(){
        return sharedPreferences.getString(Config.Key_SP_UserPassworde,"");
    }
    public void setUserName(String userName){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Config.Key_SP_UserName,userName).commit();
    }
    public void setUserPassword(String password){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Config.Key_SP_UserPassworde,password).commit();
    }
}
