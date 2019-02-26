package com.zlx.lxlib.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.zlx.lxlib.R;
import com.zlx.lxlib.base.base_ac.BaseAc;
import com.zlx.lxlib.base.base_ac.BaseMvpAc;
import com.zlx.lxlib.base.base_mvp.NewBasePresenter;
import com.zlx.lxlib.base.base_mvp.presenter.BaseContact;
import com.zlx.lxlib.util.LogUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @date: 2019\2\25 0025
 * @author: zlx
 * @description:
 */
public class LoginAc extends BaseMvpAc {

    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * 初始化view
     */
    @Override
    protected void initViews() {

    }


    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        loginPresenter.login();
    }

    /**
     * 新建一个presenter继承NewBasePresenter重写对应的方法,下次新建接口直接复制，改个url地址就ok
     */
    private LoginPresenter loginPresenter = new LoginPresenter(new BaseContact.IViewData() {
        @Override
        public Map<String, Object> getRequestMap() {
            return new HashMap<>();
        }

        @Override
        public void onRequestStart() {

        }

        @Override
        public void onRequestSuccess(String s) {

        }

        @Override
        public void onRequestFailed(String s) {

        }

        @Override
        public void onRequestError(String s) {

        }

        @Override
        public void onRequestFinished() {

        }
    });

    /**
     * 返回presenter 列表给mvpac，ondestroy时解绑 pv防止内存泄漏
     *
     * @return
     */
    @Override
    protected List<? extends NewBasePresenter> getPresenters() {
        return Arrays.asList(loginPresenter);
    }
}
