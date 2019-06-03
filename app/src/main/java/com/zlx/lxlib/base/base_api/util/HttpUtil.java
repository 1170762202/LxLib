package com.zlx.lxlib.base.base_api.util;

import android.content.Context;
import com.alibaba.fastjson.JSON;
import com.zlx.lxlib.base.base_api.WxRequestInterface;
import com.zlx.lxlib.base.base_api.bean.WebUrl;
import com.zlx.lxlib.base.base_api.observer.LoadingObserver;
import com.zlx.lxlib.util.Log;
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
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Zlx on 2017/10/30.
 */
public class HttpUtil {

    private static Context mContext;
    private static String BASEURL;

    public static void init(Context context) {
        mContext = context;
    }

    public static void post(String url, Map<String, Object> map, LoadingObserver observer) {
        RetrofitCreateHelper.getApiUrl()
                .post(url, map)
                .compose(RxHelper.rxSchedulerHelper())
                .subscribe(observer);
    }

    public static void get(String url, Map<String, Object> map, LoadingObserver observer) {
        RetrofitCreateHelper.getApiUrl()
                .get(url, map)
                .compose(RxHelper.rxSchedulerHelper())
                .subscribe(observer);
    }



    public static void postWithJson(String url, Map<String, Object> map, LoadingObserver observer) {
        WxRequestInterface services = RetrofitCreateHelper.createApiWithCommonBody(WxRequestInterface.class, WebUrl.BASE_URL);
        String userInfo = (String) map.get("userInfo");
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), userInfo);
        RetrofitCreateHelper.getApiUrl()
                .postWithJson(url, body)
                .compose(RxHelper.rxSchedulerHelper())
                .subscribe(observer);
    }

    public static void uploadOneFile(String url, Map<String, Object> map, LoadingObserver observer) {
        // 创建 RequestBody，用于封装构建RequestBody
        String img = map.get("picture").toString();
        File file = new File(img);

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("image/*"), file);

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型
        builder.addFormDataPart("picture", file.getName(), requestFile);

        RetrofitCreateHelper.getApiUrl()
                .uploadOneFile(url, builder.build().parts())
                .compose(RxHelper.rxSchedulerHelper())
                .subscribe(observer);
    }

//    // 设置变量
//    // 可重试次数
//    private static int maxConnectCount = 10;
//    // 当前已重试次数
//    private static int currentRetryCount = 0;
//    // 重试等待时间
//    private static int waitRetryTime = 0;

    public static void retryWhen(String url, Map<String, Object> map, LoadingObserver observer) {
        int maxConnectCount = 2;
        final int[] currentRetryCount = {0};
        final int[] waitRetryTime = {0};
        RetrofitCreateHelper.getApiUrl()
                .post(url, map)
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Throwable throwable) throws Exception {
                                Log.e("发生异常:" + throwable.toString());
                                /**
                                 * 需求1：根据异常类型选择是否重试
                                 * 即，当发生的异常 = 网络异常 = IO异常 才选择重试
                                 */
                                if (throwable instanceof IOException) {
                                    Log.e("属于IO异常，需重试");

                                    /**
                                     * 需求2：限制重试次数
                                     * 即，当已重试次数 < 设置的重试次数，才选择重试
                                     */
                                    if (currentRetryCount[0] < maxConnectCount) {
                                        currentRetryCount[0]++;
                                        Log.e("重试次数:" + currentRetryCount[0]);

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
                                        Log.e("等待时间:" + waitRetryTime[0]);
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
                .compose(RxHelper.rxSchedulerHelper())
                .subscribe(observer);
    }

    /**
     * @param url
     * @param map
     * @param successCosumer
     * @param failuredCosumer
     * @deprecated
     */
    public static void post(String url, Map<String, Object> map, Consumer<String> successCosumer,
                            Consumer<Throwable> failuredCosumer) {
        WxRequestInterface services = RetrofitCreateHelper.createApiWithCommonBody(WxRequestInterface.class, WebUrl.BASE_URL);
        Disposable disposable = services.post(url, map)
                .compose(RxHelper.<String>rxSchedulerHelper())
                .subscribe(successCosumer, failuredCosumer);
    }

    /**
     * @param url
     * @param map
     * @param successCosumer
     * @param failuredCosumer
     * @deprecated
     */
    public static void get(String url, Map<String, Object> map, Consumer<String> successCosumer,
                           Consumer<Throwable> failuredCosumer) {
        WxRequestInterface services = RetrofitCreateHelper.createApiWithCommonBody(WxRequestInterface.class, WebUrl.BASE_URL);
        Disposable disposable = services.get(url, map)
                .compose(RxHelper.<String>rxSchedulerHelper())
                .subscribe(successCosumer, failuredCosumer);
    }

    /**
     * @param url
     * @param map
     * @param successCosumer
     * @param failuredCosumer
     * @deprecated
     */
    public static void uploadOneFile(String url, Map<String, Object> map, Consumer<String> successCosumer,
                                     Consumer<Throwable> failuredCosumer) {
        WxRequestInterface services = RetrofitCreateHelper.createApiWithCommonBody(WxRequestInterface.class, WebUrl.BASE_URL);
        // 创建 RequestBody，用于封装构建RequestBody
        String img = map.get("picture").toString();
        File file = new File(img);

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("image/*"), file);

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型
        builder.addFormDataPart("picture", file.getName(), requestFile);

        Disposable disposable = services.uploadOneFile(url, builder.build().parts())
                .compose(RxHelper.<String>rxSchedulerHelper())
                .subscribe(successCosumer, failuredCosumer);
    }

    public static void postWx(String url, Map<String, Object> map, Consumer<String> successCosumer,
                              Consumer<Throwable> failuredCosumer) {
        WxRequestInterface services = RetrofitCreateHelper.createApiWithCommonBody(WxRequestInterface.class,
                "https://api.weixin.qq.com/");
        Disposable subscribe = services.post(url, map)
                .compose(RxHelper.<String>rxSchedulerHelper())
                .subscribe(successCosumer, failuredCosumer);
    }

}
