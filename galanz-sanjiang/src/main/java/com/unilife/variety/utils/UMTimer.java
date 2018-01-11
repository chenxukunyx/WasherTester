package com.unilife.variety.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by nicholasyu on 2015/9/15.
 */
public class UMTimer {

    static UMTimer m_instance = null;
    final int TIME_ACCURACY = 200; // 100ms
    Thread m_thread;
    boolean m_exitThread = false;
    Handler m_handler = new Handler(Looper.getMainLooper());

    ConcurrentHashMap<String, UMTimerInf> m_timers = new ConcurrentHashMap<>();

    UMTimer() {
        m_thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (!m_exitThread) {
                    /**
                     * call runing timer
                     */
                    for (ConcurrentHashMap.Entry entry : m_timers.entrySet()) {
                        UMTimerInf inf = (UMTimerInf) entry.getValue();
                        synchronized (inf) {
                            if (!inf.m_removed) {
                                String name = (String) entry.getKey();
                                timerProc(inf, name);
                                if (inf.m_run && inf.m_loopCount == 0) {
                                    inf.m_removed = true;
                                }
                            }
                        }
                    }
                    /**
                     * remove stopped timer
                     */
                    for (ConcurrentHashMap.Entry entry : m_timers.entrySet()) {
                        UMTimerInf inf = (UMTimerInf) entry.getValue();
                        if (inf.m_removed) {
                            m_timers.remove((String) entry.getKey());
                        }
                    }
                    try {
                        Thread.sleep(TIME_ACCURACY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        m_thread.start();
    }

    public static UMTimer getInstance() {
        if (m_instance == null)
            m_instance = new UMTimer();
        return m_instance;
    }

    void timerProc(UMTimerInf inf, final String name) {
        boolean timeout = false;

        if (inf.m_paused) {
            return;
        }

        if (inf.circleTime > 0) {
            long delta = System.currentTimeMillis() - inf.m_curTime;
            if (delta < 0
                    || delta > inf.circleTime * 10) {
                inf.m_curTime = System.currentTimeMillis();
                delta = 0;
            } else if (delta > inf.circleTime * 2) {
                long fixv = (delta / inf.circleTime - 1) * inf.circleTime;
                inf.m_curTime += fixv;
                delta -= fixv;
            }

            if (inf.preTime > 0) {
                if (inf.preTime > delta) {
                    inf.preTime -= delta;
                } else {
                    inf.preTime = 0;
                    timeout = true;
                }
                inf.m_curTime += delta;
            } else {
                if (delta >= inf.circleTime) {
                    inf.m_curTime += inf.circleTime;
                    timeout = true;
                }
            }
        } else {
            timeout = true;
        }

        if (timeout) {
            if (inf.m_loopCount > 0)
                inf.m_loopCount--;
            if (inf.listener != null) {
                if (inf.m_runUIThread) {
                    final UMTimerOutListener listener = inf.listener;
                    m_handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.UMTimeOut(name);
                        }
                    });
                } else {
                    inf.listener.UMTimeOut(name);
                }
                inf.m_run = true;
            }
        }
    }

