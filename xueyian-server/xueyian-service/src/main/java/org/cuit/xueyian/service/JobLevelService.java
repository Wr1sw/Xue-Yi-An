package org.cuit.xueyian.service;


import org.cuit.xueyian.dao.JObLevelMapper;
import org.cuit.xueyian.model.JObLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobLevelService {

    @Autowired
    JObLevelMapper joblevelmapper;


    public List<JObLevel> getAllJobLevels() {
        return joblevelmapper.getAllJobLevels();
    }

    public Integer addJobLevel(JObLevel jobLevel) {
        jobLevel.setCreatedate(new Date());
        jobLevel.setEnabled(false);
        return joblevelmapper.insertSelective(jobLevel);
    }

    public Integer updateJobLevelById(JObLevel jobLevel) {
        return joblevelmapper.updateByPrimaryKeySelective(jobLevel);
    }

    public Integer deleteJobLevelById(Integer id) {
        return joblevelmapper.deleteByPrimaryKey(id);
    }

    public void deleteJobLevel(JObLevel jObLevel){
        joblevelmapper.deleteJobLevel(jObLevel);
    }

    public Integer deleteJobLevelsByIds(Integer[] ids) {
        return joblevelmapper.deleteJobLevelsByIds(ids);
    }
//    // 获取职位ID与名称映射 List<HashMap<String,String>>
//    public List<Map<String,Object>> getID_Name(){
//        List<JObLevel> list = joblevelmapper.getID_Name();
//        List<Map<String,Object>> res = new ArrayList<>();
//
//        // 处理数据
//        for(JObLevel joblevel : list){
//            Map<String,Object> resMap = new HashMap<>();
//            resMap.put("id",joblevel.getId());
//            resMap.put("name",joblevel.getName());
//            res.add(resMap);
//        }
//        return res;
//    }

    public Object selectNameByPrimaryKey(Integer id){
        return joblevelmapper.selectNameByPrimaryKey(id);
    }

    //获取JobLevelID与名称映射 List<HashMap<String,String>>
    public List<JObLevel> getID_Name(){
//        List<JObLevel> list = joblevelmapper.getID_Name();
//        List<Map<String,Object>> res = new ArrayList<>();
//
//        // 处理数据
//        for(JObLevel job : list){
//            Map<String,Object> resMap = new HashMap<>();
//            resMap.put("id",job.getId());
//            resMap.put("name",job.getName());
//            res.add(resMap);
//        }
        return joblevelmapper.getID_Name();
    }
}
