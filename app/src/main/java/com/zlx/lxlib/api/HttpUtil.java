package com.zlx.lxlib.api;

import android.content.Context;


import com.zlx.lxlib.util.LogUtil;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Zlx on 2017/10/30.
 */
public class HttpUtil {

    private static Context mContext;
    private static String BASEURL;

    public static void init(Context context) {
        mContext = context;
    }

    public static void post(String url, Map<String, Object> map, Consumer<String> successCosumer,
                            Consumer<Throwable> failuredCosumer) {
        ApiService services = RetrofitCreateHelper.createApi(ApiService.class, WebUrl.URL_BASE, mContext);
        Disposable disposable = services.post(url, map)
                .compose(RxHelper.<String>rxSchedulerHelper())
                .subscribe(successCosumer, failuredCosumer);
    }

    public static void get(String url, Map<String, Object> map, Consumer<String> successCosumer,
                           Consumer<Throwable> failuredCosumer) {
        ApiService services = RetrofitCreateHelper.createApi(ApiService.class,WebUrl.URL_BASE, mContext);
        Disposable disposable = services.get(url, map)
                .compose(RxHelper.<String>rxSchedulerHelper())
                .subscribe(successCosumer, failuredCosumer);
    }


//    // 设置变量
//    // 可重试次数
//    private static int maxConnectCount = 10;
//    // 当前已重试次数
//    private static int currentRetryCount = 0;
//    // 重试等待时间
//    private static int waitRetryTime = 0;

    public static void retryWhen(String url, Map<String, Object> map, Consumer<String> successCosumer,
                                 Consumer<Throwable> failuredCosumer) {
        final int maxConnectCount = 2;
        final int[] currentRetryCount = {0};
        final int[] waitRetryTime = {0};
        ApiService services = RetrofitCreateHelper.createApi(ApiService.class, WebUrl.URL_BASE, mContext);
        Disposable disposable = services.post(url, map)
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Throwable throwable) throws Exception {

                                LogUtil.e("发生异常:" + throwable.toString());
                                /**
                                 * 需求1：根据异常类型选择是否重试
                                 * 即，当发生的异常 = 网络异常 = IO异常 才选择重试
                                 */
                                if (throwable instanceof IOException) {
                                    LogUtil.e("属于IO异常，需重试");

                                    /**
                                     * 需求2：限制重试次数
                                     * 即，当已重试次数 < 设置的重试次数，才选择重试
                                     */
                                    if (currentRetryCount[0] < maxConnectCount) {
                                        currentRetryCount[0]++;
                                        LogUtil.e("重试次数:" + currentRetryCount[0]);

                                        /**
                                         * 需求2：实现重试
                                         * 通过返回的Observable发送的事件 = Next事件，从而使得retryWhen（）重订阅，最终实现重试功能
                                         *
                                         * 需求3：延迟1段时间再重试
                                         * 采用delay操作符 = 延迟一段时间发送，以实现重试间隔设置
                                         *
                                         * 需求4：遇到的异常越多，时间越长
                                         * 在delay操作符的等待时间内设置 = 每重试1次，增多延迟重试时间1s
                                         */
                                        // 设置等待时间
                                        waitRetryTime[0] = 1000 + currentRetryCount[0] * 1000;
                                        LogUtil.e("等待时间:" + waitRetryTime[0]);
                                        return Observable.just(1).delay(waitRetryTime[0], TimeUnit.MILLISECONDS);
                                    } else {
                                        return Observable.error(new Throwable("重试次数已超过设置次数 = " + currentRetryCount[0] + "，即 不再重试"));
                                    }
                                } else {
                                    return Observable.error(new Throwable("发生了非网络异常（非I/O异常）"));
                                }
                            }
                        });
                    }
                })
                .compose(RxHelper.<String>rxSchedulerHelper())
                .subscribe(successCosumer, failuredCosumer);
    }
}
