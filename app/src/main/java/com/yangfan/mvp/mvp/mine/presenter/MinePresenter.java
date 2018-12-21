package com.yangfan.mvp.mvp.mine.presenter;

import com.yangfan.mvp.mvp.base.BaseCommonModel;
import com.yangfan.mvp.mvp.mine.model.MineModel;
import com.yangfan.mvp.mvp.mine.view.IMineView;
import com.yangfan.mvp.mvp.mine.viewmodel.Mine;

import java.util.List;

/**持有model和view的引用
 * 主要逻辑控制
 * Created by yangfan
 * nrainyseason@163.com
 */
public class MinePresenter {
    private MineModel model=new MineModel();
    private IMineView iUserView;
    public MinePresenter(IMineView iUserView) {
        this.iUserView = iUserView;
    }

    //获取用户信息
    public void getUserInfo(){
        model.loadData(new BaseCommonModel.LoadTaskCallBack<List<Mine>>() {
            @Override
            public void onLoadSuccess(List<Mine> list) {
                iUserView.onLoadUserDataSuccess(list);
            }

            @Override
            public void onLoadFailed() {
                iUserView.onLoadUserDataFailed();
            }
        });
    }

}
