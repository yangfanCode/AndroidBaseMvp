package com.yangfan.mvp.mvp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.TabHost;

import com.yangfan.mvp.R;
import com.yangfan.mvp.helper.ContextHelper;
import com.yangfan.mvp.mvp.base.BaseActivity;
import com.yangfan.mvp.mvp.mine.view.fragment.MineFragment;
import com.yangfan.mvp.mvp.openLottery.view.fragment.OpenLotteryFragment;
import com.yangfan.mvp.mvp.home.view.fragment.HomeFragment;
import com.yangfan.mvp.mvp.info.view.fragment.InfoFragment;
import com.yangfan.mvp.utils.LogUtils;
import com.yangfan.mvp.widget.LayoutTabItem;
import com.yangfan.mvp.widget.TipsToast;

public class MainActivity extends BaseActivity {
    private boolean mExitFlag;// 退出标记
    private FragmentTabHost mTabHost;
    private final Class[] fragments = {HomeFragment.class, InfoFragment.class, OpenLotteryFragment.class, MineFragment.class};
    private int mTextviewArray[] = {R.string.tab_home, R.string.tab_info, R.string.tab_openLottery, R.string.tab_mine};
    private int mImageViewArray[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher};
    private int currentTab;
    private boolean mIsAfterSaveInstance = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    public void initView() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);
        // 得到fragment的个数
        int count = fragments.length;
        for (int i = 0; i < count; i++) {
            // 为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(mTextviewArray[i]))
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragments[i], null);
        }
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId != null) {
                    if (tabId.equals(getString(mTextviewArray[0]))) {
//                        UmengAnalyticsHelper.umengEvent(UmengAnalyticsHelper.HOME_PAGE);
                        currentTab = 0;
//                        StatusBarUtil.StatusBarDarkMode(mActivity, type);
                    } else if (tabId.equals(getString(mTextviewArray[1]))) {
//                        UmengAnalyticsHelper.umengEvent(UmengAnalyticsHelper.NEWS_PAGE);
                        currentTab = 1;
//                        type = StatusBarUtil.StatusBarLightMode(mActivity);
                    } else if (tabId.equals(getString(mTextviewArray[2]))) {
//                        UmengAnalyticsHelper.umengEvent(UmengAnalyticsHelper.EXCHANGE_PAGE);
                        currentTab = 2;
//                        type = StatusBarUtil.StatusBarLightMode(mActivity);
                    } else if (tabId.equals(getString(mTextviewArray[3]))) {
//                        UmengAnalyticsHelper.umengEvent(UmengAnalyticsHelper.MINE_PAGE);
                        currentTab = 3;
//                        type = StatusBarUtil.StatusBarDarkMode(mActivity);
                    }
                }
            }
        });
        currentTab = 0;
//        Intent intent = getIntent();
//        if (intent != null && intent.getExtras() != null) {
//            Bundle bundle = intent.getExtras();
//            currentTab = bundle.getInt(Constant.INTENT_EXTRA_DATA, 0);
//        }
        setCurrentTab(currentTab);
    }

    private View getTabItemView(int index) {
        LayoutTabItem layoutTabItem = new LayoutTabItem(this);
        layoutTabItem.setTab(mImageViewArray[index], getString(mTextviewArray[index]));
        return layoutTabItem;
    }

    /**
     * 只有在onSaveInstanceState之前做Fragment切换，否则会报
     * java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
     */
    public void setCurrentTab(int index) {
        if (null != mTabHost && !mIsAfterSaveInstance)
            mTabHost.setCurrentTab(index);
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        mIsAfterSaveInstance = false;
        LogUtils.LogE(MainActivity.class, "onResumeFragments()");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mIsAfterSaveInstance = true;
        outState.putString("tab", mTabHost.getCurrentTabTag());
        LogUtils.LogE(MainActivity.class, "onSaveInstanceState()");
    }

    @Override
    public void onBackPressed() {
        if (mExitFlag) {
            ContextHelper.appExit(this);
        } else {
            TipsToast.showTips(getString(R.string.app_exit));
            mExitFlag = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mExitFlag = false;
                }
            }, 3000);
        }
    }
}
