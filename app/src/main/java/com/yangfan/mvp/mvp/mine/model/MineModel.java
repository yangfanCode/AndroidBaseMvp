package com.yangfan.mvp.mvp.mine.model;

import com.yangfan.mvp.mvp.base.BaseCommonModel;
import com.yangfan.mvp.mvp.mine.viewmodel.Mine;

import java.util.ArrayList;
import java.util.List;

/**
 * model层数据结构处理
 * Created by yangfan
 * nrainyseason@163.com
 */
public class MineModel implements BaseCommonModel<List<Mine>> {
    @Override
    public void loadData(LoadTaskCallBack callBack) {
        //此处进行网络请求...模拟数据
        List<Mine>list=new ArrayList<>();
        for(int i=0;i<20;i++){
            Mine userInfo=new Mine();
            userInfo.setName("mvp"+i);
            list.add(userInfo);
        }
        //成功
        callBack.onLoadSuccess(list);
        //失败
//        callBack.onLoadFailed();
    }
}
