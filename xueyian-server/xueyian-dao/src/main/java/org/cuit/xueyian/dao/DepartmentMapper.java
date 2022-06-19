package org.cuit.xueyian.dao;

import org.apache.ibatis.annotations.Mapper;
import org.cuit.xueyian.model.Department;
import org.cuit.xueyian.model.DepartmentSalaryDto;
import org.cuit.xueyian.model.JsonResponse;
import org.cuit.xueyian.model.SalaryTableDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    //获取部门名称与ID的映射
    List<Department> getID_Name();

    //使用部门ID获取部门名称
    String selectNameByPrimaryKey(Integer id);

    void addDep(Department dep);

    List<Department> getAllDepartmentsWithOutChildren();

    List<Department> getAllDepartmentsByParentId(Integer pid);

    void deleteDepById(Department dep);

    List<Department> selectNameByIdList(List<Integer> ids);

    List<DepartmentSalaryDto> getDepWithSalary();

    List<SalaryTableDto> getSalaryTable(String departmentName);

}