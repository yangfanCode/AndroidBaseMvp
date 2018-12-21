package com.yangfan.mvp.datamodel;

/**
 * Created by yangfan
 * nrainyseason@163.com
 */


public class ResultBean<T> {
    /**
     * code : 100
     * data : null
     * resultMsg :
     * success : true
     * pageSize : 10
     * totalResults : 20624
     * currentPage : 1
     * totalPage : 2063
     * hasNext : true
     * hasPrevious : false
     */

    public int code;
    public String resultMsg;
    public boolean success;
    public T data;

    // 返回数组  数据时使用
    public int pageSize;
    public int totalResults;
    public int currentPage;
    public int totalPage;
    public boolean hasNext;
    public boolean hasPrevious;
}
