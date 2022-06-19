package org.cuit.xueyian.api.personnel;

import org.cuit.xueyian.api.system.OperationLog;
import org.cuit.xueyian.model.*;
import org.cuit.xueyian.service.EmployeeTrainService;
import org.cuit.xueyian.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/personnel/train")
public class TrainController {

    /**
     * 培训信息服务对象
     */
    @Autowired
    TrainService trainService;

    /**
     * 员工培训服务对象
     */
    @Autowired
    EmployeeTrainService employeeTrainService;

    /**
     * 获取培训信息
     * @param pageSize 页面大小
     * @param currentPage 当前页
     * @return 培训集合
     */
    @RequestMapping("/trainList")
    @ResponseBody
    @OperationLog(operModel = "人事管理-培训模块-获取培训信息",operDesc = "查询指定页面的培训信息")
    public RespBean getTrainList(@RequestParam("PageSize")Integer pageSize, @RequestParam("currentPage")Integer currentPage){
        List<Train> list = trainService.getTrainList(pageSize, currentPage);
        if(list.size() == 0){
            return RespBean.ok(null);
        }
        return RespBean.ok(list);
    }

    /**
     * 查询指定id部门下有哪些培训
     * @param pageSize 页面记录大小
     * @param currentPage 当前页码
     * @param departId 部门id
     * @return 培训(Train)实体集合
     */
    @RequestMapping("/trainListByDepartId")
    @ResponseBody
    @OperationLog(operModel = "人事管理-培训模块-获取培训信息",operDesc = "查询指定部门id的培训信息")
    public RespBean getTrainListByDepartId(@RequestParam("PageSize")Integer pageSize, @RequestParam("currentPage")Integer currentPage, @RequestParam("departId")Integer departId){
        List<Train> list = employeeTrainService.getTrainListByDepartId(pageSize, currentPage, departId);
        if(list.size() == 0){
            return RespBean.ok(null);
        }

        return RespBean.ok(list);
    }

    /**
     * 查询公司员工参加培训的情况
     * @param pageSize 页面记录大小
     * @param currentPage 当前页码
     * @return 培训信息(TrainInfo)实体集合
     */
    @RequestMapping("/employeeTrainInfoList")
    @ResponseBody
    @OperationLog(operModel = "人事管理-培训模块-获取培训信息",operDesc = "查询所有的培训记录")
    public RespBean getEmployeeTrainInfoList(@RequestParam("PageSize")Integer pageSize, @RequestParam("currentPage")Integer currentPage){
        List<TrainInfo> list = employeeTrainService.getEmployeeTrainInfoList(pageSize, currentPage);
        if(list.size() == 0){
            return RespBean.ok(null);
        }

        return RespBean.ok(list);
    }

    /**
     * 查询指定id的培训有哪些员工参加
     * @param pageSize 页面记录大小
     * @param currentPage 当前页码
     * @param trainId 培训id
     * @return 培训信息(TrainInfo)实体集合
     */
    @RequestMapping("/trainInfoListByTrainId")
    @ResponseBody
    @OperationLog(operModel = "人事管理-培训模块-获取培训信息",operDesc = "查询指定培训id的培训记录")
    public RespBean getTrainInfoListByTrainId(@RequestParam("PageSize")Integer pageSize, @RequestParam("currentPage")Integer currentPage, @RequestParam("trainId")Integer trainId){
        List<TrainInfo> list = employeeTrainService.getTrainInfoListByTrainId(pageSize, currentPage, trainId);
        if(list.size() == 0){
            return RespBean.ok(null);
        }

        return RespBean.ok(list);
    }

    /**
     * 查询指定id部门下员工参加培训的情况
     * @param pageSize 页面记录大小
     * @param currentPage 当前页码
     * @param departId 部门id
     * @return 培训信息(TrainInfo)实体集合
     */
    @RequestMapping("/employeeTrainInfoListByDepartId")
    @ResponseBody
    @OperationLog(operModel = "人事管理-培训模块-获取培训信息",operDesc = "查询指定部门id的培训记录")
    public RespBean getEmployeeTrainInfoListByDepartId(@RequestParam("PageSize")Integer pageSize, @RequestParam("currentPage")Integer currentPage, @RequestParam("departId")Integer departId){
        List<TrainInfo> list = employeeTrainService.getEmployeeTrainInfoListByDepartId(pageSize, currentPage, departId);
        if(list.size() == 0){
            return RespBean.ok(null);
        }

        return RespBean.ok(list);
    }

