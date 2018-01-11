package com.miracle.app.base;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.miracle.app.R;
import com.miracle.app.ui.SettingActivity;
import com.unilife.common.uilogic.UMBaseUiLogic;


/**
 * Created by Cg on 2016/9/5.
 */
public abstract class BaseHomeUiLogic extends UMBaseUiLogic {

    private ImageView setting;

    public BaseHomeUiLogic(Activity activity) {
        super(activity);
        setting = findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting();
            }
        });
    }

    protected void setting() {
        startActivity(new Intent(mActivity, SettingActivity.class));
    }

    public void release() {
        super.release();
    }
}
