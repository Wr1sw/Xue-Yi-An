package org.cuit.xueyian.service;

import org.cuit.xueyian.dao.EmployeeMapper;
import org.cuit.xueyian.dao.EmployeetrainMapper;
import org.cuit.xueyian.model.Employee;
import org.cuit.xueyian.model.EmployeeTrain;
import org.cuit.xueyian.model.Train;
import org.cuit.xueyian.model.TrainInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

@Service
public class EmployeeTrainService {

    @Autowired
    EmployeetrainMapper employeetrainMapper;

    @Autowired
    EmployeeMapper employeeMapper;


    /**
     * 插入培训记录
     * @param id 培训信息表
     * @param list 员工集合
     * @return 插入结果
     */
    public int insert(Integer id, List<Employee> list){
        EmployeeTrain train;
        int res = 0 ;
        for(Employee employee: list){
            train = new EmployeeTrain();
            train.setTrainId(id);
            train.setDepartId(employee.getDepartmentid());
            train.setEid(employee.getId());
            res = employeetrainMapper.insert(train);
        }
        return res;
    }

    /**
     * 根据主键删除员工培训记录
     * @param id 员工培训表主键
     * @return 删除结果
     */
    public int deleteByPrimaryKey(Integer id){
        return employeetrainMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新一条培训数据
     * @param record 培训记录
     * @return 更新结果
     */
    public int updateByPrimaryKey(EmployeeTrain record){
        return employeetrainMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 查询指定id部门下有哪些培训
     * @param pageSize 页面记录大小
     * @param currentPage 当前页码
     * @param departId 部门id
     * @return 培训(Train)实体集合
     */
    public List<Train> getTrainListByDepartId(Integer pageSize, Integer currentPage, Integer departId){
        HashMap<String,Object> map=new HashMap<>();
        map.put("pageSize",pageSize);
        map.put("offsetData", (currentPage-1)*pageSize);
        map.put("departId", departId);

        return employeetrainMapper.getTrainListByDepartId(map);
    }

    /**
     * 查询指定id员工参加了哪些培训
     * @param pageSize 页面记录大小
     * @param currentPage 当前页码
     * @param eid 员工id
     * @return 培训信息(TrainInfo)实体集合
     */
    public List<TrainInfo> getEmpTrainInfoListByEid(Integer pageSize, Integer currentPage, Integer eid){
        HashMap<String,Object> map=new HashMap<>();
        map.put("pageSize",pageSize);
        map.put("offsetData", (currentPage-1)*pageSize);
        map.put("eid", eid);

        return employeetrainMapper.getEmpTrainInfoListByEid(map);
    }

    /**
     * 查询指定id部门下员工参加培训的情况
     * @param pageSize 页面记录大小
     * @param currentPage 当前页码
     * @param departId 部门id
     * @return 培训信息(TrainInfo)实体集合
     */
    public List<TrainInfo> getEmployeeTrainInfoListByDepartId(Integer pageSize, Integer currentPage, Integer departId){
        HashMap<String,Object> map=new HashMap<>();
        map.put("pageSize",pageSize);
        map.put("offsetData", (currentPage-1)*pageSize);
        map.put("departId", departId);

        return employeetrainMapper.getEmployeeTrainListByDepartId(map);
    }

    /**
     * 查询公司员工参加培训的情况
     * @param pageSize 页面记录大小
     * @param currentPage 当前页码
     * @return 培训信息(TrainInfo)实体集合
     */
    public List<TrainInfo> getEmployeeTrainInfoList(Integer pageSize, Integer currentPage){
        HashMap<String,Object> map=new HashMap<>();
        map.put("pageSize",pageSize);
        map.put("offsetData", (currentPage-1)*pageSize);

        return employeetrainMapper.getEmployeeTrainList(map);
    }

    /**
     * 查询指定id的培训有哪些员工参加
     * @param pageSize 页面记录大小
     * @param currentPage 当前页码
     * @param trainId 培训id
     * @return 培训信息(TrainInfo)实体集合
     */
    public List<TrainInfo> getTrainInfoListByTrainId(Integer pageSize, Integer currentPage, Integer trainId){
        HashMap<String,Object> map=new HashMap<>();
        map.put("pageSize",pageSize);
        map.put("offsetData", (currentPage-1)*pageSize);
        map.put("trainId", trainId);

        return employeetrainMapper.getTrainInfoListByTrainId(map);
    }

    /**
     * 根据实体类提供的信息，查找相应信息的记录条数
     * @param train 培训记录实体
     * @return 记录条数
     */
    public int countTrainInfoRowByEntity(EmployeeTrain train){
        return employeetrainMapper.countRowByEntity(train);
    }

    /**
     * 根据实体类提供的信息，查找相应信息的记录
     * @param train 培训记录实体
     * @return 记录集合
     */
    public List<TrainInfo> getTrainInfoByEntity(EmployeeTrain train, Integer pageSize, Integer currentPage){
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageSize", pageSize);
        map.put("offsetData", (currentPage-1)*pageSize);
        map.put("eid", train.getEid());
        map.put("departId", train.getDepartId());
        map.put("trainId", train.getTrainId());
        return employeetrainMapper.getTrainInfoByEntity(map);
    }

    public TrainInfo getTrainInfoById(Integer employeeTrainId){
        return employeetrainMapper.getTrainInfoById(employeeTrainId);
    }

    public List<Employee> getEmployeeList(){
        return employeetrainMapper.getEmployeeList();
    }
}
