package org.cuit.xueyian.api.personnel;

import org.cuit.xueyian.model.EntryInfo;
import org.cuit.xueyian.model.ResignationInfo;
import org.cuit.xueyian.service.EntryInfoService;
import org.cuit.xueyian.service.ResignationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 综合信息统计管理类
 * </p>
 *
 * @author Miracle
 * @since 2022-05-15
 */
@Controller
@RequestMapping("/statistics/all")
public class ComprehensiveInfoController {

    /**
     * 入职服务对象
     */
    @Autowired
    EntryInfoService entryService;

    /**
     * 离职服务对象
     */
    @Autowired
    ResignationInfoService resignService;

    /**
     * 获取所有员工的入职信息
     * 性能很差: 20-30秒
     * @return 员工入职信息集合
     */
    @RequestMapping("/allEntryInfo")
    @ResponseBody
    public List<EntryInfo> getAllEntryInfo(){
        return entryService.getALlEntryInfos();
    }

    /**
     * 获取指定页面的入职信息
     * @return 员工入职信息集合
     */
    @RequestMapping("/someEntryInfo")
    @ResponseBody
    public List<EntryInfo> getSomeEntryInfo(@RequestParam("PageSize") Integer pageSize, @RequestParam("currentPage") Integer currentPage){
        return entryService.getSomeEntryInfo(pageSize, currentPage);
    }

    /**
     * 通过员工工号查询他的入职信息
     * @param workerId 员工工号
     * @return 员工入职信息
     */
    @RequestMapping("/anEntryInfoByWorkerId")
    @ResponseBody
    public EntryInfo getAnEntryInfoByWorkerId(String workerId){
        return entryService.getAnEntryInfo(workerId);
    }

    /**
     * 获取指定页面的员工离职信息
     * @param pageSize 页面需要的条数
     * @param currentPage 对当前页
     * @return 员工离职信息集合
     */
    @RequestMapping("/someResignationInfo")
    @ResponseBody
    public List<ResignationInfo> getSomeResignationInfo(@RequestParam("PageSize") Integer pageSize, @RequestParam("currentPage") Integer currentPage){
        return resignService.getSomeResignationInfo(pageSize, currentPage);
    }

    /**
     * 通过员工工号查询他的离职信息
     * @param workerId 员工工号
     * @return 员工离职信息
     */
    @RequestMapping("/aResignationInfoByWorkerId")
    @ResponseBody
    public ResignationInfo getAResignationInfoByWorkerId(String workerId){
        return resignService.getAResignationInfo(workerId);
    }
}
