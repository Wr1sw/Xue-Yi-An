package org.cuit.xueyian.model;

public class SalaryGrantDetails {
    private Integer grdId;

    private String salaryGrantId;

    private Integer eid;

    private String eName;

    private Integer bounsSum;

    private Integer perSum;

    private Integer deductSum;

    private Integer salaryStandardSum;

    private Integer salaryPaidSum;

    public Integer getGrdId() {
        return grdId;
    }

    public void setGrdId(Integer grdId) {
        this.grdId = grdId;
    }

    public String getSalaryGrantId() {
        return salaryGrantId;
    }

    public void setSalaryGrantId(String salaryGrantId) {
        this.salaryGrantId = salaryGrantId == null ? null : salaryGrantId.trim();
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName == null ? null : eName.trim();
    }

    public Integer getBounsSum() {
        return bounsSum;
    }

    public void setBounsSum(Integer bounsSum) {
        this.bounsSum = bounsSum;
    }

    public Integer getPerSum() {
        return perSum;
    }

    public void setPerSum(Integer perSum) {
        this.perSum = perSum;
    }

    public Integer getDeductSum() {
        return deductSum;
    }

    public void setDeductSum(Integer deductSum) {
        this.deductSum = deductSum;
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
}