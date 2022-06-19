package org.cuit.xueyian.api.announcement;

import org.cuit.xueyian.api.system.OperationLog;
import org.cuit.xueyian.model.Announcement;
import org.cuit.xueyian.model.PageResp;
import org.cuit.xueyian.model.RespBean;
import org.cuit.xueyian.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/statistics/record")
public class AnnouncementController {

    @Autowired
    AnnouncementService service;

    /**
     * 功能描述: 获取所有数据List
     * @Param: []
     * @Return: org.cuit.xueyian.model.RespBean
     * @Author: wsy
     * @Date: 2022/6/2 12:57
     */
    @ResponseBody
    @GetMapping("/")
    @OperationLog(operModel = "其它模块-公告模块-公告信息",operDesc = "查询所有公告信息")
    public RespBean getAll(){
        return RespBean.ok(service.getAll());
    }


    /**
     * 获取指定页面的数据
     * @param pageSize 页面大小
     * @param currentPage 当前页
     * @return 指定页面的记录集合和总的记录数
     */
    @ResponseBody
    @RequestMapping("/announcementList")
    public PageResp<Announcement> getAnnouncementList(@RequestParam("pageSize") Integer pageSize, @RequestParam("currentPage") Integer currentPage){
        return service.getAnnouncementList(pageSize, currentPage);
    }

    /**
     * 根据id更新数据
     * @param announcement 公告实体
     * @return 更新结果
     */
    @ResponseBody
    @RequestMapping("/update")
    @OperationLog(operModel = "其它模块-公告模块-更新公告信息",operDesc = "更新公告信息")
    public RespBean updateByPrimaryKey(@RequestBody Announcement announcement){
        return RespBean.ok(service.updateByPrimaryKey(announcement));
    }

    /**
     * 根据id删除公告
     * @param id 公告id
     * @return 删除结果
     */
    @ResponseBody
    @RequestMapping("/delete")
    @OperationLog(operModel = "其它模块-公告模块-删除公告信息",operDesc = "删除公告信息")
    public RespBean deleteByPrimaryKey(Integer id){
        return RespBean.ok(service.deleteByPrimaryKey(id));
    }

    /**
     * 插入一条公告
     * @param announcement 公告实体
     * @return 插入结果
     */
    @ResponseBody
    @RequestMapping("/insert")
    @OperationLog(operModel = "其它模块-公告模块-插入公告信息",operDesc = "插入公告信息")
    public RespBean insert(@RequestBody Announcement announcement){
        return RespBean.ok(service.insert(announcement));
    }

    /**
     * 通过id查询一条公告
     * @param id 公告id
     * @return 公告实体
     */
    @ResponseBody
    @RequestMapping("/selectByPrimaryKey")
    @OperationLog(operModel = "其它模块-公告模块-查询指定公告信息",operDesc = "查询指定公告信息")
    public RespBean selectByPrimaryKey(Integer id){
        return RespBean.ok(service.selectByPrimaryKey(id));
    }
}
