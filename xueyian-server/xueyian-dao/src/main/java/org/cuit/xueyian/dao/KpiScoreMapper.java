package org.cuit.xueyian.dao;

import org.apache.ibatis.annotations.Mapper;
import org.cuit.xueyian.model.KpiScore;

import java.util.List;

@Mapper
public interface KpiScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KpiScore record);

    int insertSelective(KpiScore record);

    KpiScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KpiScore record);

    int updateByPrimaryKey(KpiScore record);

    List<KpiScore> getAllKpiScore();

    Integer deleteKpiScoreByIds(Integer[] ids);
}