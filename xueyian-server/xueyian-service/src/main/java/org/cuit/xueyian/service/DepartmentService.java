package org.cuit.xueyian.service;


import org.cuit.xueyian.dao.DepartmentMapper;
import org.cuit.xueyian.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    //获取部门ID与名称组合
    public List<Department> getID_Name(){
        return departmentMapper.getID_Name();
    }

    //根据ID获取名称
    public Object selectNameByPrimaryKey(Integer id){
        return departmentMapper.selectNameByPrimaryKey(id);
    }

    public List<Department> getAllDepartmentsById() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    public List<Department> getAllDepartments(){
        return departmentMapper.getAllDepartmentsWithOutChildren();
    }

    public void addDep(Department dep) {
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
    }

    public Integer updateDepName(Department department){
        return departmentMapper.updateByPrimaryKey(department);
    }

    public void deleteDepById(Department dep) {
        departmentMapper.deleteDepById(dep);
    }
}
