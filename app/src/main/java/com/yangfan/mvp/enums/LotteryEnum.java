package com.yangfan.mvp.enums;

import com.yangfan.mvp.R;

/**
 * Created by yangfan
 * nrainyseason@163.com
 * 彩种
 */

public enum LotteryEnum {


    TC_JCZQ("TC_JCZQ","竞彩足球", R.mipmap.ic_launcher,"","5大联赛 豪门送奖","热销彩种"),
    TC_JCLQ("TC_JCLQ","竞彩篮球", R.mipmap.ic_launcher,"","NBA新赛季火热开赛","热销彩种"),
    TC_BJDC("TC_BJDC","北京单场", R.mipmap.ic_launcher,"","5大联赛 豪门送奖","高频彩"),
    TC_BJSF("TC_BJSF","北京单场胜负过关", R.mipmap.ic_launcher,"","5大联赛 豪门送奖","高频彩"),
    TC_DLT("TC_DLT","大乐透", R.mipmap.ic_launcher,"每周一、三、六21:30开奖","5大联赛 豪门送奖","热销彩种"),
    TC_PL3("TC_PL3","排列三", R.mipmap.ic_launcher,"每日20：30开奖","5大联赛 豪门送奖","数字彩"),
    TC_PL5("TC_PL5","排列五", R.mipmap.ic_launcher,"周日20：30开奖","5大联赛 豪门送奖","数字彩"),
    TC_QXC("TC_QXC","七星彩", R.mipmap.ic_launcher,"每周二、五、日开奖","5大联赛 豪门送奖","数字彩"),
    TC_ZQSPF("TC_ZQSPF","任选9", R.mipmap.ic_launcher,"","5大联赛 豪门送奖","热销彩种"),
    TC_ZQSPF9("TC_ZQSPF9","任选9", R.mipmap.ic_launcher,"","5大联赛 豪门送奖","热销彩种"),
    TC_4CJQ("TC_4CJQ","进球彩", R.mipmap.ic_launcher,"","5大联赛 豪门送奖","热销彩种"),
    TC_6CBQC("TC_6CBQC","半全场", R.mipmap.ic_launcher,"","5大联赛 豪门送奖","热销彩种"),
    TC_JXDLC("TC_JXDLC","江西11选5", R.mipmap.ic_launcher,"","5大联赛 豪门送奖","高频彩"),
    TC_KLPK3("TC_KLPK3","快乐扑克3", R.mipmap.ic_launcher,"","5大联赛 豪门送奖","高频彩"),
    TC_SD11X5("TC_SD11X5","山东11选5", R.mipmap.ic_launcher,"","5大联赛 豪门送奖","高频彩"),
    TC_AH11X5("TC_AH11X5","安徽11选5", R.mipmap.ic_launcher,"","5大联赛 豪门送奖","高频彩");


    private String lotteryId;
    private String lotteryName;
    private int icon;
    private String time;
    private String desc;
    private String type;

    public String getLotteryName(){
        return lotteryName;
    }
    public String getLotteryId(){
        return lotteryId;
    }
    public int getLotteryIcon(){
        return icon;
    }
    public String getLotteryTime(){
        return time;
    }
    public String getLotteryDesc(){
        return desc;
    }
    public String getLotteryType(){
        return type;
    }

    LotteryEnum(String lotteryId, String lotteryName, int icon, String time, String desc, String type){
        this.lotteryId=lotteryId;
        this.lotteryName=lotteryName;
        this.icon=icon;
        this.time=time;
        this.desc=desc;
        this.type=type;
    }

    public static LotteryEnum createWithLotteryEnum(String lotteryId) {
        if (lotteryId == null) return null;
        for (LotteryEnum lotteryEnum : LotteryEnum.values()) {
            if (lotteryEnum.lotteryId.equals(lotteryId)) {
                return lotteryEnum;
            }
        }
        return null;
    }
}
