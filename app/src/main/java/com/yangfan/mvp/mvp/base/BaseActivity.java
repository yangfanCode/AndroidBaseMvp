package com.yangfan.mvp.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.yangfan.utils.CommonUtils;
import com.yangfan.mvp.helper.ContextHelper;
import com.yangfan.mvp.service.Service;
import com.yangfan.mvp.utils.LogUtils;
import com.yangfan.mvp.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;


/**
 * Created by yangfan
 * nrainyseason@163.com
 */


public abstract class BaseActivity extends FragmentActivity {
    public Activity mActivity = null;
    public boolean dispatchTouchEvent = true;

    public boolean isFirstOnResume = true;
    protected boolean isOnResume = true;

    protected boolean fitSystemWindows = true;
    private List<Subscription> mSubscription = new ArrayList<>();//订阅管理器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.transparencyBar(this);
        ContextHelper.addActivity(this);
        ContextHelper.setLastActivity(this);
        mActivity = this;
    }
    //统一设置透明状态栏 和 fitsystemwindow=true
    private void setFitsSystemWindows(boolean fitSystemWindows) {
        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        if (contentFrameLayout != null) {
            View parentView = contentFrameLayout.getChildAt(0);
            if (parentView != null && Build.VERSION.SDK_INT >= 14) {
                parentView.setFitsSystemWindows(fitSystemWindows);
                // 作用 同 在 布局文件 根布局 里  添加 android:fitsSystemWindows="true"
            }
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        //沉浸式状态栏设置
        if (fitSystemWindows)setFitsSystemWindows(fitSystemWindows);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //EventBus.getDefault().unregister(this);
        ContextHelper.removeActivity(getClass());
        System.gc();
    }

    public <T extends Service> T getService(Class<T> tClass) {
        return Service.getService(tClass);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ContextHelper.addActivity(this);
    }

    public void onResume() {
        super.onResume();
        isOnResume = true;
        ContextHelper.setLastActivity(this);
        //MobclickAgent.onResume(this);//友盟统计
    }

    public void onPause() {
        super.onPause();
        isOnResume = false;
        //MobclickAgent.onPause(this);//友盟统计
        //释放掉没有取消订阅的对象
        for (Subscription sub: mSubscription) {
            if (sub != null) sub.unsubscribe();
        }
    }

    /**添加到订阅管理器中**/
    public void addSubscription(Subscription subscription) {
        mSubscription.add(subscription);
    }

    /**移除订阅**/
    public void removeSubScription(Subscription subscription) {
        subscription.unsubscribe();
        mSubscription.remove(subscription);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (dispatchTouchEvent) {
                View v = getCurrentFocus();
                if (isShouldHideKeyboard(v, ev)) {
                    hideKeyboard(v.getWindowToken());
                }
            }
            if (CommonUtils.isFastDoubleClick(0.5)) {
                LogUtils.LogE(BaseActivity.class, "dispatchTouchEvent ---> isFastDoubleClick");
                return true;
            }
            LogUtils.LogE(BaseActivity.class, "dispatchTouchEvent ---> MotionEvent.ACTION_DOWN");
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //禁止修改APP内部文字大小
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;

    }

}
