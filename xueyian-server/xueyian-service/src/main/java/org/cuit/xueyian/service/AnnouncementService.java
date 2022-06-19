package org.cuit.xueyian.service;

import org.cuit.xueyian.dao.AnnouncementMapper;
import org.cuit.xueyian.model.Announcement;
import org.cuit.xueyian.model.PageResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class AnnouncementService {

    @Autowired
    AnnouncementMapper mapper;


    /**
     * 获取所有的公告
     * @return 公告集合
     */
    public List<Announcement>  getAll(){
        List<Announcement> list = mapper.getAll();
        if(list.size()==0) throw new RuntimeException("数据出错");
        return list;
    }
    /**
     * 获取指定页面的数据
     * @param pageSize 页面大小
     * @param currentPage 当前页
     * @return 指定页面的记录集合和总的记录数
     */
    public PageResp<Announcement> getAnnouncementList(Integer pageSize, Integer currentPage){
        if (pageSize == null ||  currentPage == null) {
            throw new RuntimeException("非法数据");
        }
        HashMap<String,Object> map=new HashMap<>();
        map.put("pageSize",pageSize);
        map.put("offsetData", (currentPage-1)*pageSize);
        PageResp<Announcement> res = new PageResp<>();
        List<Announcement> list = mapper.getAnnouncementList(map);
        Long length = mapper.getTotalNums();
        res.setData(list);
        res.setTotal(length);

        return res;
    }

    /**
     * 根据id更新数据
     * @param announcement 公告实体
     * @return 更新结果
     */
    public Integer updateByPrimaryKey(Announcement announcement){
        announcement.setTime(new Timestamp(new Date().getTime()));
        return mapper.updateByPrimaryKey(announcement);
    }

    /**
     * 根据id删除公告
     * @param id 公告id
     * @return 删除结果
     */
    public Integer deleteByPrimaryKey(Integer id){
        return mapper.deleteByPrimaryKey(id);
    }

    /**
     * 插入一条公告
     * @param announcement 公告实体
     * @return 插入结果
     */
    public Integer insert(Announcement announcement){
        announcement.setTime(new Timestamp(new Date().getTime()));
        return mapper.insert(announcement);
    }

    /**
     * 通过id查询一条公告
     * @param id 公告id
     * @return 公告实体
     */
    public Announcement selectByPrimaryKey(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

}
