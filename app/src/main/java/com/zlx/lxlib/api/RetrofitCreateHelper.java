package com.zlx.lxlib.api;

import android.content.Context;
import android.util.Log;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Zlx on 2017/12/12.
 */
public class RetrofitCreateHelper {
    private static final int TIMEOUT_READ = 20;
    private static final int TIMEOUT_CONNECTION = 10;


    public static <T> T createApi(Class<T> clazz, String baseUrl, final Context context) {
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
//                        String token = (String) SPUtil.get(context, SPUtil.TOKEN, "");
                        Log.e("TAG", "头参： " + "");

                        HttpUrl url = originalRequest.url()
                                .newBuilder()
                                .addQueryParameter("userId", "")
                                .addQueryParameter("id", "")
                                .build();
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
                .addConverterFactory(ScalarsConverterFactory.create())
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
