package com.zlx.lxlib.base.base_api.observer;

import android.util.Log;

import com.zlx.lxlib.base.base_api.view_model.http.HttpViewModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @date: 2019\3\22 0022
 * @author: zlx
 * @email: 1170762202@qq.com
 * @description:
 */
public abstract class BaseObserver implements Observer<String> {
    private static final String TAG = "BaseObserver";

    @Override
    public void onSubscribe(Disposable d) {
        Log.e(TAG, "onSubscribe: ");
    }

    @Override
    public void onNext(String response) {
//        Log.e(TAG, "onNext: " + response);

        //在这边对 基础数据 进行统一处理  举个例子：
//        BaseBean baseResponse = JSON.parseObject(response, BaseBean.class);
//        if (baseResponse.getCode() == 200) {
//            onSuccess(response);
//        } else {
//            onFailure(null, HttpViewModel.request_error);
//        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "Throwable: " + e.toString());
        onFailure(e, HttpViewModel.request_error);
    }

    @Override
    public void onComplete() {
        Log.e(TAG, "onComplete: ");
    }

    public abstract void onSuccess(String data);

    public abstract void onFailure(Throwable e, String errorMsg);
}
