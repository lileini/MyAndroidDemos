package com.example.android.viewdragerhelperdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * author: Eric_Li
 * date:   On 2018/6/22
 */
public class VertailDragerView extends ViewGroup {

    private View contentView;
    private View dragerView;

    private int mCurTop = 0;//当前拖拽view的位置
    private ViewDragHelper mViewDragHelper;

    public VertailDragerView(Context context) {
        super(context);
        init();
    }

    public VertailDragerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
         mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragerHelperCallback());
         mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_TOP);
    }

    public VertailDragerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //先让自身测量
//        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
//        int measuredHeight = getMeasuredHeight();
//        int measuredWidth = getMeasuredWidth();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heigthMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        contentView = getChildAt(0);
        dragerView = getChildAt(1);
        contentView.measure(getChildMeasureSpec(contentView, widthMode, width), getChildMeasureSpec(contentView, heigthMode, height));
        dragerView.measure(getChildMeasureSpec(dragerView, widthMode, width), getChildMeasureSpec(dragerView, heigthMode, height));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private int getChildMeasureSpec(View childView, int widthMode, int width) {
        MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();
        int childWidthMeasureSpec;
        switch (layoutParams.width) {
            case MarginLayoutParams.MATCH_PARENT:

                if (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST) {
                    childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width /*- usedWidth*/, MeasureSpec.EXACTLY);
                } else {
                    childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                }
                break;
            case MarginLayoutParams.WRAP_CONTENT:
                if (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST) {
                    childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width/*- usedWidth*/, MeasureSpec.AT_MOST);
                } else {
                    childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);

                }
                break;
            default:
                childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width/*- usedWidth*/, MeasureSpec.EXACTLY);
                break;
        }
        return childWidthMeasureSpec;

    }


    private int measureWidth(int measureSpec, int contentWidth) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = contentWidth + getPaddingLeft() + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        result = Math.max(result, getSuggestedMinimumWidth());
        return result;
    }

    private int measureHeight(int measureSpec, int contentHeight) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = contentHeight + getPaddingTop() + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        result = Math.max(result, getSuggestedMinimumHeight());
        return result;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        MarginLayoutParams lp = (MarginLayoutParams) contentView.getLayoutParams();
        contentView.layout(lp.leftMargin, lp.topMargin, contentView.getMeasuredWidth() + lp.leftMargin, lp.topMargin + contentView.getMeasuredHeight());
        lp = (MarginLayoutParams) dragerView.getLayoutParams();
        dragerView.layout(lp.leftMargin, mCurTop + lp.topMargin, dragerView.getMeasuredWidth() + lp.leftMargin, mCurTop + lp.topMargin + dragerView.getMeasuredHeight());
    }

    class ViewDragerHelperCallback extends ViewDragHelper.Callback {

        /**
         * 设置需要拖拽的view
         * @param child
         * @param pointerId
         * @return
         */
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            return child == dragerView;
        }


        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            //setEdgeTrackingEnabled设置的边界滑动时触发
            //captureChildView是为了让tryCaptureView返回false依旧生效
            if (edgeFlags == ViewDragHelper.EDGE_TOP){
                mViewDragHelper.captureChildView(dragerView,pointerId);

            }
        }

        /**
         * 当边缘触摸的时候触发
         * @param edgeFlags
         * @param pointerId
         */
        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            super.onEdgeTouched(edgeFlags, pointerId);
        }

        /**
         * 捕获垂直方向的位置
         * @param child
         * @param top
         * @param dy
         * @return
         */
        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            //手指触摸移动时实时回调, top表示要到的y位置
            //保证手指挪动时只能向上，向下最大到0 范围控制到（0到 -dragerView.getHeight()）
            return Math.max(Math.min(top,0),-dragerView.getHeight());
        }

        /**
         * 当view释放的时候需要自动处理view的滚动
         * @param releasedChild
         * @param xvel
         * @param yvel
         */
        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            //手指释放时回调
            super.onViewReleased(releasedChild, xvel, yvel);
            float percent = (releasedChild.getHeight()+ releasedChild.getTop())/(releasedChild.getHeight()*1.0f);
            int finalTop = yvel>0 && percent>0.5f?0:-releasedChild.getHeight();
            mViewDragHelper.settleCapturedViewAt(releasedChild.getLeft(),finalTop);
            invalidate();

        }

        /**
         * view位置改变的时候回调
         * @param changedView
         * @param left
         * @param top
         * @param dx
         * @param dy
         */
        @Override
        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
//            处理view垂直距离
            mCurTop = top;
            requestLayout();
        }

        /**
         * 处理拖拽view垂直距离的变化范围
         * @param child
         * @return
         */
        @Override
        public int getViewVerticalDragRange(@NonNull View child) {

            if (child == dragerView){
                return child.getHeight();
            }
            return 0;

        }
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /**
     * 这里必须交给viewdragerhelper来处理触摸事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    /**
     * 计算滚动的时候回调
     */
    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)){
            invalidate();
        }
    }
}
