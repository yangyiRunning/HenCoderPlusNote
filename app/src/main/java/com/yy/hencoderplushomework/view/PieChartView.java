package com.yy.hencoderplushomework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.yy.hencoderplushomework.R;
import com.yy.hencoderplushomework.util.DensityUtil;

/**
 * 饼图
 *
 * @author yangyi 2018年11月05日22:04:37
 * <p>
 * email:yangyirunning@163.com
 */
public class PieChartView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF rectF = new RectF();
    private int radius = (int) DensityUtil.dp2px(150);
    private int[] angles = {80, 20, 30, 60, 100, 70};
    private int[] colors = {
            R.color.colorAccent,
            R.color.colorOrange,
            android.R.color.holo_blue_dark,
            android.R.color.holo_red_dark,
            R.color.colorPrimary,
            R.color.colorYellow
    };
    private float[] distances = {DensityUtil.dp2px(20), DensityUtil.dp2px(30)};
    private int[] transformIndexs = {3, 1};

    public PieChartView(Context context) {
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        rectF.set(getWidth() / 2 - radius,
                getHeight() / 2 - radius,
                getWidth() / 2 + radius,
                getHeight() / 2 + radius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int currentAngle = 0;
        for (int i = 0; i < angles.length; i++) {
            paint.setColor(ContextCompat.getColor(getContext(), colors[i]));
            canvas.save();
            if (transformIndexs[0] == i) {
                double radian = Math.toRadians(currentAngle + angles[i] / 2);
                float x = (float) (distances[0] * Math.cos(radian));
                float y = (float) (distances[0] * Math.sin(radian));
                canvas.translate(x, y);
            }
            if (transformIndexs[1] == i) {
                double radian = Math.toRadians(currentAngle + angles[i] / 2);
                float x = (float) (distances[1] * Math.cos(radian));
                float y = (float) (distances[1] * Math.sin(radian));
                canvas.translate(x, y);
            }
            canvas.drawArc(rectF, currentAngle, angles[i], true, paint);
            canvas.restore();
            currentAngle += angles[i];
        }
    }
}
