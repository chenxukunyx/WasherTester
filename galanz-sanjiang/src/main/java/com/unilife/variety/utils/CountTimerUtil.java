package com.unilife.variety.utils;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 万启林 on 2015/7/31.
 */
public class CountTimerUtil {
    private static final int STEP = 500;//500ms
    private static CountTimerUtil mInstance;
    private List<CountTimerListener> mListeners;
    private Handler mHandler = new Handler();

    private Runnable mHandlerRunnable = new Runnable() {
        @Override
        public void run() {
            tick();
        }
    };

    private CountTimerUtil() {
        mListeners = new ArrayList<>();
        new MyCountTimerThread().start();
    }

    public static CountTimerUtil getInstance() {
        if(mInstance == null) {
            synchronized (CountTimerUtil.class) {
                mInstance = new CountTimerUtil();
            }
        }
        return mInstance;
    }

    /**
     * 注册
     * @param listener
     */
    synchronized public void registerListener(CountTimerListener listener) {
        mListeners.remove(listener);
        mListeners.add(listener);
    }

    /**
     * 解除
     * @param listener
     */
    synchronized public void unregisterListener(CountTimerListener listener) {
        mListeners.remove(listener);
    }

    /**
     * 计时
     */
    synchronized private void tick() {
        for (CountTimerListener listener : mListeners) {
            if(listener != null) {
                listener.onTick(STEP);
            }
        }
    }

    /**
     * 计时线程
     */
    private class MyCountTimerThread extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    sleep(STEP);
                    mHandler.post(mHandlerRunnable);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 计时回调
     */
    public interface CountTimerListener{
        public void onTick(int step);
    }
}
