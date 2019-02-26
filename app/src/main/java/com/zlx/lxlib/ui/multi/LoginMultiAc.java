package com.zlx.lxlib.ui.multi;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.zlx.lxlib.R;
import com.zlx.lxlib.base.base_ac.BaseMvpAc;
import com.zlx.lxlib.base.base_mvp.NewBasePresenter;
import com.zlx.lxlib.base.base_mvp.presenter.BaseContact;
import com.zlx.lxlib.ui.login.LoginPresenter;
import com.zlx.lxlib.util.LogUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.OnClick;

/**
 * @date: 2019\2\26 0026
 * @author: zlx
 * @email: 1170762202@qq.com
 * @description:
 */
public class LoginMultiAc extends BaseMvpAc {


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
    private LoginPresenter loginPresenter1 = new LoginPresenter(new BaseContact.IViewData() {
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

    public static void start(Context context) {
        context.startActivity(new Intent(context, LoginMultiAc.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ac_login_multi;
    }

    @Override
    protected void initViews() {

    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                LogUtil.e("login");
                loginPresenter.login();
                break;
            case R.id.btn_register:
                LogUtil.e("register");
                loginPresenter.login();

                break;
        }
    }


    @Override
    protected List<? extends NewBasePresenter> getPresenters() {
        return Arrays.asList(loginPresenter, loginPresenter1);
    }
}
