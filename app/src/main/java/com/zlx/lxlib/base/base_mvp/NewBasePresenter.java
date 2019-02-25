package com.zlx.lxlib.base.base_mvp;

import com.alibaba.fastjson.JSON;
import com.zlx.lxlib.base.base_mvp.mobdel.BaseModel;
import com.zlx.lxlib.base.base_mvp.mobdel.IBaseModelListener;
import com.zlx.lxlib.base.base_mvp.presenter.BaseContact;
import com.zlx.lxlib.base.base_mvp.presenter.BasePresenter;
import com.zlx.lxlib.util.LogUtil;

import java.util.Map;

/**
 * @date: 2019\2\25 0025
 * @author: zlx
 * @description:
 */
public class NewBasePresenter extends BasePresenter<BaseContact.IViewData> {
    private BaseModel baseModel;

    public NewBasePresenter() {
        baseModel = new BaseModel();
    }

    public void post(String url, Map<String, Object> map, final BaseContact.BaseView baseView){
        baseView.onRequestStart();
        LogUtil.e("onRequestStart");
        baseModel.post(url, map, new IBaseModelListener() {
            @Override
            public void onSuccess(String s) {
//                BaseResult baseBean = JSON.parseObject(s, BaseResult.class);
                int statusCode = 200;
                if (statusCode == 200) {
                    baseView.onRequestSuccess(s);
                    LogUtil.e("onRequestSuccess:"+s);

                } else if (statusCode == 999) {
                    baseView.onRequestFailed(s);
                    LogUtil.e("onRequestFailed:"+s);

                } else {
                    baseView.onRequestFailed(s);
                    LogUtil.e("onRequestFailed:"+s);

                }
                baseView.onRequestFinished();
                LogUtil.e("onRequestFinished");

            }

            @Override
            public void onError(String s) {
                baseView.onRequestError(s);
                baseView.onRequestFinished();
                LogUtil.e("onRequestError:"+s);
                LogUtil.e("onRequestFinished");
            }
        });
    }
}
