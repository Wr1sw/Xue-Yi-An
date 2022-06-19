package org.cuit.xueyian.api.system;

import org.cuit.xueyian.model.Department;
import org.cuit.xueyian.model.RespBean;
import org.cuit.xueyian.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/cfg/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartmentsById();
    }

    @OperationLog(operModel = "系统管理-基础信息管理-部门管理", operDesc = "添加部门")
    @PostMapping("/")
    public RespBean addDep(@RequestBody Department dep) {
        departmentService.addDep(dep);
        if (dep.getResult() == 1) {
            return RespBean.ok("添加成功", dep);
        }
        return RespBean.error("添加失败");
    }

    @OperationLog(operModel = "系统管理-基础信息管理-部门管理", operDesc = "更新部门")
    @PostMapping("/update/")
    public RespBean updateDep(@RequestBody Department dep){
        if (departmentService.updateDepName(dep) == 1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @OperationLog(operModel = "系统管理-基础信息管理-部门管理", operDesc = "删除部门")
    @DeleteMapping("/{id}")
    public RespBean deleteDepById(@PathVariable Integer id) {
        System.out.println(id);
        Department dep = new Department();
        dep.setId(id);
        departmentService.deleteDepById(dep);
        if (dep.getResult() == -2) {
            return RespBean.error("该部门下有子部门，删除失败");
        } else if (dep.getResult() == -1) {
            return RespBean.error("该部门下有员工，删除失败");
        } else if (dep.getResult() == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    // 获取部门name、id组合
    @GetMapping("/getDepName")
    public RespBean getDepartmentID_Name(){
        return RespBean.ok(departmentService.getID_Name());
    }

    // 根据ID获取部门名称
    @GetMapping("/getDepNameById")
    public RespBean getDepartmentNameById(@RequestParam("departmentId") Integer departmentId){
        return RespBean.ok(departmentService.selectNameByPrimaryKey(departmentId));
    }

}
