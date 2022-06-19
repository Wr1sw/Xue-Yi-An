package org.cuit.xueyian.model;

import java.io.Serializable;

/**
 * 请假表(AskForLeave)实体类
 *
 * @author Miracle
 * @since 2022-05-23 18:59:47
 */
public class AskForLeave implements Serializable {
    private static final long serialVersionUID = 907523642216538166L;
    /**
    * 请假表主键
    */
    private Integer leaveId;
    /**
    * 请假员工姓名
    */
    private String leaveWorkerName;
    /**
    * 请假员工联系方式
    */
    private String leaveWorkerPhone;
    /**
    * 请假员工id
    */
    private String leaveWorkerId;
    /**
    * 请假员工部门id
    */
    private Integer leaveWorkerDepartId;
    /**
    * 请假开始时间
    */
    private Object leaveStartTime;
    /**
    * 请假截止时间
    */
    private Object leaveEndTime;
    /**
    * 请假类型
    */
    private String leaveType;
    /**
    * 请假原因
    */
    private String leaveReason;
    /**
    * 经理是否同意
    */
    private Integer leaveIsHrAgree;
    /**
    * 老板是否同意
    */
    private Integer leaveIsBossAgree;


    public Integer getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Integer leaveId) {
        this.leaveId = leaveId;
    }

    public String getLeaveWorkerName() {
        return leaveWorkerName;
    }

    public void setLeaveWorkerName(String leaveWorkerName) {
        this.leaveWorkerName = leaveWorkerName;
    }

    public String getLeaveWorkerPhone() {
        return leaveWorkerPhone;
    }

    public void setLeaveWorkerPhone(String leaveWorkerPhone) {
        this.leaveWorkerPhone = leaveWorkerPhone;
    }

    public String getLeaveWorkerId() {
        return leaveWorkerId;
    }

    public void setLeaveWorkerId(String leaveWorkerId) {
        this.leaveWorkerId = leaveWorkerId;
    }

    public Integer getLeaveWorkerDepartId() {
        return leaveWorkerDepartId;
    }

    public void setLeaveWorkerDepartId(Integer leaveWorkerDepartId) {
        this.leaveWorkerDepartId = leaveWorkerDepartId;
    }

    public Object getLeaveStartTime() {
        return leaveStartTime;
    }

    public void setLeaveStartTime(Object leaveStartTime) {
        this.leaveStartTime = leaveStartTime;
    }

    public Object getLeaveEndTime() {
        return leaveEndTime;
    }

    public void setLeaveEndTime(Object leaveEndTime) {
        this.leaveEndTime = leaveEndTime;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public Integer getLeaveIsHrAgree() {
        return leaveIsHrAgree;
    }

    public void setLeaveIsHrAgree(Integer leaveIsHrAgree) {
        this.leaveIsHrAgree = leaveIsHrAgree;
    }

    public Integer getLeaveIsBossAgree() {
        return leaveIsBossAgree;
    }

    public void setLeaveIsBossAgree(Integer leaveIsBossAgree) {
        this.leaveIsBossAgree = leaveIsBossAgree;
    }

}