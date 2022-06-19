package org.cuit.xueyian.dao;

import org.apache.ibatis.annotations.Mapper;
import org.cuit.xueyian.model.EmpEcRP;
import org.cuit.xueyian.model.Employee;
import org.cuit.xueyian.model.Employeeec;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface EmployeeecMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employeeec record);

    int insertSelective(Employeeec record);

    Employeeec selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(Employeeec record);

    int updateByPrimaryKey(Employeeec record);

    /**
     * 功能描述: 分页查询 -- 注意 结果联合了employee的name 和 PR所有字段 和 employeeec字段
     * @Param: [map]
     * @Return: java.util.List<org.cuit.xueyian.model.EmpEcRP>
     * @Author: wsy
     * @Date: 2022/5/27 19:39
     */
    List<EmpEcRP> getEmpEcRPListByPage(HashMap<String, Object> map);


    /**
     * 功能描述:  获取奖惩数据总条数
     * @Param: []
     * @Return: java.lang.Long
     * @Author: wsy
     * @Date: 2022/5/27 19:19
     */
    Long getTotal();

    /**
     * 功能描述: 修改奖惩信息
     * @Param: [ecInfo]
     * @Return: java.lang.Integer
     * @Author: wsy
     * @Date: 2022/5/27 20:50
     */
    Integer editEmpEc(Employeeec ecInfo);

    /**
     * 功能描述: 查询奖惩信息
     * @Param: [map]
     * @Return: java.util.List<org.cuit.xueyian.model.EmpEcRP>
     * @Author: wsy
     * @Date: 2022/5/27 21:23
     */
    List<EmpEcRP> queryInfo(HashMap<String, Object> map);

  /**
   * 功能描述: 获取查询数据条数
   * @Param: [map]
   * @Return: java.lang.Long
   * @Author: wsy
   * @Date: 2022/5/27 22:26
   */
    Long queryInfoLength(HashMap<String, Object> map);

    /**
     * 功能描述: 根据ID 获取信息
     * @Param: [id]
     * @Return: org.cuit.xueyian.model.EmpEcRP 聚合的表
     * @Author: wsy
     * @Date: 2022/5/28 10:27
     */
    EmpEcRP selectById(Integer id);

//    Integer addEmployee(Employeeec employeeec);
}