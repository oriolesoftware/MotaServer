package com.oriole.motaserver.model;

import java.util.Date;

public class AdvertisementUpdateFlag {

    private String deviceID;
    private Byte newADFlag;
    private Date updateTime;

    public AdvertisementUpdateFlag(String deviceID, Byte newADFlag, Date updateTime) {
        this.deviceID = deviceID;
        this.newADFlag = newADFlag;
        this.updateTime = updateTime;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public Byte getNewADFlag() {
        return newADFlag;
    }

    public void setNewADFlag(Byte newADFlag) {
        this.newADFlag = newADFlag;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}