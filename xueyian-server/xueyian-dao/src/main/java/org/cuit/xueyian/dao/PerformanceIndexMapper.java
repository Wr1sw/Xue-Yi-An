package org.cuit.xueyian.dao;

import org.cuit.xueyian.model.PerformanceIndex;

public interface PerformanceIndexMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PerformanceIndex record);

    int insertSelective(PerformanceIndex record);

    PerformanceIndex selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PerformanceIndex record);

    int updateByPrimaryKey(PerformanceIndex record);
}