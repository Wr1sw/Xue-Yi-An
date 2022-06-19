package org.cuit.xueyian.api.personnel;

import org.cuit.xueyian.model.Employee;
import org.cuit.xueyian.model.PageResp;
import org.cuit.xueyian.model.RespBean;
import org.cuit.xueyian.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personnel/emp/")
public class PerEmp {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 功能描述: 分页获取员工信息
     * @Param: [PageSize, currentPage]
     * @Return: org.cuit.xueyian.model.PageResp
     * @Author: wsy
     * @Date: 2022/5/25 21:57
     */
    @GetMapping("/")
    public PageResp<Employee> getEmployeeListByPage(@RequestParam(value="PageSize",defaultValue = "1") Integer PageSize, @RequestParam(value="currentPage",defaultValue = "1") Integer currentPage){
        return employeeService.getEmployeeListByPage(PageSize,currentPage);
    }

    /**
     * 功能描述: 分页查询
     * @Param: [emp, PageSize, currentPage]
     * @Return: org.cuit.xueyian.model.RespBean
     * @Author: wsy
     * @Date: 2022/5/25 21:58
     */
    //分页-查询
    @GetMapping("/query")
    public PageResp<Employee> queryInfo(Employee emp, @RequestParam(value="PageSize",defaultValue = "1") Integer PageSize, @RequestParam(value="currentPage",defaultValue = "1") Integer currentPage){
        return employeeService.queryInfo(emp,PageSize,currentPage);
    }

    //添加员工信息
    @PostMapping("/")
    public RespBean addEmployee(@RequestBody Employee emp) {
        if ( employeeService.addEmployee(emp) == 1) {
            return RespBean.ok("添加成功,3s后退出...");
        }
        return RespBean.error("添加失败");
    }

    //根据ID获取员工信息
    @GetMapping("/{id}")
    public RespBean selectByPrimaryKey(@PathVariable Integer id){
        return RespBean.ok(employeeService.selectByPrimaryKey(id));
    }

    //更新员工信息
    @PutMapping("/")
    public RespBean updateEmployee(@RequestBody Employee emp){
        int flag = employeeService.updateByPrimaryKeySelective(emp);
        if(flag == 0){
            return RespBean.error("更新失败");
        }
        return  RespBean.ok("更新成功,3s退出...");
    }

    //根据ID删除员工信息
    @DeleteMapping("/{id}")
    public RespBean deleteByPrimaryKey(@RequestParam("id") Integer id){
        int flag = employeeService.deleteByPrimaryKey(id);
        if(flag == 0){
            return RespBean.error("删除失败");
        }
        return  RespBean.ok("删除成功");
    }

    @GetMapping("/other")
    public RespBean getNameLists(){
        return  RespBean.ok(employeeService.getNameLists());
    }

    @GetMapping("/depList")
    public RespBean getDepNameList(){
        return  RespBean.ok(employeeService.getDepNameList());
    }

}
