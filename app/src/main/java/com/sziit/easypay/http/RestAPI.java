/**
 * Copyright (c) 2011-2014 FengWoo Network Co.,Ltd.
 * <p>
 * Prohibition is granted, charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software
 */
package com.sziit.easypay.http;

import android.text.TextUtils;

import com.sziit.easypay.bean.Advertisement;
import com.sziit.easypay.bean.BankInfo;
import com.sziit.easypay.bean.BranchInfo;
import com.sziit.easypay.bean.MemberInformation;
import com.sziit.easypay.bean.ProductInfo;
import com.sziit.easypay.bean.RewardPolicyInfo;
import com.sziit.easypay.bean.SettlementItemInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName: HttpUtil
 * @Package cn.fengwoo.easysettlement.httputil
 * @Description: TODO
 * @Author <a href="mailto:xianwen@fengwoo.cn">happy</a>
 * @Date 2014年10月31日 下午10:21:56
 * @Version EasySettlement 1.0.0
 */
public class RestAPI {

    // MD5加密
    private final static String[] hexArray = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"}; // 存储十六进制值的数组

    /**
     * @param mobileNumber
     * @param password
     * @return
     * @Title: register
     * @Author: Xian Wen
     * @Description: 注册
     * @Return String
     * @Throws
     */
    public static String register(String mobileNumber, String password,
                                  String accountName, String bankCardId, String bankName,
                                  String bankProvince, String bankCity, String bankBranchName,
                                  String province, String city, String district, String address) {
        MemberInformation memberInformation = new MemberInformation();
        String status = null;
        try {
            String json = HttpUtil.httpGet(String.format(Constants.REGISTER,
                    Constants.SERVER, mobileNumber, password, accountName,
                    bankCardId, bankName, bankProvince, bankCity,
                    bankBranchName, province, city, district, address));
            JSONObject jsonObject = new JSONObject(json);
            status = jsonObject.getString("status");
            if (status.equals("0")) {
                JSONObject member = jsonObject.getJSONObject("body");

                memberInformation.setAddress(member.getString("address"));
                memberInformation.setId(member.getString("id"));
                memberInformation.setMobileNumber(member
                        .getString("mobileNumber"));
                memberInformation.setBankCardId(member.getString("bankCardId"));
                memberInformation.setAccountName(member
                        .getString("accountName"));
                memberInformation.setBankProvince(member
                        .getString("bankProvince"));
                memberInformation.setBankBranchName(member
                        .getString("bankBranchName"));
                memberInformation.setLastLoginTime(member
                        .getString("lastLoginTime"));
                memberInformation.setStatus(member.getString("status"));
                memberInformation.setPassword(member.getString("password"));
                memberInformation.setCity(member.getString("city"));
                memberInformation.setRegisterTime(member
                        .getString("registerTime"));
                memberInformation.setProvince(member.getString("province"));
                memberInformation.setDistrict(member.getString("district"));
                memberInformation.setBankName(member.getString("bankName"));
                memberInformation.setBankCity(member.getString("bankCity"));
                return status;
            } else {
                return null;
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param userName
     * @param password
     * @return
     * @Title: login
     * @Author: Xian Wen
     * @Description: 登录接口
     * @Return String
     * @Throws
     */
    public static MemberInformation login(String userName, String password) {
        MemberInformation memberInformation = new MemberInformation();
        try {
            String json = HttpUtil.httpGet(String.format(Constants.LOGIN,
                    Constants.SERVER, userName, password));
            if (TextUtils.isEmpty(json)) {
                return null;
            }
            JSONObject jsonObject = new JSONObject(json);

            String status = jsonObject.getString("status");
            if (status.equals("0")) {
                JSONObject member = jsonObject.getJSONObject("body");

                memberInformation.setAddress(member.getString("address"));
                memberInformation.setId(member.getString("id"));
                memberInformation.setMobileNumber(member
                        .getString("mobileNumber"));
                memberInformation.setBankCardId(member.getString("bankCardId"));
                memberInformation.setAccountName(member
                        .getString("accountName"));
                memberInformation.setBankProvince(member
                        .getString("bankProvince"));
                memberInformation.setBankBranchName(member
                        .getString("bankBranchName"));
                memberInformation.setLastLoginTime(member
                        .getString("lastLoginTime"));
                memberInformation.setStatus(member.getString("status"));
                memberInformation.setPassword(member.getString("password"));
                memberInformation.setCity(member.getString("city"));
                memberInformation.setRegisterTime(member
                        .getString("registerTime"));
                memberInformation.setProvince(member.getString("province"));
                memberInformation.setDistrict(member.getString("district"));
                memberInformation.setBankName(member.getString("bankName"));
                memberInformation.setBankCity(member.getString("bankCity"));
                return memberInformation;
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String forgotPassword(String phoneNumber, String newPassword) {
        String status = null;
        String json = HttpUtil.httpGet(String.format(Constants.FORGOT_PASSWORD,
                Constants.SERVER, phoneNumber, newPassword));
        try {
            JSONObject jsonObject = new JSONObject(json);
            status = jsonObject.getString("status");
            return status;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param memeberId
     * @param oldPassword
     * @param newPassword
     * @return
     * @Title: forgotPassword
     * @Author: Xian Wen
     * @Description: 修改密码
     * @Return String
     * @Throws
     */
    public static MemberInformation changePassword(String memeberId,
                                                   String oldPassword, String newPassword) {
        MemberInformation memberInformation = new MemberInformation();
        try {
            String json = HttpUtil.httpGet(String.format(
                    Constants.CHANGE_PASSWORD, Constants.SERVER, memeberId,
                    oldPassword, newPassword));
            JSONObject jsonObject = new JSONObject(json);
            JSONObject member = jsonObject.getJSONObject("body");

            memberInformation.setAddress(member.getString("address"));
            memberInformation.setId(member.getString("id"));
            memberInformation.setMobileNumber(member.getString("mobileNumber"));
            memberInformation.setBankCardId(member.getString("bankCardId"));
            memberInformation.setAccountName(member.getString("accountName"));
            memberInformation.setBankProvince(member.getString("bankProvince"));
            memberInformation.setBankBranchName(member
                    .getString("bankBranchName"));
            memberInformation.setLastLoginTime(member
                    .getString("lastLoginTime"));
            memberInformation.setStatus(member.getString("status"));
            memberInformation.setPassword(member.getString("password"));
            memberInformation.setCity(member.getString("city"));
            memberInformation.setRegisterTime(member.getString("registerTime"));
            memberInformation.setProvince(member.getString("province"));
            memberInformation.setDistrict(member.getString("district"));
            memberInformation.setBankName(member.getString("bankName"));
            memberInformation.setBankCity(member.getString("bankCity"));
            return memberInformation;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static MemberInformation changeAddress(String memberId,
                                                  String province, String city, String region, String address) {
        MemberInformation memberInformation = new MemberInformation();
        String json = HttpUtil.httpGet(String.format(Constants.CHANGE_ADDRESS,
                Constants.SERVER, memberId, province, city, region, address));
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(json);
            // JSONObject member = jsonObject.getJSONObject(null);

            memberInformation.setAddress(jsonObject.getString("address"));
            memberInformation.setId(jsonObject.getString("id"));
            memberInformation.setMobileNumber(jsonObject
                    .getString("mobileNumber"));
            memberInformation.setBankCardId(jsonObject.getString("bankCardId"));
            memberInformation.setAccountName(jsonObject
                    .getString("accountName"));
            memberInformation.setBankProvince(jsonObject
                    .getString("bankProvince"));
            memberInformation.setBankBranchName(jsonObject
                    .getString("bankBranchName"));
            memberInformation.setLastLoginTime(jsonObject
                    .getString("lastLoginTime"));
            memberInformation.setStatus(jsonObject.getString("status"));
            memberInformation.setPassword(jsonObject.getString("password"));
            memberInformation.setCity(jsonObject.getString("city"));
            memberInformation.setRegisterTime(jsonObject
                    .getString("registerTime"));
            memberInformation.setProvince(jsonObject.getString("province"));
            memberInformation.setDistrict(jsonObject.getString("district"));
            memberInformation.setBankName(jsonObject.getString("bankName"));
            memberInformation.setBankCity(jsonObject.getString("bankCity"));
            return memberInformation;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * @param
     * @return
     * @Title: getInformation
     * @Author: Xian Wen
     * @Description: 获取某个会员信息
     * @Return String
     * @Throws
     */
    public static MemberInformation getInformation(String memeberId) {
        MemberInformation memberInformation = new MemberInformation();
        try {
            String json = HttpUtil.httpGet(String.format(
                    Constants.CHANGE_PASSWORD, Constants.SERVER, memeberId));
            JSONObject jsonObject = new JSONObject(json);
            JSONObject member = jsonObject.getJSONObject("body");

            memberInformation.setAddress(member.getString("address"));
            memberInformation.setId(member.getString("id"));
            memberInformation.setMobileNumber(member.getString("mobileNumber"));
            memberInformation.setBankCardId(member.getString("bankCardId"));
            memberInformation.setAccountName(member.getString("accountName"));
            memberInformation.setBankProvince(member.getString("bankProvince"));
            memberInformation.setBankBranchName(member
                    .getString("bankBranchName"));
            memberInformation.setLastLoginTime(member
                    .getString("lastLoginTime"));
            memberInformation.setStatus(member.getString("status"));
            memberInformation.setPassword(member.getString("password"));
            memberInformation.setCity(member.getString("city"));
            memberInformation.setRegisterTime(member.getString("registerTime"));
            memberInformation.setProvince(member.getString("province"));
            memberInformation.setDistrict(member.getString("district"));
            memberInformation.setBankName(member.getString("bankName"));
            memberInformation.setBankCity(member.getString("bankCity"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return memberInformation;
    }

    /**
     * @param memeberId
     * @return
     * @Title: getAllSettlementRecord
     * @Author: Xian Wen
     * @Description: 获取某会员所有结算记录
     * @Return List<SettlementItemInfo>
     * @Throws
     */
    public static List<SettlementItemInfo> getAllSettlementRecord(
            String memeberId) {
        List<SettlementItemInfo> settlementList = new ArrayList<SettlementItemInfo>();
        try {
            String json = HttpUtil.httpGet(String.format(
                    Constants.SETTLEMENTRECOREDS, Constants.SERVER, memeberId));
            JSONArray memberList = new JSONArray(json);
            for (int i = 0; i < memberList.length(); i++) {
                JSONObject temp = (JSONObject) memberList.get(i);
                SettlementItemInfo settlementItemInfo = new SettlementItemInfo();
                settlementItemInfo.setCreateTime(temp.getString("createTime"));
                settlementItemInfo.setMoney(temp.getString("money"));
                settlementItemInfo.setMeid(temp.getString("meid"));
                settlementItemInfo.setCode(temp.getString("settlementCode"));
                settlementItemInfo.setPhoneType(temp.getString("phoneType"));
                settlementList.add(settlementItemInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return settlementList;
    }

    /**
     * @param
     * @return
     * @Title: getSettlementRecord
     * @Author: Xian Wen
     * @Description: 获取某条结算记录
     * @Return SettlementItemInfo
     * @Throws
     */
    public static SettlementItemInfo getSettlementRecord(
            String settlementRecordId) {
        SettlementItemInfo settlementItemInfo = new SettlementItemInfo();
        try {
            String json = HttpUtil.httpGet(String.format(
                    Constants.SETTLEMENTRECORED, Constants.SERVER,
                    settlementRecordId));
            JSONObject settlement = new JSONObject(json);
            settlementItemInfo
                    .setCreateTime(settlement.getString("createTime"));
            settlementItemInfo.setMoney(settlement.getString("money"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return settlementItemInfo;
    }

    /**
     * @return
     * @Title: getAllProductList
     * @Author: Xian Wen
     * @Description: 获取所有产品信息
     * @Return List<ProductInfo>
     * @Throws
     */
    public static List<ProductInfo> getAllProductList() {
        List<ProductInfo> productInfoList = new ArrayList<ProductInfo>();
        try {
            String json = HttpUtil.httpGet(
                    String.format(Constants.PRODUCTS, Constants.SERVER));
            JSONArray productList = new JSONArray(json);
            for (int i = 0; i < productList.length(); i++) {
                JSONObject temp = (JSONObject) productList.get(i);
                ProductInfo productInfo = new ProductInfo();
                productInfo.setId(temp.getString("id"));
                productInfo.setContent(temp.getString("content"));
                productInfo.setPhoneType(temp.getString("phoneType"));
                productInfo.setUserId(temp.getString("userId"));
                productInfoList.add(productInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return productInfoList;
    }

    /**
     * @param productId
     * @return
     * @Title: getProduct
     * @Author: Xian Wen
     * @Description: 获取某条产品信息
     * @Return ProductInfo
     * @Throws
     */
    public static ProductInfo getProduct(String productId) {
        ProductInfo productInfo = new ProductInfo();
        try {
            String json = HttpUtil.httpGet(String.format(Constants.PRODUCTS,
                    Constants.SERVER, productId));
            JSONObject product = new JSONObject(json);
            productInfo.setId(product.getString("id"));
            productInfo.setContent(product.getString("content"));
            productInfo.setPhoneType(product.getString("phoneType"));
            productInfo.setUserId(product.getString("userId"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return productInfo;
    }

    /**
     * @return
     * @Title: getAllAdvertisementList
     * @Author: Xian Wen
     * @Description: 获取所有广告
     * @Return List<advertisement>
     * @Throws
     */
    public static List<Advertisement> getAllAdvertisementList() {
        List<Advertisement> advertisementInfoList = new ArrayList<Advertisement>();
        String json = HttpUtil
                .httpGet(Constants.SERVER + "advertisement");
        if (json != null && json.length() > 0) {
            try {
                JSONArray advertisementList = new JSONArray(json);
                for (int i = 0; i < advertisementList.length(); i++) {
                    JSONObject temp = (JSONObject) advertisementList.get(i);
                    Advertisement advertisement = new Advertisement();
                    advertisement.setId(temp.getString("id"));
                    advertisement.setStatus(temp.getString("status"));
                    advertisement.setCreateTime(temp.getString("createTime"));
                    advertisement.setPicture(temp.getString("picture"));
                    advertisement.setBgcolor(temp.getString("bgcolor"));
                    advertisement.setEndDate(temp.getString("endDate"));
                    advertisement.setStartDate(temp.getString("startDate"));
                    advertisementInfoList.add(advertisement);
                }
                return advertisementInfoList;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * @param advertisementId
     * @return
     * @Title: getAdvertisement
     * @Author: Xian Wen
     * @Description: 获取某条广告
     * @Return advertisement
     * @Throws
     */
    public static Advertisement getAdvertisement(String advertisementId) {
        Advertisement advertisement = new Advertisement();
        JSONObject jsonObject;
        try {
            String json = HttpUtil.httpGet(
                    String.format(Constants.ADVERTISEMENT, Constants.SERVER,
                            advertisementId));
            jsonObject = new JSONObject(json);
            System.out.println("jsonObject=" + jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return advertisement;
    }

    /**
     * @return
     * @Title: getAllRewardPolicyList
     * @Author: Xian Wen
     * @Description: 获取所有奖励政策
     * @Return List<RewardPolicyInfo>
     * @Throws
     */
    public static List<RewardPolicyInfo> getAllRewardPolicyList() {
        List<RewardPolicyInfo> rewardPolicyInfoList = new ArrayList<RewardPolicyInfo>();
        try {
            String json = HttpUtil.httpGet(
                    String.format(Constants.REWARD_POLICYS, Constants.SERVER));

            JSONObject rewardProlicy = new JSONObject(json);
            JSONArray rewardProlicyList = rewardProlicy.getJSONArray("body");
            for (int i = 0; i < rewardProlicyList.length(); i++) {
                JSONObject temp = (JSONObject) rewardProlicyList.get(i);

                RewardPolicyInfo rewardPolicyInfo = new RewardPolicyInfo();
                rewardPolicyInfo.setName(temp.getString("name"));
                rewardPolicyInfo.setId(temp.getString("id"));
                rewardPolicyInfo.setPhoneType(temp.getString("productType"));
                rewardPolicyInfo.setContent(temp.getString("content"));
                rewardPolicyInfo.setEndTime(temp.getString("endTime"));
                rewardPolicyInfo.setStartTime(temp.getString("startTime"));
                rewardPolicyInfo.setStatus(temp.getString("status"));
                rewardPolicyInfo.setMoney(temp.getString("money"));
                rewardPolicyInfo.setProductId(temp.getString("productId"));
                rewardPolicyInfoList.add(rewardPolicyInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return rewardPolicyInfoList;
    }

    /**
     * @param rewardPolicyId
     * @return
     * @Title: getRewardPolicy
     * @Author: Xian Wen
     * @Description: 获取某条奖励政策
     * @Return RewardPolicyInfo
     * @Throws
     */
    public static RewardPolicyInfo getRewardPolicy(String rewardPolicyId) {
        RewardPolicyInfo rewardPolicyInfo = new RewardPolicyInfo();
        try {
            String json = HttpUtil.httpGet(String.format(
                    Constants.REWARD_POLICY, Constants.SERVER, rewardPolicyId));
            JSONObject rewardPolicy = new JSONObject(json);

            rewardPolicyInfo.setName(rewardPolicy.getString("name"));
            rewardPolicyInfo.setId(rewardPolicy.getString("id"));
            rewardPolicyInfo.setContent(rewardPolicy.getString("content"));
            rewardPolicyInfo.setEndTime(rewardPolicy.getString("endTime"));
            rewardPolicyInfo.setStartTime(rewardPolicy.getString("startTime"));
            rewardPolicyInfo.setStatus(rewardPolicy.getString("status"));
            rewardPolicyInfo.setMoney(rewardPolicy.getString("money"));
            rewardPolicyInfo.setProductId(rewardPolicy.getString("productId"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return rewardPolicyInfo;
    }

    /**
     * @param
     * @return
     * @Title: Settlement
     * @Author: Xian Wen
     * @Description: 进行结算
     * @Return SettlementItemInfo
     * @Throws
     */
    public static SettlementItemInfo Settlement(String memeberId, String meid,
                                                String settlementCode) {
        SettlementItemInfo settlementItemInfo = new SettlementItemInfo();
        try {
            String json = HttpUtil.httpGet(String.format(
                    Constants.FOR_SETTLEMENT_RECORED, Constants.SERVER,
                    memeberId, meid, settlementCode));

            JSONObject jsonObject = new JSONObject(json);
            String status = jsonObject.getString("status");
            if (status.equals("0")) {
                JSONObject settlement = jsonObject.getJSONObject("body");
                settlementItemInfo.setStatus(status);
                settlementItemInfo.setCreateTime(settlement
                        .getString("createTime"));
                settlementItemInfo.setMoney(settlement.getString("money"));
                settlementItemInfo.setMeid(settlement.getString("meid"));
                settlementItemInfo.setPhoneType(settlement
                        .getString("phoneType"));
                settlementItemInfo.setCode(settlement
                        .getString("settlementCode"));
                settlementItemInfo.setId(settlement.getString("id"));
                return settlementItemInfo;
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return
     * @Title: bankList
     * @Author: Xian Wen
     * @Description: 获取银行总行列表
     * @Return List<BankInfo>
     * @Throws
     */
    public static List<BankInfo> bankList() {
        List<BankInfo> bankInfoList = new ArrayList<BankInfo>();
        String json = HttpUtil.httpGet(
                String.format(Constants.BANK, Constants.SERVER));
        try {
            if (!TextUtils.isEmpty(json)) {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    BankInfo bankInfo = new BankInfo();
                    bankInfo.setBankName(jsonObject.getString("name"));
                    bankInfo.setBankId(jsonObject.getString("id"));
                    bankInfoList.add(bankInfo);
                }
            }
            return bankInfoList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<BranchInfo> branchInfo(String bankId,
                                              String provinceNane, String city) {
        List<BranchInfo> bankInfoList = new ArrayList<BranchInfo>();
        String json = HttpUtil.httpGet(String.format(Constants.BRANCK_BANK,
                Constants.SERVER, bankId, provinceNane, city));
        try {
            if (!TextUtils.isEmpty(json)) {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    BranchInfo branchInfo = new BranchInfo();
                    branchInfo.setBranchName(jsonObject.getString("name"));
                    System.err.println("name============"
                            + jsonObject.getString("name"));
                    branchInfo.setId(jsonObject.getString("id"));
                    System.err.println("id============"
                            + jsonObject.getString("id"));
                    bankInfoList.add(branchInfo);
                }
            }
            return bankInfoList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encrypByMD5(String originString) {
        if (originString != null) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] results = md.digest(originString.getBytes());
                String resultString = byteArrayToHex(results);
                return resultString;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    private static String byteArrayToHex(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHex(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHex(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexArray[d1] + hexArray[d2];
    }

}
