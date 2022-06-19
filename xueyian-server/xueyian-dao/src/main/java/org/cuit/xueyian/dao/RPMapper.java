package org.cuit.xueyian.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cuit.xueyian.model.RP;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface RPMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RP record);

    int insertSelective(RP record);

    RP selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RP record);

    int updateByPrimaryKey(RP record);

    List<RP> getAllRP();

    Integer deleteRPByIds(@Param("ids") Integer[] ids);

    List<RP> queryBonusOrFineByEmpId(Integer id);

    /**
     * 功能描述: 根据奖惩类型查询 rplist 注意要将integer类型的type转为string，防止值为0时出错
     * @Param: [ecTypeStr]
     * @Return: java.util.List<org.cuit.xueyian.model.RP>
     * @Author: wsy
     * @Date: 2022/5/28 13:04
     */
    List<RP> getRPByEcType(String ecTypeStr);


}
