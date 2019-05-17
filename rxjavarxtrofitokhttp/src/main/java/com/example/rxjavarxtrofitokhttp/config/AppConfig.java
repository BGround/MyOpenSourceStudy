package com.example.rxjavarxtrofitokhttp.config;

import android.content.Context;

public class AppConfig {

    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }
}
