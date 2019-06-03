package com.zlx.lxlib.api;

import com.zlx.lxlib.base.base_api.bean.WebUrl;
import com.zlx.lxlib.base.base_api.view_model.http.HttpViewModel;

import java.util.Map;

import androidx.lifecycle.LiveData;

/**
 * @date: 2019\5\29 0029
 * @author: zlx
 * @email: 1170762202@qq.com
 * @description:
 */
public class LoginViewModel extends HttpViewModel {

    public LiveData<String> login(Map<String, Object> map) {
        return requestPost(WebUrl.URL_LOGIN, map);
    }
}
