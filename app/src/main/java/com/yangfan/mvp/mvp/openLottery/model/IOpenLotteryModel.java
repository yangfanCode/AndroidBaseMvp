package com.yangfan.mvp.mvp.openLottery.model;

import com.yangfan.mvp.mvp.openLottery.viewmodel.OpenLotteryVM;

/**
 * Created by yangfan
 * nrainyseason@163.com
 * 链接model和presenter
 */
public interface IOpenLotteryModel {
    /**
     * 获取用户数据
     */
    void loadUserData(onLoadListener listener);

    interface onLoadListener{
        void onError();
        void onSuccess(OpenLotteryVM userInfo);
    }
}
