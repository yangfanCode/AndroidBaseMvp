package com.yangfan.mvp.mvp.openLottery.view;

import com.yangfan.mvp.mvp.openLottery.viewmodel.OpenLotteryVM;

import java.util.List;

/**
 * Created by yangfan
 * nrainyseason@163.com
 */
public interface IOpenLotteryView {
    void onLoadInfoDataFailed();
    void onLoadInfoDataSuccess(List<OpenLotteryVM> userInfo);
}
