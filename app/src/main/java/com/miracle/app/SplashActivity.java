package com.miracle.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.miracle.app.ui.MainActivity;

/**
 * Created with Android Studio
 *
 * @author: chenxukun
 * @date: 2018/5/31
 * @time: 上午11:52
 * @fuction:
 */
public class SplashActivity extends Activity{

    RadioGroup rg_group;
    Button btn_confirm;
    String startType = "broad";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        rg_group = (RadioGroup) findViewById(R.id.rg_group);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);

        rg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbtn_broad) {
                    startType = "broad";
                } else {
                    startType = "motor";
                }
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.putExtra("startType", startType);
                startActivity(intent);
            }
        });
    }
}
