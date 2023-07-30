package com.lqdbg.wallpaper.util.base;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.lqdbg.wallpaper.util.state.SbActivity;
import com.lqdbg.wallpaper.util.state.SbTools;

public class BaseActivity extends SbActivity {
    private Toast toast;
    private OnBackEvent onBackEvent;
    private Intent intent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public Toast showToast(String msg){
       if (toast!=null) toast.cancel();
       toast=Toast.makeText(this,msg,Toast.LENGTH_LONG);
       toast.show();
       return toast;
   }
   public  interface OnBackEvent{
       void  backEvent();
   }
    public void showLog(String tag,String msg){
        Log.i(tag, msg);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if (item.getItemId()==android.R.id.home||onBackEvent!=null)
           onBackEvent.backEvent();
       return super.onOptionsItemSelected(item);
    }

  public void state( boolean isBlack){
      if (isBlack){
          SbTools.with(this)
                  .setSbFontColorBlack()
                  .setSTSBgColor(Color.TRANSPARENT)
                  .init();
      }else{
//          SbTools.with(this)
//                  .setSbFontColorBlack()
//                  .setSTSBgColor(Color.TRANSPARENT)
//                  .init();
      }

  }
    /**
     *
     * @param title   主标题
     * @param subTitle  副标题
     * @param onBackEvent  设置返回键监听
     * @param isShow  是否显示返回键
     * @return
     */
    public ActionBar setTitleBar(String title,String subTitle,OnBackEvent onBackEvent ,boolean isShow){
    ActionBar actionBar=getSupportActionBar();
    if (actionBar!=null){
        actionBar.setDisplayHomeAsUpEnabled(isShow);
        if (title!=null){
            actionBar.setTitle(title);
            actionBar.setSubtitle(subTitle);
            this.onBackEvent=onBackEvent;
        }
    }
    return actionBar;
}


    public void setContentView(@LayoutRes  int layoutResID) {
        super.setContentView(layoutResID);

    }

    /**
     * Activity 接收信息
     */
    public String getStringExtra(String name) {
        intent = getIntent();
        return intent.getStringExtra(name);
    }

    public int getIntExtra(String name, int defaultValue) {
        intent = getIntent();
        return intent.getIntExtra(name, defaultValue);
    }

    public float getFloatExtra(String name, float defaultValue) {
        intent = getIntent();
        return intent.getFloatExtra(name, defaultValue);
    }


    public <T> T getSerializableExtra(String name,Class<T> cls) {
        return  (T) getIntent().getSerializableExtra(name);
    }
}
