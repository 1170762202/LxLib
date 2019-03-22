package com.zlx.lxlib.ui.login;

import android.widget.Button;

import com.zlx.lxlib.R;
import com.zlx.lxlib.base.base_ac.BaseAc;
import com.zlx.lxlib.base.base_ac.BaseMvpAc;
import com.zlx.lxlib.base.base_mvp.NewBasePresenter;
import com.zlx.lxlib.base.base_mvp.presenter.BaseContact;
import com.zlx.lxlib.base.base_retrofit.HttpUtil;
import com.zlx.lxlib.base.base_retrofit.MyObserver;
import com.zlx.lxlib.bean.Demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @date: 2019\2\25 0025
 * @author: zlx
 * @description:
 */
public class LoginAc extends BaseAc {

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
        HttpUtil.getDemo(this, new MyObserver<Demo>(this) {
            @Override
            public void onSuccess(Demo demo) {

            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }


}
