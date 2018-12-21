package com.yangfan.mvp.mvp.openLottery.model;

import android.content.Context;

import com.yangfan.mvp.datamodel.InfoMo;
import com.yangfan.mvp.datamodel.ResultBean;
import com.yangfan.mvp.mvp.base.BaseCommonModel;
import com.yangfan.mvp.mvp.openLottery.viewmodel.OpenLotteryVM;
import com.yangfan.mvp.network.NetClient;
import com.yangfan.mvp.network.api.NetApi;

import java.util.ArrayList;
import java.util.List;

/**
 * model层数据结构处理
 * Created by yangfan
 * nrainyseason@163.com
 */
public class OpenLotteryModel implements BaseCommonModel<List<OpenLotteryVM>> {
    private Context context;
    public OpenLotteryModel(Context context) {
        this.context=context;
    }

    @Override
    public void loadData(LoadTaskCallBack callBack) {
        NetApi.getInfo(context,new NetClient.RxObserver<ResultBean<InfoMo>>() {
            @Override
            public void onSuccess(ResultBean<InfoMo> listResultBean) {
                //成功
                List<OpenLotteryVM>list=new ArrayList<>();
                for(InfoMo.Detail detail:listResultBean.data.tech){
                    OpenLotteryVM infoVM=new OpenLotteryVM();
                    infoVM.setTitle(detail.title);
                    infoVM.setSource(detail.source);
                    list.add(infoVM);
                }
                callBack.onLoadSuccess(list);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                callBack.onLoadFailed();
            }
        });
    }

}
