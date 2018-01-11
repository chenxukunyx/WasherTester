package com.unilife.common.receiver;

import android.content.Context;

/**
 * Created by Kevin on 2015/7/15.
 */
public interface IUMReceiver {

    void startReceive(Context context);
    void setReceiverListener(IReceiverListener listener);
}
