package com.mychery.android.lib.net;

import android.util.SparseArray;

import com.mychery.android.lib.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitService {

    private static final String API_SERVER = BuildConfig.API_SERVER;

    private static Converter.Factory converterFactory;
    private static final SparseArray<Object> interfaces = new SparseArray<>();
    private Retrofit sRetrofitInstance;
    private static OkHttpClient sClient;
    private static volatile RetrofitService INSTANCE;

    public static RetrofitService get() {
        if (INSTANCE == null) {
            synchronized (RetrofitService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RetrofitService();
                }
            }
        }
        return INSTANCE;
    }

    private RetrofitService(){
        sRetrofitInstance = createRetrofitInstance().build();
    }

    /**
     * should be called before get()
     */
    public static void register(OkHttpClient.Builder builder) {
        sClient = builder.build();
    }

    public static void registerConverter(Converter.Factory factory){
        converterFactory = factory;
    }

    private static Retrofit.Builder createRetrofitInstance() {
        return new Retrofit.Builder()
                .client(sClient != null ? sClient : new OkHttpClient())
                .baseUrl(API_SERVER)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> tClass){
        T t = (T) interfaces.get(tClass.hashCode());
        if (t == null){
            t = sRetrofitInstance.create(tClass);
            interfaces.put(tClass.hashCode(),t);
        }
        return t;
    }
}
