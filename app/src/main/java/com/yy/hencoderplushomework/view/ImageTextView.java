package com.yy.hencoderplushomework.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.util.DiffUtil;
import android.util.AttributeSet;
import android.view.View;

import com.yy.hencoderplushomework.R;
import com.yy.hencoderplushomework.util.BitmapUtil;
import com.yy.hencoderplushomework.util.DensityUtil;

/**
 * 图文混合排版
 *
 * @author yangyi 2018年11月11日15:54:50
 */
public class ImageTextView extends View {
    private static final String TEXT = "gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！gakki的盛世美颜！！";
    private static final int IMAGE_TOP_OFFSET = (int) DensityUtil.dp2px(100);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;
    private float[] measuredWidth = new float[1];

    public ImageTextView(Context context) {
        super(context);
    }

    public ImageTextView(Context context,  @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        paint.setTextSize(DensityUtil.dp2px(16));
        bitmap = BitmapUtil.getAvatar(getContext(), (int) DensityUtil.dp2px(300));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, getWidth() - bitmap.getWidth(), IMAGE_TOP_OFFSET, paint);

        int currentIndex;
        float textTop;
        float textBottom;
        float maxWidth;
        float lineOffset = -paint.getFontMetrics().top;
        for (int start = 0; start < TEXT.length(); ) {
            //初始的top或者bottom+行的偏移量
            textTop = paint.getFontMetrics().top + lineOffset;
            textBottom = paint.getFontMetrics().bottom + lineOffset;
            boolean isTextTopInImg =
                    textTop > IMAGE_TOP_OFFSET && textTop < IMAGE_TOP_OFFSET + bitmap.getHeight();
            boolean isTextBottomInImg =
                    textBottom > IMAGE_TOP_OFFSET && textBottom < IMAGE_TOP_OFFSET + bitmap.getHeight();
            //在整张图片范围内
            if (isTextTopInImg || isTextBottomInImg) {
                maxWidth = getWidth() - bitmap.getWidth();
            } else {
                maxWidth = getWidth();
            }
            currentIndex = paint.breakText(TEXT,
                    start,
                    TEXT.length(),
                    true,
                    maxWidth,
                    measuredWidth);
            canvas.drawText(TEXT, start, start + currentIndex, 0, lineOffset, paint);
            start += currentIndex;
            lineOffset += paint.getFontSpacing();
        }
    }
}
