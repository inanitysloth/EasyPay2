/**
 * Copyright (c) 2011-2014 FengWoo Network Co.,Ltd.
 * <p>
 * Prohibition is granted, charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software
 */
package com.sziit.easypay.bean;

/**
 * @ClassName: SettlementItemInfo
 * @Package cn.fengwoo.easysettlement.info
 * @Description: TODO
 * @Author <a href="mailto:xianwen@fengwoo.cn">happy</a>
 * @Date 2014年10月28日 下午4:34:21
 * @Version EasySettlement 1.0.0
 */
public class SettlementItemInfo {
    private String createTime;
    private String phoneType;
    private String money;
    private String meid;
    private String status;
    private String code;
    private String id;

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the createTime
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the money
     */
    public String getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(String money) {
        this.money = money;
    }

    /**
     * @return the meid
     */
    public String getMeid() {
        return meid;
    }

    /**
     * @param meid the meid to set
     */
    public void setMeid(String meid) {
        this.meid = meid;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the phoneType
     */
    public String getPhoneType() {
        return phoneType;
    }

    /**
     * @param phoneType the phoneType to set
     */
    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

}
