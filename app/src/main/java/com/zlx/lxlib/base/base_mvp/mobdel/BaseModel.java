package com.zlx.lxlib.base.base_mvp.mobdel;

import com.zlx.lxlib.base.base_api.util.HttpUtil;

import java.util.Map;

import io.reactivex.functions.Consumer;

/**
 * @date: 2019\2\25 0025
 * @author: zlx
 * @description:
 */
public class BaseModel implements IBaseModel {
    @Override
    public void post(String url, Map<String, Object> map, final IBaseModelListener listener) {
        HttpUtil.post(url, map, new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                listener.onSuccess(s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                listener.onError(throwable.toString());
            }
        });
    }

    @Override
    public void get(String url, Map<String, Object> map, final IBaseModelListener listener) {
        HttpUtil.get(url, map, new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                listener.onSuccess(s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                listener.onError(throwable.toString());
            }
        });
    }
}
