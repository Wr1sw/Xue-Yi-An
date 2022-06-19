//package org.cuit.xueyian.api.salary;
//
//import org.cuit.xueyian.model.RespBean;
//import org.cuit.xueyian.model.Salary;
//import org.cuit.xueyian.service.SalaryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * @author wr1sw
// * @version 1.0.0
// * @description 工资账套 api
// */
//@RestController
//@RequestMapping("/salary/sob")
//public class SalaryController {
//    @Autowired
//    private SalaryService salaryService;
//
//    @GetMapping("/")
//    public List<Salary> getAllSalaries () {
//        return salaryService.getAllSalaries();
//    }
//
//    @PostMapping("/")
//    public RespBean addSalary(@RequestBody Salary salary) {
//        if (salaryService.addSalary(salary) == 2) {
//            return RespBean.ok("添加成功");
//        }
//        return RespBean.error("添加失败");
//    }
//
//    @DeleteMapping("/{id}")
//    public RespBean deleteSalary(@PathVariable Integer id) {
//        if (salaryService.deleteSalary(id) == 2 || salaryService.deleteSalary(id) == 1) {
//            return RespBean.ok("删除成功");
//        }
//        return RespBean.error("删除失败");
//    }
//
//    @PutMapping("/")
//    public RespBean updateSalaryById(@RequestBody Salary salary) {
//        if (salaryService.updateSalaryById(salary) == 1) {
//            return RespBean.ok("更新成功");
//        }
//        return RespBean.error("更新失败");
//    }
//}
