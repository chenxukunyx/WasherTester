package com.miracle.app.ui;

import android.os.Bundle;

import com.miracle.app.base.BaseHomeUiLogic;
import com.miracle.app.config.WasherConfig;
import com.miracle.app.uilogic.UiLogicManager;
import com.miracle.um_base_common.base.BaseActivity;
import com.unilife.common.entities.UMDB;

public class MainActivity extends BaseActivity {

    private BaseHomeUiLogic mUiLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUiLogic = UiLogicManager.getManager().getUiLogic(this);
    }

    @Override
    protected void onDestroy() {
        if (mUiLogic != null) {
            mUiLogic.release();
            mUiLogic = null;
        }
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return WasherConfig.getWasherConfig().getLayoutId();
    }

    @Override
    protected void onNewUMDbData(UMDB db) {
        mUiLogic.onNewUmdbData(db);
    }

    @Override
    protected void onNewUpdateRequest(int moduleId) {

    }

    @Override
    protected int hasModuleId() {
        return 0;
    }

}
