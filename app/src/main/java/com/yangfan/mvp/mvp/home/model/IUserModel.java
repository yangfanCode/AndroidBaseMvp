package com.yangfan.mvp.mvp.home.model;

import com.yangfan.mvp.mvp.home.viewmodel.UserInfo;
import com.yangfan.mvp.mvp.home.viewmodel.UserInfo;

/**
 * Created by yangfan
 * nrainyseason@163.com
 * 链接model和presenter
 */
public interface IUserModel {
    /**
     * 获取用户数据
     */
    void loadUserData(onLoadListener listener);

    interface onLoadListener{
        void onError();
        void onSuccess(UserInfo userInfo);
    }
}
