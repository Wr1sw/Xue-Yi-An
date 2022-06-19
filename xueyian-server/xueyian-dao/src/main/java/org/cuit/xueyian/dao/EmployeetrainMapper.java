package org.cuit.xueyian.dao;

import org.apache.ibatis.annotations.Mapper;
import org.cuit.xueyian.model.Employee;
import org.cuit.xueyian.model.EmployeeTrain;
import org.cuit.xueyian.model.Train;
import org.cuit.xueyian.model.TrainInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface EmployeetrainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmployeeTrain record);

    int insertSelective(EmployeeTrain record);

    EmployeeTrain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmployeeTrain record);

    int updateByPrimaryKey(EmployeeTrain record);

    /**
     * 查询指定id部门下有哪些培训
     * @param map param
     * @return 培训(Train)实体集合
     */
    List<Train> getTrainListByDepartId(HashMap<String, Object> map);

    /**
     * 查询指定id员工参加了哪些培训
     * @param map param
     * @return 培训信息(TrainInfo)实体集合
     */
    List<TrainInfo> getEmpTrainInfoListByEid(HashMap<String, Object> map);

    /**
     * 查询指定id部门下员工参加培训的情况
     * @param map param
     * @return 培训信息(TrainInfo)实体集合
     */
    List<TrainInfo> getEmployeeTrainListByDepartId(HashMap<String, Object> map);

    /**
     * 查询公司员工参加培训的情况
     * @param map param
     * @return 培训信息(TrainInfo)实体集合
     */
    List<TrainInfo> getEmployeeTrainList(HashMap<String, Object> map);

    /**
     * 查询指定id的培训有哪些员工参加
     * @param map param
     * @return 培训信息(TrainInfo)实体集合
     */
    List<TrainInfo> getTrainInfoListByTrainId(HashMap<String, Object> map);

    /**
     * 根据培训id删除员工培训表记录
     * @param trainId 培训id
     * @return 删除结果
     */
    int deleteByTrainId(Integer trainId);

    /**
     * 根据实体查找对应记录出现的次数
     * @param train 实体
     * @return 记录出现的次数
     */
    int countRowByEntity(EmployeeTrain train);

    TrainInfo getTrainInfoById(Integer id);

    List<Employee> getEmployeeList();

    List<TrainInfo> getTrainInfoByEntity(HashMap<String, Object> map);
}