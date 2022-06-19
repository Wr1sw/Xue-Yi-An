package org.cuit.xueyian.api.salary;

import org.cuit.xueyian.model.Employee;
import org.cuit.xueyian.model.PageResp;
import org.cuit.xueyian.model.RespBean;
import org.cuit.xueyian.model.Salary;
import org.cuit.xueyian.service.EmployeeService;
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
@RequestMapping("/salary/sobcfg/")
public class SalarySobConfigApi {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SalaryStandardService salaryStandardService;

    @GetMapping("/")
    public PageResp<Employee> getEmployeeByPageWithSalaryStandard(@RequestParam(defaultValue = "1") Integer page,
                                                                  @RequestParam(defaultValue = "10") Integer size) {
        return employeeService.getEmployeeByPageWithSalaryStandard(page, size);
    }

    @GetMapping("/salaries")
    public List<Salary> getAllSalaries() {
        return salaryStandardService.getAllSalaryStanders();
    }

    @PutMapping("/")
    public RespBean updateEmployeeOfSalaryById(Integer eid, Integer sid) {
        System.out.println("eid = " + eid);
        System.out.println("sid = " + sid);
        Integer res = employeeService.updateEmployeeOfSalaryStandardById(eid, sid);
        if (res == 2 || res == 1) {
            return RespBean.ok("更新成功");
        } else {
            return RespBean.error("更新失败");
        }
    }
}
