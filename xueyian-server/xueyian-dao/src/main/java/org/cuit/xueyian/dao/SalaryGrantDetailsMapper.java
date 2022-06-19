package org.cuit.xueyian.dao;


import org.apache.ibatis.annotations.Mapper;
import org.cuit.xueyian.model.SalaryGrantDetails;
import org.cuit.xueyian.model.SalaryTableDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface SalaryGrantDetailsMapper {
    int deleteByPrimaryKey(Integer grdId);

    int insert(SalaryGrantDetails record);

    int insertSelective(SalaryGrantDetails record);

    SalaryGrantDetails selectByPrimaryKey(Integer grdId);

    int updateByPrimaryKeySelective(SalaryGrantDetails record);

    int updateByPrimaryKey(SalaryGrantDetails record);

    List<SalaryTableDto> getSalaryTableWithSidSet(Set<Integer> sidSet);

    //查询状态为0的数据
    List<SalaryGrantDetails> querySal();
}