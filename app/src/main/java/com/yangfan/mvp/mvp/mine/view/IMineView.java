package com.yangfan.mvp.mvp.mine.view;

import com.yangfan.mvp.mvp.mine.viewmodel.Mine;

import java.util.List;

/**
 * Created by yangfan
 * nrainyseason@163.com
 */
public interface IMineView {
    void onLoadUserDataFailed();
    void onLoadUserDataSuccess(List<Mine> userInfo);
}
