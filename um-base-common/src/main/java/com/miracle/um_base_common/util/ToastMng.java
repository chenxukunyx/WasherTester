package com.miracle.um_base_common.util;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.unilife.common.UmInit;

public class ToastMng {
    private static ToastMng instance;
    private TextView mToastView;
    private Toast mToast;

    public ToastMng() {
        mToast = Toast.makeText(UmInit.getInstance().getContext(), "", Toast.LENGTH_SHORT);
//        mToast.setGravity(Gravity.CENTER, 0, SystemUtils.dip2px(UMApplication.getInstance(), 220));
//        View view = LayoutInflater.from(UMApplication.getInstance()).inflate(R.layout.common_toast, null);
//        mToastView = (TextView) view.findViewById(R.id.tv_toast);

        mToastView = new TextView(UmInit.getInstance().getContext());
        mToastView.setPadding(40, 28, 40, 28);
        mToastView.setGravity(Gravity.CENTER);
        mToastView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 32);
        mToastView.setTextColor(0xFFFFFFFF);

        RoundRectShape shape = new RoundRectShape(new float[]{12, 12, 12, 12, 12, 12, 12, 12}, null, null);
        ShapeDrawable drawable = new ShapeDrawable(shape);
        drawable.getPaint().setColor(0xFF484857);
        mToastView.setBackground(drawable);

        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setView(mToastView);
    }

    public static ToastMng getInstance() {
        if (instance == null) {
            synchronized (ToastMng.class) {
                if (instance == null) {
                    instance = new ToastMng();
                }
            }
        }
        return instance;
    }

    public static void toastShow(String str_Msg) {
        getInstance().showToast(str_Msg);
    }

    public static void toastShow(int resId) {
        getInstance().showToast(resId);
    }

    public void showToast(String msg) {
        if (mToastView != null) {
            mToastView.setText(msg);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    public void showToast(int resId) {
        if (mToastView != null) {
            mToastView.setText(resId);
        } else {
            mToast.setText(resId);
        }
        mToast.show();
    }

    public void setPadding(int left, int top, int right, int bottom) {
        mToastView.setPadding(left, top, right, bottom);
    }

    public void setTextSize(float size) {
        mToastView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    public void setTextColor(int color) {
        mToastView.setTextColor(color);
    }

    public void setGravity(int gravity, int xOffset, int yOffse) {
        mToast.setGravity(gravity, xOffset, yOffse);
    }

    public void setBackgroud(Drawable drawable) {
        mToastView.setBackground(drawable);
    }

    public void setBackgroud(int resId) {
        mToastView.setBackgroundResource(resId);
    }

}
