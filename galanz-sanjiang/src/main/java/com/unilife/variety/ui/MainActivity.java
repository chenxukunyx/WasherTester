package com.unilife.variety.ui;

import android.os.Bundle;
import android.util.Log;

import com.unilife.common.entities.UMDB;
import com.unilife.variety.R;
import com.unilife.variety.logic.HomeUIRefreshLogic;
import com.unilife.variety.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    HomeUIRefreshLogic mHomeUIRefreshLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHomeUIRefreshLogic = new HomeUIRefreshLogic(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onNewUMDbData(UMDB db) {
        Log.i(TAG, "----->>onNewUMDbData: ");
        mHomeUIRefreshLogic.onNewUmdbData(db);
    }

    @Override
    protected void onNewUpdateRequest(int moduleId) {

    }

    @Override
    protected int hasModuleId() {
        return 0;
    }
}
