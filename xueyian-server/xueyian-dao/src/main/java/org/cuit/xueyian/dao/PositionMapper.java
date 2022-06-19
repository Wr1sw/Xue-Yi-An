package org.cuit.xueyian.dao;

import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cuit.xueyian.model.JObLevel;
import org.cuit.xueyian.model.Position;

import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Mapper
public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    String selectNameByPrimaryKey(Integer id);

    List<Position> getID_Name();

    List<Position> getAllPositions();

    void deletePosition(Position position);

    Integer deletePositionsByIds(@Param("ids") Integer[] ids);

    // 获取一列 id 和 name
    List<Position> selectNameByIdList(List<Integer> ids);

}