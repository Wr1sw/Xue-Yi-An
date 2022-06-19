package org.cuit.xueyian.service;

import org.cuit.xueyian.dao.SalaryStandardDetailsMapper;
import org.cuit.xueyian.dao.SalaryStandardMapper;
import org.cuit.xueyian.exception.ConditionException;
import org.cuit.xueyian.model.Salary;
import org.cuit.xueyian.model.SalaryStandard;
import org.cuit.xueyian.model.SalaryStandardDetails;
import org.cuit.xueyian.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description
 */
@Service
public class SalaryStandardService {
    @Autowired
    private SalaryStandardDetailsMapper salaryStandardDetailsMapper;

    @Autowired
    private SalaryStandardMapper salaryStandardMapper;

    /**
     * 获取所有工资账套
     *
     * @return 工资账套集合
     */
    public List<SalaryStandardDetails> getAllSalaryStanderDetails() {
        return salaryStandardDetailsMapper.getAllSalaryStanderDetailsWithCheck();
    }

    /**
     * 新增工资账套
     *
     * @param salaryStandardDetails 前端传入的工资账套
     * @return 新增结果
     */
    public Integer addSalaryStanderDetails(SalaryStandardDetails salaryStandardDetails) {
        // 设置薪酬标准单编号
        salaryStandardDetails.setStandardId(CommonUtils.generateUUID());

        // 设置时间
        salaryStandardDetails.setCreateDate(new Date());

        // 初始化审批
        SalaryStandard salaryStandard = initApprove(salaryStandardDetails);

        salaryStandard.setChangeStatus("0");
        // 提交审批
        Integer res = this.addSalaryStander(salaryStandard);
        if (res == 0) throw new ConditionException("数据异常");
        return salaryStandardDetailsMapper.insertSelective(salaryStandardDetails);
    }

    /**
     * 初始化审批数据 工资账套的数据存放到审批中，
     *
     * @param salaryStandardDetails
     * @return
     */
    private SalaryStandard initApprove(SalaryStandardDetails salaryStandardDetails) {
        SalaryStandard res = new SalaryStandard();
        // 编号
        res.setStandardId(salaryStandardDetails.getStandardId());
        // 名称
        res.setStandardName(salaryStandardDetails.getStandardName());
        // 制定者
        res.setDesigner(salaryStandardDetails.getDesigner());
        // 登记者
        res.setRegister(salaryStandardDetails.getRegister());
        // 登记时间
        res.setRegistTime(new Date());
        // 计算工资总额
        Integer sum = calSalarySum(salaryStandardDetails);
        res.setSalarySum(String.valueOf(sum));
        res.setCheckStatus("0");
        return res;
    }

    /**
     * 计算总薪资，计算方法为：所有工资相加 - 所有base*per
     *
     * @param s
     * @return
     */
    private Integer calSalarySum(SalaryStandardDetails s) {
        Integer res = 0;
        res += s.getBasicSalary();
        res += s.getLunchSalary();
        res += s.getTrafficSalary();
        res -= (int) (s.getPensionBase() * s.getPensionPer());
        res -= (int) (s.getMedicalBase() * s.getMedicalPer());
        res -= (int) (s.getAccumulationfundBase() * s.getAccumulationfundPer());
        return res;
    }

    public Integer addSalaryStander(SalaryStandard salaryStandard) {
        return salaryStandardMapper.insertSelective(salaryStandard);
    }

    /**
     * 根据前端传过来的id删除指定id数据
     *
     * @param id
     * @return 返回删除结果
     */
    public Integer deleteSalaryStanderDetails(Integer id) {
        // 删除标准表相关字段
        Integer i = salaryStandardMapper.deleteByStdId(id);
        Integer res = salaryStandardDetailsMapper.deleteByPrimaryKey(id);
        return i + res;
    }

    /**
     * 更新标准单，设置更新者和更新时间，提交审批
     *
     * @param salaryStandardDetails
     * @return
     */
    public Integer updateSalaryStanderDetails(SalaryStandardDetails salaryStandardDetails) {
        // 重新生成标准单编号
        salaryStandardDetails.setStandardId(CommonUtils.generateUUID());
        // 初始化审批
        SalaryStandard salaryStandard = initApprove(salaryStandardDetails);
        // 变更人
        System.out.println("salaryStandardDetails.getChanger() = " + salaryStandardDetails.getChanger());
        salaryStandard.setChanger(salaryStandardDetails.getChanger());
        // 变更时间
        salaryStandard.setChangeTime(new Date());
        // 提交审批
        Integer res = this.addSalaryStander(salaryStandard);
        if (res == 0) throw new ConditionException("数据异常");
        // 删除旧数据
        this.salaryStandardMapper.deleteByStdId(salaryStandardDetails.getSdtId());

        return salaryStandardDetailsMapper.updateByPrimaryKeySelective(salaryStandardDetails);
    }

    /**
     * 获取所有待审批的工资账套
     *
     * @return
     */
    public List<SalaryStandard> getAllSalaryStanderNoApprove() {
        return salaryStandardMapper.getAllSalaryStanderNoApprove();
    }

    public Integer doApproval(SalaryStandard salaryStandard) {
        // 复核时间
        salaryStandard.setCheckTime(new Date());
        // 审核通过
        if (salaryStandard.getCheckStatus().equals("1")) {
            return salaryStandardMapper.updateByPrimaryKeySelective(salaryStandard);
        }
        // 复核不通过,
        // TODO 发送邮件通知
        return salaryStandardMapper.updateByPrimaryKeySelective(salaryStandard);
    }

    public SalaryStandardDetails getStandardDetailsByStanderId(String standardId) {
        return salaryStandardDetailsMapper.getStandardDetailsByStanderId(standardId);
    }

    public List<Salary> getAllSalaryStanders() {
        return salaryStandardMapper.getAllSalaryStanders();
    }
}
