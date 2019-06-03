# LxLib
#### 1.基于RxJava+Retrofit+ViewModel封装了BaseHttpAc,继承并快速实现接口的编写

#### 2.等等，还没完哦，陆续维护中............

#### 谷歌在2018年的I/O大会上推出了[Jetpack](https://developer.android.google.cn/topic/libraries/architecture/viewmodel)的概念，意图在于统一框架与UI组件。所以我也将项目架构往这一概念上靠齐。

# step1.新建LoginViewModel extends HttpViewModel

```JAVA
public class LoginViewModel extends HttpViewModel {

    public LiveData<String> login(Map<String, Object> map) {
        return requestPost(WebUrl.URL_LOGIN, map);
    }
}
```

# step2.新建LoginAc继承BaseHttpAc,重写接口请求方法，示例：

```JAVA
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
```

#### 扩展用法

##### 场景1.根据后台返回code进行不同的逻辑处理

修改BaseObserver onNext 方法

```
    public void onNext(String response) {
        Log.e(TAG, "onNext: " + response);

      //在这边对 基础数据 进行统一处理  举个例子：
        BaseBean baseResponse = JSON.parseObject(response, BaseBean.class);
        if (baseResponse.getCode() == 200) {
            onSuccess(response);
        } else {
            onFailure(null, HttpViewModel.request_error);
        }
    }
```
onFailure(Throwable e, String errorMsg) 方法中
errorMsg 可以自定义为HttpViewModel 中的静态变量，对应相应的逻辑处理，并在接口回调处判断，如上示例所书 










