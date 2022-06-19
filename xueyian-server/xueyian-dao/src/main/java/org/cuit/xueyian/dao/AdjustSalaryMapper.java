package org.cuit.xueyian.dao;

import org.apache.ibatis.annotations.Mapper;
import org.cuit.xueyian.model.AdjustSalary;

@Mapper
public interface AdjustSalaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdjustSalary record);

    int insertSelective(AdjustSalary record);

    AdjustSalary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdjustSalary record);

    int updateByPrimaryKey(AdjustSalary record);
}