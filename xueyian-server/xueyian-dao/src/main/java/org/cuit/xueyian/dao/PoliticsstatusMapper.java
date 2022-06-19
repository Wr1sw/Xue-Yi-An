package org.cuit.xueyian.dao;

import org.apache.ibatis.annotations.Mapper;
import org.cuit.xueyian.model.Politicsstatus;

import java.util.List;

@Mapper
public interface PoliticsstatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Politicsstatus record);

    int insertSelective(Politicsstatus record);

    Politicsstatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Politicsstatus record);

    int updateByPrimaryKey(Politicsstatus record);

    // 获取name id映射， 下拉框选择
    List<Politicsstatus> getNameList();


}