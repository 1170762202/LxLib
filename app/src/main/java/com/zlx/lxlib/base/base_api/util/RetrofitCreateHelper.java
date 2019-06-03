package com.zlx.lxlib.base.base_api.util;

import android.util.Log;


import com.zlx.lxlib.base.base_api.WxRequestInterface;
import com.zlx.lxlib.base.base_api.bean.WebUrl;
import com.zlx.lxlib.base.base_api.interceptor.BodyInterceptor;
import com.zlx.lxlib.base.base_api.interceptor.LogInterceptor;
import com.zlx.lxlib.util.LogUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Zlx on 2017/12/12.
 */
public class RetrofitCreateHelper {
    private static final int TIMEOUT_READ = 60;
    private static final int TIMEOUT_CONNECTION = 60;

    private static WxRequestInterface mApiUrl;

    /**
     * 单例模式
     */
    public static WxRequestInterface getApiUrl() {
        if (mApiUrl == null) {
            synchronized (RetrofitCreateHelper.class) {
                if (mApiUrl == null) {
                    mApiUrl = new RetrofitCreateHelper().getRetrofit();
                }
            }
        }
        return mApiUrl;
    }

    private RetrofitCreateHelper() {
    }

    public WxRequestInterface getRetrofit() {
        // 初始化Retrofit
        WxRequestInterface apiUrl = initRetrofit(initOkHttp()).create(WxRequestInterface.class);
        return apiUrl;
    }

    /**
     * 初始化Retrofit
     */
    @NonNull
    private Retrofit initRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(WebUrl.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    /**
     * 初始化okhttp
     */
    @NonNull
    private OkHttpClient initOkHttp() {
        return new OkHttpClient().newBuilder()
                .readTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(TIMEOUT_READ, TimeUnit.SECONDS)//设置请求超时时间
                .writeTimeout(TIMEOUT_READ, TimeUnit.SECONDS)//设置写入超时时间
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                //失败重连
                .retryOnConnectionFailure(true)
                .addInterceptor(new LogInterceptor())//添加打印拦截器
                .addInterceptor(new BodyInterceptor())
                .build();
    }


    public static <T> T createApiWithCommonBody(Class<T> clazz, String baseUrl) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                //time out
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                //失败重连
                .retryOnConnectionFailure(true)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        String selfID = null;
                        String token = null;
                        HttpUrl url = originalRequest.url()
                                .newBuilder()
                                .addQueryParameter("userId", selfID)
                                .build();
                        Log.e("TAG", "统一参数： " + selfID + "   " + token);
                        Request authorised = originalRequest.newBuilder()
                                .header("Authorization", selfID + token)
                                .url(url)
                                .build();

                        return chain.proceed(authorised);
                    }
                }).build();
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(clazz);
    }

    public static <T> T createApiWithCommonBody(Class<T> clazz, String baseUrl, Converter.Factory factory) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                //time out
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                //失败重连
                .retryOnConnectionFailure(true)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        String selfID = null;
                        HttpUrl url = originalRequest.url()
                                .newBuilder()
                                .addQueryParameter("userId", selfID)
                                .addQueryParameter("id", selfID)
                                .build();
                        Log.e("TAG", "统一参数： " + selfID);
                        Request authorised = originalRequest.newBuilder()
                                .header("x-token", "")
                                .url(url)
                                .build();
                        return chain.proceed(authorised);
                    }
                }).build();
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(clazz);
    }

    public static <T> T createApi(Class<T> clazz, String baseUrl) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                //time out
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                //失败重连
                .retryOnConnectionFailure(true)
                .build();
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(clazz);
    }

}
