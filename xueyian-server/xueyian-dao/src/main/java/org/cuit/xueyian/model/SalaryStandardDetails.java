package org.cuit.xueyian.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SalaryStandardDetails {
    private Integer sdtId;

    private String standardId;

    private String standardName;

    private Integer basicSalary;

    private Integer lunchSalary;

    private Integer trafficSalary;

    private Integer allSalary;

    private Integer pensionBase;

    private Float pensionPer;

    private Integer medicalBase;

    private Float medicalPer;

    private Integer accumulationfundBase;

    private Float accumulationfundPer;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date createDate;

    // 制订人
    private String designer;

    // 登记者
    private String register;

    // 更改者
    private String changer;

    public String getChanger() {
        return changer;
    }

    public void setChanger(String changer) {
        this.changer = changer;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public Integer getSdtId() {
        return sdtId;
    }

    public void setSdtId(Integer sdtId) {
        this.sdtId = sdtId;
    }

    public String getStandardId() {
        return standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId == null ? null : standardId.trim();
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName == null ? null : standardName.trim();
    }

    public Integer getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Integer basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Integer getLunchSalary() {
        return lunchSalary;
    }

    public void setLunchSalary(Integer lunchSalary) {
        this.lunchSalary = lunchSalary;
    }

    public Integer getTrafficSalary() {
        return trafficSalary;
    }

    public void setTrafficSalary(Integer trafficSalary) {
        this.trafficSalary = trafficSalary;
    }

    public Integer getAllSalary() {
        return allSalary;
    }

    public void setAllSalary(Integer allSalary) {
        this.allSalary = allSalary;
    }

    public Integer getPensionBase() {
        return pensionBase;
    }

    public void setPensionBase(Integer pensionBase) {
        this.pensionBase = pensionBase;
    }

    public Float getPensionPer() {
        return pensionPer;
    }

    public void setPensionPer(Float pensionPer) {
        this.pensionPer = pensionPer;
    }

    public Integer getMedicalBase() {
        return medicalBase;
    }

    public void setMedicalBase(Integer medicalBase) {
        this.medicalBase = medicalBase;
    }

    public Float getMedicalPer() {
        return medicalPer;
    }

    public void setMedicalPer(Float medicalPer) {
        this.medicalPer = medicalPer;
    }

    public Integer getAccumulationfundBase() {
        return accumulationfundBase;
    }

    public void setAccumulationfundBase(Integer accumulationfundBase) {
        this.accumulationfundBase = accumulationfundBase;
    }

    public Float getAccumulationfundPer() {
        return accumulationfundPer;
    }

    public void setAccumulationfundPer(Float accumulationfundPer) {
        this.accumulationfundPer = accumulationfundPer;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}