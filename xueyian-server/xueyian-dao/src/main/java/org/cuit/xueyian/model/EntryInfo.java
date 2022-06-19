package org.cuit.xueyian.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 入职信息实体类
 *
 * @author Miracle
 * @since 2022-05-23 18:59:47
 */
public class EntryInfo {

    /**
     * 员工工号
     */
    private String workId;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 员工性别
     */
    private String gender;

    /**
     * 员工身份证号
     */
    private String idCard;

    /**
     * 员工所属部门名称
     */
    private String department;
//
//    /**
//     * 员工入职的审批人
//     */
//    private String HR;

    /**
     * 员工聘用形式
     */
    private String engageForm;

    /**
     * 员工最高学历
     */
    private String tiptopDegree;

    /**
     * 员工专业
     */
    private String specialty;

    /**
     * 员工毕业学校
     */
    private String school;

    /**
     * 员工入职日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date beginDate;

    /**
     * 员工合同期限
     */
    private Double contractTerm;

    /**
     * 员工转正日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date conversionTime;

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

//    public String getHR() {
//        return HR;
//    }


    public String getEngageForm() {
        return engageForm;
    }

    public void setEngageForm(String engageForm) {
        this.engageForm = engageForm;
    }

    public String getTiptopDegree() {
        return tiptopDegree;
    }

    public void setTiptopDegree(String tiptopDegree) {
        this.tiptopDegree = tiptopDegree;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Double getContractTerm() {
        return contractTerm;
    }

    public void setContractTerm(Double contractTerm) {
        this.contractTerm = contractTerm;
    }

    public Date getConversionTime() {
        return conversionTime;
    }

    public void setConversionTime(Date conversionTime) {
        this.conversionTime = conversionTime;
    }

    public EntryInfo(){

    }

    public EntryInfo(Employee employee, String department){
        this.department = department;
        this.beginDate = employee.getBegindate();
        this.contractTerm = employee.getContractterm();
        this.name = employee.getName();
        this.gender = employee.getGender();
        this.idCard = employee.getIdcard();
        this.engageForm = employee.getEngageform();
        this.tiptopDegree = employee.getTiptopdegree();
        this.specialty = employee.getSpecialty();
        this.school = employee.getSchool();
        this.workId = employee.getWorkid();
        this.conversionTime = employee.getConversiontime();
    }
}
