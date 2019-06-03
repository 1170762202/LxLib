package com.zlx.lxlib.base.base_api.view_model.http;


import android.util.Log;


import com.zlx.lxlib.base.base_api.observer.LoadingObserver;
import com.zlx.lxlib.base.base_api.util.HttpUtil;

import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


/**
 * @date: 2019\5\29 0029
 * @author: zlx
 * @email: 1170762202@qq.com
 * @description:
 */
public class HttpViewModel extends ViewModel {
    /**
     * 请求失败
     */
    public static final String request_error = "request_error";

    private MutableLiveData<String> liveData;

    public HttpViewModel() {
        liveData = new MutableLiveData<>();
    }

    protected LiveData<String> requestPost(String url, Map<String, Object> map) {
        HttpUtil.post(url, map, new LoadingObserver() {
            @Override
            public void onSuccess(String data) {
                liveData.setValue(data);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {
                Log.e("TAG", "onFailure:" + errorMsg);
                liveData.setValue(errorMsg);
            }
        });
        return liveData;
    }

    protected LiveData<String> requestGet(String url, Map<String, Object> map) {
        HttpUtil.get(url, map, new LoadingObserver() {
            @Override
            public void onSuccess(String data) {
                liveData.setValue(data);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {
                Log.e("TAG", "onFailure:" + errorMsg);
                liveData.setValue(errorMsg);
            }
        });
        return liveData;
    }


}
