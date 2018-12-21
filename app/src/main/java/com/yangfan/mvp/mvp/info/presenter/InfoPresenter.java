package com.yangfan.mvp.mvp.info.presenter;

import android.content.Context;

import com.yangfan.mvp.mvp.base.BaseCommonModel;
import com.yangfan.mvp.mvp.info.model.InfoModel;
import com.yangfan.mvp.mvp.info.view.IInfoView;
import com.yangfan.mvp.mvp.base.BaseCommonModel;
import com.yangfan.mvp.mvp.info.model.InfoModel;
import com.yangfan.mvp.mvp.info.view.IInfoView;
import com.yangfan.mvp.mvp.info.viewmodel.InfoVM;

import java.util.List;

/**持有model和view的引用
 * 主要逻辑控制
 * Created by yangfan
 * nrainyseason@163.com
 */
public class InfoPresenter {
    private InfoModel model;
    private IInfoView iInfoView;
    private Context context;
    public InfoPresenter(IInfoView iInfoView,Context context) {
        this.iInfoView = iInfoView;
        model=new InfoModel(context);
    }

    //获取用户信息
    public void getInfo(){
        model.loadData(new BaseCommonModel.LoadTaskCallBack<List<InfoVM>>() {
            @Override
            public void onLoadSuccess(List<InfoVM> list) {
                iInfoView.onLoadInfoDataSuccess(list);
            }

            @Override
            public void onLoadFailed() {
                iInfoView.onLoadInfoDataFailed();
            }
        });
    }

}
