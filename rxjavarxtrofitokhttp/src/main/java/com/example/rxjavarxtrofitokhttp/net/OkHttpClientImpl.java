package com.mychery.android.lib.net;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class OkHttpClientImpl {

    private static final int DEFAULT_TIME_OUT = 20000;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType PROTOBUF = MediaType.parse("application/x-protobuf; charset=utf-8");

    private static final Gson formBodyParser = new Gson();

    private OkHttpClient.Builder mBuilder;
    private OkHttpClient mClient;

    private OkHttpClientImpl() {
        mBuilder = new OkHttpClient.Builder();

        // 设置超时时间
        mBuilder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS);
        mBuilder.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS);
        mBuilder.readTimeout(DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS);
    }

    public OkHttpClient.Builder getBuilder() {
        return mBuilder;
    }

    /**
     * must be called before creating request
     */
    public void init() {
        this.mClient = mBuilder.build();
    }

    public OkHttpClient getClient() {
        return mClient;
    }

    private static final class ClientHolder {
        private static final OkHttpClientImpl INSTANCE = new OkHttpClientImpl();
    }

    public static OkHttpClientImpl get() {
        return ClientHolder.INSTANCE;
    }


}
