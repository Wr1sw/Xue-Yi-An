package org.cuit.xueyian.service;

import com.sun.xml.internal.bind.v2.TODO;
import org.cuit.xueyian.dao.*;
import org.cuit.xueyian.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PayrollService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private SalaryGrantDetailsMapper salaryGrantDetailsMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 获取每个部门中拥有工资账套的人数
     *
     * @return
     */
    public List<DepartmentSalaryDto> getDepWithSalary() {
        List<DepartmentSalaryDto> list = departmentMapper.getDepWithSalary();
        return list;
    }

    public List<SalaryTableDto> getSalaryTable(String departmentName) {
        List<SalaryTableDto> ans = departmentMapper.getSalaryTable(departmentName);
        Set<Integer> sidSet = ans.stream().map(SalaryTableDto::getSid).collect(Collectors.toSet());
        List<SalaryTableDto> salaryTableDtoList = salaryGrantDetailsMapper.getSalaryTableWithSidSet(sidSet);
        for (SalaryTableDto i : ans) {
            for (SalaryTableDto salaryTableDto : salaryTableDtoList) {
                if (i.getSid() == salaryTableDto.getSid()) {
                    i.setBasicSalary(salaryTableDto.getBasicSalary());
                    i.setLunchSalary(salaryTableDto.getLunchSalary());
                    i.setTrafficSalary(salaryTableDto.getTrafficSalary());
                    i.setPensionBase(salaryTableDto.getPensionBase());
                    i.setPensionPer(salaryTableDto.getPensionPer());
                    i.setMedicalBase(salaryTableDto.getMedicalBase());
                    i.setMedicalPer(salaryTableDto.getMedicalPer());
                    i.setAccumulationFundBase(salaryTableDto.getAccumulationFundBase());
                    i.setAccumulationFundPer(salaryTableDto.getAccumulationFundPer());
                    i.setCreateDate(salaryTableDto.getCreateDate());
                    i.setAllSalary(salaryTableDto.getAllSalary());
                    i.setSalarySum(salaryTableDto.getSalarySum());
                }
            }
        }
        return ans;
    }

//    public PageResp<PayrollDto> getEmployeePayroll(Integer page, Integer size) {
//        List<Employee> employeeByPageWithSalary = employeeMapper.getEmployeeByPageWithSalary(page, size);
//        List<PayrollDto> payrollDtoList = new ArrayList<>();
//        for (Employee employee : employeeByPageWithSalary) {
//            Integer realPay = 0;
//            PayrollDto payrollDto = new PayrollDto();
//            Salary salary = employee.getSalary();
//            payrollDto.setId(employee.getId());
//            payrollDto.setName(employee.getName());
//            payrollDto.setDepartmentName(employee.getDepartment().getName());
//            realPay += salary.getBasicSalary();
//            payrollDto.setBasicSalary(salary.getBasicSalary());
//            realPay += salary.getLunchSalary();
//            payrollDto.setLunchSalary(salary.getLunchSalary());
//            realPay += salary.getTrafficSalary();
//            payrollDto.setTrafficSalary(salary.getTrafficSalary());
//            payrollDto.setPension((int) (salary.getPensionBase() * salary.getPensionPer()));
//            realPay += payrollDto.getPension();
//            payrollDto.setMedical((int) (salary.getMedicalBase() * salary.getMedicalPer()));
//            realPay += payrollDto.getMedical();
//            payrollDto.setAccumulationFund((int) (salary.getAccumulationFundBase() * salary.getAccumulationFundPer()));
//            realPay += payrollDto.getAccumulationFund();
//            payrollDto.setBonusBySOB(salary.getBonus());
//            realPay += payrollDto.getBonusBySOB();
//            List<RP> rps = queryBonusOrFineByEmpId(employee.getId());
//            if (rps.size() > 0) {
//                int temp = 0;
//                for (RP rp : rps) {
//                    if (rp.getEcType() == 1) {
//                        temp += rp.getResult();
//                    } else if (rp.getEcType() == 0) {
//                        temp -= rp.getResult();
//                    }
//                }
//                payrollDto.setBonusByEmp(temp);
//                realPay += temp;
//            }
//            payrollDto.setAllSalary(realPay);
//            // TODO 计算在职天数
//            payrollDtoList.add(payrollDto);
//        }
//        Long total = employeeMapper.getTotal();
//        PageResp<PayrollDto> res = new PageResp<>(total, payrollDtoList);
//        return res;
//    }
//
//    public List<RP> queryBonusOrFineByEmpId(Integer id) {
//        return rpMapper.queryBonusOrFineByEmpId(id);
//    }

}
