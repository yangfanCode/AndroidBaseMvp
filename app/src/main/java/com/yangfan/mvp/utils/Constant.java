package com.yangfan.mvp.utils;

import android.net.Uri;

/**
 * Created by yangfan
 * nrainyseason@163.com
 */

public class Constant {
    //bungle key
    public static final String INTENT_EXTRA_DATA = "intent_extra_data";
    public static final String ARGS_PARAM1 = "args_param1";
    public static final String ARGS_PARAM2 = "args_param2";
    public static final String ARGS_PARAM3 = "args_param3";
    public static final String ARGS_PARAM4 = "args_param4";
    //request code
    public static final int REQUEST_CODE1=100;
    public static final int REQUEST_CODE_WITH_LOGIN=500;
    public static final int RESULT_CODE1=1000;
    public static final int PAGE_SIZE = 10;
    public static Uri uri;
    //以下为接口请求头 参数 固定格式
    public static final String MESSAGEID = "80012012040600000001";//messageId
    public static final String SIGN = "wx45F2vrUFDvUnT2";//sign

    //首页加载更多 key
    public static final String MORE="MORE";
    public static final String HOT_LOTTERYS="hot_lotterys";

    public final static int REQUEST_CODE_PHOTO_CAMERA = 100;
    public final static int REQUEST_CODE_PHOTO_ALBUM = REQUEST_CODE_PHOTO_CAMERA + 1;

    //webview
    public static final String INTENT_EXTRA_WEB_IS_REFRESH = "intent_extra_web_is_refresh";// webview 是否可刷新
    public static final String INTENT_EXTRA_TITLE = "intent_extra_title";
    public static final String INTENT_EXTRA_ID = "intent_extra_id";
    public static final String INTENT_EXTRA_URL = "intent_extra_url";
}
