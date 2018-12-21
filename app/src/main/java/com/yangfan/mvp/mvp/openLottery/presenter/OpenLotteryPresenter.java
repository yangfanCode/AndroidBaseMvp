package com.yangfan.mvp.mvp.openLottery.presenter;

import android.content.Context;

import com.yangfan.mvp.mvp.base.BaseCommonModel;
import com.yangfan.mvp.mvp.openLottery.model.OpenLotteryModel;
import com.yangfan.mvp.mvp.openLottery.view.IOpenLotteryView;
import com.yangfan.mvp.mvp.openLottery.viewmodel.OpenLotteryVM;

import java.util.List;

/**持有model和view的引用
 * 主要逻辑控制
 * Created by yangfan
 * nrainyseason@163.com
 */
public class OpenLotteryPresenter {
    private OpenLotteryModel model;
    private IOpenLotteryView iInfoView;
    private Context context;
    public OpenLotteryPresenter(IOpenLotteryView iInfoView, Context context) {
        this.iInfoView = iInfoView;
        model=new OpenLotteryModel(context);
    }

    //获取用户信息
    public void getInfo(){
        model.loadData(new BaseCommonModel.LoadTaskCallBack<List<OpenLotteryVM>>() {
            @Override
            public void onLoadSuccess(List<OpenLotteryVM> list) {
                iInfoView.onLoadInfoDataSuccess(list);
            }

            @Override
            public void onLoadFailed() {
                iInfoView.onLoadInfoDataFailed();
            }
        });
    }

}
