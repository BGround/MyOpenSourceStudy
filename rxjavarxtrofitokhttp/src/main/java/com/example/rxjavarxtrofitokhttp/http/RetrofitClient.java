package com.example.rxjavarxtrofitokhttp.http;

import android.os.Build;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.rxjavarxtrofitokhttp.base.MyApplication;
import com.example.rxjavarxtrofitokhttp.http.converter.FastJsonConverterFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author:: wangjianchi
 * @time: 2018/1/4  9:48.
 * @description:
 */

public abstract class RetrofitClient {
    private static final int DEFAULT_TIMEOUT = 15;
    public static final String BASE_URL = MyApplication.getInstances().API_SERVER;
    protected static OkHttpClient mOkHttpClient;
    private static String mWxtClient;
    private static ApiStores mApiStores;
    public static final int CODE_OK = 10000;
    private static JSONObject header = new JSONObject();

    private RetrofitClient(){

    }

    /**
     * 初始化retrofit 需重写
     */
    public static void init() {
        initOkHttpClient();
        initApiStore();
        initHeader();
    }

    /**
     * 初始化okhttp
     */
    protected static void initOkHttpClient() {
        if (mOkHttpClient == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            mOkHttpClient = builder
//                    .addNetworkInterceptor(new StethoInterceptor())
                    .addInterceptor(interceptor)
                    .build();
        }
    }

    protected static void initApiStore() {
        if (mApiStores == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(mOkHttpClient)
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
            mApiStores = retrofit.create(ApiStores.class);
        }
    }

    private static void initHeader(){
        header.put("osVersion", Build.VERSION.RELEASE);
        header.put("deviceType","android");
        header.put("deviceBuild", Build.MODEL);
        header.put("langCode","zh");
        header.put("source","app");
        header.put("version","2.3.7");
    }

    public static Observable<retrofit2.Response<AppResultData>> getMyCherryData(String path, JSONObject object) {
//        Log.i("RetrofitClient" + path, "paramter: " + JSON.toJSONString(object));

//        if (!TextUtils.isEmpty(UserPreferences.getAppToken())){
//            header.put("token",UserPreferences.getAppToken());
//        }
        return mApiStores.getData(path,JSON.toJSONString(header),object)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private static RequestBody toRequstBody(String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body;
    }

}
