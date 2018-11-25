package com.yy.hencoderplushomework.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * 方形ImageView
 *
 * @author yangyi 2018年11月25日17:46:52
 */
public class SquareImageView extends android.support.v7.widget.AppCompatImageView {
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int squareLength = Math.min(width, height);
        setMeasuredDimension(squareLength, squareLength);
    }
}
