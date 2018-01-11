package com.unilife.variety.logic.base;

import android.app.Activity;

import com.unilife.common.entities.UMDB;
import com.unilife.common.ui.activities.UMBaseActivity;
import com.unilife.common.uilogic.UMBaseUiLogic;
import com.unilife.variety.widget.RotateImageView;



/**
 * Created by Cg on 2016/9/5.
 */
public class HomeBaseUILogic extends UMBaseUiLogic{


    public HomeBaseUILogic(Activity activity) {
        super(activity);
    }

    protected void start(int rpm) {

    }

    protected void stop() {}


    public void onSendCmd(UMDB fridgeDB, RotateImageView view, @TYPE int direction) {
        ((UMBaseActivity) mActivity).update(fridgeDB);
        if (direction == CW) {
            view.cw();
        } else if (direction == CCW) {
            view.ccw();
        } else {
            view.hide();
        }
    }
}
