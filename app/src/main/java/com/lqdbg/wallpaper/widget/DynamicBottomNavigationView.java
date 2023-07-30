package com.lqdbg.wallpaper.widget;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lqdbg.wallpaper.R;

public class DynamicBottomNavigationView extends BottomNavigationView {

    private int selectedColor;
    private int defaultColor;

    public DynamicBottomNavigationView(Context context) {
        super(context);
        init();
    }

    public DynamicBottomNavigationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DynamicBottomNavigationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 初始化选中和未选中状态下的颜色
        selectedColor = getResources().getColor(R.color.blue);
        defaultColor = getResources().getColor(R.color.black_2);
    }

    // 设置选中和未选中状态下的颜色
    public void setIconColors(int selectedColor, int defaultColor) {
        this.selectedColor = selectedColor;
        this.defaultColor = defaultColor;
    }

    // 底部导航栏图标填充颜色动画
    public void animateIconColor(ImageView icon, int targetColor) {
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
