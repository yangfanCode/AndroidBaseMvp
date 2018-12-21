package com.yangfan.mvp.adapter.base;

import android.databinding.BaseObservable;

import java.io.Serializable;

/**
 * Created by yangfan
 * nrainyseason@163.com
 */
public abstract class BaseViewModel extends BaseObservable implements ItemModel, Serializable {
    private static final long serialVersionUID = 20160903L;

//    public abstract long getId();

    public abstract long getType();
}
