package com.yangfan.mvp.mvp.info.view;

import com.yangfan.mvp.mvp.info.viewmodel.InfoVM;

import java.util.List;

/**
 * Created by yangfan
 * nrainyseason@163.com
 */
public interface IInfoView {
    void onLoadInfoDataFailed();
    void onLoadInfoDataSuccess(List<InfoVM> userInfo);
}
