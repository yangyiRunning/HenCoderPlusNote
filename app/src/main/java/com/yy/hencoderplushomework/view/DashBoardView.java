package com.yy.hencoderplushomework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.view.View;

import com.yy.hencoderplushomework.util.DensityUtil;

/**
 * 仪表盘
 *
 * @author yangyi 2018年11月05日23:39:34
 */
public class DashBoardView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF rectF = new RectF();
    private float radius = DensityUtil.dp2px(150);
    private float strokeWidth = DensityUtil.dp2px(1);
    private double useAngle = 120;
    private int viewerLength = (int) DensityUtil.dp2px(80);
    private float start = (float) (90 - useAngle / 2 + useAngle);
    private float sweep = (float) (360 - useAngle);

    private PathDashPathEffect pathDashPathEffect;

    public DashBoardView(Context context) {
        super(context);
    }

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        rectF.set(getWidth() / 2 - radius,
                getHeight() / 2 - radius,
                getWidth() / 2 + radius,
                getHeight() / 2 + radius);
        //每一个刻度
        Path path = new Path();
        path.addRect(0, 0, DensityUtil.dp2px(2), DensityUtil.dp2px(10), Path.Direction.CW);

        //弧度的总长
        Path arc = new Path();
        arc.addArc(rectF, start, sweep);
        PathMeasure pathMeasure = new PathMeasure();
        pathMeasure.setPath(arc, false);
        float advance = (pathMeasure.getLength() - DensityUtil.dp2px(2)) / 20;
        pathDashPathEffect = new PathDashPathEffect(path, advance, 0, PathDashPathEffect.Style.ROTATE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画盘
        canvas.drawArc(rectF, start, sweep, false, paint);

        //画刻度
        paint.setPathEffect(pathDashPathEffect);
        canvas.drawArc(rectF, start, sweep, false, paint);
        paint.setPathEffect(null);

        //画指针
        float stopX = getWidth() / 2 + (float) (viewerLength * Math.cos(Math.toRadians(getAngle(9))));
        float stopY = getHeight() / 2 + (float) (viewerLength * Math.sin(Math.toRadians(getAngle(9))));
        canvas.drawLine(getWidth() / 2, getHeight() / 2,
                stopX, stopY, paint);
    }

    /**
     * 制定刻度对应的角度
     */
    private float getAngle(int index) {
        double startAngle = 90 - useAngle / 2 + useAngle;
        double deltaAngle = (360 - useAngle) / 20;
        return (float) (startAngle + index * deltaAngle);
    }
}
