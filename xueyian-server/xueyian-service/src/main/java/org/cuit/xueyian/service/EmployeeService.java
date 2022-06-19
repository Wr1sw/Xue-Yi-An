package org.cuit.xueyian.service;


import org.cuit.xueyian.dao.*;
import org.cuit.xueyian.exception.ConditionException;
import org.cuit.xueyian.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    public static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    NationMapper nationMapper;

    @Autowired
    JObLevelMapper jObLevelMapper;

    @Autowired
    PositionMapper positionMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    PoliticsstatusMapper politicsstatusMapper;

    @Autowired
    DepartmentService departmentService;


    public int getEmployeeListLength(Employee emp){
        return employeeMapper.getEmployeeListLength(emp);
    }

    //分页获取数据
    public PageResp<Employee> getEmployeeListByPage(Integer PageSize,Integer currentPage){
        if (PageSize == null ||  currentPage == null) {
            throw new RuntimeException("非法数据");
        }
        HashMap<String,Object> map=new HashMap<>();
        map.put("PageSize",PageSize);
        map.put("offsetData", (currentPage-1)*PageSize); // 设置数据库数据开始的条数

        PageResp<Employee> res = new PageResp<>();
        List<Employee>  list = employeeMapper.getEmployeeListByPage(map);

        //获取总条数
        res.setTotal(employeeMapper.getTotal());
        res.setData(list);
        return res;
    }

    public PageResp<Employee> queryInfo(Employee emp, Integer PageSize, Integer currentPage){
        if (PageSize == null ||  currentPage == null) {
            throw new RuntimeException("非法数据");
        }

        HashMap<String,Object> map=new HashMap<>();

        map.put("name",emp.getName());
        map.put("gender",emp.getGender());
        map.put("departmentid",emp.getDepartmentid());

        //之后查询若前端加字段，再添加
        // birthday, idCard, wedlock, nationId, nativePlace,
        // politicId, email, phone, address, , jobLevelId, posId,
        // engageForm, tiptopDegree, specialty, school, beginDate, workState, workID,
        // contractTerm, conversionTime, notWorkDate, beginContract, endContract, workAge

        map.put("PageSize",PageSize);
        map.put("offsetData", (currentPage-1)*PageSize); // 设置数据库数据开始的条数

        List<Employee> list = employeeMapper.queryInfo(map);
        PageResp<Employee> res = new PageResp();
        res.setTotal(employeeMapper.queryInfoLength(map));
        res.setData(list);
        return res;
    }

    //根据ID删除员工信息
    public int deleteByPrimaryKey(Integer id){
        return employeeMapper.deleteByPrimaryKey(id);
    }

    //根据ID获取员工信息
    public Employee  selectByPrimaryKey(Integer id){
        return employeeMapper.selectAllInfoById(id);
    }

    //根据ID更新员工信息
    public int updateByPrimaryKeySelective(Employee emp){return employeeMapper.updateByPrimaryKeySelective(emp);}

    //添加员工信息
    public int addEmployee (Employee emp){

        //设置workID
        String maxWorkId = employeeMapper.getLastWorkId();
        if(maxWorkId == null)
            throw  new RuntimeException("获取出错");
        int workIdLength = maxWorkId.length();
        //设置成前缀有0的形式
        String workId = String.format("%0"+workIdLength+"d",Long.parseLong(maxWorkId)+1);
        emp.setWorkid(workId);
        int result = employeeMapper.insertSelective(emp);
        // 查职称、部门
        if (result == 1) {

            Employee employee = employeeMapper.getEmployeeWithAllById(emp.getId());
            logger.info(employee.toString());
            // 队列名 + 数据
            rabbitTemplate.convertAndSend("xueyian.mail.welcome",employee);
        }
        return result;
    }

    public PageResp<Employee> getEmployeeByPageWithSalary(Integer page, Integer size) {
        if (page == null ||  size == null) {
            throw new RuntimeException("非法数据");
        }
        page = (page - 1) * size;
        Long total = employeeMapper.getTotal();
        if (total < 0) throw new RuntimeException("数据异常");
        List<Employee> employeeList = employeeMapper.getEmployeeByPageWithSalary(page, size);
        PageResp<Employee> res = new PageResp<>();
        res.setTotal(total);
        res.setData(employeeList);
        return res;
    }

    public Integer updateEmployeeOfSalaryById(Integer eid, Integer sid) {
        return employeeMapper.updateEmployeeOfSalaryById(eid, sid);
    }

    public Integer updateEmployeeOfSalaryStandardById(Integer eid, Integer sid) {
        return employeeMapper.updateEmployeeOfSalaryStandardById(eid, sid);
    }

    public HashMap<String, List<Object>> getNameLists() {
        List<Department> departmentList = departmentMapper.getID_Name();
        List<Nation> nationList = nationMapper.getNameList();
        List<Politicsstatus> politicsstatusMapperList = politicsstatusMapper.getNameList();
        List<JObLevel> jobLevelList = jObLevelMapper.getID_Name();
        List<Position> positionList = positionMapper.getID_Name();

        HashMap<String, List<Object>> map = new HashMap<>();
        map.put("departmentList", Collections.singletonList(departmentList));
        map.put("nationList", Collections.singletonList(nationList));
        map.put("politicsstatusMapperList", Collections.singletonList(politicsstatusMapperList));
        map.put("jobLevelList", Collections.singletonList(jobLevelList));
        map.put("positionList", Collections.singletonList(positionList));

        return map;
    }
    public List<Department> getDepNameList(){
        return departmentMapper.getID_Name();
    }

    // 级联 获取员工和部门 映射信息
    public List<Map<String,Object>>getDepEmpList(){
        List<Department> depList = departmentMapper.getID_Name();
        List<Map<String,Object>> res = new ArrayList<>();
        for(Department dep: depList){
            Map<String,Object> map = new HashMap<>();
            map.put("value",dep.getId());
            map.put("label",dep.getName());
            List<Employee> empList = employeeMapper.getAllEmpByDepId(dep.getId());
            List<Map<String,Object>> empRes = new ArrayList<>();
            for(Employee emp: empList) {
                Map<String, Object> empMap = new HashMap<>();
                empMap.put("value", emp.getId());
                empMap.put("label", emp.getName());

                empRes.add(empMap);
            }
            map.put("children", empRes);
            res.add(map);
        }
        return res;
    }

    public PageResp<Employee> getEmployeeByPageWithSalaryStandard(Integer page, Integer size) {
        if (page == null || size == null) {
            throw new RuntimeException("非法数据");
        }
        page = (page - 1) * size;
        Long total = employeeMapper.getTotal();
        if (total < 0) throw new ConditionException("数据异常");
        List<Employee> employeeList = employeeMapper.getEmployeeByPageWithSalaryStandard(page, size);
        PageResp<Employee> res = new PageResp<>();
        res.setTotal(total);
        res.setData(employeeList);
        return res;
    }


}
