package org.cuit.xueyian.dao;

import org.apache.ibatis.annotations.Mapper;
import org.cuit.xueyian.model.NeglectWork;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 旷工表(NeglectWork)表数据库访问层
 *
 * @author Miracle
 * @since 2022-05-07
 */
@Mapper
public interface NeglectWorkMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param neglectId 主键
     * @return 实例对象
     */
    NeglectWork queryById(Integer neglectId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<NeglectWork> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param neglectWork 实例对象
     * @return 对象列表
     */
    List<NeglectWork> queryAll(NeglectWork neglectWork);

    /**
     * 新增数据
     *
     * @param neglectWork 实例对象
     * @return 影响行数
     */
    int insert(NeglectWork neglectWork);

    /**
     * 修改数据
     *
     * @param neglectWork 实例对象
     * @return 影响行数
     */
    int update(NeglectWork neglectWork);

    /**
     * 通过主键删除数据
     *
     * @param neglectId 主键
     * @return 影响行数
     */
    int deleteById(Integer neglectId);

}