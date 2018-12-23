package com.yy.hencoderplushomework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.yy.hencoderplushomework.util.DensityUtil;

/**
 * 圆形View
 *
 * @author yangyi 2018年11月25日17:58:27
 */
public class CircleImageView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final static int RADIUS = (int) DensityUtil.dp2px(180);

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStrokeWidth(DensityUtil.dp2px(8));
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = (int) DensityUtil.dp2px(50);
        int height = (int) DensityUtil.dp2px(40);
        //修正计算出来的，准备设置的宽和高，让其符合控件调用者的要求
        int finalWidth = resolveSize(width, widthMeasureSpec);
        int finalHeight = resolveSize(height, heightMeasureSpec);
//        setMeasuredDimension(width, height);
        setMeasuredDimension(finalWidth, finalHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        canvas.drawCircle(getWidth() / 2,
                getHeight() / 2,
                RADIUS,
                paint);
    }
}
