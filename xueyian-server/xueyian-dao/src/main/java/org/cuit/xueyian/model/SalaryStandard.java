package org.cuit.xueyian.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SalaryStandard {
    private Integer ssdId;

    private String standardId;

    private String standardName;

    private String designer;

    private String register;

    private String checker;

    private String changer;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date registTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date checkTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date changeTime;

    private String salarySum;

    private String checkStatus;

    private String changeStatus;

    private String checkComment;

    private String remark;

    public Integer getSsdId() {
        return ssdId;
    }

    public void setSsdId(Integer ssdId) {
        this.ssdId = ssdId;
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

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer == null ? null : designer.trim();
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register == null ? null : register.trim();
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker == null ? null : checker.trim();
    }

    public String getChanger() {
        return changer;
    }

    public void setChanger(String changer) {
        this.changer = changer == null ? null : changer.trim();
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getSalarySum() {
        return salarySum;
    }

    public void setSalarySum(String salarySum) {
        this.salarySum = salarySum == null ? null : salarySum.trim();
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus == null ? null : checkStatus.trim();
    }

    public String getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus == null ? null : changeStatus.trim();
    }

    public String getCheckComment() {
        return checkComment;
    }

    public void setCheckComment(String checkComment) {
        this.checkComment = checkComment == null ? null : checkComment.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "SalaryStandard{" +
                "ssdId=" + ssdId +
                ", standardId='" + standardId + '\'' +
                ", standardName='" + standardName + '\'' +
                ", designer='" + designer + '\'' +
                ", register='" + register + '\'' +
                ", checker='" + checker + '\'' +
                ", changer='" + changer + '\'' +
                ", registTime=" + registTime +
                ", checkTime=" + checkTime +
                ", changeTime=" + changeTime +
                ", salarySum='" + salarySum + '\'' +
                ", checkStatus='" + checkStatus + '\'' +
                ", changeStatus='" + changeStatus + '\'' +
                ", checkComment='" + checkComment + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}