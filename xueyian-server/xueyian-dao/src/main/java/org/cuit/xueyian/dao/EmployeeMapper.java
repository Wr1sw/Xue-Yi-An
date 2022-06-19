package org.cuit.xueyian.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cuit.xueyian.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> getEmployeeList(HashMap<String, Object> map);

    int getEmployeeListLength(Employee emp);

//    List<Employee> queryInfo(Employee emp);


    /**
     * 功能描述: 分页获取员工基础数据
     * @Param: [map]
     * @Return: java.util.List<org.cuit.xueyian.model.Employee>
     * @Author: wsy
     * @Date: 2022/5/25 21:42
     */
    List<Employee> getEmployeeListByPage(HashMap<String, Object> map);

    /**
     * 功能描述: 获取查询的员工信息
     * @Param: [map]
     * @Return: java.util.List<org.cuit.xueyian.model.Employee>
     * @Author: wsy
     * @Date: 2022/5/25 22:03
     */
    List<Employee> queryInfo(HashMap<String, Object> map);

    /**
     * 功能描述: 获取查询的员工长度
     * @Param: [emp]
     * @Return: java.lang.Long
     * @Author: wsy
     * @Date: 2022/5/25 22:01
     */
    Long queryInfoLength(HashMap<String, Object> map);


    String getLastWorkId();


    Long getTotal();

    List<Employee> getEmployeeByPageWithSalary(@Param("page") Integer page, @Param("size") Integer size);

    /**
     * 查询所有的员工信息
     * Created by Miracle
     * @return 员工信息集合
     */
    List<Employee> queryALl();

    /**
     * 查询所有离职员工信息
     * Created by Miracle
     * @param map 存放返回条数
     * @return 员工离职信息集合
     */
    List<Employee> getResignedEmployeeList(HashMap<String, Object> map);

    /**
     * 查询指定工号员工信息
     * Created by Miracle
     *
     * @param workerId 员工工号
     * @return 指定工号员工信息
     */
    Employee selectByWorkerId(String workerId);

    Integer updateEmployeeOfSalaryById(@Param("eid") Integer eid, @Param("sid") Integer sid);

    Integer updateEmployeeOfSalaryStandardById(@Param("eid") Integer eid, @Param("sid") Integer sid);

    List<Employee> getEmployeeByPageWithSalaryStandard(Integer page, Integer size);


    List<Employee> getAllEmpByDepId(Integer departmentId);

//    /**
//     * 功能描述: 获取 员工调动功能 所需的 dep，pos, joblevel信息
//     * @Param: [id] 员工id
//     * @Return: org.cuit.xueyian.model.Employee
//     * @Author: wsy
//     * @Date: 2022/5/29 21:38
//     */
//    Employee selectRmInfoById(Integer id);

    /**
     * 功能描述:  获取员工的所有信息 包括部门，pos，joblevel
     * @Param: [id]
     * @Return: org.cuit.xueyian.model.Employee
     * @Author: wsy
     * @Date: 2022/6/1 15:26
     */
    Employee selectAllInfoById(Integer id);

    Employee getEmployeeWithAllById(Integer id);
}