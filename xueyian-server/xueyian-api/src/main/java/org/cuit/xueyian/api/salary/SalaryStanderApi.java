package org.cuit.xueyian.api.salary;

import org.cuit.xueyian.model.RespBean;
import org.cuit.xueyian.model.Salary;
import org.cuit.xueyian.model.SalaryStandard;
import org.cuit.xueyian.model.SalaryStandardDetails;
import org.cuit.xueyian.service.SalaryStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description
 */
@RestController
@RequestMapping("/salary/sob")
public class SalaryStanderApi {
    @Autowired
    private SalaryStandardService salaryStandardService;

    @GetMapping("/")
    public List<SalaryStandardDetails> getAllSalaries() {
        return salaryStandardService.getAllSalaryStanderDetails();
    }

    @PostMapping("/")
    public RespBean addSalaryStanderDetails(@RequestBody SalaryStandardDetails salaryStandardDetails) {
        if (salaryStandardService.addSalaryStanderDetails(salaryStandardDetails) == 1) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteSalaryStanderDetails(@PathVariable Integer id) {
        Integer res = salaryStandardService.deleteSalaryStanderDetails(id);
        if (res == 2 || res == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @PutMapping("/")
    public RespBean updateSalaryStanderDetails(@RequestBody SalaryStandardDetails salaryStandardDetails) {
        if (salaryStandardService.updateSalaryStanderDetails(salaryStandardDetails) == 1) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
