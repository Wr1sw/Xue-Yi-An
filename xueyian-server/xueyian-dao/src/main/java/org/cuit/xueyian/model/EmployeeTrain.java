package org.cuit.xueyian.model;


public class EmployeeTrain {
    private Integer id;

    /**
     * 员工工号
     */
    private Integer eid;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门编号
     */
    private Integer departId;

    /**
     * 培训id
     */
    private Integer trainId;

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

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    @Override
    public String toString() {
        return "EmployeeTrain{" +
                "id=" + id +
                ", eid=" + eid +
                ", remark='" + remark + '\'' +
                ", departId=" + departId +
                ", trainId=" + trainId +
                '}';
    }
}
