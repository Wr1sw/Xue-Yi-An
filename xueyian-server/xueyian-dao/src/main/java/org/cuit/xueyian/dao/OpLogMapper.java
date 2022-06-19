package org.cuit.xueyian.dao;

import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;
import org.cuit.xueyian.model.OpLog;

import java.util.List;

@Mapper
public interface OpLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OpLog record);

    int insertSelective(OpLog record);

    OpLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OpLog record);

    int updateByPrimaryKey(OpLog record);

    List<OpLog> getAllOplog();

    Integer deleteOplogByIds(@Param("ids") Integer[] ids);
}