package org.cuit.xueyian.model;

import java.util.Date;

public class SalaryGrant {
    private Integer sgrId;

    private String salaryGrantId;

    private String salaryStandardId;

    private Integer salaryStandardSum;

    private Integer salaryPaidSum;

    private String register;

    private Date registTime;

    private String checker;

    private Date checkTime;

    private String checkStatus;

    public Integer getSgrId() {
        return sgrId;
    }

    public void setSgrId(Integer sgrId) {
        this.sgrId = sgrId;
    }

    public String getSalaryGrantId() {
        return salaryGrantId;
    }

    public void setSalaryGrantId(String salaryGrantId) {
        this.salaryGrantId = salaryGrantId == null ? null : salaryGrantId.trim();
    }

    public String getSalaryStandardId() {
        return salaryStandardId;
    }

    public void setSalaryStandardId(String salaryStandardId) {
        this.salaryStandardId = salaryStandardId == null ? null : salaryStandardId.trim();
    }

    public Integer getSalaryStandardSum() {
        return salaryStandardSum;
    }

    public void setSalaryStandardSum(Integer salaryStandardSum) {
        this.salaryStandardSum = salaryStandardSum;
    }

    public Integer getSalaryPaidSum() {
        return salaryPaidSum;
    }

    public void setSalaryPaidSum(Integer salaryPaidSum) {
        this.salaryPaidSum = salaryPaidSum;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register == null ? null : register.trim();
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker == null ? null : checker.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus == null ? null : checkStatus.trim();
    }
}