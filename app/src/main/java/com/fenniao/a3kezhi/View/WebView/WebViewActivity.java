package com.fenniao.a3kezhi.View.WebView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.fenniao.a3kezhi.R;
import com.fenniao.a3kezhi.Config;
import com.fenniao.a3kezhi.View.BaseActivity;

/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class WebViewActivity extends BaseActivity {
    Intent intent;
    WebView webView;
    ProgressBar lodingProgress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_activity);
        intent=getIntent();
        webView= (WebView) findViewById(R.id.webview_body);
        lodingProgress= (ProgressBar) findViewById(R.id.loding_progress);

        enableBackButton();
        setMainTitle(intent.getStringExtra(Config.Key_MainTitle));

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                lodingProgress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                lodingProgress.setVisibility(View.GONE);
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                lodingProgress.setProgress(newProgress);
                Log.v("dtw","webprogress " + newProgress);
            }
        });
        webView.loadUrl(intent.getStringExtra(Config.Key_WebUrl));
    }
}
