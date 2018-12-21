package com.yangfan.mvp.mvp.common;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.yangfan.mvp.R;
import com.yangfan.mvp.databinding.ActivityWebBinding;
import com.yangfan.mvp.mvp.base.BaseActivity;
import com.yangfan.mvp.utils.CommonUtil;
import com.yangfan.mvp.utils.Constant;
import com.yangfan.mvp.utils.LoadAnimationUtils;
import com.yangfan.mvp.utils.LogUtils;
import com.yangfan.mvp.utils.StatusBarUtil;
import com.yangfan.mvp.utils.ViewUtils;
import com.yangfan.mvp.widget.TipsToast;
import com.yangfan.utils.CommonUtils;

import java.util.HashMap;
import java.util.Map;


public class WebActivity extends BaseActivity implements OnClickListener {
    private String path="https://trade.lendx.vip/pkg/android/lendchain.apk";
    WebView webview;
    String url;
    private LoadAnimationUtils utils;

    private String mTitle;
    private ActivityWebBinding binding;
    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_web);
        setContentView(R.layout.activity_web);
        StatusBarUtil.setStatusBarColor(WebActivity.this, R.color.white);
        StatusBarUtil.StatusBarLightMode(WebActivity.this);
        initView();
    }



    @SuppressLint("JavascriptInterface")
    public void initView() {
        utils = new LoadAnimationUtils(this);
        utils.showProcessAnimation();
        webview = new WebView(this);
        binding.swipeContainer.addView(webview);
        binding.baseTitleBar.setLayLeftBackClickListener(v -> finish());
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            boolean isCanRefresh = bundle.getBoolean(Constant.INTENT_EXTRA_WEB_IS_REFRESH, true);
            binding.swipeContainer.setEnabled(isCanRefresh);
            url = bundle.getString(Constant.INTENT_EXTRA_URL, "");
            mTitle = bundle.getString(Constant.INTENT_EXTRA_TITLE, "");
            if (!TextUtils.isEmpty(mTitle)) {
                binding.baseTitleBar.setTitle(mTitle);
            }

        }
        ViewUtils.initSwipeLayout(binding.swipeContainer, onRefreshListener, 100);
        webViewSetting();

        getData();
        LogUtils.LogE(WebActivity.class, "url =" + url);
    }

    private void getData() {
        if (!CommonUtil.isNetworkConnected(this)) {
            utils.closeProcessAnimation();
            binding.swipeContainer.setVisibility(View.GONE);
            TipsToast.showTips(getString(R.string.netWorkError));
        } else {
            webview.loadUrl(url);
        }
    }

    private void webViewSetting() {

//        syncCookie(this, url);
        WebSettings webSettings = webview.getSettings();
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        // 如果访问的页面中有Javascript，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // 修改ua使得web端正确判断
        String ua = webSettings.getUserAgentString();
        webSettings.setUserAgentString(
                ua.concat(" app_lendchain_Android"));
        // 尝试缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDatabaseEnabled(true);// 开启 数据库存储机制
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadWithOverviewMode(true);
        // 设置编码
        webSettings.setDefaultTextEncodingName("utf-8");
//        idwebSettings.setBuiltInZoomControls(true);
        // 设置背景颜色 透明
        webview.setBackgroundColor(Color.argb(0, 0, 0, 0));
        // 设置本地调用对象及其接口
        /*** 打开本地缓存提供JS调用// 开启DOM storage **/
        webSettings.setDomStorageEnabled(true);
        // 设置缓存大小
        webSettings.setAppCacheMaxSize(1024 * 1024 * 8);
        String appCachePath = getApplicationContext().getCacheDir()
                .getAbsolutePath();
        webSettings.setAppCachePath(appCachePath);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        webview.setHorizontalScrollBarEnabled(false);//水平不显示
        webview.setVerticalScrollBarEnabled(false); //垂直不显示
        // 支持用户输入获取手势焦点
        webview.requestFocusFromTouch();
        String cacheDirPath = this.getFilesDir().getAbsolutePath() + "cache/";
        webSettings.setDatabasePath(cacheDirPath);// 设置缓存路径
        webSettings.setDatabaseEnabled(true);
        // 只需设置支持JS就自动打开IndexedDB存储机制
        // Android 在4.4开始加入对 IndexedDB 的支持，只需打开允许 JS 执行的开关就好了。

        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
//
//        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        webview.addJavascriptInterface(new JsInterface(this), "AndroidWebView");
//        webSettings.setSupportZoom(false);
//        webSettings.setDefaultTextEncodingName("utf-8");// 设置字符编码
        webview.setWebViewClient(webViewClient);// 会导致 js方法失效
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    utils.closeProcessAnimation();
                    binding.swipeContainer.setRefreshing(false);
                    if (TextUtils.isEmpty(mTitle)) {
                        binding.baseTitleBar.setTitle(webview.getTitle());
                    }
                }
                super.onProgressChanged(view, newProgress);
            }


        });
        webview.addJavascriptInterface(new JSBridge(), "lendchain");

    }

    WebViewClient webViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;//true自己处理跳转 刷新url false webview处理跳转
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (TextUtils.isEmpty(mTitle)) {
                binding.baseTitleBar.setTitle(webview.getTitle());
            }
            utils.closeProcessAnimation();
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            utils.closeProcessAnimation();
            binding.swipeContainer.setVisibility(View.GONE);
        }
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            if(error.getPrimaryError() == android.net.http.SslError.SSL_INVALID ){// 校验过程遇到了bug
                handler.proceed();
            }else{
                handler.cancel();
            }  //接受信任所有网站的证书
        }
    };

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            webview.loadUrl(webview.getUrl());
        }
    };

    @Override
    public void onDestroy() {
        CommonUtils.onDestroyWebView(webview);
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (webview.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lay_title_image_left_back:
                if (webview.canGoBack()) {
                    webview.goBack();
                } else {
                    finish();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;

//        if (requestCode == REQUEST_CODE_FOR_LOGIN_WITH_WEB_COLLECT) {
//            // 由于登录流程可跨页面，数据回传不一定能做到，所以在 onResume()方法中做登录状态判断
////            getDetail();
//            return;
//        }


    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstOnResume) {
            isFirstOnResume = false;
        } else {
        }
//        MobclickAgent.onPageStart(UmengAnalyticsPageHelper.UM_PAGE_Web);
//        MobclickAgent.onResume(this);

    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd(UmengAnalyticsPageHelper.UM_PAGE_Web);
//        MobclickAgent.onPause(this);
    }


    public class JSBridge {
        //js调用安卓方法
        @JavascriptInterface
        public String callapp(String message) {
            Log.e("test", message);
            String str="";
            Map<String, Object> map = new HashMap<String, Object>();
            map = new Gson().fromJson(message, map.getClass());
            double t= (double) map.get("type");
            String type = String.valueOf((int)t);
            if("1".equals(type)) {//靠海行动传token
            }else if("2".equals(type)){//安全认证
            }else if("3".equals(type)){//我的钱包
            }else if("10".equals(type)){//下载app
            }
            return str;
        }

    }
}
