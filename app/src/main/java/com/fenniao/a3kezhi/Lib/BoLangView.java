package com.fenniao.a3kezhi.Lib;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.fenniao.a3kezhi.R;

/**
 * Created by Administrator on 2017/5/16 0016.
 */

public class BoLangView extends View {
    private int mScreenWidth;
    private int mScreenHeight;
    private int mCenterY;
    private int mWaveCount;
    private int mWaveLength;
    private int offsetFirst;
    private int offsetSecond;

    private Path mPathFirst;
    private Paint mPaintFirst;
    private Path mPathSecond;
    private Paint mPaintSecond;

    private ValueAnimator animatorFirst;
    private ValueAnimator animatorSecond;

    public BoLangView(Context context) {
        super(context);
    }

    public BoLangView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BoLangView);
        mPaintFirst = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintFirst.setStyle(Paint.Style.FILL);
        mPaintFirst.setColor(typedArray.getColor(R.styleable.BoLangView_first_color, Color.LTGRAY));
        mPaintSecond = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintSecond.setStyle(Paint.Style.FILL);
        mPaintSecond.setColor(typedArray.getColor(R.styleable.BoLangView_second_color, Color.LTGRAY));

    }

    public BoLangView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 需要计算出屏幕能容纳多少个波形
        mPathFirst = new Path();
        mPathSecond = new Path();
        mScreenHeight = h;
        mScreenWidth = w;
        mCenterY = h / 2;
        mWaveCount = 1;
        mWaveLength = w / mWaveCount;
        startAnim();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPathFirst.reset();
        mPathSecond.reset();
        mPathFirst.moveTo(-0, mCenterY);
        mPathSecond.moveTo(-0, mCenterY);
        for (int i = 0; i < mWaveCount + 1; i++) {
            mPathFirst.quadTo(-mWaveLength * 3 / 4 + i * mWaveLength + offsetFirst, mCenterY + mScreenHeight / 2, -mWaveLength / 2 + i * mWaveLength + offsetFirst, mCenterY);
            mPathFirst.quadTo(-mWaveLength / 4 + i * mWaveLength + offsetFirst, mCenterY - mScreenHeight / 2, i * mWaveLength + offsetFirst, mCenterY);
            mPathSecond.quadTo(-mWaveLength * 3 / 4 + i * mWaveLength + offsetSecond, mCenterY - mScreenHeight / 3, -mWaveLength / 2 + i * mWaveLength + offsetSecond, mCenterY);
            mPathSecond.quadTo(-mWaveLength / 4 + i * mWaveLength + offsetSecond, mCenterY + mScreenHeight / 3, i * mWaveLength + offsetSecond, mCenterY);
        }
        mPathFirst.lineTo(mScreenWidth, mScreenHeight);
        mPathFirst.lineTo(0, mScreenHeight);
        mPathFirst.close();
        mPathSecond.lineTo(mScreenWidth, mScreenHeight);
        mPathSecond.lineTo(0, mScreenHeight);
        mPathSecond.close();
        canvas.drawPath(mPathFirst, mPaintFirst);
        canvas.drawPath(mPathSecond, mPaintSecond);

    }

    public void startAnim() {
        animatorFirst = ValueAnimator.ofInt(0, mWaveLength);
        animatorFirst.setDuration(2000);
        animatorFirst.setInterpolator(new LinearInterpolator());
        animatorFirst.setRepeatCount(ValueAnimator.INFINITE);
        animatorFirst.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offsetFirst = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animatorFirst.start();

        animatorSecond = ValueAnimator.ofInt(0, mWaveLength);
        animatorSecond.setDuration(2500);
        animatorSecond.setInterpolator(new LinearInterpolator());
        animatorSecond.setRepeatCount(ValueAnimator.INFINITE);
        animatorSecond.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offsetSecond = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animatorSecond.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animatorFirst.cancel();
        animatorSecond.cancel();
    }
}