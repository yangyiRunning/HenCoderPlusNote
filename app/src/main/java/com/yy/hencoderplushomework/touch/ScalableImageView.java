package com.yy.hencoderplushomework.touch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yy.hencoderplushomework.util.BitmapUtil;
import com.yy.hencoderplushomework.util.DensityUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 双向滑动的可收缩的imageView
 */
public class ScalableImageView extends View {

    private static int bitmapWidth;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;

    public ScalableImageView(Context context) {
        super(context);
    }

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmapWidth = (int) DensityUtil.dp2px(250);
        bitmap = BitmapUtil.getAvatar(getContext(), bitmapWidth);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap,
                (getWidth() - bitmap.getWidth()) / 2f,
                (getHeight() - bitmap.getHeight()) / 2f,
                paint);
    }
}
