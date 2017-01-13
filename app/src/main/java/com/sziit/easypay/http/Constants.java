/**
 * Copyright (c) 2011-2012 FengWoo Network Co.,Ltd
 * <p>
 * Prohibition is granted, charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software
 */
package com.sziit.easypay.http;

/**
 * @ClassName: Constants
 * @Package cn.fengwoo.imovie.common
 * @Description: TODO
 * @Author <a href="mailto:356607560@qq.com">yeconglin</a>
 * @Date 2014-10-26 上午1:29:05
 * @Version imovie 1.0
 */
public class Constants {
    public final static String APPKEY = "MSRF^sas2@";
    public final static String CINEMA = "CINEMA";
    public final static String MOVIE = "MOVIE";
    /**
     * 网络是否连接成功
     */
    public static final String KEY_ISNET = "key_isnet";
    /**
     * 1.1 获取某个会员信息 /member/{id} 如：http://www.fddsj.com/rest/api/member/1
     */
    public final static String MEMBER = "%smember/%s";
    // 获取某个会员信息/member/{id},
    /**
     * 1.2 获取某会员所有结算记录 /settlementRecord?memberId=? 如：http://www.fddsj.com/rest/api/settlementRecord?memberId=1
     */
    public final static String SETTLEMENTRECOREDS = "%ssettlementRecord?memberId=%s";
    /**
     * 1.3 获取某条结算记录 /settlementRecord/{id} 如：http://www.fddsj.com/rest/api/settlementRecord/1
     */
    public final static String SETTLEMENTRECORED = "%ssettlementRecord/%s";
    /**
     * 1.4 获取所有产品信息 /product 如：http://www.fddsj.com/rest/api/product
     */
    public final static String PRODUCTS = "%sproduct";
    /**
     * 1.5 获取某条产品信息 /product/{id} 如：http://www.fddsj.com/rest/api/product/1
     */
    public final static String PRODUCT = "%sproduct/%s";
    /**
     * 注册
     */
    public final static String REGISTER = "%sregister?mobileNumber=%s&password=%s&accountName=%s&bankCardId=%s&bankName=%s&bankProvince=%s&bankCity=%s&bankBranchName=%s&province=%s&city=%s&district=%s&address=%s";
    /**
     * 登录
     */
    public final static String LOGIN = "%slogin?mobileNumber=%s&&password=%s";
    /**
     * 所有广告
     */
    public final static String ADVERTISEMENTS = "%sadvertisement";
    /**
     * 某个广告
     */
    public final static String ADVERTISEMENT = "%sadvertisement/%s";
    /**
     * 获取所有奖励政策
     */
    public final static String REWARD_POLICYS = "%srewardPolicy";
    /**
     * /** 获取某条奖励政策
     */
    public final static String REWARD_POLICY = "%srewardPolicy/%s";
    /**
     * 修改密码
     */
    public final static String CHANGE_PASSWORD = "%spassword?memberId=%s&oldPassword=%s&newPassword=%s";
    /**
     * 进行结算
     */
    public final static String FOR_SETTLEMENT_RECORED = "%ssettlement?memberId=%s&meid=%s&settlementCode=%s";
    /**
     * 忘记密码
     */
    public final static String FORGOT_PASSWORD = "%sretrievePassword?mobileNumber=%s&newPassword=%s";
    /**
     * 修改地址
     */
    public final static String CHANGE_ADDRESS = "%smember/address?memberId=%s&province=%s&city=%s&district=%s&address=%s";
    /**
     * 获取总行
     */
    public final static String BANK = "%sbank";
    /**
     * 获取特定地区分行
     */
    public final static String BRANCK_BANK = "%sbankBranch?bankId=%s&province=%s&city=%s";
    //"http://www.fddsj.com/rest/api/";
    public static String SERVER = "http://es.fengwoo.cn/rest/api/"; // 服务器地址头部 es.fengwoo.cn
}
