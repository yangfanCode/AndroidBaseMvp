package com.yangfan.mvp.datamodel;

/**
 * Created by yangfan on 2018/1/23.
 */

public class MessageEvent<T> {
    public String message;
    public T data;
    public int type;
    public static final int SELECT_PULL = 1;//投注页面和下方选号布局交互
    public static final int PERSONAL_GUANZHU = 2;//个人主页关注数据更新
    public static final int PERSONAL_QUXIAOGUANZHU = 3;//个人主页关注取消关注数据更新
    public static final int PERSONAL_OPENACT = 4;//个人主页点击头像刷新数据

    public MessageEvent(String message) {
        this.message = message;
    }

    public MessageEvent(int type) {
        this.type = type;
    }

    public MessageEvent(String message, T data) {
        this.message = message;
        this.data = data;
    }
    public MessageEvent(int type, T data) {
        this.type=type;
        this.data = data;
    }
}
