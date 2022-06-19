package org.cuit.xueyian.api.personnel;

import org.cuit.xueyian.model.*;
import org.cuit.xueyian.service.EmployeeRemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personnel/remove/")
public class EmpRemove {
    @Autowired
    EmployeeRemoveService employeeRemoveService;

    /**
     * 功能描述: 分页获取
     * @Param: [PageSize, currentPage]
     * @Return: org.cuit.xueyian.model.PageResp<org.cuit.xueyian.model.Employeeremove>
     * @Author: wsy
     * @Date: 2022/5/28 21:51
     */

    @GetMapping("/")
    public PageResp<Employeeremove> getEmployeeListByPage(@RequestParam(value="PageSize",defaultValue = "1") Integer PageSize, @RequestParam(value="currentPage",defaultValue = "1") Integer currentPage){
        return employeeRemoveService.getEmployeeListByPage(PageSize,currentPage);
    }

    /**
     * 功能描述: 分页查询
     * @Param: [info, PageSize, currentPage]
     * @Return: org.cuit.xueyian.model.PageResp<org.cuit.xueyian.model.Employeeremove>
     * @Author: wsy
     * @Date: 2022/5/28 21:52
     */
    @GetMapping("/query")
    public PageResp<Employeeremove> queryInfo(Employeeremove info, @RequestParam(value="PageSize",defaultValue = "1") Integer PageSize, @RequestParam(value="currentPage",defaultValue = "1") Integer currentPage){
        return employeeRemoveService.queryInfo(info,PageSize,currentPage);
    }


    /**
     * 功能描述:  添加 员工调动信息
     * @Param: [info]
     * @Return: org.cuit.xueyian.model.RespBean
     * @Author: wsy
     * @Date: 2022/5/28 21:53
     */

    @PostMapping("/")
    public RespBean addEmployeeRemove(@RequestBody Employeeremove info) {
        if ( employeeRemoveService.addEmpRm(info) == 1) {
            return RespBean.ok("添加成功,3s后退出...");
        }
        return RespBean.error("添加失败");
    }

    /**
     * 功能描述: 根据empRm ID 获取数据
     * @Param: [id]
     * @Return: org.cuit.xueyian.model.RespBean
     * @Author: wsy
     * @Date: 2022/5/28 21:54
     */
    @GetMapping("/{id}")
    public RespBean selectByPrimaryKey(@PathVariable Integer id){
        return RespBean.ok(employeeRemoveService.selectByEmpRmId(id));
    }

    /**
     * 功能描述: 更新 员工调动信息
     * @Param: [emp]
     * @Return: org.cuit.xueyian.model.RespBean
     * @Author: wsy
     * @Date: 2022/5/28 21:55
     */
    @PutMapping("/")
    public RespBean updateEmpRm(@RequestBody Employeeremove info){
        int flag = employeeRemoveService.editEmpRm(info);
        if(flag == 0){
            return RespBean.error("更新失败");
        }
        return  RespBean.ok("更新成功,3s退出...");
    }

    /**
     * 功能描述:  获取 dep,job,pos 的 id与name组合
     * @Param: []
     * @Return: org.cuit.xueyian.model.RespBean
     * @Author: wsy
     * @Date: 2022/5/28 21:57
     */
    @GetMapping("/getNameList")
    public RespBean getNameList(){
        return  RespBean.ok(employeeRemoveService.getNameList());
    }


    /**
     * 功能描述: 根据eid 获取 emp 表中信息
     * @Param: [eid]
     * @Return: org.cuit.xueyian.model.Employee
     * @Author: wsy
     * @Date: 2022/5/29 21:13
     */
    @GetMapping("/empId/{eid}")
    public RespBean getEmployeeListByPage(@PathVariable Integer eid){
        return RespBean.ok(employeeRemoveService.getEmpInfo(eid));
    }

    /**
     * 功能描述: 获取部门和id信息
     * @Param: []
     * @Return: org.cuit.xueyian.model.RespBean
     * @Author: wsy
     * @Date: 2022/5/29 23:12
     */

    @GetMapping("/depEmpList")
    public RespBean getEmpNameList() {
        return  RespBean.ok(employeeRemoveService.getDepEmpList());
    }

}
