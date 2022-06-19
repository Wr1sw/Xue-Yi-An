package org.cuit.xueyian.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * <p>
 * 辞职信息实体类
 * </p>
 *
 * @author Miracle
 * @since 2022-05-23 18:59:47
 */
public class ResignationInfo {

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

    /**
     * 员工聘用形式
     */
    private String engageForm;

    /**
     * 员工入职日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date beginDate;

    /**
     * 员工离职日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date notWorkDate;

    /**
     * 员工职位等价
     */
    private String jobLevel;

    /**
     * 员工头衔
     */
    private String pos;

    /**
     * 员工工龄
     */
    private Integer workAge;

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

    public String getEngageForm() {
        return engageForm;
    }

    public void setEngageForm(String engageForm) {
        this.engageForm = engageForm;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getNotWorkDate() {
        return notWorkDate;
    }

    public void setNotWorkDate(Date notWorkDate) {
        this.notWorkDate = notWorkDate;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public Integer getWorkAge() {
        return workAge;
    }

    public void setWorkAge(Integer workAge) {
        this.workAge = workAge;
    }

    public ResignationInfo(){

    }

    public ResignationInfo(Employee employee, String department, String pos, String jobLevel){
        this.name = employee.getName();
        this.gender = employee.getGender();
        this.idCard = employee.getIdcard();
        this.department =department;
        this.pos = pos;
        this.jobLevel = jobLevel;
        this.engageForm = employee.getEngageform();
        this.beginDate = employee.getBegindate();
        this.workId = employee.getWorkid();
        this.notWorkDate = employee.getNotworkdate();
        this.workAge = employee.getWorkage();
    }
}
