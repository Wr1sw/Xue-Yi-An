package org.cuit.xueyian.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Employeeec {
    private Integer id;

    private Integer eid;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date ecdate;

    private String remark;

    private Integer rid;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public void setEcdate(Date ecdate) {
        this.ecdate = ecdate;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}