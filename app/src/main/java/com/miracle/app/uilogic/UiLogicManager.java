package com.miracle.app.uilogic;

import android.app.Activity;

import com.miracle.app.base.BaseHomeUiLogic;
import com.miracle.app.config.BrandConfig;
import com.miracle.app.config.WasherConfig;
import com.miracle.app.uilogic.galanz.GalanzUiLogic;
import com.miracle.app.uilogic.jide.JiDeUiLogic;
import com.miracle.app.uilogic.wolong.WolongUiLogic;


/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/22
 * @time: 上午10:52
 */

public class UiLogicManager {

    private static UiLogicManager manager;

    private UiLogicManager() {
    }

    public static UiLogicManager getManager() {
        if (manager == null) {
            synchronized (UiLogicManager.class) {
                if (manager == null) {
                    manager = new UiLogicManager();
                }
            }
        }
        return manager;
    }

    public BaseHomeUiLogic getUiLogic(Activity activity) {
        if (WasherConfig.getWasherConfig().getMotorBrand().equals(BrandConfig.MODEL_GALANZ)) {
            return new GalanzUiLogic(activity);
        } else if (WasherConfig.getWasherConfig().getMotorBrand().equals(BrandConfig.MODEL_WOLONG)) {
            return new WolongUiLogic(activity);
        } else if (WasherConfig.getWasherConfig().getMotorBrand().equals(BrandConfig.MODEL_JIDE)) {
            return new JiDeUiLogic(activity);
        }
        return null;
    }

}
