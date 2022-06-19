package org.cuit.xueyian.service;

import org.cuit.xueyian.dao.SalaryMapper;
import org.cuit.xueyian.model.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description 工资账套service
 */
@Service
public class SalaryService {

    @Autowired
    private SalaryMapper salaryMapper;
    public List<Salary> getAllSalaries() {
        return salaryMapper.getAllSalaries();
    }

    public Integer addSalary(Salary salary) {
        salary.setCreateDate(new Date());
        return salaryMapper.insertSelective(salary);
    }

    public Integer deleteSalary(Integer id) {
        return salaryMapper.deleteByPrimaryKey(id);
    }

    public Integer updateSalaryById(Salary salary) {
        return salaryMapper.updateByPrimaryKeySelective(salary);
    }
}
