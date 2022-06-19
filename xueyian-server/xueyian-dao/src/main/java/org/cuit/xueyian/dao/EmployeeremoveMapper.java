package org.cuit.xueyian.dao;

import org.apache.ibatis.annotations.Mapper;
import org.cuit.xueyian.model.Employeeremove;

import java.util.HashMap;
import java.util.List;


@Mapper
public interface EmployeeremoveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employeeremove record);

    Integer insertSelective(Employeeremove record);

    Employeeremove selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(Employeeremove record);

    int updateByPrimaryKey(Employeeremove record);

    /**
     * 功能描述:  获取 员工调动数据
     * @Param: [map]
     * @Return: java.util.List<org.cuit.xueyian.model.Employeeremove>
     * @Author: wsy
     * @Date: 2022/5/28 20:09
     */
    List<Employeeremove> getEmployeeListByPage(HashMap<String, Object> map);

    /**
     * 功能描述:  获取 员工调动总数据条数
     * @Param: []
     * @Return: java.lang.Long
     * @Author: wsy
     * @Date: 2022/5/28 20:17
     */
    Long getTotal();


    /**
     * 功能描述: 根据 empRM的id获取员工调动信息
     * @Param: [id]
     * @Return: org.cuit.xueyian.model.Employeeremove
     * @Author: wsy
     * @Date: 2022/5/28 20:27
     */
    Employeeremove getEmpRmById(Integer id);

   /**
    * 功能描述:  根据条件 查询数据
    * @Param: [map]
    * @Return: java.util.List<org.cuit.xueyian.model.Employeeremove>
    * @Author: wsy
    * @Date: 2022/5/28 20:36
    */
    List<Employeeremove> queryInfo(HashMap<String, Object> map);

    /**
     * 功能描述: 获取查询的数据条数
     * @Param: [map]
     * @Return: java.lang.Long
     * @Author: wsy
     * @Date: 2022/5/28 20:44
     */
    Long queryInfoLength(HashMap<String, Object> map);


}