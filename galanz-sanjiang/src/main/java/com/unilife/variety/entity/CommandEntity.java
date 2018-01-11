package com.unilife.variety.entity;


import com.unilife.common.entities.UMDB;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/11/29
 * @time: 下午5:32
 */

public class CommandEntity {

    public CommandEntity() {
    }

    public CommandEntity(boolean recycle, int direction) {
        this.recycle = recycle;
        this.direction = direction;
    }

    public CommandEntity(int runTime, UMDB fridgeDB, int direction) {
        this.runTime = runTime;
        this.mFridgeDB = fridgeDB;
        this.direction = direction;
    }

    private int runTime;
    private UMDB mFridgeDB;
    private int direction;

    private boolean recycle = false;

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public UMDB getFridgeDB() {
        return mFridgeDB;
    }

    public void setFridgeDB(UMDB fridgeDB) {
        this.mFridgeDB = fridgeDB;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isRecycle() {
        return recycle;
    }

    public void setRecycle(boolean recycle) {
        this.recycle = recycle;
    }
}
