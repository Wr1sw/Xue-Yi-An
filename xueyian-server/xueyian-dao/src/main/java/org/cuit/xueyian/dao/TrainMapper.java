package org.cuit.xueyian.dao;

import org.apache.ibatis.annotations.Mapper;
import org.cuit.xueyian.model.Train;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface TrainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Train record);

    int insertSelective(Train record);

    Train selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Train record);

    int updateByPrimaryKey(Train record);

    List<Train> getAllTrain();

    List<Train> getTrainList(HashMap<String, Object> map);

    List<Train> getAllTrainList();

    int countRowsNum();
}