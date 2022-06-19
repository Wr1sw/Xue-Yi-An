package org.cuit.xueyian.api.salary;

import org.cuit.xueyian.model.*;
import org.cuit.xueyian.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/salary/table")
public class PayrollController {
    @Autowired
    private PayrollService payrollService;

//    @GetMapping("/")
//    public PageResp<PayrollDto> getEmployeePayrollByPage(@RequestParam(defaultValue = "1")Integer page,
//                                                         @RequestParam(defaultValue = "10")Integer size) {
//        return payrollService.getEmployeePayroll(page, size);
//    }


    @GetMapping("/department-salary")
    public JsonResponse<DepartmentSalaryDto> getDepWithSalary() {
        List<DepartmentSalaryDto> depWithSalary = payrollService.getDepWithSalary();
        return new JsonResponse(depWithSalary);
    }

    @GetMapping("/salaries")
    public JsonResponse<SalaryTableDto> getSalaryTable(String departmentName) {
        List<SalaryTableDto> list = payrollService.getSalaryTable(departmentName);
        return new JsonResponse(list);
    }
}
