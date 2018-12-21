package com.yangfan.mvp.network.api;

import com.yangfan.mvp.datamodel.InfoMo;
import com.yangfan.mvp.datamodel.ResultBean;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by yangfan on 17/04/24.
 */
public interface AppApi {

    /**
     * 普通Get请求 QueryMap
     * 发送验证码
     * @param account
     * @return
     */
    @GET("cgi-bin/user/cgi_personal_card")
    Observable<ResultBean> sendCode(@QueryMap Map<String, Object> account);
    /**
     * Post表单请求 FieldMap
     * 发送邮箱验证码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("trade/mail/send")
    Observable<ResultBean> sendEmailCode(@FieldMap Map<String, Object> account);
    /**
     * Post Body请求 Body
     * 发送邮箱验证码
     *
     * @return
     */
    @POST("trade/mail/send")
    Observable<ResultBean> sendSMSCode(@Body Map<String, Object> account);

    /**
     * Post 文件上传 PartMap
     *
     * @return
     */
    @Multipart
    @POST("trade/api/upload")
    Observable<ResultBean<String>> uploadPic(@PartMap Map<String, RequestBody> account);

    /**
     * Post Json流请求
     *
     * @param route
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("other")
    Observable<ResultBean> getBanner(@Body RequestBody route);
    /**
     * 新闻资讯
     * @return
     */
    @GET("journalismApi")
    Observable<ResultBean<InfoMo>> getInfo();

}
