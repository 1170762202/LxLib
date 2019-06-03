package com.zlx.lxlib.base.base_ac;

import android.os.Bundle;

import com.zlx.lxlib.base.base_mvp.NewBasePresenter;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

/**
 * @date: 2019\2\26 0026
 * @author: zlx
 * @email: 1170762202@qq.com
 * @description:
 */
public abstract class BaseHttpAc<T extends ViewModel> extends BaseAc {


    protected T viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = bindViewModel();
    }

    protected abstract T bindViewModel();


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
