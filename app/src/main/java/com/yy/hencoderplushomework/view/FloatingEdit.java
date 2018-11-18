package com.yy.hencoderplushomework.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.yy.hencoderplushomework.R;
import com.yy.hencoderplushomework.util.DensityUtil;

/**
 * floating编辑框
 *
 * @author yangyi 2018年11月18日19:10:24
 */
public class FloatingEdit extends android.support.v7.widget.AppCompatEditText {
    private static final float FLOATING_TEXT_SIZE = DensityUtil.dp2px(14);
    private static final int DURATION = 300;
    private static final float TEXT_MARGIN = DensityUtil.dp2px(12);
    private static final int TEXT_LEFT = (int) DensityUtil.dp2px(5);
    private static final int TEXT_RIGHT = (int) DensityUtil.dp2px(5);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Rect rect = new Rect();
    private ObjectAnimator floatingAnimator;
    private float fraction;
    private boolean isFloatingShow;
    private boolean isFloatingUse;

    public FloatingEdit(Context context) {
        super(context);
        init(context, null);
    }

    public FloatingEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void setFloatingUse(boolean floatingUse) {
        isFloatingUse = floatingUse;
        initAnimator();
        initEditPadding();
    }

    public float getFraction() {
        return fraction;
    }

    public void setFraction(float fraction) {
        this.fraction = fraction;
        invalidate();
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FloatingEdit);
        isFloatingUse = typedArray.getBoolean(R.styleable.FloatingEdit_isFloatingUse, true);
        typedArray.recycle();

        initEdit();
        initPaint();
        initEditPadding();
        initAnimator();
    }

    private void initEdit() {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        setLayoutParams(layoutParams);
        setHint("你想说什么？");
        getBackground().getPadding(rect);
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorYellow));
    }

    private void initPaint() {
        paint.setTextSize(FLOATING_TEXT_SIZE);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
    }

    private void initAnimator() {
        if (isFloatingUse) {
            if (floatingAnimator == null) {
                floatingAnimator = ObjectAnimator.ofFloat(this,
                        "fraction", 0f, 1f);
                floatingAnimator.setDuration(DURATION);
            }
            addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (floatingAnimator != null) {
                        if (isFloatingShow && TextUtils.isEmpty(s)) {
                            floatingAnimator.reverse();
                            isFloatingShow = false;
                        }
                        if (!isFloatingShow && !TextUtils.isEmpty(s)) {
                            floatingAnimator.start();
                            isFloatingShow = true;
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        } else {
            floatingAnimator = null;
        }
    }

    private void initEditPadding() {
        if (isFloatingUse) {
            setPadding(TEXT_LEFT, (int) (rect.top + FLOATING_TEXT_SIZE + TEXT_MARGIN), 0, rect.bottom);
        } else {
            setPadding(TEXT_LEFT, rect.top, 0, rect.bottom);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        floatingAnimator = null;
    }

    /**
     * 绘制floatingText
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAlpha((int) (255 * fraction));
        float initPaddingTopValue = rect.top + FLOATING_TEXT_SIZE;
        float paddingAnimatorOffset = initPaddingTopValue * (1 - fraction);
        //初始值+偏移量
        float top = initPaddingTopValue + paddingAnimatorOffset;
        canvas.save();
        canvas.drawText(getHint().toString(),
                TEXT_LEFT,
                top,
                paint);
        canvas.restore();

        canvas.save();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorRed));
        paint.setStrokeWidth(DensityUtil.dp2px(3));
        float lineY = getHeight() - rect.bottom / 2;
        canvas.drawLine(TEXT_LEFT,
                lineY,
                getWidth() - TEXT_RIGHT,
                lineY,
                paint);
        canvas.restore();
    }
}
