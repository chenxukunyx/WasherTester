package com.unilife.common.sender;

import android.content.Context;
import android.content.Intent;

import com.unilife.common.entities.UMDB;

/**
 * Created by Kevin on 2015/7/15.
 */
public class UISender implements IUMSender {
    /**
     * UMDB更新引起的界面显示设置数据的更新ACTION
     */
    public static String UMDB_UPDATE_ACTION = "com.unilife.common.actions.umdb.UPDATE";
    /**
     * 控制服务来的界面重新加载的请求
     */
    public static String UI_REFRESH_ACTION = "com.unilife.common.actions.ui.REFRESH";

    @Override
    public void start() {

    }

    @Override
    public void send(Context context, UMDB umdb) {
        Intent it = new Intent(UMDB_UPDATE_ACTION);
        it.putExtra("umdb", umdb);
        context.sendBroadcast(it);

    }

    public void sendUIUpdateRequest(Context context, int moduleId)
    {
        Intent it = new Intent(UI_REFRESH_ACTION);
        it.putExtra("moduleId", moduleId);
        context.sendBroadcast(it);
    }
}
