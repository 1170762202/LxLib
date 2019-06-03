package com.zlx.lxlib.base.base_api.view_model.util;


import com.zlx.lxlib.MyApp;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @date: 2019\5\29 0029
 * @author: zlx
 * @email: 1170762202@qq.com
 * @description: api 创建器
 */
public class ViewModelUtil {
    public static<T extends ViewModel> T create(Class<T> modelClass) {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(MyApp.instance).create(modelClass);
    }
}
