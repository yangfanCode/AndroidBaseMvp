package com.yangfan.mvp.mvp.mine.viewmodel;

import android.databinding.Bindable;
import com.yangfan.mvp.BR;
import com.yangfan.mvp.R;
import com.yangfan.mvp.adapter.base.BaseViewModel;

/**
 * Created by yangfan
 * nrainyseason@163.com
 */
public class Mine extends BaseViewModel {
    private String name;
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Override
    public long getType() {
        return 0;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_test;
    }
}
