package com.yy.hencoderplushomework.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 流式布局(标签布局)
 *
 * @author yangyi 2019年01月17日10:04:08
 */
public class TagLayout extends ViewGroup {
    private List<Rect> rectList = new ArrayList<>();

    public TagLayout(Context context) {
        super(context);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 关键的测量其实就是两步:
     * 1. 每个子view测量
     * 2. 针对自己本身的测量
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //1. 子View测量
        //整个控件使用了的宽度和高度
        int widthUsed = 0;
        int heightUsed = 0;
        //每行中最高的那个view
        int lineMaxHeight = 0;
        //测量出来的控件的宽度
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        //每行子view使用了的宽度和高度
        int lineWidthUsed = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            //从0开始，给予其足够的宽度来进行子view的测量
            measureChildWithMargins(child, widthMeasureSpec, 0,
                    heightMeasureSpec, heightUsed);
            //当已用的宽度+子view的测量的宽度>本身测得的宽度时
            if (specMode != MeasureSpec.UNSPECIFIED &&
                    lineWidthUsed + child.getMeasuredWidth() > specWidth) {
                //回车
                lineWidthUsed = 0;
                //换行
                heightUsed += lineMaxHeight;
                lineMaxHeight = 0;
                measureChildWithMargins(child, widthMeasureSpec, 0,
                        heightMeasureSpec, heightUsed);
            }
            //将每一个左上右下的参数收集起来
            Rect rect;
            if (rectList.size() <= i) {
                rect = new Rect();
                rectList.add(rect);
            } else {
                rect = rectList.get(i);
            }
            //左上右下，右和下都是加上测量出来的宽度即可
            rect.set(lineWidthUsed,
                    heightUsed,
                    lineWidthUsed + child.getMeasuredWidth(),
                    heightUsed + child.getMeasuredHeight());
            lineWidthUsed += child.getMeasuredWidth();
            widthUsed = Math.max(widthUsed, lineWidthUsed);
            lineMaxHeight = Math.max(lineMaxHeight, child.getMeasuredHeight());
        }

        //2. 本身自己的尺寸
        //本身的宽度就是用掉的宽度，本身的高度就是所有的子view当中最高的那个
        int width = widthUsed;
        int height = heightUsed + lineMaxHeight;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Rect rect = rectList.get(i);
            child.layout(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
