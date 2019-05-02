package com.oriole.motaserver.model;

public class AdvertisementBase {

    private String aid;
    private String adName;
    private String adFile;
    private String adSupplierID;
    private String adResidualTimes;
    private String adStartData;
    private String adEndData;
    private String examineFlag;
    private String paymentFlag;
    private String effectiveFlag;

    public AdvertisementBase(String aid, String adName, String adFile, String adSupplierID, String adResidualTimes, String adStartData, String adEndData, String examineFlag, String paymentFlag, String effectiveFlag) {
        this.aid = aid;
        this.adName = adName;
        this.adFile = adFile;
        this.adSupplierID = adSupplierID;
        this.adResidualTimes = adResidualTimes;
        this.adStartData = adStartData;
        this.adEndData = adEndData;
        this.examineFlag = examineFlag;
        this.paymentFlag = paymentFlag;
        this.effectiveFlag = effectiveFlag;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdFile() {
        return adFile;
    }

    public void setAdFile(String adFile) {
        this.adFile = adFile;
    }

    public String getAdSupplierID() {
        return adSupplierID;
    }

    public void setAdSupplierID(String adSupplierID) {
        this.adSupplierID = adSupplierID;
    }

    public String getAdResidualTimes() {
        return adResidualTimes;
    }

    public void setAdResidualTimes(String adResidualTimes) {
        this.adResidualTimes = adResidualTimes;
    }

    public String getAdStartData() {
        return adStartData;
    }

    public void setAdStartData(String adStartData) {
        this.adStartData = adStartData;
    }

    public String getAdEndData() {
        return adEndData;
    }

    public void setAdEndData(String adEndData) {
        this.adEndData = adEndData;
    }

    public String getExamineFlag() {
        return examineFlag;
    }

    public void setExamineFlag(String examineFlag) {
        this.examineFlag = examineFlag;
    }

    public String getPaymentFlag() {
        return paymentFlag;
    }

    public void setPaymentFlag(String paymentFlag) {
        this.paymentFlag = paymentFlag;
    }

    public String getEffectiveFlag() {
        return effectiveFlag;
    }

    public void setEffectiveFlag(String effectiveFlag) {
        this.effectiveFlag = effectiveFlag;
    }
}
