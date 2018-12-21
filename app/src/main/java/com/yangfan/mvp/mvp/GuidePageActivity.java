package com.yangfan.mvp.mvp;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.yangfan.mvp.R;
import com.yangfan.mvp.adapter.GuideViewPageAdapter;
import com.yangfan.mvp.databinding.ActivityGuidepageBinding;
import com.yangfan.mvp.utils.CommonUtil;
import com.yangfan.mvp.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;


public class GuidePageActivity extends Activity implements OnPageChangeListener, OnClickListener {
    private ActivityGuidepageBinding binding;
    private List<View> views;
    private static final int[] pics = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_guidepage);
        SPUtil.put(SPUtil.KEY_VERSIONID, CommonUtil.getVersionId(GuidePageActivity.this));
        initView();
    }

    public void initView() {
        views = new ArrayList<>();
        // 初始化引导图片列表
        for (int resId : pics) {
            ImageView iv = new ImageView(this);
            iv.setScaleType(ScaleType.CENTER_CROP);
            iv.setImageResource(resId);
            iv.setOnClickListener(this);
            views.add(iv);
        }
        // 初始化Adapter
        GuideViewPageAdapter vpAdapter = new GuideViewPageAdapter(views);
        binding.vpGuide.setAdapter(vpAdapter);
        // 绑定回调
        binding.vpGuide.addOnPageChangeListener(this);
    }

    // 当滑动状态改变时调用
    @Override
    public void onPageScrollStateChanged(int arg0) {
        //
    }

    // 当前页面被滑动时调用 0909 0516 2249
    @Override
    public void onPageScrolled(int nowpage, float arg1, int arg2) {
    }

    // 当新的页面被选中时调用
    @Override
    public void onPageSelected(int arg0) {
        currentIndex = arg0;
    }

    @Override
    public void onClick(View v) {
        if (currentIndex == 3) {
            CommonUtil.openActicity(GuidePageActivity.this, MainActivity.class,
                    null, true);
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
}
