package com.zlx.lxlib.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.SharedElementCallback;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;
import com.zlx.lxlib.R;
import com.zlx.lxlib.base.base_ac.BaseAc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @date: 2019\2\26 0026
 * @author: zlx
 * @description: 模仿微信点击放大图片，拖拽缩回
 */
public class DragViewAc extends BaseAc {
    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.img3)
    ImageView img3;

    private ArrayList<String> list = new ArrayList<>();

    private List<ImageView> imgList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.ac_drag_view;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).transparentStatusBar().init();
        setSharedElementCallback(this);
        list.add("http://img4.duitang.com/uploads/item/201210/06/20121006120433_CZXuC.jpeg");
        list.add("http://69.171.66.103:8888/headImg/20190201/ea7f10bc7398596d475b1caa6f81099e.jpg");
        list.add("http://pic17.nipic.com/20111021/8633866_210108284151_2.jpg");
        imgList.add(img1);
        imgList.add(img2);
        imgList.add(img3);
        for (int i = 0; i < imgList.size(); i++) {
            Glide.with(this).load(list.get(i)).into(imgList.get(i));
        }
    }


    private Bundle mReenterState;

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        mReenterState = new Bundle(data.getExtras());
    }


    /**
     * 接管Activity的setExitSharedElementCallback
     *
     * @param activity
     */
    public void setSharedElementCallback(Activity activity) {
        ActivityCompat.setExitSharedElementCallback(activity, new SharedElementCallback() {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                if (mReenterState != null) {
                    int index = mReenterState.getInt("index", 0);
                    sharedElements.clear();
                    sharedElements.put("tansition_view", imgList.get(index));
                    mReenterState = null;
                }
            }
        });

    }


    @OnClick({R.id.img1, R.id.img2, R.id.img3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img1:
                PhotoBrowseAc.startWithElement(this, list, 0, img1);
                break;
            case R.id.img2:
                PhotoBrowseAc.startWithElement(this, list, 1, img1);
                break;
            case R.id.img3:
                PhotoBrowseAc.startWithElement(this, list, 2, img1);
                break;
        }
    }
}
