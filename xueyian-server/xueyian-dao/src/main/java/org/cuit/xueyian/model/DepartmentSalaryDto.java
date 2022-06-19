package org.cuit.xueyian.model;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description
 */
public class DepartmentSalaryDto {
    /**
     * 人数
     */
    private Integer count;

    /**
     * 部门名称
     */
    private String departmentName;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
