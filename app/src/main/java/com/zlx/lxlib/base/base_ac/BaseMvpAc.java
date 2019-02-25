package com.zlx.lxlib.base.base_ac;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zlx.lxlib.base.base_mvp.presenter.BaseContact;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @date: 2019\2\25 0025
 * @author: zlx
 * @description:
 */
public abstract class BaseMvpAc<P extends BaseContact.BasePresenter> extends BaseAc implements BaseContact.IViewData {

    protected P mPresenter;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        attachView();
    }


    /**
     * 在子View中初始化Presenter
     *
     * @return
     */
    protected abstract P initPresenter();



    /**
     * 挂载view
     */
    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 卸载view
     */
    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void onRequestStart() {
        if (mDialog == null) {
            mDialog = new ProgressDialog(this);
        }
        mDialog.show();
    }

    @Override
    public void onRequestError(String s) {

    }

    @Override
    public void onRequestFinished() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachView();

    }
}
