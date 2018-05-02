package com.miracle.app.uilogic.weili;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.miracle.app.R;
import com.miracle.app.base.BaseHomeUiLogic;
import com.miracle.app.base.BaseProtocolCmdLogic;
import com.miracle.app.config.BrandConfig;
import com.miracle.app.logic.GalanzProtocolLogic;
import com.miracle.app.logic.WeiLiProtocolLogic;
import com.miracle.app.ui.MainActivity;
import com.miracle.um_base_common.entity.ProtocolCmd;
import com.miracle.um_base_common.listener.IProtocolListener;
import com.miracle.um_base_common.util.MLog;
import com.miracle.um_base_common.util.StringUtils;
import com.miracle.um_base_common.view.RotateImageView;
import com.unilife.common.entities.UMDB;

import java.util.Map;


/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/21
 * @time: 下午2:11
 */

public class WeiLiUiLogic extends BaseHomeUiLogic implements IProtocolListener, View.OnClickListener {

    protected TextView voltage;
    protected TextView power;
    protected TextView electricity;
    protected TextView temperature;
    protected TextView rpm;
    protected TextView version;
    protected ImageView tip;
    protected Button reset;
    private Button start;

    protected RotateImageView rotateImageView;
    protected TextView tipTextView;

    private BaseProtocolCmdLogic mProtocolCmdLogic;

    public WeiLiUiLogic(Activity activity) {
        super(activity);
        initView();
        initData();
        initEvent();
    }

    @Override
    public void onNewUmdbData(UMDB db) {
        Log.d(TAG, "----->onNewUMDbData");
        Map<String, String> newDb = db.getDb();

        int error = StringUtils.parseInteger(newDb.get(UMDB.PadCommErr));
        error = 0;
//        int cmdAck = StringUtils.parseInteger(newDb.get(UMDB.MotorAck));
//        int[] data = new int[21];
//        data[0] = StringUtils.parseInteger(newDb.get(UMDB.MotorAckData0));
//        data[1] = StringUtils.parseInteger(newDb.get(UMDB.MotorAckData1));
//        data[2] = StringUtils.parseInteger(newDb.get(UMDB.MotorAckData2));
//        data[3] = StringUtils.parseInteger(newDb.get(UMDB.MotorAckData3));
//        data[4] = StringUtils.parseInteger(newDb.get(UMDB.MotorAckData4));
//        data[5] = StringUtils.parseInteger(newDb.get(UMDB.MotorAckData5));
//        data[6] = StringUtils.parseInteger(newDb.get(UMDB.MotorAckData6));
//        data[7] = StringUtils.parseInteger(newDb.get(UMDB.MotorAckData7));
//        data[8] = StringUtils.parseInteger(newDb.get(UMDB.MotorAckData8));

//        mProtocolCmdLogic.recvResponse(error, cmdAck, data);
        mProtocolCmdLogic.recvResponse(error, 0, db);
    }

    private void initView() {
        voltage = findViewById(R.id.voltage);
        power = findViewById(R.id.power);
        electricity = findViewById(R.id.electricity);
        temperature = findViewById(R.id.temperature);
        rpm = findViewById(R.id.rpm);
        version = findViewById(R.id.version);
        tip = findViewById(R.id.tip);
        reset = findViewById(R.id.reset);
        tipTextView = findViewById(R.id.tipTextView);
        rotateImageView = findViewById(R.id.rotateImageView);
        start = findViewById(R.id.start);
    }


    protected void initData() {
        //三洋
        mProtocolCmdLogic = WeiLiProtocolLogic.getInstance();

        mProtocolCmdLogic.setProtocolListener(this);
    }

    private void initEvent() {
        start.setOnClickListener(this);
        reset.setOnClickListener(this);
        tipTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.start) {
            startOrPause();
        } else if (id == R.id.reset) {
            reset();
        } else if (id == R.id.tipTextView) {
            hideTipView();
        }
    }

    /**
     * 启动或者暂停
     */
    protected void startOrPause() {
        this.tip.setVisibility(View.GONE);
        tipTextView.setVisibility(View.GONE);
        if (!mProtocolCmdLogic.isStart()) { //当前状态为暂停
//            if (!mProtocolCmdLogic.isReset()) {
                start.setBackgroundResource(R.drawable.selector_pause);
                mProtocolCmdLogic.start();
//            }
        } else { //当前状态为开始
            start.setBackgroundResource(R.drawable.selector_start);
            mProtocolCmdLogic.pause();
        }
    }

    @Override
    public void release() {
        if (mProtocolCmdLogic != null) {
            mProtocolCmdLogic = null;
        }
    }

    /**
     * 复位
     */
    private void doReset() {
        mProtocolCmdLogic.reset();
        start.setBackgroundResource(R.drawable.selector_start);
    }

    /**
     * 复位
     */
    protected void reset() {
        doReset();

        //延迟隐藏提示信息
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tip.setVisibility(View.GONE);

                power.setText("0W");
                electricity.setText("0A");
                voltage.setText("0V");
                temperature.setText("0℃");
                rpm.setText("0");
                version.setText("");
                tipTextView.setVisibility(View.GONE);
            }
        }, 200);
    }

    /**
     * 点击提示，关闭
     */
    protected void hideTipView() {
        tipTextView.setVisibility(View.GONE);
    }

    /**
     * 显示提示
     *
     * @param msg
     */
    private void showTipView(String msg) {
        tipTextView.setText(msg);
        tipTextView.setVisibility(View.VISIBLE);
    }

    /**
     * 检测成功
     */
    @Override
    public void onSuccess() {
        findViewById(R.id.start).setBackgroundResource(R.drawable.selector_start);
        doReset();
        showTipView("检测成功");
        rotateImageView.hide();
        MLog.d(TAG, "onSuccess");
    }

    @Override
    public void onSendCmd(UMDB umdb, int circleDirect) {
        ((MainActivity) mActivity).updateDb(umdb);
        if (circleDirect == ProtocolCmd.CW) {
            rotateImageView.cw();
        } else if (circleDirect == ProtocolCmd.CCW) {
            rotateImageView.ccw();
        } else {
            rotateImageView.hide();
        }
    }

    /**
     * 检测失败
     *
     * @param msg
     */
    @Override
    public void onError(boolean isHigh, String msg) {
        //isHigh = false;
        MLog.d(TAG, msg);
        showTipView(msg);
        if (isHigh) {
            doReset();
            this.tip.setVisibility(View.VISIBLE);
            rotateImageView.hide();
        }
    }

    @Override
    public void onTemperatureChange(String temperature) {
        this.temperature.setText(temperature);
    }

    @Override
    public void onRpmChange(String rpm) {
        this.rpm.setText(rpm);
    }

    @Override
    public void onVoltageChange(String voltage) {
        this.voltage.setText(voltage);
    }

    @Override
    public void onPowerChange(String power) {
        this.power.setText(power);
    }

    @Override
    public void onElectricityChange(String electricity) {
        this.electricity.setText(electricity);
    }

    @Override
    public void onVersionChange(String version) {
        this.version.setText(version);
    }

    @Override
    public String getUMDBValue(String key) {
        return ((MainActivity) mActivity).getValue(key);
    }
}
