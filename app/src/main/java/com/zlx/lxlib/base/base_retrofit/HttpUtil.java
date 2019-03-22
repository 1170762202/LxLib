package com.zlx.lxlib.base.base_retrofit;

import android.content.Context;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zlx.lxlib.bean.Demo;

import static com.zlx.lxlib.api.WebUrl.URL_LOGIN;

/**
 * Created by Zlx on 2017/10/30.
 */
public class HttpUtil {

    private static Context mContext;
    private static String BASEURL;

    public static void init(Context context) {
        mContext = context;
    }

    /**
     * Get 请求demo
     *
     * @param context
     * @param observer
     */
    public static void getDemo(RxAppCompatActivity context, MyObserver<Demo> observer) {
        RetrofitUtil.getApiUrl()
                .getDemo(URL_LOGIN)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(observer);
    }


}
