package com.lqdbg.wallpaper.index.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lqdbg.wallpaper.R;
import com.lqdbg.wallpaper.util.base.BaseFragment;

public class MeFragment extends BaseFragment {
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_me,container,false);
        return root;
    }

    @Override
    protected void initData() {

    }
}
