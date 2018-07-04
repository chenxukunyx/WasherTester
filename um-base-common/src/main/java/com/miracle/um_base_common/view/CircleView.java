package com.miracle.um_base_common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.miracle.um_base_common.R;

/**
 * Created with Android Studio
 *
 * @author: chenxukun
 * @date: 2018/5/31
 * @time: 上午11:32
 * @fuction:
 */
public class CircleView extends View{

    private static final int Default_Ring_Width = 25;
    private Context mContext;
    private Paint mLingPaint;
    private Paint mRingPaint;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        mLingPaint = new Paint();
        mLingPaint.setAntiAlias(true);
        mLingPaint.setColor(mContext.getResources().getColor(R.color.line_color));
        mLingPaint.setDither(true);
        mLingPaint.setStyle(Paint.Style.STROKE);
        mLingPaint.setStrokeWidth(2);

        mRingPaint = new Paint(mLingPaint);
        mRingPaint.setColor(mContext.getResources().getColor(R.color.ring_color));
        mRingPaint.setStrokeWidth(Default_Ring_Width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int center = getWidth() / 2;
        int radius = center - Default_Ring_Width - 2;
        canvas.drawCircle(center, center, radius, mLingPaint);
        canvas.drawCircle(center, center,radius - 2, mRingPaint);
    }
}
