/*
 * ==============================================================
 *   Copyright (C) China TSP Company Limited 2019
 * ==============================================================
 *
 */

package com.eric.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * 中间的textview宽度自适应不能让后面的控件显示不出来
 * 暂时没写margin这些
 */
public class AutoMiddleLinearLayout extends LinearLayout {
    private static final String TAG = "AutoMiddleLinearLayout";

    public AutoMiddleLinearLayout(Context context) {
        super(context);
    }

    public AutoMiddleLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoMiddleLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AutoMiddleLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChild(getChildAt(2), widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        int width = getMeasuredWidth();
        int allChildWidth = 0;
        for (int i = 0; i < count; ++i) {
            final View child = getChildAt(i);
            int measuredWidth = child.getMeasuredWidth();
            allChildWidth += measuredWidth;
        }
        Log.d(TAG, "onMeasure: allChildWidth= " + allChildWidth + ",width= " + width);
        if (allChildWidth > width) {
            View middleView = getChildAt(1);
            int middleChildWidth = width - (allChildWidth - middleView.getMeasuredWidth());
            ViewGroup.LayoutParams layoutParams = middleView.getLayoutParams();
            layoutParams.width = middleChildWidth;
            measureChild(middleView, widthMeasureSpec, heightMeasureSpec);
        }

    }
}
