package com.miracle.um_base_common.checkcrc;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/11/29
 * @time: 上午9:47
 */

public class BaseCheck {

    private static final int[] table = { 0x0000, 0xC0C1, 0xC181, 0x0140, 0xC301, 0x03C0, 0x0280, 0xC241, 0xC601, 0x06C0, 0x0780, 0xC741,
            0x0500, 0xC5C1, 0xC481, 0x0440, 0xCC01, 0x0CC0, 0x0D80, 0xCD41, 0x0F00, 0xCFC1, 0xCE81, 0x0E40, 0x0A00, 0xCAC1, 0xCB81, 0x0B40,
            0xC901, 0x09C0, 0x0880, 0xC841, 0xD801, 0x18C0, 0x1980, 0xD941, 0x1B00, 0xDBC1, 0xDA81, 0x1A40, 0x1E00, 0xDEC1, 0xDF81, 0x1F40,
            0xDD01, 0x1DC0, 0x1C80, 0xDC41, 0x1400, 0xD4C1, 0xD581, 0x1540, 0xD701, 0x17C0, 0x1680, 0xD641, 0xD201, 0x12C0, 0x1380, 0xD341,
            0x1100, 0xD1C1, 0xD081, 0x1040, 0xF001, 0x30C0, 0x3180, 0xF141, 0x3300, 0xF3C1, 0xF281, 0x3240, 0x3600, 0xF6C1, 0xF781, 0x3740,
            0xF501, 0x35C0, 0x3480, 0xF441, 0x3C00, 0xFCC1, 0xFD81, 0x3D40, 0xFF01, 0x3FC0, 0x3E80, 0xFE41, 0xFA01, 0x3AC0, 0x3B80, 0xFB41,
            0x3900, 0xF9C1, 0xF881, 0x3840, 0x2800, 0xE8C1, 0xE981, 0x2940, 0xEB01, 0x2BC0, 0x2A80, 0xEA41, 0xEE01, 0x2EC0, 0x2F80, 0xEF41,
            0x2D00, 0xEDC1, 0xEC81, 0x2C40, 0xE401, 0x24C0, 0x2580, 0xE541, 0x2700, 0xE7C1, 0xE681, 0x2640, 0x2200, 0xE2C1, 0xE381, 0x2340,
            0xE101, 0x21C0, 0x2080, 0xE041, 0xA001, 0x60C0, 0x6180, 0xA141, 0x6300, 0xA3C1, 0xA281, 0x6240, 0x6600, 0xA6C1, 0xA781, 0x6740,
            0xA501, 0x65C0, 0x6480, 0xA441, 0x6C00, 0xACC1, 0xAD81, 0x6D40, 0xAF01, 0x6FC0, 0x6E80, 0xAE41, 0xAA01, 0x6AC0, 0x6B80, 0xAB41,
            0x6900, 0xA9C1, 0xA881, 0x6840, 0x7800, 0xB8C1, 0xB981, 0x7940, 0xBB01, 0x7BC0, 0x7A80, 0xBA41, 0xBE01, 0x7EC0, 0x7F80, 0xBF41,
            0x7D00, 0xBDC1, 0xBC81, 0x7C40, 0xB401, 0x74C0, 0x7580, 0xB541, 0x7700, 0xB7C1, 0xB681, 0x7640, 0x7200, 0xB2C1, 0xB381, 0x7340,
            0xB101, 0x71C0, 0x7080, 0xB041, 0x5000, 0x90C1, 0x9181, 0x5140, 0x9301, 0x53C0, 0x5280, 0x9241, 0x9601, 0x56C0, 0x5780, 0x9741,
            0x5500, 0x95C1, 0x9481, 0x5440, 0x9C01, 0x5CC0, 0x5D80, 0x9D41, 0x5F00, 0x9FC1, 0x9E81, 0x5E40, 0x5A00, 0x9AC1, 0x9B81, 0x5B40,
            0x9901, 0x59C0, 0x5880, 0x9841, 0x8801, 0x48C0, 0x4980, 0x8941, 0x4B00, 0x8BC1, 0x8A81, 0x4A40, 0x4E00, 0x8EC1, 0x8F81, 0x4F40,
            0x8D01, 0x4DC0, 0x4C80, 0x8C41, 0x4400, 0x84C1, 0x8581, 0x4540, 0x8701, 0x47C0, 0x4680, 0x8641, 0x8201, 0x42C0, 0x4380, 0x8341,
            0x4100, 0x81C1, 0x8081, 0x4040, };

