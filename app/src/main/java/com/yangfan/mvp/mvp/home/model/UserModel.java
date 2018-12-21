package com.yangfan.mvp.mvp.home.model;

import com.yangfan.mvp.mvp.base.BaseCommonModel;
import com.yangfan.mvp.mvp.home.viewmodel.UserInfo;
import com.yangfan.mvp.mvp.base.BaseCommonModel;
import com.yangfan.mvp.mvp.home.viewmodel.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * model层数据结构处理
 * Created by yangfan
 * nrainyseason@163.com
 */
public class UserModel implements BaseCommonModel<List<UserInfo>> {
    @Override
    public void loadData(LoadTaskCallBack callBack) {
        //此处进行网络请求...模拟数据
        List<UserInfo>list=new ArrayList<>();
        for(int i=0;i<20;i++){
            UserInfo userInfo=new UserInfo();
            userInfo.setName("mvp"+i);
            list.add(userInfo);
        }
        //成功
        callBack.onLoadSuccess(list);
        //失败
//        callBack.onLoadFailed();
    }
}
