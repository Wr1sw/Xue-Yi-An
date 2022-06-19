package org.cuit.xueyian.service;

import org.cuit.xueyian.dao.NeglectWorkMapper;
import org.cuit.xueyian.model.NeglectWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 旷工服务类
 *
 * @author Miracle
 * @since 2022-05-07
 */
@Service
public class NeglectWorkService {

    @Autowired
    NeglectWorkMapper mapper;

    /**
     * 获取全公司人员旷工信息
     * @return 公司人员旷工信息集合
     */
    public List<NeglectWork> getAllNeglectWorkRows(){
        return mapper.queryAll(null);
    }

    /**
     * 获取指定部门人员旷工信息集合
     * @param id 部门id
     * @return 指定部门旷工信息集合
     */
    public List<NeglectWork> getDepartmentNeglectWorkRows(Integer id){
        NeglectWork work = new NeglectWork();
        work.setLeaveWorkerDepartId(id);
        return mapper.queryAll(work);
    }

    /**
     * 获取指定id的旷工记录
     * @param neglectId 旷工表主键
     * @return 旷工记录信息
     */
    public NeglectWork getNeglectWorkById(Integer neglectId){
        return mapper.queryById(neglectId);
    }

    /**
     * 获取指定员工id所有的旷工记录
     * @param id 员工id
     * @return 员工旷工信息
     */
    public List<NeglectWork> getNeglectWorkByEmployeeId(String id){
        NeglectWork work = new NeglectWork();
        work.setNeglectWorkerId(id);
        return mapper.queryAll(work);
    }

}
