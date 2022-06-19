package org.cuit.xueyian.dao;

import org.apache.ibatis.annotations.Mapper;
import org.cuit.xueyian.model.Appraise;

@Mapper
public interface AppraiseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Appraise record);

    int insertSelective(Appraise record);

    Appraise selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Appraise record);

    int updateByPrimaryKey(Appraise record);
}