package com.mengpeng.mdialog.loading;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mengpeng.mdialog.R;
import com.mengpeng.mdialog.base.BaseLazyDialog;


/**
 * 作者: 孟鹏
 * 时间: 2018/4/13 - 13:08
 * 作用: 加载dialog
 */
public class LoadingDialog extends BaseLazyDialog {

    private RelativeLayout container;
    private ObjectAnimator animator;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                content.setText("加载中");
                handler.sendEmptyMessageDelayed(2, 500);
            } else if (msg.what == 2) {
                content.setText("加载中.");
                handler.sendEmptyMessageDelayed(3, 500);
            } else if (msg.what == 3) {
                content.setText("加载中..");
                handler.sendEmptyMessageDelayed(4, 500);
            } else if (msg.what == 4) {
                content.setText("加载中...");
                handler.sendEmptyMessageDelayed(1, 500);
            }
        }
    };
    private TextView content;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_loading_dialog);

        container = (RelativeLayout) findViewById(R.id.loadingDialog_container);
        content = (TextView) findViewById(R.id.loadingDialog_content);

        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(R.mipmap.loading);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dp2px(60), dp2px(60));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(params);
        layoutParams.setMargins(dp2px(30), dp2px(16), 0, 0);

        imageView.setLayoutParams(layoutParams);

        container.addView(imageView);

        animator = ObjectAnimator.ofFloat(imageView, "rotation", 0, 360);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();

        handler.sendEmptyMessage(1);

    }

    public int dp2px(float dpValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }

    public static void dismissDialog(){

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (animator.isRunning()) {
            animator.cancel();
        }
        if (handler.hasMessages(1)) {
            handler.removeMessages(1);
        }
        if (handler.hasMessages(2)) {
            handler.removeMessages(2);
        }
        if (handler.hasMessages(3)) {
            handler.removeMessages(3);
        }
        if (handler.hasMessages(4)) {
            handler.removeMessages(4);
        }
    }
}
