package com.oriole.motaserver.model;

import java.util.Date;

public class DeviceBase {
    private String deviceID;
    private String deviceName;
    private String deviceLocation;
    private String deviceState;

    public DeviceBase(String deviceID, String deviceName, String deviceLocation, String deviceState) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.deviceLocation = deviceLocation;
        this.deviceState = deviceState;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceLocation() {
        return deviceLocation;
    }

    public void setDeviceLocation(String deviceLocation) {
        this.deviceLocation = deviceLocation;
    }

    public String getDeviceState() {
        return deviceState;
    }

    public void setDeviceState(String deviceState) {
        this.deviceState = deviceState;
    }
}
