package org.cuit.xueyian.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class TrainInfo {

    /**
     * 员工培训表主键
     */
    private Integer id;

    /**
     * 员工工号
     */
    private Integer eid;

    /**
     * 员工姓名
     */
    private String empName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门编号
     */
    private Integer departId;

    /**
     * 部门名称
     */
    private String departName;

    /**
     * 培训id
     */
    private Integer trainId;

    /**
     * 培训名称
     */
    private String trainName;

    /**
     * 培训内容
     */
    private String content;

    /**
     * 培训开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date beginDate;

    /**
     * 培训结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date endDate;


    @Override
    public String toString() {
        return "TrainInfo{" +
                "id=" + id +
                ", eid=" + eid +
                ", empName='" + empName + '\'' +
                ", remark='" + remark + '\'' +
                ", departId=" + departId +
                ", departName='" + departName + '\'' +
                ", trainId=" + trainId +
                ", trainName='" + trainName + '\'' +
                ", content='" + content + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                '}';
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
