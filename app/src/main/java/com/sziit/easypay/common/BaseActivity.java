package com.sziit.easypay.common;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by inanitysloth on 2017/1/10.
 */

public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public abstract void initView();

    public abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
