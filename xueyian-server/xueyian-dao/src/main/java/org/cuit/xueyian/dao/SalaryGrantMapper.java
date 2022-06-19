package org.cuit.xueyian.dao;


import org.apache.ibatis.annotations.Mapper;
import org.cuit.xueyian.model.SalaryGrant;
import org.cuit.xueyian.model.SalaryGrantDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository

public interface SalaryGrantMapper {
    int deleteByPrimaryKey(Integer sgrId);

    int insert(SalaryGrant record);

    int insertSelective(SalaryGrant record);

    SalaryGrant selectByPrimaryKey(Integer sgrId);

    int updateByPrimaryKeySelective(SalaryGrant record);

    int updateByPrimaryKey(SalaryGrant record);

    //将状态为0改成状态为1
    Integer updateSalary(String salary_grant_id);

}