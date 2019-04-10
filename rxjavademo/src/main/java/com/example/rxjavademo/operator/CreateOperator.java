package com.example.rxjavademo.operator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.rxjavademo.R;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CreateOperator extends AppCompatActivity {
    private final String TAG = getClass().getTypeName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_operator);

        //******************* 快速创建和发送事件**********************
        /**
         *Just
         * 发送的事件小于10个
         */
//        Observable.just(1,2,3,4)
//            .subscribe(new Observer<Integer>() {
//                @Override
//                public void onSubscribe(Disposable d) {
//                    Log.d(TAG, "开始采用onSubscribe2连接");
//                }
//
//                @Override
//                public void onNext(Integer integer) {
//                    Log.d(TAG, "onNext: 对"+integer+"做出反应");
//                }
//
//                @Override
//                public void onError(Throwable e) {
//                    Log.d(TAG, "onError: ");
//                }
//
//                @Override
//                public void onComplete() {
//                    Log.d(TAG, "onComplete: ");
//                }
//        });

        /**
         *fromArray
         * 发送的事件可以大于10个
         */
//        Integer[] items = {5, 6, 7, 8};
//        Observable.fromArray(items)
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "开始采用onSubscribe2连接");
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.d(TAG, "onNext: 对"+integer+"做出反应");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError: ");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "onComplete: ");
//                    }
//                });

        /**
         *
         * //下列方法一般用于测试使用
         <-- empty()  -->
         该方法创建的被观察者对象发送事件的特点：仅发送Complete事件，直接通知完成
                        Observable observable1=Observable.empty();
         即观察者接收后会直接调用onCompleted（）

         <-- error()  -->
         该方法创建的被观察者对象发送事件的特点：仅发送Error事件，直接通知异常
         可自定义异常
                        Observable observable2=Observable.error(new RuntimeException())
         即观察者接收后会直接调用onError（）

         <-- never()  -->
         该方法创建的被观察者对象发送事件的特点：不发送任何事件
                        Observable observable3=Observable.never();
         即观察者接收后什么都不调用
         */

        //*********************延迟创建************************

        /**
         * defer
         */

//        //第一次赋值
//        Integer i = 15;
//
//        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
//            @Override
//            public ObservableSource<? extends Integer> call() throws Exception {
//                return Observable.just(i);
//            }
//        });
//
//        //第二次赋值
//        i = 30;
//        observable.subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, "开始采用defer onSubscribe连接");
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.d(TAG, "onNext: 对"+integer+"做出反应");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "onError: ");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "onComplete: ");
//            }
//        });

        /**
         * timer
         * 该例子 = 延迟3s后，发送一个long类型数值
         */
//        Observable.timer(3, TimeUnit.SECONDS)
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "timer onSubscribe连接");
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        Log.d(TAG, "onNext: 对"+aLong+"做出反应");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError: ");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "onComplete: ");
//                    }
//                });

        /**
         * interval
         * 参数1 = 第1次延迟时间；
         * 参数2 = 间隔时间数字；
         * 参数3 = 时间单位；
         */
//        Observable.interval(3,1,TimeUnit.SECONDS)
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "interval onSubscribe连接");
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        Log.d(TAG, "onNext: 对"+aLong+"做出反应");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError: ");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "onComplete: ");
//                    }
//                });

        /**
         * intervalRange
         * 参数1 = 事件序列起始点；
         * 参数2 = 事件数量
         * 参数3 = 第1次事件延迟发送时间
         * 参数4 = 间隔时间数字；
         * 参数5 = 时间单位；
         */
        Observable.intervalRange(3,10,2,1,TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "intervalRange onSubscribe连接");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "onNext: 对"+aLong+"做出反应");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });


    }
}
