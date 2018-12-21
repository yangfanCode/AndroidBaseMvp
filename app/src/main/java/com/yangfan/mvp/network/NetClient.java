package com.yangfan.mvp.network;

import android.content.Context;
import android.text.TextUtils;

import com.jkyeo.basicparamsinterceptor.BasicParamsInterceptor;
import com.yangfan.mvp.helper.ContextHelper;
import com.yangfan.mvp.network.api.AppApi;
import com.yangfan.mvp.network.converter.CustomGsonConverterFactory;
import com.yangfan.mvp.utils.CommonUtil;
import com.yangfan.mvp.utils.LoadAnimationUtils;
import com.yangfan.mvp.utils.LogUtils;
import com.yangfan.mvp.widget.TipsToast;
import com.yangfan.mvp.R;
import com.yangfan.mvp.helper.ContextHelper;
import com.yangfan.mvp.network.api.AppApi;
import com.yangfan.mvp.network.converter.CustomGsonConverterFactory;
import com.yangfan.mvp.utils.CommonUtil;
import com.yangfan.mvp.utils.LoadAnimationUtils;
import com.yangfan.mvp.utils.LogUtils;
import com.yangfan.mvp.widget.TipsToast;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;

/**
 * Created by yangfan
 * nrainyseason@163.com
 */

public class NetClient {
    public static NetClient mHttpUtils;
    private static AppApi api;

    private static OkHttpClient okHttpClient = null;
    private static LoadAnimationUtils loadAnimationUtils = null;
    private static String mToken = "";
    private static String mCity;

    public static NetClient getInstance() {
        synchronized (NetClient.class) {
            if (null == mHttpUtils) {
                mHttpUtils = new NetClient();
            }
        }
        return mHttpUtils;
    }

    /**
     * 默认请求头方法
     * @param text
     * @param isShow
     * @param activity
     * @return
     */
    public AppApi getPost(String text, boolean isShow, Context activity) {
        if (isShow) {
            if (activity != null) {
                loadAnimationUtils = new LoadAnimationUtils(activity);
                loadAnimationUtils.showProcessAnimation(text);
            }
        }
        api = createAppApi(null);// header 中 变量参数太多，不方便判断是否需要重新生成

        return api;
    }

    /**
     * 自定义请求头方法
     * @param text
     * @param isShow
     * @param activity
     * @param headerParamsMap
     * @return
     */
    public AppApi getPost(String text, boolean isShow, Context activity, Map<String, String> headerParamsMap) {
        if (isShow) {
            if (activity != null) {
                loadAnimationUtils = new LoadAnimationUtils(activity);
                loadAnimationUtils.showProcessAnimation(text);
            }
        }
        return createAppApi(headerParamsMap);
    }

    /**
     * 更换主域名方法
     * @param text
     * @param isShow
     * @param activity
     * @param baseUrl
     * @return
     */
    public AppApi getPost(String text, boolean isShow, Context activity, String baseUrl) {
        if (isShow) {
            if (activity != null) {
                loadAnimationUtils = new LoadAnimationUtils(activity);
                loadAnimationUtils.showProcessAnimation(text);
            }
        }
        return createAppApi(null, baseUrl);
    }

    /**
     * 自定义请求头和更换主域名方法
     * @param text
     * @param isShow
     * @param activity
     * @param headerParamsMap
     * @param baseUrl
     * @return
     */
    public AppApi getPost(String text, boolean isShow, Context activity, Map<String, String> headerParamsMap, String baseUrl) {
        if (isShow) {
            if (activity != null) {
                loadAnimationUtils = new LoadAnimationUtils(activity);
                loadAnimationUtils.showProcessAnimation(text);
            }
        }
        return createAppApi(headerParamsMap, baseUrl);
    }


    private AppApi createAppApi(Map<String, String> headerParamsMap) {
        return createAppApi(headerParamsMap, null);
    }

    private AppApi createAppApi(Map<String, String> headerParamsMap, String baseUrl) {
        //请求头拦截器
        BasicParamsInterceptor basicParamsInterceptor =
                new BasicParamsInterceptor.Builder()
                        .addHeaderParamsMap(headerParamsMap != null ? headerParamsMap : CommonUtil.getHeaderParamsMap())
                        .build();
        //log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> LogUtils.LogJson(NetClient.class,message));
        //这行必须加 不然默认不打印
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(basicParamsInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();
        final Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(TextUtils.isEmpty(baseUrl) ? NetConst.dynamicBaseUrl() : baseUrl)
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(AppApi.class);
    }

    public static abstract class RxObserver<T> implements Observer<T> {

        //        @Override
//        public void onStart() {
//            super.onStart();
//            LogUtil.LogE(HttpUtils.class, "onStart");
//        }
        private String apiUrl;
        private Object obj;
        public RxObserver(){}
        public RxObserver(String apiUrl){
            this.apiUrl=apiUrl;
        }

        public RxObserver(String apiUrl, Object obj) {
            this.apiUrl = apiUrl;
            this.obj = obj;
        }

        public Object getObj() {
            return obj;
        }

        //public RxObserver(){}
        @Override
        public void onCompleted() {
            if (loadAnimationUtils != null) {
                loadAnimationUtils.closeProcessAnimation();
                loadAnimationUtils = null;
            }
        }

        @Override
        public void onError(Throwable e) {
            if (loadAnimationUtils != null) {
                loadAnimationUtils.closeProcessAnimation();
                loadAnimationUtils = null;
            }
            LogUtils.LogE(NetClient.class, "error-->   " + e.getMessage());
            e.printStackTrace();
            //在这里做全局的错误处理
            if (e instanceof HttpException) {
                TipsToast.showTips("网络异常");
            } else if(e instanceof SocketTimeoutException){
                TipsToast.showTips("网络连接超时");
            }else if (e instanceof UnknownHostException) {// 网络未连接
                TipsToast.showTips(ContextHelper.getApplication().getString(R.string.netWorkError));
            }
        }

        @Override
        public void onNext(T t) {
            LogUtils.LogE(NetClient.class, "next");
//            ResultBean resultBean= (ResultBean) t;
//            if (resultBean != null) {
//                if(resultBean.code!=0){//code不为0上传错误日志
//                    LogUtils.LogE(NetClient.class,"saveErrorLog:"+resultBean.code+" "+apiUrl);
//                    if(!TextUtils.isEmpty(apiUrl)){
//                        NetApi.saveErrorLog(ContextHelper.getApplication(), apiUrl, resultBean.resultMsg, new RxObserver<T>("") {
//                            @Override
//                            public void onSuccess(T t) {
//
//                            }
//                        });
//                    }
//                }
//            }
            onSuccess(t);
        }

        public abstract void onSuccess(T t);


    }
}
