package com.yangfan.mvp.mvp.info.viewmodel;

import android.databinding.Bindable;

import com.yangfan.mvp.BR;
import com.yangfan.mvp.R;
import com.yangfan.mvp.adapter.base.BaseViewModel;

/**
 * Created by yangfan
 * nrainyseason@163.com
 */
public class InfoVM extends BaseViewModel {
    private String source;
    private String title;
    @Bindable
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
        notifyPropertyChanged(BR.source);
    }
    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Override
    public long getType() {
        return 0;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_info;
    }


}
