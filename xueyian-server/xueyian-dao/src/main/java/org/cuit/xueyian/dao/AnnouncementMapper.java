package org.cuit.xueyian.dao;

import org.apache.ibatis.annotations.Mapper;
import org.cuit.xueyian.model.Announcement;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface AnnouncementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Announcement record);

    int insertSelective(Announcement record);

    Announcement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Announcement record);

    int updateByPrimaryKey(Announcement record);

    List<Announcement> getAnnouncementList(HashMap<String, Object> map);

    Long getTotalNums();
    List<Announcement>  getAll();
}