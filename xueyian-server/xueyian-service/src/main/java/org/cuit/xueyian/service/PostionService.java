package org.cuit.xueyian.service;


import org.cuit.xueyian.dao.PositionMapper;
import org.cuit.xueyian.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostionService {

    @Autowired
    PositionMapper positionMapper;

    public List<Position> getAllPositions() {
        return positionMapper.getAllPositions();
    }

    public Integer addPosition(Position position) {
        position.setEnabled(true);
        position.setCreatedate(new Date());
        return positionMapper.insertSelective(position);
    }

    public Integer updatePositions(Position position) {
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    public void deletePosition(Position position) {
        positionMapper.deletePosition(position);
    }

    public Integer deletePositionsByIds(Integer[] ids) {
        return positionMapper.deletePositionsByIds(ids);
    }

    public Object selectNameByPrimaryKey(Integer id){
        return positionMapper.selectNameByPrimaryKey(id);
    }
    //获取名字与ID的映射
    public List<Position> getID_Name(){
//        List<Position> list =
//        List<Map<String,Object>> res = new ArrayList<>();
//
//        // 处理数据
//        for(Position pos : list){
//            Map<String,Object> resMap = new HashMap<>();
//            resMap.put("id",pos.getId());
//            resMap.put("name",pos.getName());
//            res.add(resMap);
//        }
        return positionMapper.getID_Name();
    }
}
