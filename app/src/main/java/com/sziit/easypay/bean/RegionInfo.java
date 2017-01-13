/**
 * Copyright (c) 2011-2014 FengWoo Network Co.,Ltd.
 * <p>
 * Prohibition is granted, charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software
 */
package com.sziit.easypay.bean;

/**
 * @ClassName: CityInfo
 * @Package cn.fengwoo.easysettlement.info
 * @Description: TODO
 * @Author <a href="mailto:xianwen@fengwoo.cn">happy</a>
 * @Date 2014年11月17日 下午5:15:14
 * @Version EasySettlement 1.0.0
 */
public class RegionInfo {
    private String cityId;
    private String proviceId;
    private String cityName;

    /**
     * @return the cityId
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * @param cityId the cityId to set
     */
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    /**
     * @return the proviceId
     */
    public String getProviceId() {
        return proviceId;
    }

    /**
     * @param proviceId the proviceId to set
     */
    public void setProviceId(String proviceId) {
        this.proviceId = proviceId;
    }

    /**
     * @return the cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName the cityName to set
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
