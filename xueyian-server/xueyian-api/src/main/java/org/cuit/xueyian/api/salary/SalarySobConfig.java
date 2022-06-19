//package org.cuit.xueyian.api.salary;
//
//
//import org.cuit.xueyian.model.Employee;
//import org.cuit.xueyian.model.PageResp;
//import org.cuit.xueyian.model.RespBean;
//import org.cuit.xueyian.model.Salary;
//import org.cuit.xueyian.service.EmployeeService;
//import org.cuit.xueyian.service.SalaryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/salary/sobcfg/")
//public class SalarySobConfig {
//    @Autowired
//    private EmployeeService employeeService;
//
//    @Autowired
//    private SalaryService salaryService;
//
//    @GetMapping("/")
//    public PageResp<Employee> getEmployeeByPageWithSalary(@RequestParam(defaultValue = "1") Integer page,
//                                                          @RequestParam(defaultValue = "10") Integer size) {
//        return employeeService.getEmployeeByPageWithSalary(page, size);
//    }
//
//    @GetMapping("/salaries")
//    public List<Salary> getAllSalaries() {
//        return salaryService.getAllSalaries();
//    }
//
//    @PutMapping("/")
//    public RespBean updateEmployeeOfSalaryById(Integer eid, Integer sid) {
//        System.out.println(eid);
//        System.out.println(sid);
//        Integer res = employeeService.updateEmployeeOfSalaryById(eid, sid);
//        if (res == 2 || res == 1) {
//            return RespBean.ok("更新成功");
//        } else {
//            return RespBean.error("更新失败");
//        }
//    }
//}
