package org.cuit.xueyian.api.salary;


import org.cuit.xueyian.model.RespBean;
import org.cuit.xueyian.model.SalaryGrantDetails;
import org.cuit.xueyian.service.SalaryGrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary/sg")
public class SalaryGrantApi {

    @Autowired
    private SalaryGrantService salaryGrantService;

    @PostMapping("/")
    public RespBean addSalaryGrantDetails(@RequestBody SalaryGrantDetails salaryGrantDetails){
        System.out.println(salaryGrantDetails.getSalaryPaidSum());

        if (salaryGrantService.addSalaryGrantDetails(salaryGrantDetails) == 1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    //查询状态为0的数据
    @GetMapping("/")
    public List<SalaryGrantDetails> getquerySal(){
        return salaryGrantService.querySal();
    }

    //将状态为0改为1
    @PostMapping("/update/{salary_grant_id}")
    public RespBean updateSalary(@PathVariable String salary_grant_id){
        if(salaryGrantService.updateSalaryGrant(salary_grant_id) == 1){
            return RespBean.ok("审批成功");
        }
        return RespBean.error("审批失败");
    }

}
