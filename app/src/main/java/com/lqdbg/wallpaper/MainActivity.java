package com.lqdbg.wallpaper;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lqdbg.wallpaper.index.home.HomeFragment;
import com.lqdbg.wallpaper.index.me.MeFragment;
import com.lqdbg.wallpaper.index.wallpaper.WallPaperFragment;
import com.lqdbg.wallpaper.util.adapter.PagerAdapter;
import com.lqdbg.wallpaper.util.base.BaseActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {


    private ViewPager mainVp;
    private ArrayList<Fragment> fragments;
    private PagerAdapter pagerAdapter;
    private BottomIdRes[] bottomIdRes;
  public static class  BottomIdRes{
        int selectedImageIdRes,notSelectedImageIdRes;
        LinearLayout main_bar_ll;
        TextView textView;
        ImageView imageView;
        public BottomIdRes(Activity activity, @DrawableRes int selectedImageIdRes , @DrawableRes int notSelectedImageIdRes, @IdRes int main_bar_ll
                , @IdRes int textView, @IdRes int imageView){
            this.selectedImageIdRes=selectedImageIdRes;
            this.notSelectedImageIdRes=notSelectedImageIdRes;
            this.main_bar_ll=activity.findViewById(main_bar_ll);
            this.textView=activity.findViewById(textView);
            this.imageView=activity.findViewById(imageView);
        }
  }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        state(true);
        initView();
        init();
    }
    private void init(){
      fragments=new ArrayList<>();
      fragments.add(new HomeFragment());
      fragments.add(new WallPaperFragment());
      fragments.add(new MeFragment());
      pagerAdapter=new PagerAdapter(getSupportFragmentManager(),fragments);
      mainVp.setAdapter(pagerAdapter);
      mainVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
          int positionId=0;
          @Override
          public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
              BottomIdRes bottomIdRe = bottomIdRes[positionId];
              bottomIdRe.textView.setVisibility(View.GONE);
              bottomIdRe.imageView.setImageResource( bottomIdRe.selectedImageIdRes);
              bottomIdRe = bottomIdRes[position];
              bottomIdRe.textView.setVisibility(View.VISIBLE);
              bottomIdRe.imageView.setImageResource(bottomIdRe.notSelectedImageIdRes);
//              animateIconColor(bottomIdRe.imageView,R.color.black_2,R.color.blue);

              positionId = position;
          }

          @Override
          public void onPageSelected(int position) {

          }

          @Override
          public void onPageScrollStateChanged(int state) {

          }
      });
    }
    private void initView() {
      mainVp=findViewById(R.id.main_vp);
      bottomIdRes=new BottomIdRes[]{
              new BottomIdRes(this,R.drawable.home_icon_s1,R.drawable.home_icon_s1f,R.id.main_bar_menu1,R.id.main_bar_tv1,R.id.main_bar_iv1),
              new BottomIdRes(this,R.drawable.home_icon_s2,R.drawable.home_icon_s2f,R.id.main_bar_menu2,R.id.main_bar_tv2,R.id.main_bar_iv2),
              new BottomIdRes(this,R.drawable.home_icon_s3,R.drawable.home_icon_s3f,R.id.main_bar_menu3,R.id.main_bar_tv3,R.id.main_bar_iv3)
      };
        for (int i = 0; i < bottomIdRes.length; i++) {
            int finalI = i;
            bottomIdRes[i].main_bar_ll.setOnClickListener(v -> mainVp.setCurrentItem(finalI));


        }

  }
    public void animateIconColor(ImageView icon, int defaultColor,int targetColor) {
        // 创建颜色过渡动画
        ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), defaultColor, targetColor);
        colorAnimator.addUpdateListener(animation -> {
            int animatedValue = (int) animation.getAnimatedValue();
            icon.setImageTintList(ColorStateList.valueOf(animatedValue)); // 设置动态颜色
        });
        colorAnimator.setDuration(200); // 设置动画时长
        colorAnimator.start(); // 启动动画
    }

}