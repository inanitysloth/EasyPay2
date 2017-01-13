/**
 * Copyright (c) 2011-2014 FengWoo Network Co.,Ltd.
 * <p>
 * Prohibition is granted, charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software
 */
package com.sziit.easypay.bean;

/**
 * @ClassName: ProductInfo
 * @Package cn.fengwoo.easysettlement.info
 * @Description: TODO
 * @Author <a href="mailto:xianwen@fengwoo.cn">happy</a>
 * @Date 2014年11月1日 下午4:45:32
 * @Version EasySettlement 1.0.0
 */
public class ProductInfo {
    private String id;
    private String content;
    private String phoneType;
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
