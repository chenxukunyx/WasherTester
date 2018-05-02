package com.unilife.common.entities;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nicholasyu on 7/8/15.
 */
public class UMDB extends BitDB implements Parcelable{

    /*冰箱命令*/

    public final static String PadCommErr = "PadCommErr";/*显板和主板通信故障*/
    public final static String WiFiCommErr = "WiFiCommErr";/*WiFi模块通信故障*/
    public final static String WiFiConnMainErr = "WiFiConnMainErr";/*WiFi连接主板故障*/
    public final static String WiFiConnRounterErr = "WiFiConnRounterErr";/*WiFi连接路由故障*/
    public final static String CommChkSumErrCount = "CommChkSumErrCount";/*通信校验失败计数*/
    public final static String CommNoResponseCount = "CommNoResponseCount";/*通信无响应计数*/
    public final static String CommCount = "CommCount";/*通信计数*/


    /*洗衣机命令*/
    public final static String MotorCmd = "MotorCmd";/*电机控制命令*/
    public final static String MotorCmdDataChk = "MotorCmdDataChk";/*电机控制命令*/
    public final static String MotorCmdDataStx = "MotorCmdDataStx";//开始位
    public final static String MotorCmdDataEtx = "MotorCmdDataEtx";//结束位

    public final static String MotorCmdDataSrcAddr = "MotorCmdDataSrcAddr";//源地址
    public final static String MotorCmdDataDestAddr = "MotorCmdDataDestAddr";//目标地址
    public final static String MotorCmdData0 = "MotorCmdData0";/*电机控制命令参数0*/
    public final static String MotorCmdData1 = "MotorCmdData1";/*电机控制命令参数1*/
    public final static String MotorCmdData2 = "MotorCmdData2";/*电机控制命令参数2*/
    public final static String MotorCmdData3 = "MotorCmdData3";/*电机控制命令参数3*/
    public final static String MotorCmdData4 = "MotorCmdData4";/*电机控制命令参数4*/
    public final static String MotorCmdData5 = "MotorCmdData5";/*电机控制命令参数5*/
    public final static String MotorCmdData6 = "MotorCmdData6";/*电机控制命令参数6*/
    public final static String MotorCmdData7 = "MotorCmdData7";/*电机控制命令参数7*/
    public final static String MotorCmdData8 = "MotorCmdData8";/*电机控制命令参数8*/
    public final static String MotorCmdData9 = "MotorCmdData9";/*电机控制命令参数9*/
    public final static String MotorCmdData10 = "MotorCmdData10";/*电机控制命令参数10*/
    public final static String MotorCmdData11 = "MotorCmdData11";/*电机控制命令参数11*/
    public final static String MotorCmdData12 = "MotorCmdData12";/*电机控制命令参数12*/
    public final static String MotorCmdData13 = "MotorCmdData13";/*电机控制命令参数13*/
    public final static String MotorCmdData14 = "MotorCmdData14";/*电机控制命令参数13*/
    public final static String MotorCmdData15 = "MotorCmdData15";/*电机控制命令参数13*/
    public final static String MotorCmdData16 = "MotorCmdData16";/*电机控制命令参数13*/
    public final static String MotorCmdData17 = "MotorCmdData17";/*电机控制命令参数13*/
    public final static String MotorCmdData18 = "MotorCmdData18";/*电机控制命令参数13*/
    public final static String MotorCmdData19 = "MotorCmdData19";/*电机控制命令参数13*/
    public final static String MotorCmdData20 = "MotorCmdData20";/*电机控制命令参数13*/
    public final static String MotorCmdData21 = "MotorCmdData21";/*电机控制命令参数13*/
    public final static String MotorCmdData22 = "MotorCmdData22";/*电机控制命令参数13*/
    public final static String MotorCmdData23 = "MotorCmdData23";/*电机控制命令参数13*/
    public final static String MotorCmdData24 = "MotorCmdData24";/*电机控制命令参数13*/
    public final static String MotorCmdData25 = "MotorCmdData25";/*电机控制命令参数13*/
    public final static String MotorCmdData26 = "MotorCmdData26";/*电机控制命令参数13*/
    public final static String MotorCmdData27 = "MotorCmdData27";/*电机控制命令参数13*/
    public final static String MotorCmdData28 = "MotorCmdData28";/*电机控制命令参数13*/
    public final static String MotorCmdData29 = "MotorCmdData29";/*电机控制命令参数13*/
    public final static String MotorCmdData30 = "MotorCmdData30";/*电机控制命令参数13*/
    public final static String MotorCmdData31 = "MotorCmdData31";/*电机控制命令参数13*/
    public final static String MotorCmdData32 = "MotorCmdData32";/*电机控制命令参数13*/
    public final static String MotorCmdData33 = "MotorCmdData33";/*电机控制命令参数13*/
    public final static String MotorCmdData34 = "MotorCmdData34";/*电机控制命令参数13*/
    public final static String MotorCmdData35 = "MotorCmdData35";/*电机控制命令参数13*/
    public final static String MotorCmdData36 = "MotorCmdData36";/*电机控制命令参数13*/
    public final static String MotorCmdData37 = "MotorCmdData37";/*电机控制命令参数13*/
    public final static String MotorCmdData38 = "MotorCmdData38";/*电机控制命令参数13*/
    public final static String MotorCmdData39 = "MotorCmdData39";/*电机控制命令参数13*/
    public final static String MotorCmdData40 = "MotorCmdData40";/*电机控制命令参数13*/
    public final static String MotorCmdData41 = "MotorCmdData41";/*电机控制命令参数13*/

