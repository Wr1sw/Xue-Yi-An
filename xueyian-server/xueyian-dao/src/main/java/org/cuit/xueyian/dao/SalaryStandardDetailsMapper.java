package org.cuit.xueyian.dao;


import org.apache.ibatis.annotations.Mapper;
import org.cuit.xueyian.model.Salary;
import org.cuit.xueyian.model.SalaryStandardDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SalaryStandardDetailsMapper {
    int deleteByPrimaryKey(Integer sdtId);

    int insert(SalaryStandardDetails record);

    int insertSelective(SalaryStandardDetails record);

    SalaryStandardDetails selectByPrimaryKey(Integer sdtId);

    int updateByPrimaryKeySelective(SalaryStandardDetails record);

    int updateByPrimaryKey(SalaryStandardDetails record);

    List<SalaryStandardDetails> getAllSalaryStanderDetailsWithCheck();

    SalaryStandardDetails getStandardDetailsByStanderId(String standardId);
}