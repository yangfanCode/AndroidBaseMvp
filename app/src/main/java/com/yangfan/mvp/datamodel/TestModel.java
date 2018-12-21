package com.yangfan.mvp.datamodel;

import com.yangfan.mvp.R;
import com.yangfan.mvp.adapter.base.BaseViewModel;

/**
 * Created by yangfan
 * nrainyseason@163.com
 */
public class TestModel extends BaseViewModel {
    public String name;
//    @Override
//    public long getId() {
//        return 0;
//    }

    @Override
    public long getType() {
        return 0;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_test;
    }
}
