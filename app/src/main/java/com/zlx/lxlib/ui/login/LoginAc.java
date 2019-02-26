package com.zlx.lxlib.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.zlx.lxlib.R;
import com.zlx.lxlib.base.base_ac.BaseMvpAc;
import com.zlx.lxlib.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @date: 2019\2\25 0025
 * @author: zlx
 * @description:
 */
public class LoginAc extends BaseMvpAc<LoginPresenter> {

    @BindView(R.id.btn_login)
    Button btnLogin;

    public static void start(Context context) {
        context.startActivity(new Intent(context, LoginAc.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }


    @Override
    public void onRequestSuccess(String s) {
    }

    @Override
    public void onRequestFailed(String s) {

    }

    @Override
    public void onRequestError(String s) {
        super.onRequestError(s);
    }

    @Override
    public Map<String, Object> getRequestMap() {
        return new HashMap<>();
    }


    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        LogUtil.e("login");
        mPresenter.login();
    }
}
