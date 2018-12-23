package com.yy.hencoderplushomework.touch;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.yy.hencoderplushomework.R;

/**
 * 触摸事件模拟点击事件
 *
 * @author yangyi 2018年12月23日23:07:38
 */
public class TouchPerClickView extends View {

    public TouchPerClickView(Context context) {
        super(context);
    }

    public TouchPerClickView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorYellow));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            performClick();
            Toast.makeText(getContext(), "UP", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