    public void release() {
        m_exitThread = true;
        try {
            m_thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m_timers.clear();
    }

    public boolean startTimer(String name, long circleTime, UMTimerOutListener timeout) {
        return startTimer(name, 0, circleTime, -1, true, timeout);
    }

    public boolean startTimer(String name, long circleTime, long loopCount, UMTimerOutListener timeout) {
        return startTimer(name, 0, circleTime, loopCount, true, timeout);
    }

    public boolean startTimer(String name, long circleTime, long loopCount, boolean runUiThread, UMTimerOutListener timeout) {
        return startTimer(name, 0, circleTime, loopCount, runUiThread, timeout);
    }

    public boolean startTimer(String name, long preTime, long circleTime, long loopCount, boolean runUiThread, UMTimerOutListener timeout) {

        if (circleTime < TIME_ACCURACY)
            circleTime = TIME_ACCURACY;

        if (loopCount == 0)
            loopCount = -1;

        UMTimerInf inf_new = new UMTimerInf(preTime, circleTime, loopCount, timeout);

        inf_new.m_runUIThread = runUiThread;

        UMTimerInf inf = m_timers.get(name);
        if (inf != null) {
            synchronized (inf) {
                m_timers.put(name, inf_new);
            }
        } else {
            m_timers.put(name, inf_new);
        }
        return true;
    }

    /**
     * stop timer by name
     *
     * @param name - timer name
     */
    public void stopTimer(String name) {
        UMTimerInf inf = m_timers.get(name);
        if (inf != null) {
            synchronized (inf) {
                inf.m_removed = true;
            }
        }
    }

    /**
     * pause timer
     */
    public void pauseTimer(String name) {
        UMTimerInf inf = m_timers.get(name);
        if (inf != null) {
            inf.m_pauseDeltaTime = System.currentTimeMillis() - inf.m_curTime;
            inf.m_paused = true;
        }
    }

    /**
     * resume timer
     */
    public void resumeTimer(String name) {
        UMTimerInf inf = m_timers.get(name);
        if (inf != null) {
            inf.m_curTime = System.currentTimeMillis() - inf.m_pauseDeltaTime;
            inf.m_pauseDeltaTime = 0;
            inf.m_paused = false;
        }
    }

    /**
     * stop timer by name and listener, name and listener must both be same with input
     *
     * @param name
     * @param timeout
     */
    public void stopTimer(String name, UMTimerOutListener timeout) {
        UMTimerInf inf = m_timers.get(name);
        if (inf != null && inf.listener == timeout) {
            inf.m_removed = true;
        }
    }

    /**
     * reset timer circle time
     *
     * @param name
     * @param circleTime
     */
    public void resetTimer(String name, long circleTime) {
        UMTimerInf inf = m_timers.get(name);
        if (inf != null) {
            inf.circleTime = circleTime;
        }
    }

    public boolean isRunning(String name) {
        return (m_timers.get(name) != null);
    }

    public long getPastTime(String name) {
        UMTimerInf inf = m_timers.get(name);
        if (inf != null) {
            return inf.m_curTime - inf.m_startTime;
        } else {
            return 0;
        }
    }

    public long getLastTime(String name) {
        UMTimerInf inf = m_timers.get(name);
        if (inf != null && inf.m_loopCount > 0) {
            return inf.m_totalCount * inf.circleTime + inf.m_startTime - inf.m_curTime;
        } else {
            return 0;
        }
    }

    public long getPastCount(String name) {
        UMTimerInf inf = m_timers.get(name);
        if (inf != null) {
            return inf.m_totalCount - inf.m_loopCount;
        } else {
            return 0;
        }
    }

    public long getLastCount(String name) {
        UMTimerInf inf = m_timers.get(name);
        if (inf != null) {
            return inf.m_loopCount;
        } else {
            return 0;
        }
    }

    public interface UMTimerOutListener {
        void UMTimeOut(String name);
    }

    public class UMTimerInf {
        long preTime;
        long circleTime;
        long m_startTime;
        long m_curTime;
        long m_pauseDeltaTime;
        long m_loopCount;
        long m_totalCount;
        boolean m_run;
        boolean m_runUIThread;
        boolean m_removed;
        boolean m_paused;

        UMTimerOutListener listener;

        UMTimerInf(long preTime, long circleTime, long loopCount, UMTimerOutListener listener) {
            this.preTime = preTime;
            this.circleTime = circleTime;
            this.m_pauseDeltaTime = 0;
            this.listener = listener;
            this.m_loopCount = loopCount;
            this.m_totalCount = loopCount;
            this.m_run = false;
            this.m_removed = false;
            this.m_paused = false;
            this.m_runUIThread = true;
            this.m_startTime = System.currentTimeMillis();
            this.m_curTime = m_startTime;
        }
    }
}
