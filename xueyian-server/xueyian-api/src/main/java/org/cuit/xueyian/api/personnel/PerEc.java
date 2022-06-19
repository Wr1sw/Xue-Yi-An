package org.cuit.xueyian.api.personnel;

import org.cuit.xueyian.model.PageResp;
import org.cuit.xueyian.model.*;
import org.cuit.xueyian.service.EmployeeEcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personnel/ec")
public class PerEc {

    @Autowired
    private EmployeeEcService employeeEcService;


    @GetMapping("/")
    public PageResp<EmpEcRP> getEmployeeListByPage(@RequestParam(value="PageSize",defaultValue = "1") Integer PageSize, @RequestParam(value="currentPage",defaultValue = "1") Integer currentPage){
        return employeeEcService.getEmployeeListByPage(PageSize,currentPage);
    }

    // query-- 输入名称模糊查询， 奖、罚， 奖罚原因
    @GetMapping("/query")
    public PageResp<EmpEcRP> queryInfo(EmpEcRP info, @RequestParam(value="PageSize",defaultValue = "1") Integer PageSize, @RequestParam(value="currentPage",defaultValue = "1") Integer currentPage){
        return employeeEcService.queryInfo(info,PageSize,currentPage);
    }

    //更新员工信息
    @PutMapping("/")
    public RespBean  editEmpEc(@RequestBody Employeeec info){
        int flag = employeeEcService.editEmpEc(info);
        if(flag == 0){
            return RespBean.error("更新失败");
        }
        return  RespBean.ok("更新成功,3s退出...");
    }

    //根据ID获取员工奖惩信息
    @GetMapping("/{id}")
    public RespBean getEmpEcById(@PathVariable Integer id){
        return RespBean.ok(employeeEcService.getEmpEcById(id));
    }


    // 删除一条奖罚记录 传入ID
    @DeleteMapping("/{id}")
    public RespBean deleteEmpEc(@PathVariable Integer id){
        int res = employeeEcService.deleteEmpEc(id);
        if(res != 1)
            return RespBean.error("删除失败");
        return RespBean.ok("删除成功");
    }

    // 获取 RP list 用于展示
    @GetMapping("ecType")
    public RespBean  getRPList(@RequestParam(value="ecType",defaultValue = "-1") Integer ecType){
        String ecTypeStr="";
        if (ecType != -1) {
            ecTypeStr = ecType.toString();
        }
        return  RespBean.ok(employeeEcService.getRPListByEcType(ecTypeStr));
    }


    @GetMapping("/depEmpList")
    public RespBean getEmpNameList() {
        return  RespBean.ok(employeeEcService.getDepEmpList());
    }


    //添加员工信息
    @PostMapping("/")
    public RespBean addEmployeeEc(@RequestBody Employeeec emp) {
        if ( employeeEcService.addEmpEc(emp) == 1) {
            return RespBean.ok("添加成功,3s后退出...");
        }
        return RespBean.error("添加失败");
    }
}
