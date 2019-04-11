package com.example.rxjavademo.operator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.rxjavademo.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class SwitchOperator extends AppCompatActivity {
    private final String TAG = getClass().getTypeName();
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //************转换操作符********************
        /**
         *map
         * 对 被观察者发送的每1个事件都通过 指定的函数 处理，从而变换成另外一种事件
         */
//        Observable.just(1,2,3,4)
//                .map(new Function<Integer, String>() {
//
//                    @Override
//                    public String apply(Integer integer) throws Exception {
//                        return "使用 Map变换操作符 将事件" + integer +"的参数从 整型"+integer + " 变换成 字符串类型" + integer ;
//                    }
//                })
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        Log.d(TAG, s);
//                    }
//                });

        /**
         * flatMap
         * 将被观察者发送的事件序列进行 拆分 & 单独转换，再合并成一个新的事件序列，最后再进行发送
         * 原理
         * 1.为事件序列中每个事件都创建一个 Observable 对象；
         * 2.将对每个 原始事件 转换后的 新事件 都放入到对应 Observable对象；
         * 3.将新建的每个Observable 都合并到一个 新建的、总的Observable 对象；
         * 4.新建的、总的Observable 对象 将 新合并的事件序列 发送给观察者（Observer）
         *
         */
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//                @Override
//                public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                    e.onNext(1);
//                    e.onNext(2);
//                    e.onNext(3);
//                    e.onNext(4);
//                }
//            }).flatMap(new Function<Integer, ObservableSource<String>>() {
//                    @Override
//                    public ObservableSource<String> apply(Integer integer) throws Exception {
//                        final List<String> list = new ArrayList<>();
//                        for(int i=0;i<4;i++) {
//                            list.add("我是flatMap事件 " + integer + "拆分后的子事件" + i);
//                        }
//                        return Observable.fromIterable(list);
//                    }
//                })
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        Log.d(TAG, "accept: "+s);
//                    }
//                });

        /**
         * ContactMap
         *与FlatMap（）的 区别在于：拆分 & 重新合并生成的事件序列 的顺序 = 被观察者旧序列生产的顺序
         */
        Observable.just(1,2,3,4)
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        final List<String> list = new ArrayList<>();
                        for(int i=0;i<4;i++) {
                            list.add("我是concatMap事件 " + integer + "拆分后的子事件" + i);
                        }
                        return  Observable.fromIterable(list);
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "accept: "+s);
                    }
                });

        /**
         * Buffer
         * 从发送的事件中去一定的数量到缓存区中
         *
         */
        Observable.just(1,2,3,4)
                .buffer(3,1)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        Log.d(TAG, "缓存区数量: "+integers.size());
                        for(Integer value : integers) {
                            Log.d(TAG, "onNext: 事件 = " + value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