    public static final String MotorAck = "MotorAck";
    public final static String MotorAckData0 = "MotorCmdAck0";/*电机控制命令反馈数据0*/
    public final static String MotorAckData1 = "MotorCmdAck1";/*电机控制命令反馈数据1*/
    public final static String MotorAckData2 = "MotorCmdAck2";/*电机控制命令反馈数据2*/
    public final static String MotorAckData3 = "MotorCmdAck3";/*电机控制命令反馈数据3*/
    public final static String MotorAckData4 = "MotorCmdAck4";/*电机控制命令反馈数据4*/
    public final static String MotorAckData5 = "MotorCmdAck5";/*电机控制命令反馈数据5*/
    public final static String MotorAckData6 = "MotorCmdAck6";/*电机控制命令反馈数据6*/
    public final static String MotorAckData7 = "MotorCmdAck7";/*电机控制命令反馈数据7*/
    public final static String MotorAckData8 = "MotorCmdAck8";/*电机控制命令反馈数据8*/
    public final static String MotorAckData9 = "MotorCmdAck9";/*电机控制命令反馈数据9*/
    public final static String MotorAckData10 = "MotorAckData10";/*电机控制命令反馈数据9*/
    public final static String MotorAckData11 = "MotorAckData11";/*电机控制命令反馈数据9*/
    public final static String MotorAckData12 = "MotorAckData12";/*电机控制命令反馈数据9*/
    public final static String MotorAckData13 = "MotorAckData13";/*电机控制命令反馈数据9*/
    public final static String MotorAckData14 = "MotorAckData14";/*电机控制命令反馈数据9*/
    public final static String MotorAckData15 = "MotorAckData15";/*电机控制命令反馈数据9*/
    public final static String MotorAckData16 = "MotorAckData16";/*电机控制命令反馈数据9*/
    public final static String MotorAckData17 = "MotorAckData17";/*电机控制命令反馈数据9*/
    public final static String MotorAckData18 = "MotorAckData18";/*电机控制命令反馈数据9*/
    public final static String MotorAckData19 = "MotorAckData19";/*电机控制命令反馈数据9*/
    public final static String MotorAckData20 = "MotorAckData20";/*电机控制命令反馈数据9*/
    public final static String MotorAckData21 = "MotorAckData21";/*电机控制命令反馈数据9*/
    public final static String MotorAckData22 = "MotorAckData22";/*电机控制命令反馈数据9*/
    public final static String MotorAckData23 = "MotorAckData23";/*电机控制命令反馈数据9*/
    public final static String MotorAckData24 = "MotorAckData24";/*电机控制命令反馈数据9*/
    public final static String MotorAckData25 = "MotorAckData25";/*电机控制命令反馈数据9*/
    public final static String MotorAckData26 = "MotorAckData26";/*电机控制命令反馈数据9*/
    public final static String MotorAckData27 = "MotorAckData27";/*电机控制命令反馈数据9*/
    public final static String MotorAckData28 = "MotorAckData28";/*电机控制命令反馈数据9*/
    public final static String MotorAckData29 = "MotorAckData29";/*电机控制命令反馈数据9*/
    public final static String MotorAckData30 = "MotorAckData30";/*电机控制命令反馈数据9*/
    public final static String MotorAckData31 = "MotorAckData31";/*电机控制命令反馈数据9*/
    public final static String MotorAckData32 = "MotorAckData32";/*电机控制命令反馈数据9*/
    public final static String MotorAckData33 = "MotorAckData33";/*电机控制命令反馈数据9*/
    public final static String MotorAckData34 = "MotorAckData34";/*电机控制命令反馈数据9*/
    public final static String MotorAckData35 = "MotorAckData35";/*电机控制命令反馈数据9*/
    public final static String MotorAckData36 = "MotorAckData36";/*电机控制命令反馈数据9*/
    public final static String MotorAckData37 = "MotorAckData37";/*电机控制命令反馈数据9*/
    public final static String MotorAckData38 = "MotorAckData38";/*电机控制命令反馈数据9*/
    public final static String MotorAckData39 = "MotorAckData39";/*电机控制命令反馈数据9*/
    public final static String MotorAckData40 = "MotorAckData40";/*电机控制命令反馈数据9*/
    public final static String MotorAckData41 = "MotorAckData41";/*电机控制命令反馈数据9*/
    public final static String MotorAckData42 = "MotorAckData42";/*电机控制命令反馈数据9*/
    public final static String MotorAckData43 = "MotorAckData43";/*电机控制命令反馈数据9*/

