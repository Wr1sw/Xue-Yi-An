package org.cuit.xueyian.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Employeeremove {

    private Integer id;

    private Integer eid;

    private Integer afterdepid;

    private Integer afterjobid;

    private Integer afterposid;

    private Integer beforedepid;

    private Integer beforejobid;

    private Integer beforeposid;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date removedate;

    private String reason;

    private String remark;


    // 聚合 emp/dep/pos/job名称

    private  String name;
    private  String beforedepName;
    private  String afterdepName;
    private  String beforejobName;
    private  String afterjobName;
    private  String beforeposName;
    private  String afterposName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getAfterdepid() {
        return afterdepid;
    }

    public void setAfterdepid(Integer afterdepid) {
        this.afterdepid = afterdepid;
    }

    public Integer getAfterjobid() {
        return afterjobid;
    }

    public void setAfterjobid(Integer afterjobid) {
        this.afterjobid = afterjobid;
    }

    public Integer getAfterposid() {
        return afterposid;
    }

    public void setAfterposid(Integer afterposid) {
        this.afterposid = afterposid;
    }

    public Integer getBeforedepid() {
        return beforedepid;
    }

    public void setBeforedepid(Integer beforedepid) {
        this.beforedepid = beforedepid;
    }

    public Integer getBeforejobid() {
        return beforejobid;
    }

    public void setBeforejobid(Integer beforejobid) {
        this.beforejobid = beforejobid;
    }

    public Integer getBeforeposid() {
        return beforeposid;
    }

    public void setBeforeposid(Integer beforeposid) {
        this.beforeposid = beforeposid;
    }

    public Date getRemovedate() {
        return removedate;
    }

    public void setRemovedate(Date removedate) {
        this.removedate = removedate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBeforedepName() {
        return beforedepName;
    }

    public void setBeforedepName(String beforedepName) {
        this.beforedepName = beforedepName;
    }

    public String getAfterdepName() {
        return afterdepName;
    }

    public void setAfterdepName(String afterdepName) {
        this.afterdepName = afterdepName;
    }

    public String getBeforejobName() {
        return beforejobName;
    }

    public void setBeforejobName(String beforejobName) {
        this.beforejobName = beforejobName;
    }

    public String getAfterjobName() {
        return afterjobName;
    }

    public void setAfterjobName(String afterjobName) {
        this.afterjobName = afterjobName;
    }

    public String getBeforeposName() {
        return beforeposName;
    }

    public void setBeforeposName(String beforeposName) {
        this.beforeposName = beforeposName;
    }

    public String getAfterposName() {
        return afterposName;
    }

    public void setAfterposName(String afterposName) {
        this.afterposName = afterposName;
    }
}