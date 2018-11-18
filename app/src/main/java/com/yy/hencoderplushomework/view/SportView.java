package com.yy.hencoderplushomework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.yy.hencoderplushomework.R;
import com.yy.hencoderplushomework.util.DensityUtil;

/**
 * 运动表盘
 *
 * @author yangyi 2018年11月07日00:19:58
 */
public class SportView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF rectF = new RectF();
    private static final float RADIUS = DensityUtil.dp2px(180);
    private static int useAngle = 120;
    private static float width = DensityUtil.dp2px(15);
    private static float fontSize = DensityUtil.dp2px(80);
    private static String content = "abcdefg";
    private Typeface typeface;

    public SportView(Context context) {
        super(context);
    }

    public SportView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(width);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorYellow));
        typeface = Typeface.createFromAsset(getContext().getAssets(), "Quicksand-Regular.ttf");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        rectF.set(getWidth() / 2 - RADIUS,
                getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS,
                getHeight() / 2 + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, paint);
        canvas.restore();

        canvas.save();
        paint.setColor(ContextCompat.getColor(getContext(), android.R.color.holo_blue_light));
        canvas.drawArc(rectF, 90 - useAngle / 2 + useAngle + 90,
                360 - useAngle,
                false,
                paint);
        canvas.restore();

        canvas.save();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(fontSize);
        paint.setStrokeWidth(DensityUtil.dp2px(2));
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(typeface);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float ascent = fontMetrics.ascent;
        float descent = fontMetrics.descent;
        float offset = (ascent + descent) / 2;
        canvas.drawText(content, getWidth() / 2, getHeight() / 2 - offset, paint);
        canvas.restore();
    }
}