    public final static String MotorAckData50 = "MotorAckData50";
    public final static String MotorAckData51 = "MotorAckData51";
    public final static String MotorAckData52 = "MotorAckData52";
    public final static String MotorAckData53 = "MotorAckData53";
    public final static String MotorAckData54 = "MotorAckData54";
    public final static String MotorAckData55 = "MotorAckData55";
    public final static String MotorAckData56 = "MotorAckData56";
    public final static String MotorAckData57 = "MotorAckData57";
    public final static String MotorAckData58 = "MotorAckData58";
    public final static String MotorAckData59 = "MotorAckData59";
    public final static String MotorAckData60 = "MotorAckData60";
    public final static String MotorAckData61 = "MotorAckData61";
    public final static String MotorAckData62 = "MotorAckData62";
    public final static String MotorAckData63 = "MotorAckData63";
    public final static String MotorAckData64 = "MotorAckData64";
    public final static String MotorAckData65 = "MotorAckData65";
    public final static String MotorAckData66 = "MotorAckData66";
    public final static String MotorAckData67 = "MotorAckData67";

    //send
    public final static String EventPLatformMessage = "EventPLatformMessage";
    public final static String EventAccelerateTime = "EventAccelerateTime";
    public final static String EventDrumSpeed = "EventDrumSpeed";
    //recv
    public final static String SoftwareVersion_A1 = "SoftwareVersion_A1";
    public final static String MotorNumber_A1 = "MotorNumber_A1";
    public final static String IPMTemperature_A2 = "IPMTemperature_A2";
    public final static String FaultCode_A2 = "FaultCode_A2";
    public final static String DrumSpeed_A2 = "DrumSpeed_A2";
    public final static String Load_A2 = "Load_A2";
    public final static String OOB_A2 = "OOB_A2";
    public final static String IPMTemperature_A4 = "IPMTemperature_A4";
    public final static String FaultCode_A4 = "FaultCode_A4";
    public final static String DrumSpeed_A4 = "DrumSpeed_A4";
    public final static String Load_A4 = "Load_A4";
    public final static String OOB_A4 = "OOB_A4";
    public final static String Current_A4 = "Current_A4";
    public final static String DCBusVoltage_A4 = "DCBusVoltage_A4";
    public final static String SoftwareVersion_A7 = "SoftwareVersion_A7";



    public final static String RemoteControlServerStatus = "RemoteControlServerStatus";

    public final static String RCSS_ONLINE = "1";
    public final static String RCSS_OFFLINE = "0";

    public static final String FORCE_REFRESH = "FORCE_REFRESH";     // 强制刷新

    public static final String SYNC_FOOD = "SYNC_FOOD";

    // 临时的操作
    public final static String ForceStatUpload = "ForceStatUpload"; // 强制提交当前状态

    public UMDB() {

    }

