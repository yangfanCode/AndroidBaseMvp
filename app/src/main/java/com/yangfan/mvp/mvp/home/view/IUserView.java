package com.yangfan.mvp.mvp.home.view;

import com.yangfan.mvp.mvp.home.viewmodel.UserInfo;
import com.yangfan.mvp.mvp.home.viewmodel.UserInfo;

import java.util.List;

/**
 * Created by yangfan
 * nrainyseason@163.com
 */
public interface IUserView {
    void onLoadUserDataFailed();
    void onLoadUserDataSuccess(List<UserInfo> userInfo);
}
