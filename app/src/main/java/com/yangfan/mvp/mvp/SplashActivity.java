package com.yangfan.mvp.mvp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.yangfan.mvp.R;
import com.yangfan.mvp.utils.CommonUtil;
import com.yangfan.mvp.utils.SPUtil;

import java.lang.ref.WeakReference;

import rx.Observer;

public class SplashActivity extends Activity {

    public static final int DEFAULT_SPLASH_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        CommonUtil2.jPushAlias(true, CommonUtil2.getHxImUserIdByAppUserId(CommonUtil2.getPhoneSign()));
//        CommonUtil2.queryCurrencyRelationList(true);

        if (!isTaskRoot()) {
            finish();
            return;
        }
        setContentView(R.layout.activity_splash);
        initView();
    }



    public void initView() {
        //请求权限
        RxPermissions rxPermission = new RxPermissions(SplashActivity.this);
        rxPermission
                .request(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onNext(Boolean args) {
                        new StaticHandler(SplashActivity.this, SplashActivity.this).sendEmptyMessageDelayed(0, DEFAULT_SPLASH_TIME);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onCompleted() {
                    }
                });
    }

    private static class StaticHandler extends Handler {
        private final WeakReference<Context> weakContext;
        private final WeakReference<SplashActivity> mParent;

        public StaticHandler(Context context, SplashActivity view) {
            weakContext = new WeakReference<>(context);
            mParent = new WeakReference<>(view);
        }

        @Override
        public void handleMessage(Message msg) {
            Context context = weakContext.get();
            SplashActivity parent = mParent.get();
            if (null != context && null != parent) {
                parent.initGoTo();
                super.handleMessage(msg);
            }
        }
    }

    private void initGoTo() {
        int verId = (int) SPUtil.getInt(SPUtil.KEY_VERSIONID,0);
        if (CommonUtil.getVersionId(SplashActivity.this) > verId) {
            CommonUtil.openActicity(SplashActivity.this, GuidePageActivity.class,
                    null, true);
        } else {
            CommonUtil.openActicity(SplashActivity.this, MainActivity.class, null, true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