    /**
     * 查询指定id员工参加了哪些培训
     * @param pageSize 页面记录大小
     * @param currentPage 当前页码
     * @param eid 员工id
     * @return 培训信息(TrainInfo)实体集合
     */
    @RequestMapping("/empTrainInfoListByEid")
    @ResponseBody
    @OperationLog(operModel = "人事管理-培训模块-获取培训信息",operDesc = "查询指定员工id的培训记录")
    public RespBean getEmpTrainInfoListByEid(@RequestParam("PageSize")Integer pageSize, @RequestParam("currentPage")Integer currentPage, @RequestParam("eid")Integer eid){
        List<TrainInfo> list = employeeTrainService.getEmpTrainInfoListByEid(pageSize, currentPage, eid);
        if(list.size() == 0){
            return RespBean.ok(null);
        }

        return RespBean.ok(list);
    }

    /**
     * 添加一条培训信息
     * @param record 培训信息
     * @return 插入结果
     */
    @RequestMapping("/insertATrain")
    @ResponseBody
    @OperationLog(operModel = "人事管理-培训模块-插入培训信息",operDesc = "插入培训信息")
    public RespBean insertATrain(@RequestBody Train record){
        int flag = trainService.insert(record);
        if(flag == 0){
            return RespBean.error("添加失败");
        }
        return RespBean.ok("success");
    }

    /**
     * 根据培训表id查询培训信息
     * @param id 培训表主键
     * @return 培训实体
     */
    @RequestMapping("/selectTrainByPrimaryKey")
    @ResponseBody
    @OperationLog(operModel = "人事管理-培训模块-获取培训信息",operDesc = "查询指定培训id的培训信息")
    public RespBean selectTrainByPrimaryKey(Integer id){
        return RespBean.ok(trainService.selectByPrimaryKey(id));
    }

    /**
     * 根据培训id删除记录
     * @param id 培训id
     * @return 删除结果
     */
    @RequestMapping("/deleteTrainByPrimaryKey")
    @ResponseBody
    @OperationLog(operModel = "人事管理-培训模块-删除培训信息",operDesc = "删除指定培训id的培训信息")
    public RespBean deleteTrainByPrimaryKey(Integer id){
        int flag = trainService.deleteByPrimaryKey(id);
        if(flag == 0){
            return RespBean.error("删除失败");
        }
        return  RespBean.ok("success");
    }

    /**
     * 更新培训表信息
     * @param record 培训表记录
     * @return 更新结果
     */
    @RequestMapping("/updateTrainByPrimaryKeySelective")
    @ResponseBody
    @OperationLog(operModel = "人事管理-培训模块-更新培训信息",operDesc = "更新指定培训id的培训信息")
    public RespBean updateTrainByPrimaryKeySelective(@RequestBody Train record){
        int flag = trainService.updateByPrimaryKeySelective(record);
        if(flag == 0){
            return RespBean.error("更新失败");
        }
        return  RespBean.ok("success");
    }

    /**
     * 插入培训记录
     * @param id 培训信息表
     * @param list 员工集合
     * @return 插入结果
     */
    @RequestMapping("/insertATrainInfo")
    @ResponseBody
    @OperationLog(operModel = "人事管理-培训模块-插入培训记录",operDesc = "插入培训记录")
    public RespBean insertATrainInfo(Integer id, @RequestBody List<Employee> list){
        int flag = employeeTrainService.insert(id, list);
        if(flag == 0){
            return RespBean.error("添加失败");
        }
        return RespBean.ok("success");
    }

