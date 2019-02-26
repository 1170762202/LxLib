package com.zlx.lxlib.ui.login;

import com.zlx.lxlib.api.WebUrl;
import com.zlx.lxlib.base.base_mvp.presenter.BaseContact;
import com.zlx.lxlib.base.base_mvp.NewBasePresenter;

/**
 * @date: 2019\2\25 0025
 * @author: zlx
 * @description:
 */
public class LoginPresenter extends NewBasePresenter {

    public LoginPresenter(BaseContact.IViewData iRealView) {
        super(iRealView);
    }

    public void login(){
        post(WebUrl.URL_LOGIN, mView.getRequestMap(), new BaseContact.BaseView() {
            @Override
            public void onRequestStart() {
                mView.onRequestStart();
            }

            @Override
            public void onRequestSuccess(String s) {
                mView.onRequestSuccess(s);
            }

            @Override
            public void onRequestFailed(String s) {
                mView.onRequestFailed(s);
            }

            @Override
            public void onRequestError(String s) {
                mView.onRequestError(s);
            }

            @Override
            public void onRequestFinished() {
                mView.onRequestFinished();
            }
        });
    }

}

