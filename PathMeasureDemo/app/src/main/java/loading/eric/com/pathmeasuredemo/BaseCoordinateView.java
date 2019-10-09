
package loading.eric.com.pathmeasuredemo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public abstract class BaseCoordinateView extends View {

    private Paint mGridPaint;
    private Paint mCoordinatePaint;
    // 写字画笔
    private Paint mTextPaint;

    // 坐标颜色
    private int mCoordinateColor;
    private int mGridColor;

    // 网格宽度 50px
    private final int mGridWidth = 50;

    // 坐标线宽度
    private final float mCoordinateLineWidth = 2.5f;
    // 网格宽度
    private final float mGridLineWidth = 1f;
    // 字体大小
    private float mTextSize;

    // 标柱的高度
    private final float mCoordinateFlagHeight = 8f;

    protected float mWidth;
    protected float mHeight;

    public BaseCoordinateView(Context context) {
        this(context, null);
    }

    public BaseCoordinateView(Context context, @androidx.annotation.Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseCoordinateView(Context context, @androidx.annotation.Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BaseCoordinateView(Context context, @androidx.annotation.Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initCoordinate();
        init();
    }

    protected abstract void init();

    /**
     * 初始化坐标、网格和文字的画笔
     */
    private void initCoordinate() {
        mCoordinateColor = Color.BLACK;
        mGridColor = Color.LTGRAY;

        mTextSize = spToPx(10);

        mGridPaint = new Paint();
        mGridPaint.setAntiAlias(true);
        mGridPaint.setColor(mGridColor);
        mGridPaint.setStrokeWidth(mGridLineWidth);

        mCoordinatePaint = new Paint();
        mCoordinatePaint.setAntiAlias(true);
        mCoordinatePaint.setColor(mCoordinateColor);
        mCoordinatePaint.setStrokeWidth(mCoordinateLineWidth);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mCoordinateColor);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(mTextSize);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCoordinate(canvas);

    }

    private void drawCoordinate(Canvas canvas) {
        float halfWidth = mWidth / 2;
        float halfHeight = mHeight / 2;
        canvas.save();
        //移动屏幕到画布的中心
        canvas.translate(halfWidth, halfHeight);

        //画坐标
        canvas.drawLine(-halfWidth, 0, halfWidth, 0, mCoordinatePaint);
        canvas.drawLine(0, -halfHeight, 0, halfHeight, mCoordinatePaint);
        //画网格
        int curWidth = mGridWidth;
        //画竖线
        while (curWidth <= mWidth / 2) {
            canvas.drawLine(curWidth, -halfHeight, curWidth, halfHeight, mGridPaint);
            canvas.drawLine(-curWidth, -halfHeight, -curWidth, halfHeight, mGridPaint);
            //横线标注
            canvas.drawLine(curWidth, -mCoordinateFlagHeight, curWidth, 0, mCoordinatePaint);
            canvas.drawLine(-curWidth, -mCoordinateFlagHeight, -curWidth, 0, mCoordinatePaint);

            if (curWidth % (mGridWidth * 2) == 0) {
                canvas.drawText(String.valueOf(curWidth),curWidth,mTextSize*1.5f,mTextPaint);
                canvas.drawText(String.valueOf(-curWidth),-curWidth,mTextSize*1.5f,mTextPaint);
            }

            curWidth += mGridWidth;
        }
        int currHeight = mGridWidth;
        //画横线
        while (currHeight <= halfHeight) {
            canvas.drawLine(-halfWidth, currHeight, halfWidth, currHeight, mGridPaint);
            canvas.drawLine(-halfWidth, -currHeight, halfWidth, -currHeight, mGridPaint);

            //竖线标注
            canvas.drawLine(0, currHeight, mCoordinateFlagHeight, currHeight, mCoordinatePaint);
            canvas.drawLine(0, -currHeight, mCoordinateFlagHeight, -currHeight, mCoordinatePaint);
            //竖线标注
            if (currHeight % (mGridWidth * 2) == 0) {
                canvas.drawText(String.valueOf(currHeight),-mTextSize*2f,currHeight+mTextSize/2,mTextPaint);
                canvas.drawText(String.valueOf(-currHeight),-mTextSize*2f,-currHeight+mTextSize/2,mTextPaint);
            }

            currHeight += mGridWidth;
        }
        canvas.restore();
    }

    /**
     * 转换 sp 至 px
     *
     * @param spValue sp值
     * @return px值
     */
    protected int spToPx(float spValue) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 转换 dp 至 px
     *
     * @param dpValue dp值
     * @return px值
     */
    protected int dpToPx(float dpValue) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return (int) (dpValue * metrics.density + 0.5f);
    }

}
