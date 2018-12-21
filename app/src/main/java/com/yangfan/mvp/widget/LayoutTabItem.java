package com.yangfan.mvp.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.yangfan.mvp.R;
import com.yangfan.mvp.databinding.LayoutTabItemBinding;

public class LayoutTabItem extends FrameLayout {

    private Context context;
    private LayoutTabItemBinding binding;

    public LayoutTabItem(Context context) {
        super(context);
        initUI(context);
    }

    public LayoutTabItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    private void initUI(Context context) {
        this.context = context;
        binding=DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.layout_tab_item,this,true);
    }

    // 重置view
    public void reset() {
        setOvalSymbolVisibility(GONE);
        binding.tvCount.setVisibility(GONE);
    }

    // 设置 消息数字
    public void setCount(int count) {
        binding.tvCount.setText("" + count);
        binding.tvCount.setVisibility(VISIBLE);
    }

    // 设置圆点  Visibility
    public void setOvalSymbolVisibility(int visibility) {
        binding.vOvalSymbol.setVisibility(visibility);
    }

    public void setTab(int resId, String tabValue) {
        binding.imvImage.setImageResource(resId);
        binding.tvTab.setText(tabValue);
    }

}