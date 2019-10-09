/*
 * ==============================================================
 *   Copyright (C) China TSP Company Limited 2019
 * ==============================================================
 *
 */

package loading.eric.com.pathmeasuredemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

public class PathMeasureView extends BaseCoordinateView {
    private Paint mPaint,mTextPaint;
    public PathMeasureView(Context context) {
        super(context);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(2.5f);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint();
        mTextPaint.setStrokeWidth(1f);
        mTextPaint.setColor(Color.BLUE);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setTextSize(16);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        Path path = new Path();

        /*path.moveTo(-100,-100);
        path.rLineTo(200,0);
        path.rLineTo(0,200);
        path.rLineTo(-200,0);
        path.rLineTo(0,-200);*/
        path.addOval(-200,-200,200,200,Path.Direction.CCW);
        canvas.drawPath(path,mPaint);
        canvas.drawTextOnPath("这是测试。。。。。。。。。。。。。",path,50,0,mTextPaint);
    }


}