    public void initDefault(){

        addValue(ForceStatUpload, 0);

        addValue(PadCommErr, 0);
        addValue(WiFiCommErr, 0);
        addValue(WiFiConnMainErr, 0);
        addValue(WiFiConnRounterErr, 0);
        addValue(CommChkSumErrCount, 0);
        addValue(CommNoResponseCount, 0);
        addValue(CommCount, 0);

        addValue(RemoteControlServerStatus, 0);

        addValue(MotorCmd, 0);
        addValue(MotorCmdData0, 0);
        addValue(MotorCmdData1, 0);
        addValue(MotorCmdData2, 0);
        addValue(MotorCmdData3, 0);
        addValue(MotorCmdData4, 0);
        addValue(MotorCmdData5, 0);
        addValue(MotorCmdData6, 0);
        addValue(MotorCmdData7, 0);
        addValue(MotorCmdData8, 0);
        addValue(MotorCmdData9, 0);
        addValue(MotorCmdData10, 0);
        addValue(MotorCmdData11, 0);
        addValue(MotorCmdData12, 0);
        addValue(MotorCmdData13, 0);
        addValue(MotorCmdData14, 0);
        addValue(MotorCmdData15, 0);
        addValue(MotorCmdData16, 0);
        addValue(MotorCmdData17, 0);
        addValue(MotorCmdData18, 0);
        addValue(MotorCmdData19, 0);
        addValue(MotorCmdData20, 0);
        addValue(MotorCmdData21, 0);
        addValue(MotorCmdData22, 0);
        addValue(MotorCmdData23, 0);
        addValue(MotorCmdData24, 0);
        addValue(MotorCmdData25, 0);
        addValue(MotorCmdData26, 0);
        addValue(MotorCmdData27, 0);
        addValue(MotorCmdData28, 0);
        addValue(MotorCmdData29, 0);
        addValue(MotorCmdData30, 0);
        addValue(MotorCmdData31, 0);
        addValue(MotorCmdData32, 0);
        addValue(MotorCmdData33, 0);
        addValue(MotorCmdData34, 0);
        addValue(MotorCmdData35, 0);
        addValue(MotorCmdData36, 0);
        addValue(MotorCmdData37, 0);
        addValue(MotorCmdData38, 0);
        addValue(MotorCmdData39, 0);
        addValue(MotorCmdData40, 0);
        addValue(MotorCmdData41, 0);
        addValue(MotorAckData0, 0);
        addValue(MotorAckData1, 0);
        addValue(MotorAckData2, 0);
        addValue(MotorAckData3, 0);
        addValue(MotorAckData4, 0);
        addValue(MotorAckData5, 0);
        addValue(MotorAckData6, 0);
        addValue(MotorAckData7, 0);
        addValue(MotorAckData8, 0);
        addValue(MotorAckData9, 0);
        addValue(MotorAckData10, 0);
        addValue(MotorAckData11, 0);
        addValue(MotorAckData12, 0);
        addValue(MotorAckData13, 0);
        addValue(MotorAckData14, 0);
        addValue(MotorAckData15, 0);
        addValue(MotorAckData16, 0);
        addValue(MotorAckData17, 0);
        addValue(MotorAckData18, 0);
        addValue(MotorAckData19, 0);
        addValue(MotorAckData20, 0);
        addValue(MotorAckData21, 0);
        addValue(MotorAckData22, 0);
        addValue(MotorAckData23, 0);
        addValue(MotorAckData24, 0);
        addValue(MotorAckData25, 0);
        addValue(MotorAckData26, 0);
        addValue(MotorAckData27, 0);
        addValue(MotorAckData28, 0);
        addValue(MotorAckData29, 0);
        addValue(MotorAckData30, 0);
        addValue(MotorAckData31, 0);
        addValue(MotorAckData32, 0);
        addValue(MotorAckData33, 0);
        addValue(MotorAckData34, 0);
        addValue(MotorAckData35, 0);
        addValue(MotorAckData36, 0);
        addValue(MotorAckData37, 0);
        addValue(MotorAckData38, 0);
        addValue(MotorAckData39, 0);
        addValue(MotorAckData40, 0);
        addValue(MotorAckData41, 0);
        addValue(MotorAckData42, 0);
        addValue(MotorAckData43, 0);

        addValue(MotorAckData50, 0);
        addValue(MotorAckData51, 0);
        addValue(MotorAckData52, 0);
        addValue(MotorAckData53, 0);
        addValue(MotorAckData54, 0);
        addValue(MotorAckData55, 0);
        addValue(MotorAckData56, 0);
        addValue(MotorAckData57, 0);
        addValue(MotorAckData58, 0);
        addValue(MotorAckData59, 0);
        addValue(MotorAckData60, 0);
        addValue(MotorAckData61, 0);
        addValue(MotorAckData62, 0);
        addValue(MotorAckData63, 0);
        addValue(MotorAckData64, 0);
        addValue(MotorAckData65, 0);
        addValue(MotorAckData66, 0);
        addValue(MotorAckData67, 0);

        addValue(EventPLatformMessage, 0);
        addValue(EventAccelerateTime, 0);
        addValue(EventDrumSpeed, 0);

        if (isUpdateToEmpty()) {

            /**
             * 需要上报服务器的模式
             */
            setUpdateTo(PadCommErr, REPORT_FROM_SERIAL|UPDATE_TO_SERVER | UPDATE_TO_UI);
            setUpdateTo(WiFiCommErr, REPORT_FROM_SERIAL|UPDATE_TO_SERVER | UPDATE_TO_UI);
            setUpdateTo(WiFiConnMainErr, REPORT_FROM_SERIAL|UPDATE_TO_SERVER | UPDATE_TO_UI);
            setUpdateTo(WiFiConnRounterErr, REPORT_FROM_SERIAL|UPDATE_TO_SERVER | UPDATE_TO_UI);
//            setUpdateTo(CommChkSumErrCount, REPORT_FROM_SERIAL | UPDATE_TO_UI);
//            setUpdateTo(CommNoResponseCount, REPORT_FROM_SERIAL | UPDATE_TO_UI);
//            setUpdateTo(CommCount, REPORT_FROM_SERIAL | UPDATE_TO_UI);

            setUpdateTo(ForceStatUpload, REPORT_FROM_SERIAL|UPDATE_TO_SERVER | UPDATE_TO_UI);

            setUpdateTo(MotorCmd, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorCmdDataChk, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorCmdDataStx, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorCmdDataEtx, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorCmdDataSrcAddr, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorCmdDataDestAddr, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAck, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData0, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData1, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData2, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData3, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData4, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData5, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData6, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData7, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData8, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData9, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData10, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData11, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData12, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData13, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData14, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData15, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData16, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData17, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData18, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData19, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData20, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData21, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData22, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData23, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData24, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData25, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData26, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData27, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData28, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData29, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData30, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData31, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData32, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData33, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData34, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData35, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData36, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData37, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData38, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData39, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData40, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData41, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData42, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData43, REPORT_FROM_SERIAL| UPDATE_TO_UI);
            setUpdateTo(MotorAckData50, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(MotorAckData51, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(MotorAckData52, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(MotorAckData53, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(MotorAckData54, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(MotorAckData55, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(MotorAckData56, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(MotorAckData57, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(MotorAckData58, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(MotorAckData59, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(MotorAckData60, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(MotorAckData61, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(MotorAckData62, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(MotorAckData63, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(MotorAckData64, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(MotorAckData65, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(MotorAckData66, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(MotorAckData67, REPORT_FROM_SERIAL | UPDATE_TO_UI);

            setUpdateTo(SoftwareVersion_A1, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(MotorNumber_A1, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(IPMTemperature_A2, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(FaultCode_A2, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(DrumSpeed_A2, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(Load_A2, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(OOB_A2, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(IPMTemperature_A4, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(FaultCode_A4, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(DrumSpeed_A4, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(Load_A4, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(OOB_A4, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(Current_A4, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(DCBusVoltage_A4, REPORT_FROM_SERIAL | UPDATE_TO_UI);
            setUpdateTo(SoftwareVersion_A7, REPORT_FROM_SERIAL | UPDATE_TO_UI);
        }

    }

    public void readFromParcel(Parcel in) {
        Bundle bundle = in.readBundle();
        m_db = (HashMap<String, String>) bundle.getSerializable("map");
    }
    public UMDB(Parcel in)
    {
        readFromParcel(in);
    }
    private static String[] m_sErrorKey ;
    static
    {
        m_sErrorKey = new String[]{
                PadCommErr,
                WiFiCommErr,
                WiFiConnMainErr,
                WiFiConnRounterErr
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("map", m_db);
        dest.writeBundle(bundle);
    }

    public static final Creator<UMDB> CREATOR = new Creator<UMDB>()
    {
        @Override
        public UMDB createFromParcel(Parcel source) {
            return new UMDB(source);
        }

        @Override
        public UMDB[] newArray(int size) {
            return new UMDB[size];
        }

    };

    public List<String> getErrorStatus(){
        List<String> ret = new ArrayList<>();
        for (String key : m_sErrorKey)
        {
            if (m_db.containsKey(key) && "1".equals(getValue(key)))
            {
                ret.add(key);
            }
        }
        return ret;
    }

    public UMDB getErrorDB(){
        UMDB dberr = new UMDB();
        for (String key : m_sErrorKey)
        {
            if (m_db.containsKey(key) && "1".equals(getValue(key)))
            {
                dberr.setValue(key, "1");
            }
        }
        return dberr;
    }

    public boolean hasErrorStatus(){
        return !getErrorStatus().isEmpty();
    }

    /**
     * @return
     */
    public static UMDB createEmpty(){
        return new UMDB();
    }
}
