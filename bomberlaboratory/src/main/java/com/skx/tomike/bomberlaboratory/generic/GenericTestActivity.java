package com.skx.tomike.bomberlaboratory.generic;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.skx.tomike.bomberlaboratory.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

/**
 * 描述 : 泛型demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/23 9:56 AM
 */
public class GenericTestActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("泛型demo").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_generic_test;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test1();
    }

    private void test1() {

    }
}
