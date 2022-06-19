package org.cuit.xueyian.service;

import org.cuit.xueyian.dao.AskForLeaveMapper;
import org.cuit.xueyian.model.AskForLeave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 请假服务类
 *
 * @author Miracle
 * @since 2022-05-07
 */
@Service
public class AskForLeaveService {

    @Autowired
    AskForLeaveMapper mapper;

    /**
     *  查询公司所有的请假信息
     * @return 公司所有员工的请假信息
     */
    public List<AskForLeave> getAllAskForLeaveInformation(){
        return mapper.queryAll(null);
    }

    /**
     * 查询指定部门的请假信息
     * @param id 部门id
     * @return 部门请假信息集合
     */
    public List<AskForLeave> getDepartmentAskForLeave(Integer id){
        AskForLeave leave = new AskForLeave();
        leave.setLeaveWorkerDepartId(id);
        return mapper.queryAll(leave);
    }

    /**
     * 老板或经理同意员工请假
     * @param leaveIsHrAgree 经理同意1,不同意0
     * @param leaveIsBossAgree 老板同意1,不同意0
     * @return 更新操作结果
     */
    public int updateAskForLeave(Integer leaveIsHrAgree, Integer leaveIsBossAgree){
        AskForLeave leave = new AskForLeave();
        leave.setLeaveIsHrAgree(leaveIsHrAgree);
        leave.setLeaveIsBossAgree(leaveIsBossAgree);
        return mapper.update(leave);
    }

    /**
     * 获取指定id的请假信息
     * @param leaveId 请假表主键
     * @return 指定ID的请假信息
     */
    public AskForLeave getAskForLeaveById(Integer leaveId){
        return mapper.queryById(leaveId);
    }

    /**
     * 获取指定员工的请假信息记录
     * @param id 指定员工的id
     * @return 指定员工的请假信息记录
     */
    public List<AskForLeave> getAskForLeaveByEmployeeId(String id){
        AskForLeave leave = new AskForLeave();
        leave.setLeaveWorkerId(id);
        return mapper.queryAll(leave);
    }
}
