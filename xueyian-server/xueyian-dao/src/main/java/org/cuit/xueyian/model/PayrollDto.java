package org.cuit.xueyian.model;

public class PayrollDto {

    // 序号
    private Integer id;

    // 姓名
    private String name;

    // 基本工资
    private Integer basicSalary;

    // 账套奖金
    private Integer bonusBySOB;

    // 午餐补助
    private Integer lunchSalary;

    // 交通补助
    private Integer trafficSalary;

    // 养老金
    private Integer pension;

    // 医疗保险
    private Integer medical;

    // 公积金
    private Integer accumulationFund;

    // 应发工资
    private Integer allSalary;

    // 奖金（罚金)
    private Integer bonusByEmp;

    // 实发工资
    private Integer realSalary;

    // 在职天数
    private Integer workDay;

    // 部门名称
    private String departmentName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Integer basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Integer getBonusBySOB() {
        return bonusBySOB;
    }

    public void setBonusBySOB(Integer bonusBySOB) {
        this.bonusBySOB = bonusBySOB;
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

    public Integer getPension() {
        return pension;
    }

    public void setPension(Integer pension) {
        this.pension = pension;
    }

    public Integer getMedical() {
        return medical;
    }

    public void setMedical(Integer medical) {
        this.medical = medical;
    }

    public Integer getAccumulationFund() {
        return accumulationFund;
    }

    public void setAccumulationFund(Integer accumulationFund) {
        this.accumulationFund = accumulationFund;
    }

    public Integer getAllSalary() {
        return allSalary;
    }

    public void setAllSalary(Integer allSalary) {
        this.allSalary = allSalary;
    }

    public Integer getBonusByEmp() {
        return bonusByEmp;
    }

    public void setBonusByEmp(Integer bonusByEmp) {
        this.bonusByEmp = bonusByEmp;
    }

    public Integer getRealSalary() {
        return realSalary;
    }

    public void setRealSalary(Integer realSalary) {
        this.realSalary = realSalary;
    }

    public Integer getWorkDay() {
        return workDay;
    }

    public void setWorkDay(Integer workDay) {
        this.workDay = workDay;
    }
}
