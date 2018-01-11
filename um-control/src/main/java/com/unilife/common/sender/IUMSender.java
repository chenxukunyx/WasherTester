package com.unilife.common.sender;

import android.content.Context;

import com.unilife.common.entities.UMDB;

/**
 * Created by Kevin on 2015/7/15.
 */
public interface IUMSender {
    void start();
    void send(Context context, UMDB umdb);
}
