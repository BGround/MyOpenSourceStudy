package com.example.rxjavarxtrofitokhttp.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.example.rxjavarxtrofitokhttp.BuildConfig;
import com.example.rxjavarxtrofitokhttp.config.CheryConfig;
import com.example.rxjavarxtrofitokhttp.http.RetrofitClient;
import com.example.rxjavarxtrofitokhttp.net.OkHttpClientImpl;
import com.example.rxjavarxtrofitokhttp.net.RetrofitService;

import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {
    public static MyApplication instances;
    public static MyApplication getInstances() {
        return instances;
    }


    public static String API_SERVER = "https://emap.mychery.com:4430/prod/api/v2/";

    @Override
    public void onCreate() {
        super.onCreate();
        CheryConfig.init(this);
        RetrofitClient.init();
        initThirdPartWithSo();

    }

    /**
     * 初始化有so库的第三方代码 测试时需关闭
     */
    private void initThirdPartWithSo() {

        final OkHttpClient.Builder builder = OkHttpClientImpl.get().getBuilder();

//        builder.addInterceptor(TokenInterceptor.get());
//        builder.addInterceptor(DefaultHeaderInterceptor.get());
        RetrofitService.register(builder);
        RetrofitService.registerConverter(GsonConverterFactory.create());

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
