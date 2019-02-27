# LxLib（有意向一起维护的联系qq:1170762202）
#### 1.基于RxJava+Retrofit+MVP封装了BaseMvp,继承NewBasePresenter快速实现接口的编写

#### 2.等等，还没完哦，陆续维护中............

# BaseMvp

新建activity继承BaseMvpAc,重写接口请求方法，示例：

```JAVA
public class LoginAc extends BaseMvpAc {

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
        loginPresenter.login();
    }

    /**
     * 新建一个presenter继承NewBasePresenter重写对应的方法,下次新建接口直接复制，改个url地址就ok
     */
    private LoginPresenter loginPresenter = new LoginPresenter(new BaseContact.IViewData() {
        @Override
        public Map<String, Object> getRequestMap() {
            return new HashMap<>();
        }

        @Override
        public void onRequestStart() {

        }

        @Override
        public void onRequestSuccess(String s) {

        }

        @Override
        public void onRequestFailed(String s) {

        }

        @Override
        public void onRequestError(String s) {

        }

        @Override
        public void onRequestFinished() {

        }
    });

    /**
     * 返回presenter 列表给mvpac，ondestroy时解绑 pv防止内存泄漏
     *
     * @return
     */
    @Override
    protected List<? extends NewBasePresenter> getPresenters() {
        return Arrays.asList(loginPresenter);
    }
}
```









