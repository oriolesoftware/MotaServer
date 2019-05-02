package com.oriole.motaserver.model;

import java.util.Date;

public class FileUploadCode {
    private String fileUploadCode;
    private String deviceID;
    private Date datetime;

    public FileUploadCode(String fileUploadCode, String deviceID, Date datetime) {
        this.fileUploadCode = fileUploadCode;
        this.deviceID = deviceID;
        this.datetime = datetime;
    }

    public String getFileUploadCode() {
        return fileUploadCode;
    }

    public void setFileUploadCode(String fileUploadCode) {
        this.fileUploadCode = fileUploadCode;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
