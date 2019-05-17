package com.mychery.appcloud.config;

import android.content.Context;

import com.mychery.android.lib.config.AppConfig;
import com.mychery.android.lib.http.RetrofitClient;
import com.mychery.android.lib.storage.StorageUtil;

/**
 * @author:: wangjianchi
 * @time: 2018/1/15  16:19.
 * @description:
 */

public class CheryConfig {
    public static void init(Context context) {
        AppConfig.init(context);
        RetrofitClient.init();
        StorageUtil.init(context);
    }


    public interface OnHttpLoadListener {
        void loadSuccess();

    }



}
