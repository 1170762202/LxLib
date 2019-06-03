package com.zlx.lxlib.base.base_api.interceptor;

import android.util.Log;


import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @date: 2019\5\29 0029
 * @author: zlx
 * @email: 1170762202@qq.com
 * @description:
 */
public class BodyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl url = originalRequest.url()
                .newBuilder()
                .addQueryParameter("userId", "")
                .build();
        Request authorised = originalRequest.newBuilder()
                .header("Authorization", "")
                .url(url)
                .build();

        return chain.proceed(authorised);
    }
}
