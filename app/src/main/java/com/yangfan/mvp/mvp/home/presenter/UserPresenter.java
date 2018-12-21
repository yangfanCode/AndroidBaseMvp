package com.yangfan.mvp.mvp.home.presenter;

import com.yangfan.mvp.mvp.base.BaseCommonModel;
import com.yangfan.mvp.mvp.base.BaseCommonModel;
import com.yangfan.mvp.mvp.home.model.UserModel;
import com.yangfan.mvp.mvp.home.view.IUserView;
import com.yangfan.mvp.mvp.home.viewmodel.UserInfo;

import java.util.List;

/**持有model和view的引用
 * 主要逻辑控制
 * Created by yangfan
 * nrainyseason@163.com
 */
public class UserPresenter {
    private UserModel model=new UserModel();
    private IUserView iUserView;
    public UserPresenter(IUserView iUserView) {
        this.iUserView = iUserView;
    }

    //获取用户信息
    public void getUserInfo(){
        model.loadData(new BaseCommonModel.LoadTaskCallBack<List<UserInfo>>() {
            @Override
            public void onLoadSuccess(List<UserInfo> list) {
                iUserView.onLoadUserDataSuccess(list);
            }

            @Override
            public void onLoadFailed() {
                iUserView.onLoadUserDataFailed();
            }
        });
    }

}
