package com.sziit.easypay.common;

import android.app.Application;

import cn.smssdk.SMSSDK;

/**
 * Created by inanitysloth on 2017/1/11.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SMSSDK.initSDK(this, "1ab3dbd660604", "4869ba3722faf39a16e660de025e83c5");
    }
}
