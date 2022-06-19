package org.cuit.xueyian.dao;


import org.apache.ibatis.annotations.Mapper;
import org.cuit.xueyian.model.Salary;
import org.cuit.xueyian.model.SalaryStandard;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SalaryStandardMapper {
    int deleteByPrimaryKey(Integer ssdId);

    int insert(SalaryStandard record);

    int insertSelective(SalaryStandard record);

    SalaryStandard selectByPrimaryKey(Integer ssdId);

    int updateByPrimaryKeySelective(SalaryStandard record);

    int updateByPrimaryKey(SalaryStandard record);

    Integer deleteByStdId(Integer id);

    List<SalaryStandard> getAllSalaryStanderNoApprove();

    List<Salary> getAllSalaryStanders();

}