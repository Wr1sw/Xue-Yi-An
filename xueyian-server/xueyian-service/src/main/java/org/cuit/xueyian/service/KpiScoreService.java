package org.cuit.xueyian.service;

import org.cuit.xueyian.dao.KpiScoreMapper;
import org.cuit.xueyian.model.JObLevel;
import org.cuit.xueyian.model.KpiScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KpiScoreService {

    @Autowired
    KpiScoreMapper kpiScoreMapper;

    public List<KpiScore> getAllKpiScore(){
        return kpiScoreMapper.getAllKpiScore();
    }

    public Integer addKpiScore(KpiScore kpiScore){
        return kpiScoreMapper.insertSelective(kpiScore);
    }

    public Integer updateKpiScore(KpiScore kpiScore){
        return kpiScoreMapper.updateByPrimaryKeySelective(kpiScore);
    }

    public Integer deleteKpiScoreById(Integer id){
        return kpiScoreMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteKpiScoreByIds(Integer[] ids){
        return kpiScoreMapper.deleteKpiScoreByIds(ids);
    }

}