    public static int[] getCRCBytes(int[] data, int start, int len) {
        int crc = 0x0000;
        for (int i = start; i < len; i++) {
            crc = (crc >>> 8) ^ table[(crc ^ data[i]) & 0xff];
        }
        return new int[] { (0xff & crc), ((0xff00 & crc) >> 8) };
    }

    public static int[] getCRCBytes(int[] data) {
        return getCRCBytes(data, 0, data.length);
    }

    public static int getCRC16(int[] data, int start, int len) {
        int crc = 0x0000;
        for (int i = start; i < len; i++) {
            crc = (crc >>> 8) ^ table[(crc ^ data[i]) & 0xff];
        }
        return crc;
    }

    public static int crc_16_CCITT_False(int[] data, int size) {
        int crc = 0xffff; // initial value
        int polynomial = 0x1021; // poly value
        for (int index = 1; index < size; index++) {
            int b = data[index];
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b >> (7 - i) & 1) == 1);
                boolean c15 = ((crc >> 15 & 1) == 1);
                crc <<= 1;
                if (c15 ^ bit)
                    crc ^= polynomial;
            }
        }
        crc &= 0xffff;
        //输出String字样的16进制
        String strCrc = Integer.toHexString(crc).toUpperCase();
        System.out.println(strCrc);
        return crc;
    }

    private static int crc16_sanyo_table[] = { 0x0000, 0x1081, 0x2102, 0x3183, 0x4204, 0x5285, 0x6306, 0x7387, 0x8408, 0x9489, 0xa50a, 0xb58b, 0xc60c, 0xd68d, 0xe70e, 0xf78f };

    public static char getCRC8048(int b[], int len) {
        int vCRC = 0xffff;
        int i = 0;
        while (len-- != 0) {
            vCRC = (vCRC >> 4) ^ crc16_sanyo_table[(vCRC & 0x0f) ^ ((b[i]) & 0x0f)];
            vCRC = (vCRC >> 4) ^ crc16_sanyo_table[(vCRC & 0x0f) ^ ((b[i]) >> 4)];
            i++;
        }
        return (char) ~vCRC;
    }

    /************************************************************************/
    /* Name : GetVRC VRC8 */
    /* Date : 2006/05/29 */
    /* Function : Calculate VRC cheksum Even check */
    /************************************************************************/
    /* Input : var,len */
    /* Return : VRC checksum */
    /************************************************************************/
    public static int GetVRC(int var, int len)
    {
        int i;
        int lrc = 0;
        int Temp = var;

        for(i=0; i<len; i++)
        {
            lrc += (int)(var&0x1);
            var = (int)(var>>1);
        }
        lrc = (int)(lrc&0x1);
        if(lrc != 0)
        {
            Temp |= 0x80;
        }else{
            Temp &= 0x7F;
        }
        return Temp;
    }


    /************************************************************************/
    /* Name : GetLRC LRC8 */
    /* Date : 2006/05/29 */
    /* Function : Calculate LRC cheksum Even check */
    /************************************************************************/
    /* Input : ptr,len */
    /* Return : LRC checksum */
    /************************************************************************/
    public static int GetLRC(int[] ptr, int len)
    {
        int lrc,lrc1,i,TempLen;
        lrc = 0;
        lrc1 = 0;
        i = 0;
        TempLen = len;

        for(i=0;i<8;i++)
        {
            while(TempLen--!=0)
            {
                lrc1 += (int)(ptr[len-TempLen-1]&(0x1<<i));
            }
            lrc |= (int)(lrc1&(0x1<<i));
            lrc1 = 0;
            TempLen = len;
        }

        return lrc;
    }

    public static int GetLRC(int[] ptr, int len, int offset)
    {
        int lrc,lrc1,i,TempLen;
        lrc = 0;
        lrc1 = 0;
        i = 0;
        TempLen = len;

        for(i=0;i<8;i++)
        {
            while(TempLen--!=offset)
            {
                lrc1 += (int)(ptr[len-TempLen-1]&(0x1<<i));
            }
            lrc |= (int)(lrc1&(0x1<<i));
            lrc1 = 0;
            TempLen = len;
        }

        return lrc;
    }
}
