package com.miracle.um_base_common.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

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

    public static void read(Context context, String name) {
        try {
            InputStream is = null;
            String key = "";
            String value = "";
            String unit = "";
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/config/" + name;
            File file = new File(path);
            if (file.exists()) {
                is = context.getApplicationContext().openFileInput(path);
                Log.i(TAG, "-----> sdcard: ");
            } else {
                is = context.getApplicationContext().getAssets().open("tester.xls");
                Log.i(TAG, "-----> assets: ");
            }
            Workbook workbook = Workbook.getWorkbook(is);
            Sheet sheet = workbook.getSheet(0);
            int rows = sheet.getRows();
            int col = sheet.getColumns();
            for (int i = 0; i < rows; i++) {
                key = sheet.getCell(0, i).getContents();
                 value = sheet.getCell(1, i).getContents();
                if (col >= 3) {
                    unit = sheet.getCell(2, i).getContents();
                }
                Log.i(TAG, "----->>data: " + key + ": " + value + " " + unit);
            }
            Log.i(TAG, "----->>read: " + rows + " : " + col);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }
}
