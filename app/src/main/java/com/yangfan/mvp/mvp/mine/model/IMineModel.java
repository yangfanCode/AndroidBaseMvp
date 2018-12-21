package com.yangfan.mvp.mvp.mine.model;

import com.yangfan.mvp.mvp.mine.viewmodel.Mine;

/**
 * Created by yangfan
 * nrainyseason@163.com
 * 链接model和presenter
 */
public interface IMineModel {
    /**
     * 获取用户数据
     */
    void loadUserData(onLoadListener listener);

    interface onLoadListener{
        void onError();
        void onSuccess(Mine userInfo);
    }
}
