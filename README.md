# LxLib
#### 1.基于RxJava+Retrofit+ViewModel封装了BaseHttpAc,继承并快速实现接口的编写

#### 2.等等，还没完哦，陆续维护中............

#### 谷歌在2018年的I/O大会上推出了[Jetpack](https://developer.android.google.cn/guide/)的概念，意图在于统一框架与UI组件。所以我也将项目架构往这一概念上靠齐。

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
        });

    }


    @Override
    protected LoginViewModel bindViewModel() {
        return ViewModelUtil.create(LoginViewModel.class);
    }
}
```









