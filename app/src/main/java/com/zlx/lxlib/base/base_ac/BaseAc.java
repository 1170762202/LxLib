package com.zlx.lxlib.base.base_ac;

import android.os.Bundle;

import com.zlx.lxlib.base.base_manage.ActivityManage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @date: 2019\2\25 0025
 * @author: zlx
 * @description:
 */
public abstract class BaseAc extends AppCompatActivity {

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ActivityManage.addActivity(this);
        mUnBinder = ButterKnife.bind(this);
        initViews();
    }

    protected abstract int getLayoutId();

    /**
     * 初始化View
     */
    protected abstract void initViews();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != Unbinder.EMPTY) {
            mUnBinder.unbind();
        }
        ActivityManage.finishActivity(this);
    }
}
