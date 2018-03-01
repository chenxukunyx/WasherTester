package com.miracle.um_base_common.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.miracle.um_base_common.constant.WasherConstants;
import com.miracle.um_base_common.entity.ConfigEntity;
import com.miracle.um_base_common.logic.ConfigLogic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/21
 * @time: 下午9:57
 */

public class ExcelUtils {

    private static final String TAG = "ExcelUtils";
    private static final String ConfigInfo = "ConfigInfo.xls";

    public static void read(Context context) {
        read(context, ConfigInfo);
    }

    public static void read(Context context, String name) {
        try {
            ConfigLogic logic = new ConfigLogic();
            ConfigEntity entity = new ConfigEntity();
            InputStream is = null;
            String key = "";
            float value = 0;
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + name;
            File file = new File(path);
            if (file.exists()) {
                is = context.getApplicationContext().openFileInput(path);
                Log.i(TAG, "-----> sdcard: ");
            } else {
                is = context.getApplicationContext().getAssets().open(name);
                Log.i(TAG, "-----> assets: ");
            }
            Workbook workbook = Workbook.getWorkbook(is);
            Sheet sheet = workbook.getSheet(0);
            int rows = sheet.getRows();
            int col = sheet.getColumns();
            for (int i = 1; i < rows; i++) {
                key = sheet.getCell(0, i).getContents();
                value = StringUtils.parseInteger(sheet.getCell(1, i).getContents());
                if (key.equals(WasherConstants.Tambient)) {
                    entity.setTambient(value);
                } else if (key.equals(WasherConstants.SW_Version)) {
                    entity.setSW_Version(value);
                } else if (key.equals(WasherConstants.Umain_norm)) {
                    entity.setUmain_norm(value);
                } else if (key.equals(WasherConstants.Umain_LL)) {
                    entity.setUmain_LL(value);
                } else if (key.equals(WasherConstants.Umain_UL)) {
                    entity.setUmain_UL(value);
                } else if (key.equals(WasherConstants.High_Speed)) {
                    entity.setHigh_Speed(value);
                } else if (key.equals(WasherConstants.Low_Speed)) {
                    entity.setLow_Speed(value);
                } else if (key.equals(WasherConstants.I_hi_speed)) {
                    entity.setI_hi_speed(value);
                } else if (key.equals(WasherConstants.I_LL_hi_speed)) {
                    entity.setI_LL_hi_speed(value);
                } else if (key.equals(WasherConstants.I_UL_hi_speed)) {
                    entity.setI_UL_hi_speed(value);
                } else if (key.equals(WasherConstants.I_low_speed)) {
                    entity.setI_low_speed(value);
                } else if (key.equals(WasherConstants.I_LL_low_speed)) {
                    entity.setI_LL_low_speed(value);
                } else if (key.equals(WasherConstants.I_UL_low_speed)) {
                    entity.setI_UL_low_speed(value);
                }  else if (key.equals(WasherConstants.P_hi_speed)) {
                    entity.setP_hi_speed(value);
                } else if (key.equals(WasherConstants.P_LL_hi_speed)) {
                    entity.setP_LL_hi_speed(value);
                } else if (key.equals(WasherConstants.P_UL_hi_speed)) {
                    entity.setP_UL_hi_speed(value);
                }  else if (key.equals(WasherConstants.P_low_speed)) {
                    entity.setP_low_speed(value);
                } else if (key.equals(WasherConstants.P_LL_low_speed)) {
                    entity.setP_LL_low_speed(value);
                } else if (key.equals(WasherConstants.P_UL_low_speed)) {
                    entity.setP_UL_low_speed(value);
                } else if (key.equals(WasherConstants.T_ambient)) {
                    entity.setT_ambient(value);
                } else if (key.equals(WasherConstants.T_LL_ambient)) {
                    entity.setT_LL_ambient(value);
                } else if (key.equals(WasherConstants.T_UL_ambient)) {
                    entity.setT_UL_ambient(value);
                } else if (key.equals(WasherConstants.T_hi_speed)) {
                    entity.setT_hi_speed(value);
                } else if (key.equals(WasherConstants.T_LL_hi_speed)) {
                    entity.setT_LL_hi_speed(value);
                } else if (key.equals(WasherConstants.T_UL_hi_speed)) {
                    entity.setT_UL_hi_speed(value);
                }
            }
            logic.saveConfig(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }
}
