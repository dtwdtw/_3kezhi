package com.fenniao.a3kezhi;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class Config {
    public static boolean ShowDrawerLayout = false;
    public static boolean ShowFloatingActionButton = false;
    public static String AgreementUrl ="https://image.xiaoniu88.com/static/WebXn/dist/user/querytip/querytips.html?v=20170427";
    public static boolean ShowToolBar = false;
    public static boolean isChartActivityOnTop=false;

    public static String Key_WebUrl = "weburl";
    public static String Key_MainTitle = "maintitle";
    public static String Key_PhoneNum="phonenum";
    public static String Key_Password="password";

    public static String Key_SP_UserName="username";
    public static String Key_SP_UserPassworde="userpassword";

    public static final int RequestCodeRegiste =1;
    public static final int RequestCodeSubmitCode =2;

    public static final int CodeSuccess =1001;
    public static final int CodeFail =2001;

    //SMSSDK 错误代码
    public static final int VerifiedCodeError=468; //验证码错误
    public static final int OutOfCount =477;   //手机号超过验证次数

    public static String[] tagList = new String[]{"licai", "zhanghu"};
}
