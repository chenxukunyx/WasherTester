package com.unilife.common.converter;

import android.content.Context;
import android.content.Intent;

import com.unilife.common.entities.UMDB;

/**
 * Created by nicholasyu on 7/8/15.
 * Convert the data from broad cast
 */
public interface IBaseReceiverConverter {
    /**
     * Created by nicholasyu on 7/8/15.
     */
     UMDB convert(Context context, Intent intent, UMDB current);
}
