package com.yangfan.mvp.mvp.info.model;

import com.yangfan.mvp.mvp.info.viewmodel.InfoVM;

/**
 * Created by yangfan
 * nrainyseason@163.com
 * 链接model和presenter
 */
public interface IInfoModel {
    /**
     * 获取用户数据
     */
    void loadUserData(onLoadListener listener);

    interface onLoadListener{
        void onError();
        void onSuccess(InfoVM userInfo);
    }
}
