package com.example.rxjavarxtrofitokhttp.http;


import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.example.rxjavarxtrofitokhttp.R;
import com.example.rxjavarxtrofitokhttp.widgets.CustomToast;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * @author:: wangjianchi
 * @time: 2018/1/4  13:55.
 * @description:
 */

public abstract class BaseObserver implements Observer<Response<AppResultData>> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Response<AppResultData> data) {
        AppResultData appResultData = data.body();
        //Log.i("RetrofitClient", "onNext: "+JSON.toJSONString(appResultData));
        String token = data.headers().get("Set-Authorization");
        if (!TextUtils.isEmpty(token)){
//            token = InputUtils.getRandomString(7)+token.substring(4);
            //Log.i("RetrofitClient", "token: "+token);
//            UserPreferences.saveAppToken(token);
        }

        if (appResultData!=null&&appResultData.getCode() != null){

            loadSuccess(appResultData.getCode().getValue(),appResultData.getCode().getName(),JSON.toJSONString(appResultData.getData()));
//            if (data.getCode().equals("300000")){
//                CustomToast.showToast("TOKEN失效，请重新登录");
//                UserPreferences.saveUserName("");
//            }
        }else {
            CustomToast.showToast("服务器错误，请稍后重试");
            loadSuccess(0,data.message(),"");
        }
    }

    @Override
    public void onError(Throwable e) {
        CustomToast.showToast("network error");
        loadSuccess(404,"","");
        //Log.i("Retrofit", "onError: "+e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    public abstract void loadSuccess(int code, String msg, String result);


}
