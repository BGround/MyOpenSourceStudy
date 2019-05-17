package com.example.rxjavarxtrofitokhttp.config;

import android.content.Context;

/**
 * @author:: wangjianchi
 * @time: 2018/1/15  16:19.
 * @description:
 */

public class CheryConfig {

    public static void init(Context context) {
//        RetrofitClient.init();
        AppConfig.init(context);

    }

    public static Context getContext() {
        return AppConfig.getContext();
    }


}
