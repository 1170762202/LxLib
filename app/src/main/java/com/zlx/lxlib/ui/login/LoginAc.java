package com.zlx.lxlib.ui.login;

import android.os.Build;
import android.widget.Button;

import com.zlx.lxlib.R;
import com.zlx.lxlib.api.LoginViewModel;
import com.zlx.lxlib.base.base_ac.BaseHttpAc;
import com.zlx.lxlib.base.base_api.view_model.util.ViewModelUtil;
import com.zlx.lxlib.util.Log;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, Object> map = new HashMap<>();
        map.put("wxCode", "081ZytGZ0em4bX1DUUFZ09rDGZ0ZytGB");
        map.put("deviceType", 1);

        map.put("deviceNo", "35/7/4/9/4/1/6/8/4/4/7/2/4/4/");
        map.put("deviceName", Build.MODEL);
        map.put("deviceVersion", Build.VERSION.RELEASE);

        viewModel.login(map).observe(this, s -> Log.e("onChanged:" + s));

    }


    @Override
    protected LoginViewModel bindViewModel() {
        return ViewModelUtil.create(LoginViewModel.class);
    }
}
