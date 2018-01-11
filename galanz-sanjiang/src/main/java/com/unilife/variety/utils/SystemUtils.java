package com.unilife.variety.utils;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.PowerManager;

import java.util.List;

public class SystemUtils {
	/**
	 * 获取软件版本号
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context) {
		String versionName = "";
		try {
			versionName = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionName;
	}
	
	public static String getMac(Context context) {
		String mac = "";
		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);  
	    WifiInfo info = wifi.getConnectionInfo();
	    if(info != null && info.getMacAddress() != null) {
	    	mac = info.getMacAddress();
	    }
		return mac.replace(":", "").replace("-", "");
	}
	
	/**
	 * 获取软件版本code
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context) {
		int versionCode = 0;
		try {
			versionCode = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}
	
	/**
	 * app是否安装
	 * @param context
	 * @param packageName
	 * @return
	 */
	public static boolean isAppInstalled(Context context, String packageName) {
		PackageManager packageManager = context.getPackageManager();
		List<PackageInfo> infos = packageManager.getInstalledPackages(0);
		for (PackageInfo packageInfo : infos) {
			if(packageInfo.packageName.equals(packageName)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 获取wifi信号强度
	 * @param level
	 * @return
	 */
	public static int getWifiLevel(int level) {
		if(level <= 0 && level >= -50) { //强
			return 1;
		} else if(level >= -70) { //较强
			return 2;
		} else if(level >= -85) { //一般
			return 3;
		}else { //弱
			return 4;
		}
	}
	
	/**
	 * 唤醒屏幕
	 * @param context
	 */
	public static void wakeUpAndUnlock(Context context){
        KeyguardManager km= (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
        //解锁
        kl.disableKeyguard();
        //获取电源管理器对象
        PowerManager pm=(PowerManager) context.getSystemService(Context.POWER_SERVICE);
        //获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK,"bright");
        //点亮屏幕
        wl.acquire();
        //释放
        wl.release();
    }
	
	public static void wakeOff(Context context) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.USER_POWERKEY");
		intent.putExtra("do_power","off");
		context.sendBroadcast(intent);
	}
	
	public static void wakeOn(Context context) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.USER_POWERKEY");
		intent.putExtra("do_power","on");
		context.sendBroadcast(intent);
	}
}
