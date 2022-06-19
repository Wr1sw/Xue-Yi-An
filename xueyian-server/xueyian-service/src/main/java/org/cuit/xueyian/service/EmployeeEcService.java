package org.cuit.xueyian.service;

import org.cuit.xueyian.dao.EmployeeMapper;
import org.cuit.xueyian.dao.EmployeeecMapper;
import org.cuit.xueyian.dao.RPMapper;
import org.cuit.xueyian.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeEcService {


    @Autowired
    EmployeeecMapper employeeecMapper;

    @Autowired
    RPMapper rpMapper;

    @Autowired
    EmployeeService employeeService;

    // query-- 输入 员工名称模糊查询 employee表， 奖、罚 PR表， 奖罚原因 PR表
    public PageResp<EmpEcRP> queryInfo(EmpEcRP empEcRP,Integer PageSize, Integer currentPage){
        if (PageSize == null || currentPage == null) {
            throw new RuntimeException("非法数据");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("ename", empEcRP.getEname());
        // Number to String
        if (empEcRP.getEcType() != null) {
            map.put("ecType", empEcRP.getEcType().toString());
        }
        map.put("rid", empEcRP.getRid());
        map.put("PageSize", PageSize);
        map.put("offsetData", (currentPage - 1) * PageSize); // 设置数据库数据开始的条数
        Long total = employeeecMapper.queryInfoLength(map);
        if (total < 0) throw new RuntimeException("数据异常");
        List<EmpEcRP> empEcRPList = employeeecMapper.queryInfo(map);
        PageResp<EmpEcRP> res = new PageResp<>();
        res.setTotal(total);
        res.setData(empEcRPList);
        return res;
    }

    //分页获取数据
    public PageResp<EmpEcRP> getEmployeeListByPage(Integer PageSize, Integer currentPage){
        if (PageSize == null ||  currentPage == null) {
            throw new RuntimeException("非法数据");
        }
        HashMap<String,Object> map=new HashMap<>();
        map.put("PageSize",PageSize);
        map.put("offsetData", (currentPage-1)*PageSize); // 设置数据库数据开始的条数

        PageResp<EmpEcRP> res = new PageResp<>();
        List<EmpEcRP> list = employeeecMapper.getEmpEcRPListByPage(map);

        //获取总条数
        res.setTotal(employeeecMapper.getTotal());
        res.setData(list);
        return res;
    }

    //根据ecID更新员工奖惩信息 --
    public Integer editEmpEc(Employeeec ecInfo){
        int length = employeeecMapper.updateByPrimaryKeySelective(ecInfo);
        if(length == 0) throw new RuntimeException("数据异常");
        return length;
    }


    // 删除一条奖罚记录 传入ID
    public Integer deleteEmpEc(Integer id){
        int length = employeeecMapper.deleteByPrimaryKey(id);
        if(length == 0) throw new RuntimeException("数据异常");
        return length;
    }

    //查询RP List
    public List<RP> getRPListByEcType(String ecTypeStr){
        return rpMapper.getRPByEcType(ecTypeStr);
    }

    // 根据 empec ID获取数据
   public EmpEcRP getEmpEcById(Integer id){
       return employeeecMapper.selectById(id);
   }

   // 获取部门名称
   public List<Map<String,Object>> getDepEmpList(){
        return employeeService.getDepEmpList();
   }

   // 新增员工奖惩信息
    public Integer addEmpEc(Employeeec employeeec){
        int res = employeeecMapper.insertSelective(employeeec);
        if(res!=1) throw new RuntimeException();
        return res;
    }

}
