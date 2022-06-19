package org.cuit.xueyian.service;

import org.cuit.xueyian.dao.OpLogMapper;
import org.cuit.xueyian.model.OpLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OplogService {

    @Autowired
    OpLogMapper opLogMapper;

    public Integer addOplog(OpLog opLog){

        return opLogMapper.insertSelective(opLog);
    }

    public List<OpLog> getAllOplog(){
        return opLogMapper.getAllOplog();
    }

    public Integer deleteOplogById(Integer id){
        return opLogMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteOplogByIds(Integer[] ids){
        return opLogMapper.deleteOplogByIds(ids);
    }

}
