package com.yangfan.mvp.mvp.base;

/**
 * 可用于数据操作，网络，数据库，和文件等
 * 通用model请求数据
 * 链接model和presenter
 * Created by yangfan
 * nrainyseason@163.com
 */
public interface BaseCommonModel<T> {
    interface LoadTaskCallBack<T> {
        void onLoadSuccess(T t);

        void onLoadFailed();
    }

    void loadData(LoadTaskCallBack callBack);
}