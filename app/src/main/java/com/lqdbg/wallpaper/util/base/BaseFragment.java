package com.lqdbg.wallpaper.util.base;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public abstract class BaseFragment extends Fragment {
    protected View root;
    private Toast toast;

    public Activity mActivity;


    protected boolean isVisible = false; //是否可见
    private boolean isPrepared = false;//是否初始化完成
    private boolean isFirst = true; //是否第一次加载
    FragmentTransaction ab;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (root == null) {
            return;
        }
        if (getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
            onInvisible();
        }

    }

    public Toast showToast(String msg){
        if (toast!=null) toast.cancel();
        toast=Toast.makeText(mActivity,msg,Toast.LENGTH_LONG);
        toast.show();
        return toast;
    }
    public void showLog(String tag,String msg){
        Log.i(tag, msg);
    }
    protected abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (root==null){
            root=initView(inflater,container,savedInstanceState);
            mActivity=getActivity();
        }
        return root;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup) root.getParent()).removeView(root);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isVisible = false;
        isPrepared = false;
        isFirst = true;
        root = null;
    }
    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint())
            setUserVisibleHint(true);

    }
    /**
     * 懒加载
     */
    private void lazyLoad() {

        if (isPrepared && isVisible && !isFirst)
            onDisplay();

        if (!isPrepared || !isVisible || !isFirst) {
            return;
        }
        initData();
        isFirst = false;
    }

    protected abstract void initData();

    protected void onDisplay(){}
    /**
     * 获取数据
     */


    /**
     * 不可见时调用
     */
    protected void onInvisible(){}
}
