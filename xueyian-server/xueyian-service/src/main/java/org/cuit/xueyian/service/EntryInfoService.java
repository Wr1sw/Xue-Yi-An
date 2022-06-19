package org.cuit.xueyian.service;

import org.cuit.xueyian.dao.DepartmentMapper;
import org.cuit.xueyian.dao.EmployeeMapper;
import org.cuit.xueyian.model.Department;
import org.cuit.xueyian.model.Employee;
import org.cuit.xueyian.model.EntryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 入职信息服务类
 * </p>
 *
 * @author Miracle
 * @since 2022-05-23 18:59:47
 */
@Service
public class EntryInfoService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    public List<EntryInfo> getALlEntryInfos(){
        List<Employee> employees = employeeMapper.queryALl();

        return this.getEntryInfoList(employees);
    }

    public List<EntryInfo> getSomeEntryInfo(Integer PageSize, Integer currentPage){
        HashMap<String, Object> map = new HashMap<>();
        map.put("PageSize",PageSize);
        map.put("offsetData", (currentPage-1)*PageSize); // 设置数据库数据开始的条数
        List<Employee> employees = employeeMapper.getEmployeeList(map);

        return this.getEntryInfoList(employees);
    }

    public EntryInfo getAnEntryInfo(String workerId){
        HashMap<String, Object> map = new HashMap<>();
        map.put("workid", workerId);
        List<Employee> employees = employeeMapper.getEmployeeList(map);
        List<EntryInfo> infos = this.getEntryInfoList(employees);

        return infos.get(0);
    }

    public List<EntryInfo> getEntryInfoList(List<Employee> employees){
        EntryInfo info;
        List<EntryInfo> infos = new ArrayList<>();
        for(Employee employee: employees){
            String department = departmentMapper.selectNameByPrimaryKey(employee.getDepartmentid());
            info = new EntryInfo(employee, department);
            infos.add(info);
        }
        return infos;
    }
}
