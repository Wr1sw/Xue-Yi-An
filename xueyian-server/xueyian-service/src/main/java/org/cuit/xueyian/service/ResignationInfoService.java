package org.cuit.xueyian.service;

import org.cuit.xueyian.dao.DepartmentMapper;
import org.cuit.xueyian.dao.EmployeeMapper;
import org.cuit.xueyian.dao.JObLevelMapper;
import org.cuit.xueyian.dao.PositionMapper;
import org.cuit.xueyian.model.Employee;
import org.cuit.xueyian.model.ResignationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * <p>
 * 辞职信息服务类
 * </p>
 *
 * @author Miracle
 * @since 2022-05-23 18:59:47
 */
@Service
public class ResignationInfoService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    JObLevelMapper jobMapper;

    @Autowired
    PositionMapper positionMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    public List<ResignationInfo> getSomeResignationInfo(Integer PageSize, Integer currentPage){
        HashMap<String, Object> map = new HashMap<>();
        map.put("PageSize",PageSize);
        map.put("offsetData", (currentPage-1)*PageSize); // 设置数据库数据开始的条数
        List<Employee> employees = employeeMapper.getResignedEmployeeList(map);

        return this.getResignationInfoList(employees);
    }

    public ResignationInfo getAResignationInfo(String workerId){
        Employee employee = employeeMapper.selectByWorkerId(workerId);
        ResignationInfo info;
        String department = departmentMapper.selectNameByPrimaryKey(employee.getDepartmentid());
        String pos = positionMapper.selectNameByPrimaryKey(employee.getPosid());
        String jobLevel = jobMapper.selectNameByPrimaryKey(employee.getJoblevelid());
        info = new ResignationInfo(employee, department, pos, jobLevel);

        return info;
    }

    public List<ResignationInfo> getResignationInfoList(List<Employee> employees){
        ResignationInfo info;
        List<ResignationInfo> infos = new ArrayList<>();
        for(Employee employee: employees){
            String department = departmentMapper.selectNameByPrimaryKey(employee.getDepartmentid());
            String pos = positionMapper.selectNameByPrimaryKey(employee.getPosid());
            String jobLevel = jobMapper.selectNameByPrimaryKey(employee.getJoblevelid());
            info = new ResignationInfo(employee, department, pos, jobLevel);
            infos.add(info);
        }

        return infos;
    }

}
