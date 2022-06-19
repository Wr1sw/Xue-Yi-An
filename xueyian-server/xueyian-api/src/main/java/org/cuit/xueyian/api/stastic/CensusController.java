package org.cuit.xueyian.api.stastic;

import org.cuit.xueyian.api.system.OperationLog;
import org.cuit.xueyian.model.Census;
import org.cuit.xueyian.model.RespBean;
import org.cuit.xueyian.service.CensusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 人事信息统计管理类
 * </p>
 *
 * @author Miracle
 * @since 2022-05-15
 */
@Controller
@RequestMapping("/statistics/personnel")
public class CensusController {
    /**
     * 人事信息统计服务对象
     */
    @Autowired
    CensusService service;

    /*************************** 人事信息部分 ***************************************/

    /**
     * 获取公司所有部门人数信息集合
     * @return 公司各部门人数集合
     */
    @RequestMapping("/departmentPerson")
    @ResponseBody
    @OperationLog(operModel = "统计管理-人事信息统计-统计各部门人数",operDesc = "查询部门人数")
    public RespBean getDepartmentPerson(){
        return RespBean.ok(service.getDepartmentPerson());
    }

    /**
     * 获取公司所有的职称信息
     * @return 职称信息集合
     */
    @RequestMapping("/title")
    @ResponseBody
    @OperationLog(operModel = "统计管理-人事信息统计-统计各职称人数",operDesc = "查询职称人数")
    public RespBean getTitle(){
        return RespBean.ok(service.getTitle());
    }

    /**
     * 获取公司所有员工的工龄
     * @return 工龄信息集合
     */
    @RequestMapping("/workAge")
    @ResponseBody
    @OperationLog(operModel = "统计管理-人事信息统计-统计工龄",operDesc = "查询工龄")
    public RespBean getWorkAge(){
        return RespBean.ok(service.getWorkAge());
    }

    /*************************** 综合信息部分 ***************************************/

    /**
     * 获取公司所有人的毕业学校信息
     * @return 毕业学校集合
     */
    @RequestMapping("/schoolInfo")
    @ResponseBody
    @OperationLog(operModel = "统计管理-综合信息统计-统计学校信息",operDesc = "查询学校信息")
    public RespBean getSchoolInfo(){
        return RespBean.ok(service.getSchoolInfo());
    }

    /**
     * 获取公司所有人的专业信息
     * @return 专业信息集合
     */
    @RequestMapping("/major")
    @ResponseBody
    @OperationLog(operModel = "统计管理-综合信息统计-统计专业信息",operDesc = "查询专业信息")
    public RespBean getMajor(){
        return RespBean.ok(service.getMajor());
    }

    /**
     * 获取公司所有的学历信息
     * @return 学历信息集合
     */
    @RequestMapping("/topDegree")
    @ResponseBody
    @OperationLog(operModel = "统计管理-综合信息统计-统计学位信息",operDesc = "查询学位信息")
    public RespBean getTopDegree(){
        return RespBean.ok(service.getTopDegree());
    }

    /**
     * 获取公司员工婚姻状况信息
     * @return 婚姻状况信息集合
     */
    @RequestMapping("/wedlock")
    @ResponseBody
    @OperationLog(operModel = "统计管理-综合信息统计-统计感情信息",operDesc = "查询感情信息")
    public RespBean getWedlock(){
        return  RespBean.ok(service.getWedlock());
    }

    /**
     * 获取公司所有人的出生年信息
     * @return 出生年信息集合
     */
    @RequestMapping("/birthYear")
    @ResponseBody
    @OperationLog(operModel = "统计管理-综合信息统计-统计出生年信息",operDesc = "查询出生年信息")
    public RespBean getBirthYear(){

        return RespBean.ok(service.getBirthYear());
    }

    /**
     * 获取公司所有人的政治信息
     * @return 政治信息集合
     */
    @RequestMapping("/politicStatus")
    @ResponseBody
    @OperationLog(operModel = "统计管理-综合信息统计-统计政治信息",operDesc = "查询政治信息")
    public RespBean getPoliticStatus(){
        return RespBean.ok(service.getPoliticStatus());
    }

    /**
     * 获取公司所有人的性别信息
     * @return 性别信息集合
     */
    @RequestMapping("/gender")
    @ResponseBody
    @OperationLog(operModel = "统计管理-综合信息统计-统计性别信息",operDesc = "查询性别信息")
    public RespBean getGender(){
        return RespBean.ok(service.getGender());
    }


}
