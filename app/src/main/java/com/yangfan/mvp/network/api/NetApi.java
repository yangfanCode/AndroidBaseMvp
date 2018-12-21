package com.yangfan.mvp.network.api;

import android.content.Context;

import com.yangfan.mvp.network.NetClient;
import com.yangfan.mvp.utils.CommonUtil;
import com.yangfan.mvp.network.NetClient;
import com.yangfan.mvp.utils.CommonUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 接口调用类
 *Created by yangfan on 2017/04/26.
 */

public class NetApi {

    /**
     * 发送验证码
     * String countryCode  // 国家代码 邮箱时 也要传，空参就好
     * String account //手机号或邮箱
     * Integer messageType //1注册 2登录 3手机号绑定 4邮箱绑定 5忘记密码
     * Integer sendType //1手机 2邮箱
     */
    public static void sendGetVerificationCode(Context context, String countryCode, String account, Integer messageType, Integer sendType, Observer observer) {
        Map<String, Object> map = new HashMap<>();
        map.put("countryCode", countryCode);
        map.put("account", account);
        map.put("messageType", messageType);
        map.put("sendType", sendType);

        NetClient.getInstance().getPost("", true, context).sendCode(map).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    public static void test(Context context, Observer observer) {
        Map<String, Object> map = new HashMap<>();
        map.put("uin", "332610443");

        NetClient.getInstance().getPost("", true, context,null,"http://r.qzone.qq.com/").sendCode(map).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 上传图片
     */
    public static void uploadPic(Context context, File file, Observer observer) {
        Map<String, RequestBody> map = new HashMap<>();
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        map.put("file\"; filename=\""+ file.getName(), requestBody);
        NetClient.getInstance().getPost("", true, context).uploadPic(map).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //Json 流
    public static void getBanner(Context context, String fromType, String isNewUser, String timeId, Observer observer) {
        Map<String, Object> map = new HashMap<>();
        map.put("fromType", fromType);
        map.put("isNewUser", isNewUser);
        map.put("timeId", timeId);
        NetClient.getInstance().getPost("", false, context).getBanner(CommonUtil.getRequestBody( map)).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    /**新闻资讯
     */
    public static void getInfo(Context context, Observer observer) {
        NetClient.getInstance().getPost("", true, context,"https://www.apiopen.top/").getInfo().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
