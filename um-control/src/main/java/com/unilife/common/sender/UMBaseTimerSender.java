package com.unilife.common.sender;

import android.content.Context;

import com.unilife.common.entities.UMDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by nicholasyu on 7/8/15.
 */
public abstract class UMBaseTimerSender<T> implements IUMSender{

    public final static int ERROR_CMD_ID = -1;
    protected void onStart()
    {

    }

    @Override
    public void start(){
        onStart();
    }

    public void destroy(){
        for (Timer timer : m_lstTimers)
        {
            timer.cancel();
        }
        m_lstTimers.clear();

        onDestroy();
    }

    protected void onDestroy(){

    }

    List<Timer> m_lstTimers = new ArrayList<>();

    /**
     *
     * @param interval  任务执行的间隔(毫秒)
     * @param cmdId
     */
    protected Timer registerTimerSender(int interval, final int cmdId)
    {
        Timer timer = new Timer(true);
        TimerTask task = new TimerTask() {
            public void run() {
                // 到达时间点
                sendCmd(cmdId, null);
            }
        };
        timer.schedule(task, interval);
        m_lstTimers.add(timer);
        return timer;
    }

    protected abstract int sendCmd(final int cmd, final UMDB umdb);

    protected abstract int getCmd(UMDB db);

    @Override
    public void send(Context context, UMDB umdb) {
        int cmdId = getCmd(umdb);
        sendCmd(cmdId, umdb);
    }
}
