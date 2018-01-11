package com.unilife.variety.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.unilife.variety.R;


/**
 * Created by 万启林 on 2015/7/28.
 */
public class RotateImageView extends ImageView {
    private final int MAX_DURATION = 5000;
    private final int MIN_DURATION = 1000;
    private RotateAnimation mCCWAnimation;
    private RotateAnimation mCWAnimation;

    public RotateImageView(Context context) {
        this(context, null);
    }

    public RotateImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCCWAnimation = new RotateAnimation(1440f,0f, Animation.RELATIVE_TO_SELF,
                0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        mCCWAnimation.setDuration(MAX_DURATION);
        mCCWAnimation.setRepeatCount(-1);
        mCCWAnimation.setInterpolator(new LinearInterpolator());
        mCCWAnimation.setFillAfter(true);

        mCWAnimation = new RotateAnimation(0f,1440f, Animation.RELATIVE_TO_SELF,
                0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        mCWAnimation.setDuration(MAX_DURATION);
        mCWAnimation.setRepeatCount(-1);
        mCWAnimation.setInterpolator(new LinearInterpolator());
        mCWAnimation.setFillAfter(true);
    }

    public void ccw() {
        clearAnimation();
        setBackgroundResource(R.drawable.arrow_ccw);
        setVisibility(View.VISIBLE);
        setAnimation(mCCWAnimation);
        startAnimation(mCCWAnimation);
    }

    public void cw() {
        clearAnimation();
        setBackgroundResource(R.drawable.arrow_cw);
        setVisibility(View.VISIBLE);
        setAnimation(mCWAnimation);
        startAnimation(mCWAnimation);
    }

    public void setLevel(int offsetDuration) {
        int duration = MAX_DURATION - offsetDuration;
        duration = duration < MIN_DURATION ? MIN_DURATION : duration;
        mCCWAnimation.setDuration(duration);
        mCWAnimation.setDuration(duration);
    }

    public void hide() {
        setVisibility(View.INVISIBLE);
        clearAnimation();
    }
}
