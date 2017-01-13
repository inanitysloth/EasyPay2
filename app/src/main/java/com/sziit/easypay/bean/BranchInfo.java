/**
 * Copyright (c) 2011-2014 FengWoo Network Co.,Ltd.
 * <p>
 * Prohibition is granted, charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software
 */
package com.sziit.easypay.bean;

/**
 * @ClassName: BranchInfo
 * @Package cn.fengwoo.easysettlement.info
 * @Description: TODO
 * @Author <a href="mailto:xianwen@fengwoo.cn">happy</a>
 * @Date 2014年11月25日 下午4:10:45
 * @Version drugstore 1.0.0
 */
public class BranchInfo {
    private String id;
    private String branchName;

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

    /**
     * @return the branchName
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * @param branchName the branchName to set
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
