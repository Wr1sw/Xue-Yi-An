package org.cuit.xueyian.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cuit.xueyian.model.Department;
import org.cuit.xueyian.model.JObLevel;
import org.springframework.boot.autoconfigure.batch.JobLauncherCommandLineRunner;

import java.util.List;

@Mapper
public interface JObLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JObLevel record);

    int insertSelective(JObLevel record);

    JObLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JObLevel record);

    int updateByPrimaryKey(JObLevel record);

    //获取职位名称与ID的映射
    List<JObLevel> getID_Name();

    //使用部门ID获取JobLevel名称
    String selectNameByPrimaryKey(Integer id);

    List<JObLevel> getAllJobLevels();

    void deleteJobLevel(JObLevel jObLevel);

    Integer deleteJobLevelsByIds(@Param("ids") Integer[] ids);

    // 获取一列 id 和 name
    List<JObLevel> selectNameByIdList(List<Integer> ids);

    Integer queryJobById(Integer id);

}