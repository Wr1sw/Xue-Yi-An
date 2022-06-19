package org.cuit.xueyian.model;

import java.io.Serializable;

/**
 * 旷工表(NeglectWork)实体类
 *
 * @author makejava
 * @since 2022-05-23 19:01:28
 */
public class NeglectWork implements Serializable {
    private static final long serialVersionUID = -25318939783388752L;
    /**
    * 旷工表主键
    */
    private Integer neglectId;
    /**
    * 旷工员工姓名
    */
    private String neglectWorkerName;
    /**
    * 旷工员工id
    */
    private String neglectWorkerId;
    /**
    * 旷工员工部门id
    */
    private Integer leaveWorkerDepartId;
    /**
    * 旷工日期
    */
    private Object neglectDate;
    /**
    * 旷工备注
    */
    private String neglectRemarks;


    public Integer getNeglectId() {
        return neglectId;
    }

    public void setNeglectId(Integer neglectId) {
        this.neglectId = neglectId;
    }

    public String getNeglectWorkerName() {
        return neglectWorkerName;
    }

    public void setNeglectWorkerName(String neglectWorkerName) {
        this.neglectWorkerName = neglectWorkerName;
    }

    public String getNeglectWorkerId() {
        return neglectWorkerId;
    }

    public void setNeglectWorkerId(String neglectWorkerId) {
        this.neglectWorkerId = neglectWorkerId;
    }

    public Integer getLeaveWorkerDepartId() {
        return leaveWorkerDepartId;
    }

    public void setLeaveWorkerDepartId(Integer leaveWorkerDepartId) {
        this.leaveWorkerDepartId = leaveWorkerDepartId;
    }

    public Object getNeglectDate() {
        return neglectDate;
    }

    public void setNeglectDate(Object neglectDate) {
        this.neglectDate = neglectDate;
    }

    public String getNeglectRemarks() {
        return neglectRemarks;
    }

    public void setNeglectRemarks(String neglectRemarks) {
        this.neglectRemarks = neglectRemarks;
    }

}