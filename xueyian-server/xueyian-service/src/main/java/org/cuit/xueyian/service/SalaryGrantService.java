package org.cuit.xueyian.service;

import org.cuit.xueyian.dao.SalaryGrantDetailsMapper;
import org.cuit.xueyian.dao.SalaryGrantMapper;
import org.cuit.xueyian.model.SalaryGrant;
import org.cuit.xueyian.model.SalaryGrantDetails;
import org.cuit.xueyian.model.SalaryStandard;
import org.cuit.xueyian.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SalaryGrantService {

    @Autowired
    private SalaryGrantMapper salaryGrantMapper;

    @Autowired
    private SalaryGrantDetailsMapper salaryGrantDetailsMapper;

    public Integer addSalaryGrantDetails(SalaryGrantDetails salaryGrantDetails){

        salaryGrantDetails.setSalaryGrantId(CommonUtils.generateUUID());

        addSalaryGrant(salaryGrantDetails);

        return salaryGrantDetailsMapper.insertSelective(salaryGrantDetails);
    }

    public Integer addSalaryGrant(SalaryGrantDetails salaryGrantDetails){

        SalaryGrant salaryGrant = new SalaryGrant();

        salaryGrant.setSalaryGrantId(salaryGrantDetails.getSalaryGrantId());

        salaryGrant.setCheckStatus("0");

        return salaryGrantMapper.insertSelective(salaryGrant);
    }

    //查询状态为0的数据
    public List<SalaryGrantDetails> querySal(){
        return salaryGrantDetailsMapper.querySal();
    }

    //更新  将状态为0改成状态为1
    public Integer updateSalaryGrant(String salary_grant_id){
        return salaryGrantMapper.updateSalary(salary_grant_id);
    }

}
