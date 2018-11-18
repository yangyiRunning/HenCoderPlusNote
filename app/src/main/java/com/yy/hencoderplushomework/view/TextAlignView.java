package com.yy.hencoderplushomework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.yy.hencoderplushomework.R;
import com.yy.hencoderplushomework.util.DensityUtil;

/**
 * 文本内容无关大小绝对靠边
 *
 * @author yangyi 2018年11月07日20:11:21
 */
public class TextAlignView extends View {

    private static final String TEXT = "bcdeg";
    private static final String TEXT_2 = "hwxyz";
    private static final String CHINESE_TEXT = "绝对靠左和居顶";
    private static final String CHINESE_TEXT_2 = "绝对靠右和居顶";

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Rect rect = new Rect();

    public TextAlignView(Context context) {
        super(context);
    }

    public TextAlignView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorOrange));
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(DensityUtil.dp2px(50));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.getTextBounds(TEXT, 0, TEXT.length(), rect);
        float fontSpacing = paint.getFontSpacing();
        canvas.drawText(TEXT, -rect.left, -paint.getFontMetrics().ascent, paint);

        paint.setTextSize((DensityUtil.dp2px(16)));
        paint.getTextBounds(CHINESE_TEXT, 0, CHINESE_TEXT.length(), rect);
        canvas.drawText(CHINESE_TEXT, -rect.left, -paint.getFontMetrics().ascent + fontSpacing, paint);

        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setTextSize(DensityUtil.dp2px(35));
        paint.getTextBounds(CHINESE_TEXT_2, 0, CHINESE_TEXT_2.length(), rect);
        float fontSpacing2 = paint.getFontSpacing();
        canvas.drawText(CHINESE_TEXT_2, DensityUtil.getScreenWidth() - CHINESE_TEXT_2.length(),
                -paint.getFontMetrics().ascent, paint);

        paint.setTextSize(DensityUtil.dp2px(20));
        paint.getTextBounds(TEXT_2, 0, TEXT_2.length(), rect);
        canvas.drawText(TEXT_2, DensityUtil.getScreenWidth() - TEXT_2.length(),
                -paint.getFontMetrics().ascent + fontSpacing2, paint);
    }
}
