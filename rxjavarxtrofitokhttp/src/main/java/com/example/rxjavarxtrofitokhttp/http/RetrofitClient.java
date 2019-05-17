package com.mychery.android.lib.http;

import android.os.Build;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.mychery.android.lib.BuildConfig;
import com.mychery.android.lib.R;
import com.mychery.android.lib.config.AppConfig;
import com.mychery.android.lib.config.preferences.UserPreferences;
import com.mychery.android.lib.http.converter.FastJsonConverterFactory;
import com.mychery.android.lib.utils.DeviceUtils;
import com.mychery.android.lib.utils.UIUtils;
import com.mychery.android.lib.view.widget.CustomToast;

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
    public static final String BASE_URL = BuildConfig.API_SERVER;
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
            builder.connectTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .readTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS);
            mOkHttpClient = builder
                    .addNetworkInterceptor(new StethoInterceptor())
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
        header.put("osVersion",Build.VERSION.RELEASE);
        header.put("deviceType","android");
        header.put("deviceBuild",Build.MODEL);
        header.put("langCode","zh");
        header.put("source","app");
        header.put("version",DeviceUtils.getVersion(AppConfig.getContext()));
    }

    public static Observable<retrofit2.Response<AppResultData>> getMyCherryData(String path, JSONObject object) {
//        Log.i("RetrofitClient" + path, "paramter: " + JSON.toJSONString(object));

        if (!TextUtils.isEmpty(UserPreferences.getAppToken())){
            header.put("token",UserPreferences.getAppToken());
        }
        return mApiStores.getData(path,JSON.toJSONString(header),object)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<AppResultData> getDataFromService(String path, Object requestParam) {
        //Log.i("RetrofitClient" + path, "paramter: " + JSON.toJSONString(requestParam));
        return mApiStores.getData(path, getWxtClient(), JSON.toJSONString(requestParam), "zh", "client-side", "4.1", UserPreferences.getAppToken())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private static RequestBody toRequstBody(String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body;
    }

    public static String getWxtClient() {
        if (TextUtils.isEmpty(mWxtClient)) {
            com.alibaba.fastjson.JSONObject middlejson = new com.alibaba.fastjson.JSONObject();
            middlejson.put("deviceType", "1025");
            middlejson.put("macAddr", AppConfig.getMacAddress());
            middlejson.put("uuid", AppConfig.getUUID());
            middlejson.put("ipAddr", AppConfig.getIpAddress());
            mWxtClient = middlejson.toString();
        }
        return mWxtClient;
    }

    /**
     * 单个文件上传
     *
     * @param url
     * @param file
     * @param observer
     */
    public static void uploadFile(String url, String type, File file, final BaseObserver observer) {
        Map<String, RequestBody> params = null;
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        params = new HashMap<>();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("type", type);
        params.put("client", toRequstBody(getWxtClient()));
        params.put("type", toRequstBody(type));
//        params.put("parameter", toRequstBody(JSON.toJSONString(jsonObject)));
        params.put("langCode", toRequstBody("zh"));
        params.put("source", toRequstBody("client-side"));
        params.put("version", toRequstBody("4.1"));
        params.put("token", toRequstBody(UserPreferences.getAppToken()));
        params.put("file" + "\";filename=\"" + file.getName(), requestFile);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
        ApiStores apiStores = retrofit.create(ApiStores.class);
        Call<AppResultData> resultCall = apiStores.uploadFile(url, params);
        resultCall.enqueue(new Callback<AppResultData>() {
            @Override
            public void onResponse(Call<AppResultData> call, retrofit2.Response<AppResultData> response) {
                if (response.body() != null) {
                    try {
                        AppResultData result = response.body();
                        if (result != null && response.code() == 200) {
                            Object msg = result.getCode().getName();
                            if (observer != null) {
                                observer.loadSuccess(RetrofitClient.CODE_OK, "上传成功", JSON.toJSONString(msg));
                            }
                        } else {
                            if (observer != null) {
                                CustomToast.showToast(UIUtils.getString(R.string.network_server_failed));
                                observer.loadSuccess(0, "", "");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AppResultData> call, Throwable t) {
                //Log.i("RetrofitService", "onFailure: ");
                if (observer != null) {
                    CustomToast.showToast(UIUtils.getString(R.string.network_server_failed));
                    observer.loadSuccess(0, "", "");
                }
            }
        });
    }
}
