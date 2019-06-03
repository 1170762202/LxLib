package com.zlx.lxlib.base.base_api.observer;

/**
 * @date: 2019\3\22 0022
 * @author: zlx
 * @email: 1170762202@qq.com
 * @description:
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import com.zlx.lxlib.MyApp;

import io.reactivex.disposables.Disposable;

/**
 * Observer加入加载框
 *
 * @param
 */
public abstract class LoadingObserver extends BaseObserver {
    private Disposable d;

    private Context mContext;

    public LoadingObserver() {
        mContext = MyApp.instance;
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
        if (!isConnected(mContext)) {
            Toast.makeText(mContext, "未连接网络", Toast.LENGTH_SHORT).show();
            if (d.isDisposed()) {
                d.dispose();
            }
        } else {
            /*正在加载中*/
        }
    }

    @Override
    public void onError(Throwable e) {
        if (d.isDisposed()) {
            d.dispose();
        }
        /*隐藏加载*/
        super.onError(e);
    }

    @Override
    public void onComplete() {
        if (d.isDisposed()) {
            d.dispose();
        }
        /*隐藏加载*/
        super.onComplete();
    }


    /**
     * 是否有网络连接，不管是wifi还是数据流量
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            return false;
        }
        boolean available = info.isAvailable();
        return available;
    }
}
