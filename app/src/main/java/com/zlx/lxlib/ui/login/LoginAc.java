package com.zlx.lxlib.ui.login;

import android.widget.Button;

import com.zlx.lxlib.R;
import com.zlx.lxlib.api.LoginViewModel;
import com.zlx.lxlib.base.base_ac.BaseHttpAc;
import com.zlx.lxlib.base.base_api.bean.MapSet;
import com.zlx.lxlib.base.base_api.view_model.http.HttpViewModel;
import com.zlx.lxlib.base.base_api.view_model.util.ViewModelUtil;
import com.zlx.lxlib.util.Log;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @date: 2019\2\25 0025
 * @author: zlx
 * @description:
 */
public class LoginAc extends BaseHttpAc<LoginViewModel> {

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
        viewModel.login(MapSet.getNullMap()).observe(this, s -> {
            Log.e("onChanged:" + s);
            if (!s.equals(HttpViewModel.request_error)){
                /*逻辑处理*/
            }
        });

    }


    @Override
    protected LoginViewModel bindViewModel() {
        return ViewModelUtil.create(LoginViewModel.class);
    }
}
