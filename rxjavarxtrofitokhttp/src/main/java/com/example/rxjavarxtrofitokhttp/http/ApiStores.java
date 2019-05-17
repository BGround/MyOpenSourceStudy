package com.example.rxjavarxtrofitokhttp.http;


import com.alibaba.fastjson.JSONObject;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * @author:: wangjianchi
 * @time: 2018/1/3  16:40.
 * @description:
 */

public interface ApiStores {

    @FormUrlEncoded
    @POST
    Observable<AppResultData> getData(@Url String path,
                                      @Field("client") String client,
                                      @Field("parameter") String parameter,
                                      @Field("langCode") String langCode,
                                      @Field("source") String source,
                                      @Field("version") String version,
                                      @Field("token") String lky_token);

    @POST("{url}")
    Observable<retrofit2.Response<AppResultData>> getData(@Path(value = "url", encoded = true) String url,
                                                          @Header("data") String data,
                                                          @Body JSONObject jsonObject);

    @Multipart
    @POST
    Call<AppResultData> uploadFile(@Url String path,
                                   @PartMap Map<String, RequestBody> param);


}
