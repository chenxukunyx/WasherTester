package com.miracle.app;

import com.miracle.um_base_common.MyApplication;
import com.miracle.um_base_common.util.ExcelUtils;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2018/3/1
 * @time: 下午12:57
 */

public class WasherApplication extends MyApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ExcelUtils.read(this);
    }
}
