package com.lqdbg.wallpaper.index.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.lqdbg.wallpaper.R;
import com.lqdbg.wallpaper.app.Tools;
import com.lqdbg.wallpaper.util.base.BaseAppBarStateChangeListener;
import com.lqdbg.wallpaper.util.base.BaseFragment;
import com.lqdbg.wallpaper.util.state.QMUIStatusBarHelper;
import com.youth.banner.Banner;

public class HomeFragment extends BaseFragment {

    private View root;

    private CollapsingToolbarLayout home_ctl;
    private View home_view;

    private TabLayout home_tab;
    private ViewPager home_vp;
    private Banner home_banner;
    private FragmentActivity activity;
    private AppBarLayout home_appBar;
    private boolean isAppBarOpen;
    private int page;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home,container,false);
        activity=getActivity();
        initView();
        init();
        return root;

    }

    private void init() {
        Tools.setNonHigh(activity,home_view);
        home_tab.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initView() {
        home_ctl = root.findViewById(R.id.home_ctl); // 折叠布局
        home_banner = root.findViewById(R.id.home_banner); //
        home_appBar = root.findViewById(R.id.home_appBar);
        home_vp = root.findViewById(R.id.home_vp);
        home_tab = root.findViewById(R.id.home_tab);
        home_view = root.findViewById(R.id.home_view);

    }
    @Override
    protected void initData() {
        home_appBar.addOnOffsetChangedListener(new BaseAppBarStateChangeListener(){
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if( state == State.EXPANDED ) {
                    //展开状态
//                    banner.startTurning();//开启
                    QMUIStatusBarHelper.setStatusBarDarkMode(activity);
                    isAppBarOpen = true;
                }else if(state == State.COLLAPSED){
                    //折叠状态
//                    banner.stopTurning();
                    QMUIStatusBarHelper.setStatusBarLightMode(activity);
                    isAppBarOpen = false;
                }
            }
        });

    }
}
