package com.yy.hencoderplushomework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;

import com.yy.hencoderplushomework.R;
import com.yy.hencoderplushomework.util.DensityUtil;

/**
 * 多行文字
 *
 * @author yangyi 2018年11月11日15:22:15
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class MultiLineTextView extends View {
    private static final String TEXT = "中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！中华人民共和国万岁！";
    private TextPaint paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private StaticLayout staticLayout;

    public MultiLineTextView(Context context) {
        super(context);
    }

    {
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        paint.setTextSize(DensityUtil.dp2px(16));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        StaticLayout.Builder builder = StaticLayout.Builder.obtain(
                TEXT,
                0,
                TEXT.length(),
                paint,
                getWidth());
        staticLayout = builder.build();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        staticLayout.draw(canvas);
    }
}
