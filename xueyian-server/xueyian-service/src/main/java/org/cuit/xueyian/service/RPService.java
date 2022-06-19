package org.cuit.xueyian.service;

import org.apache.ibatis.annotations.Param;
import org.cuit.xueyian.dao.RPMapper;
import org.cuit.xueyian.model.RP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RPService {

    @Autowired
    RPMapper rpMapper;

    public List<RP> getAllRP(){
        return rpMapper.getAllRP();
    }

    public Integer addRP(RP rp){
        return rpMapper.insertSelective(rp);
    }

    public Integer updateRP(RP rp){
        return rpMapper.updateByPrimaryKeySelective(rp);
    }

    public Integer deleteRPById(Integer id){
        return rpMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteRPByIds(@Param("ids") Integer[] ids){
        return rpMapper.deleteRPByIds(ids);
    }
}
