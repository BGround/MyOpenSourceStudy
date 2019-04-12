package com.example.rxjavademo.operator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.rxjavademo.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CombineOperator extends AppCompatActivity {
    private final String TAG = getClass().getTypeName();
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //************组合操作符********************

        /**
         * contact() / contactArray()
         * 组合多个被观察者
         * concat（）组合被观察者数量≤4个，而concatArray（）则可＞4个
         */
//        Observable.concatArray(Observable.just(1,2),
//                Observable.just(3,4),
//                Observable.just(5,6),
//                Observable.just(7,8),
//                Observable.just(9,10))

//        Observable.concat(Observable.just(1,2,3),
//                          Observable.just(4,5,6),
//                          Observable.just(7,8,9))
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.d(TAG, "concat: 接收到了事件"+integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        /**
         * merge（） / mergeArray（）
         * 作用：组合被观察者的数量，即merge（）组合被观察者数量≤4个，而mergeArray（）则可＞4个
         * 区别上述concat（）操作符：同样是组合多个被观察者一起发送数据，但concat（）操作符合并后是按发送顺序串行执行
         */
//        Observable.mergeArray(
//                Observable.intervalRange(0,3,1,1, TimeUnit.SECONDS),
//                Observable.intervalRange(1,3,1,1, TimeUnit.SECONDS),
//                Observable.intervalRange(2,3,1,1, TimeUnit.SECONDS),
//                Observable.intervalRange(3,3,1,1, TimeUnit.SECONDS))

//        Observable.merge(
//                    Observable.intervalRange(0,3,2,3, TimeUnit.SECONDS),
//                    Observable.intervalRange(3,3,2,3, TimeUnit.SECONDS))
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        Log.d(TAG, "merge: 接收到了事件"+aLong);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        /**
         * contactDelayError() / mergeDelayError()
         * concatArrayDelayError() / mergeArrayDelayError()
         *
         * 直接使用contact的话会事件3之后直接发送Error事件，导致第二个观察者事件无法发送
         *
         * contactDelayError() / mergeDelayError()，第1个被观察者的Error事件将在第2个被观察者发送完事件后再继续发送
         *
         */
//        Observable.concatArrayDelayError(Observable.create(new ObservableOnSubscribe<Integer>() {
//
//                    @Override
//                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                        e.onNext(1);
//                        e.onNext(2);
//                        e.onNext(3);
//                        e.onError(new NullPointerException());
//                        e.onComplete();
//                    }
//                }),
//                Observable.just(4,5,6,7)
//        ).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.d(TAG, "merge: 接收到了事件"+integer);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "onError: 接受到Error事件");
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });


        //*****************合并事件**********************

//        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                Log.d(TAG, "observable1: 被观察者发送了事件1");
//                e.onNext(1);
//                Thread.sleep(1000);
//
//                Log.d(TAG, "observable1: 被观察者发送了事件2");
//                e.onNext(2);
//                Thread.sleep(1000);
//
//                Log.d(TAG, "observable1: 被观察者发送了事件3");
//                e.onNext(3);
//                Thread.sleep(1000);
//
//                e.onComplete();
//                Log.d(TAG, "被观察者1 onComplete");
//            }
//        }).subscribeOn(Schedulers.io());
//
//        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                Log.d(TAG, "observable2: 被观察者发送了事件A");
//                e.onNext("A");
//                Thread.sleep(1000);
//
//                Log.d(TAG, "observable2: 被观察者发送了事件B");
//                e.onNext("B");
//                Thread.sleep(1000);
//
//                Log.d(TAG, "observable2: 被观察者发送了事件C");
//                e.onNext("C");
//                Thread.sleep(1000);
//
//                Log.d(TAG, "observable2: 被观察者发送了事件D");
//                e.onNext("D");
////                Thread.sleep(1000);
//
//                e.onComplete();
//                Log.d(TAG, "被观察者2 onComplete");
//            }
//        }).subscribeOn(Schedulers.newThread());

        /**
         * Zip()
         * 合并 多个被观察者（Observable）发送的事件，生成一个新的事件序列（即组合过后的事件序列），并最终发送
         *
         */

//        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
//            @Override
//            public String apply(Integer integer, String s) throws Exception {
//                return integer+s;
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.d(TAG, "最终接收到的事件 = " +s);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "onComplete");
//            }
//        });

        /**
         * combineLatest（）
         * 参数：aLong 第一个被观察者最后一个事件，aLong2 第二个北广场这第一个事件
         *
         * 与Zip（）的区别：Zip（） = 按个数合并，即1对1合并；CombineLatest（） = 按时间合并，即在同一个时间点上合并
         */

//        Observable.combineLatest(
//                Observable.just(1L,2L,3L),
//                Observable.intervalRange(0,3,1,1,TimeUnit.SECONDS),
//        new BiFunction<Long, Long, Long>() {
//            @Override
//            public Long apply(Long aLong, Long aLong2) throws Exception {
//                return aLong + aLong2;
//            }
//        }).subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long value) throws Exception {
//                Log.d(TAG, "accept: "+value);
//            }
//        });


        /**
         * reduce（）
         * 把被观察者需要发送的事件聚合成1个事件 & 发送
         *
         */
//
//        Observable.just(2L,3L,4L)
//                .reduce(new BiFunction<Long, Long, Long>() {
//                    @Override
//                    public Long apply(Long s1, Long s2) throws Exception {
//                        Log.d(TAG, "本次计算的数据是： "+s1 +" 乘 "+ s2);
//                        return s1 * s2;
//                    }
//                }).subscribe(new Consumer<Long>() {
//                        @Override
//                        public void accept(Long s) throws Exception {
//                            Log.d(TAG, "最终计算的结果是： "+s);
//                        }
//        });

        /**
         * collect ()
         * 将被观察者Observable发送的数据事件收集到一个数据结构里
         */
//        Observable.just(1,2,3,4,5,6)
//                .collect(new Callable<ArrayList<Integer>>() {
//                    @Override
//                    public ArrayList<Integer> call() throws Exception {
//                        return new ArrayList<>();
//                    }
//                }, new BiConsumer<ArrayList<Integer>, Integer>() {
//                    @Override
//                    public void accept(ArrayList<Integer> list, Integer integer) throws Exception {
//                        list.add(integer);
//                    }
//                }).subscribe(new Consumer<ArrayList<Integer>>() {
//                    @Override
//                    public void accept(ArrayList<Integer> items) throws Exception {
//                        Log.d(TAG, "本次发送的数据是: "+items);
//                    }
//        });

        /**
         * startWith（） / startWithArray（）
         * 在一个被观察者发送事件前，追加发送一些数据 / 一个新的被观察者
         * 
         * 注：追加数据顺序 = 后调用先追加
         */
        Observable.just(4,5,6)
                .startWith(0)
                .startWithArray(1,2,3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer value) throws Exception {
                        Log.d(TAG, "接收到了事件"+ value);
                    }
                });

        /**
         * count ()
         * 统计事件发送数量
         */
        Observable.just(4,5,6)
                .count()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long count) throws Exception {
                        Log.d(TAG, "接收到了事件数量"+ count);
                    }
                });


    }

}

