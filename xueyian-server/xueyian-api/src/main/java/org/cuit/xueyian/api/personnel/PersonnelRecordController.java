package org.cuit.xueyian.api.personnel;

import org.cuit.xueyian.model.AskForLeave;
import org.cuit.xueyian.model.NeglectWork;
import org.cuit.xueyian.service.AskForLeaveService;
import org.cuit.xueyian.service.NeglectWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 人事记录统计管理类
 * </p>
 *
 * @author Miracle
 * @since 2022-05-15
 */
@Controller
@RequestMapping("/statistics/recored")
public class PersonnelRecordController {

    /**
     * 请假服务对象
     */
    @Autowired
    AskForLeaveService leaveService;

    /**
     * 旷工服务对象
     */
    @Autowired
    NeglectWorkService workService;

    /**
     *  获取公司所有员工的请假信息
     * @return 请假信息集合
     */
    @RequestMapping("/allAskForLeaveInfo")
    @ResponseBody
    public List<AskForLeave> getAllAskForLeaveInfo(){
        return leaveService.getAllAskForLeaveInformation();
    }

    /**
     * 获取指定部门所有员工的请假信息
     * @param leaveWorkerDepartId 部门id
     * @return 请假信息集合
     */
    @RequestMapping("/departmentAskForLeave")
    @ResponseBody
    public List<AskForLeave> getDepartmentAskForLeave(Integer leaveWorkerDepartId){
        return leaveService.getDepartmentAskForLeave(leaveWorkerDepartId);
    }

    /**
     * 获取指定员工所有的请假信息
     * @param leaveWorkerId 员工id
     * @return 请假信息集合
     */
    @RequestMapping("/workerAskForLeaveInfos")
    @ResponseBody
    public List<AskForLeave> getWorkerAskForLeaveInfos(String leaveWorkerId){
        return leaveService.getAskForLeaveByEmployeeId(leaveWorkerId);
    }

    /**
     * 根据请假表主键，获取指定id的请假信息
     * @param leaveId 请假表主键
     * @return 请假信息
     */
    @RequestMapping("/askForLeaveById")
    @ResponseBody
    public AskForLeave getAskForLeaveInfoById(Integer leaveId){
        return leaveService.getAskForLeaveById(leaveId);
    }

    /**
     * 获取公司所有人的旷工信息
     * @return 旷工信息集合
     */
    @RequestMapping("/allNeglectWorkInfo")
    @ResponseBody
    public List<NeglectWork> getAllNeglectWorkInfo(){
        return workService.getAllNeglectWorkRows();
    }

    /**
     * 获取指定部门所有员工的旷工信息
     * @param leaveWorkerDepartId 部门id
     * @return 旷工信息集合
     */
    @RequestMapping("/departmentNeglectWork")
    @ResponseBody
    public List<NeglectWork> getDepartmentNeglectWork(Integer leaveWorkerDepartId){
        return workService.getDepartmentNeglectWorkRows(leaveWorkerDepartId);
    }

    /**
     * 获取指定员工所有的旷工信息
     * @param neglectWorkerId 员工id
     * @return 旷工信息集合
     */
    @RequestMapping("/workerNeglectWorkInfos")
    @ResponseBody
    public List<NeglectWork> getWorkerNeglectWorkInfos(String neglectWorkerId){
        return workService.getNeglectWorkByEmployeeId(neglectWorkerId);
    }

    /**
     * 获取指定的旷工信息
     * @param neglectId 旷工表主键
     * @return 旷工信息
     */
    @RequestMapping("/NeglectWorkById")
    @ResponseBody
    public NeglectWork getNeglectWorkInfoById(Integer neglectId){
        return workService.getNeglectWorkById(neglectId);
    }
}
