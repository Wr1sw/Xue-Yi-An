package org.cuit.xueyian.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description 工资表dto
 */
public class SalaryTableDto {
    /**
     * 工号
     */
    private String workID;

    /**
     * 姓名
     */
    private String ename;

    /**
     * 工资账套id
     */
    private Integer sid;

    @JsonIgnore
    private String standardId;

    @JsonIgnore
    private String standardName;

    private Integer basicSalary;

    private Integer lunchSalary;

    private Integer trafficSalary;

    private Integer pensionBase;

    private Double pensionPer;

    private Double medicalPer;

    private Integer medicalBase;

    private Integer accumulationFundBase;

    private Double accumulationFundPer;

    private Date createDate;

    // 应发工资
    private String salarySum;

    // 实发工资
    private Integer allSalary;

    public String getSalarySum() {
        return salarySum;
    }

    public void setSalarySum(String salarySum) {
        this.salarySum = salarySum;
    }

    public Integer getAllSalary() {
        return allSalary;
    }

    public void setAllSalary(Integer allSalary) {
        this.allSalary = allSalary;
    }

    public String getWorkID() {
        return workID;
    }

    public void setWorkID(String workID) {
        this.workID = workID;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getStandardId() {
        return standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
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

    public Integer getPensionBase() {
        return pensionBase;
    }

    public void setPensionBase(Integer pensionBase) {
        this.pensionBase = pensionBase;
    }

    public Double getPensionPer() {
        return pensionPer;
    }

    public void setPensionPer(Double pensionPer) {
        this.pensionPer = pensionPer;
    }

    public Double getMedicalPer() {
        return medicalPer;
    }

    public void setMedicalPer(Double medicalPer) {
        this.medicalPer = medicalPer;
    }

    public Integer getMedicalBase() {
        return medicalBase;
    }

    public void setMedicalBase(Integer medicalBase) {
        this.medicalBase = medicalBase;
    }

    public Integer getAccumulationFundBase() {
        return accumulationFundBase;
    }

    public void setAccumulationFundBase(Integer accumulationFundBase) {
        this.accumulationFundBase = accumulationFundBase;
    }

    public Double getAccumulationFundPer() {
        return accumulationFundPer;
    }

    public void setAccumulationFundPer(Double accumulationFundPer) {
        this.accumulationFundPer = accumulationFundPer;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "SalaryTableDto{" +
                "workID='" + workID + '\'' +
                ", ename='" + ename + '\'' +
                ", sid=" + sid +
                ", standardId='" + standardId + '\'' +
                ", standardName='" + standardName + '\'' +
                ", basicSalary=" + basicSalary +
                ", lunchSalary=" + lunchSalary +
                ", trafficSalary=" + trafficSalary +
                ", pensionBase=" + pensionBase +
                ", pensionPer=" + pensionPer +
                ", medicalPer=" + medicalPer +
                ", medicalBase=" + medicalBase +
                ", accumulationFundBase=" + accumulationFundBase +
                ", accumulationFundPer=" + accumulationFundPer +
                ", createDate=" + createDate +
                '}';
    }
}
