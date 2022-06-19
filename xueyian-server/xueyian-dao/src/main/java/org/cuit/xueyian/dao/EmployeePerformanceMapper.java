package org.cuit.xueyian.dao;

import org.cuit.xueyian.model.EmployeePerformance;

public interface EmployeePerformanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmployeePerformance record);

    int insertSelective(EmployeePerformance record);

    EmployeePerformance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmployeePerformance record);

    int updateByPrimaryKey(EmployeePerformance record);
}