    /**
     * 根据员工培训表主键删除记录
     * @param id 员工培训表主键
     * @return 删除结果
     */
    @RequestMapping("/deleteTrainInfoByPrimaryKey")
    @ResponseBody
    @OperationLog(operModel = "人事管理-培训模块-删除培训记录",operDesc = "删除指定培训记录id的培训记录")
    public RespBean deleteTrainInfoByPrimaryKey(Integer id){
        int flag = employeeTrainService.deleteByPrimaryKey(id);
        if(flag == 0){
            return RespBean.error("删除失败");
        }
        return  RespBean.ok("success");
    }

    /**
     * 更新员工培训表信息
     * @param record 员工培训信息
     * @return 更新结果
     */
    @RequestMapping("/updateTrainInfoByPrimaryKey")
    @ResponseBody
    @OperationLog(operModel = "人事管理-培训模块-更新培训记录",operDesc = "更新指定培训id的培训记录")
    public RespBean updateTrainInfoByPrimaryKey(EmployeeTrain record){
        int flag = employeeTrainService.updateByPrimaryKey(record);
        if(flag == 0){
            return RespBean.error("更新失败");
        }
        return  RespBean.ok("success");
    }

    /**
     * 统计培训信息的条数
     * @return 培训信息的条数
     */
    @RequestMapping("/countTrainRowsNum")
    @ResponseBody
    @OperationLog(operModel = "人事管理-培训模块-获取培训信息数",operDesc = "查询所有培训信息数")
    public RespBean countTrainRowsNum(){
        return RespBean.ok(trainService.countTrainRowsNum());
    }

    /**
     * 根据实体类提供的信息，查找相应信息的记录条数
     * @param train 培训记录实体
     * @return 记录条数
     */
    @RequestMapping("/countTrainInfoRowByEntity")
    @ResponseBody
    @OperationLog(operModel = "人事管理-培训模块-获取培训记录",operDesc = "模糊查询培训记录")
    public RespBean countTrainInfoRowByEntity(EmployeeTrain train){
        return RespBean.ok(employeeTrainService.countTrainInfoRowByEntity(train));
    }

    /**
     * 根据员工培训记录表id，返回一条记录信息
     * @param id 培训记录表主键
     * @return TrainInfo实体
     */
    @RequestMapping("/trainInfoById")
    @ResponseBody
    @OperationLog(operModel = "人事管理-培训模块-获取培训记录",operDesc = "查询指定培训记录id的培训记录")
    public RespBean getATrainInfoById(Integer id){
        TrainInfo info = employeeTrainService.getTrainInfoById(id);
        return RespBean.ok(info);
    }

    /**
     * 所有员工的信息
     * @return 员工信息集合
     */
    @RequestMapping("/employeeList")
    @ResponseBody
    @OperationLog(operModel = "人事管理-培训模块-获取所有员工信息",operDesc = "查询所有员工的信息")
    public RespBean getEmployeeList(){
        return RespBean.ok(employeeTrainService.getEmployeeList());
    }

    /**
     * 根据传入实体查找相应培训记录信息
     * @param train 培训记录
     * @param pageSize 页面大小
     * @param currentPage 当前页
     * @return 培训记录集合
     */
    @RequestMapping("/trainInfoByEntity")
    @ResponseBody
    @OperationLog(operModel = "人事管理-培训模块-获取培训记录",operDesc = "查询指定条件的培训记录")
    public RespBean getTrainInfoByEntity(EmployeeTrain train,@RequestParam("PageSize")Integer pageSize, @RequestParam("currentPage")Integer currentPage){
        System.out.println(train);
        List<TrainInfo> list = employeeTrainService.getTrainInfoByEntity(train, pageSize, currentPage);
        for(TrainInfo info : list){
            System.out.println(info);
        }
        return RespBean.ok(list);
    }

    /**
     * 获取所有培训信息
     * @return 培训集合
     */
    @RequestMapping("/allTrainList")
    @ResponseBody
    @OperationLog(operModel = "人事管理-培训模块-获取培训信息",operDesc = "查询所有培训信息")
    public RespBean getAllTrainList(){
        return RespBean.ok(trainService.getAllTrainList());
    }
}
