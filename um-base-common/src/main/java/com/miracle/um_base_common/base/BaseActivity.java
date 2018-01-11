package com.miracle.um_base_common.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.miracle.um_base_common.util.MLog;
import com.unilife.common.ui.activities.UMBaseActivity;


/**
 * Created by 万启林 on 2015/7/27.
 */

public abstract class BaseActivity extends UMBaseActivity {
    protected static String TAG;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    protected Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        mToast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
        MLog.d(TAG, "onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MLog.d(TAG, "onDestroy");
        mToast = null;
    }

    /**
     * 显示toast
     * @param obj 要显示的内容
     */
    protected void showMsg(final Object obj) {
        if(mToast != null && obj != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mToast.setText(obj.toString());
                    mToast.show();
                }
            });
        }
    }

    /**
     * 显示toast
     * @param resId 要显示的资源id
     */
    protected void showMsg(final int resId) {
        if(mToast != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mToast.setText(resId);
                    mToast.show();
                }
            });
        }
    }

    protected abstract int getLayoutId();
}
