package org.cuit.xueyian.service;

import org.cuit.xueyian.dao.EmployeetrainMapper;
import org.cuit.xueyian.dao.TrainMapper;
import org.cuit.xueyian.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TrainService {

    @Autowired
    TrainMapper trainMapper;

    @Autowired
    EmployeetrainMapper employeetrainMapper;

    public List<Train> getAllTrain(){
        return trainMapper.getAllTrain();
    }
    public Integer addTrain(Train train){
        return trainMapper.insertSelective(train);
    }
    public Integer updateTrain(Train train){
        return trainMapper.updateByPrimaryKeySelective(train);
    }
    public Integer deleteTrain(Integer id){
        return trainMapper.deleteByPrimaryKey(id);
    }

    /**
     * insert a record
     * @param record Train entity
     * @return result
     */
    public int insert(Train record){
        return trainMapper.insert(record);
    }

    /**
     * 删除培训表记录的同时删除员工培训表的记录
     * @param id 培训id
     * @return 删除结果
     */
    public int deleteByPrimaryKey(Integer id){
        employeetrainMapper.deleteByTrainId(id);
        return trainMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据培训id查询培训信息
     * @param id 培训id
     * @return 培训实体
     */
    public Train selectByPrimaryKey(Integer id){
        return trainMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新培训信息
     * @param record 培训记录
     * @return 更新结果
     */
    public int updateByPrimaryKeySelective(Train record){
        return trainMapper.updateByPrimaryKey(record);
    }

    /**
     * 获取培训信息
     * @param pageSize 页面大小
     * @param currentPage 当前页
     * @return 培训信息集合
     */
    public List<Train> getTrainList(Integer pageSize, Integer currentPage){
        HashMap<String,Object> map=new HashMap<>();
        map.put("pageSize",pageSize);
        map.put("offsetData", (currentPage-1)*pageSize);

        return trainMapper.getTrainList(map);
    }

    /**
     * 统计培训信息的条数
     * @return 培训信息的条数
     */
    public int countTrainRowsNum(){
        return trainMapper.countRowsNum();
    }

    public List<Train> getAllTrainList(){
        return trainMapper.getAllTrainList();
    }
}
