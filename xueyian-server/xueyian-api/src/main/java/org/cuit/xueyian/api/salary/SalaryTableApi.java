//package org.cuit.xueyian.api.salary;
//
//import org.cuit.xueyian.model.Employee;
//import org.cuit.xueyian.model.PageResp;
//import org.cuit.xueyian.service.EmployeeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
///**
// * @author wr1sw
// * @version 1.0.0
// * @description
// */
//@RestController
//@RequestMapping("/salary/table")
//public class SalaryTableApi {
//
//    @Autowired
//    EmployeeService employeeService;
//    @GetMapping("/")
//    public PageResp<Employee> getAllsalarts(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Employee employee, Date[] beginDateScope){
//        return employeeService.getAllsalarts(page,size,employee,beginDateScope);
//    }
//}
