/**
 * Copyright (c) 2011-2014 FengWoo Network Co.,Ltd.
 * <p>
 * Prohibition is granted, charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software
 */
package com.sziit.easypay.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @ClassName: NetworkDetector
 * @Package cn.fengwoo.easusettlement.utils
 * @Description: TODO
 * @Author <a href="mailto:xianwen@fengwoo.cn">happy</a>
 * @Date 2014年11月17日 下午8:42:31
 * @Version EasySettlement 1.0.0
 */
public class NetworkDetector {
    public static boolean detect(Activity act) {

        ConnectivityManager manager = (ConnectivityManager) act.getApplicationContext().getSystemService(
                Context.CONNECTIVITY_SERVICE);

        if (manager == null) {
            return false;
        }

        NetworkInfo networkinfo = manager.getActiveNetworkInfo();

        if (networkinfo == null || !networkinfo.isAvailable()) {
            return false;
        }

        return true;
    }
}
