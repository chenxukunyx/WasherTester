package com.mediatek.factorymode.serial;
public class jniSERIAL{
	private static final String TAG = "jniSERIAL";
	public int [] rd_data = new int[32];
	public int [] wr_data = new int[32];
	public static boolean no_serial_lib = false;
	public native int init(Object param,int new_baudrate, int length, char parity_c, int stopbits);  
 
	public native int serial_read(Object param);  

	public native int serial_write(Object param, int len);  
	public native int exit();

	static {    
		try {
			System.loadLibrary("fm_serial_jni");
			//no_serial_lib = true;

		} catch(Exception e) {
			e.printStackTrace(System.out);
			no_serial_lib = true;
		}

	}  	
	
}

