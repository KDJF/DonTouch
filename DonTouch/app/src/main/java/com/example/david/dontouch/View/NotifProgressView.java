package com.example.david.dontouch.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.david.dontouch.R;


/**
 * Created by david on 2017/4/21.
 */

public class NotifProgressView extends View {

    // 画实心圆的画笔
    private Paint mCirclePaint;
    // 画圆环的画笔
    private Paint mRingPaint;
    // 画字体的画笔
    private Paint mTextPaint;
    // 圆形颜色
    private int mCircleColor;
    // 圆环颜色
    private int mRingColor;
    // 半径
    private float mRadius;
    // 圆环半径
    private float mRingRadius;
    // 圆环宽度
    private float mStrokeWidth;
    // 圆心x坐标
    private int mXCenter;
    // 圆心y坐标
    private int mYCenter;
    // 字的长度
    private float mTxtWidth;
    // 字的高度
    private float mTxtHeight;
    // 总进度
    private int mTotalProgress = 100;
    // 当前进度
    private int mProgress;
    // 显示总时间
    private double totalMin = 30;
    // 显示已用时间
    private double currentMin = 20;

    public NotifProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initVariable();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.NotifProgressView, 0, 0);
        mRadius = typeArray.getDimension(R.styleable.NotifProgressView_radius, 80);
        mStrokeWidth = typeArray.getDimension(R.styleable.NotifProgressView_strokeWidth, 20);
        mCircleColor = typeArray.getColor(R.styleable.NotifProgressView_circleColor, 0xFFFFFFFF);
        mRingColor = typeArray.getColor(R.styleable.NotifProgressView_ringColor, 0xFFFFFFFF);

        mRingRadius = mRadius + mStrokeWidth / 2;
    }

    private void initVariable() {
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStyle(Paint.Style.FILL);

        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);
        mRingPaint.setColor(mRingColor);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setStrokeWidth(mStrokeWidth);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setARGB(255, 255, 255, 255);
        mTextPaint.setTextSize(mRadius / 4);

        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        mTxtHeight = (int) Math.ceil(fm.descent - fm.ascent);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        mXCenter = getWidth() / 2;
        mYCenter = getHeight() / 2;

        canvas.drawCircle(mXCenter, mYCenter, mRadius, mCirclePaint);

        if (mProgress > 0 ) {
            RectF oval = new RectF();
            oval.left = (mXCenter - mRingRadius);
            oval.top = (mYCenter - mRingRadius);
            oval.right = mRingRadius * 2 + (mXCenter - mRingRadius);
            oval.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
            canvas.drawArc(oval, -90, ((float)mProgress / mTotalProgress) * 360, false, mRingPaint);
            String txt = currentMin + " / " + totalMin;
            mTxtWidth = mTextPaint.measureText(txt, 0, txt.length());
            canvas.drawText(txt, mXCenter - mTxtWidth / 2, mYCenter + mTxtHeight / 4, mTextPaint);
        }
    }

    public void setTotalMin(double text) {
        totalMin = text;
        updateProgress(currentMin, totalMin);
    }

    public double getTotalMin() {
        return totalMin;
    }

    public double getCurrentMin() {
        return currentMin;
    }

    public void setCurrentMin(double text) {
        currentMin = text;
        postInvalidate();
    }

    public void setProgress(int progress) {
        mProgress = progress;
        postInvalidate();
    }

    private void updateProgress(double cur, double tot) {
        mProgress = (int)(cur / tot * 100);
        postInvalidate();
    }
}
