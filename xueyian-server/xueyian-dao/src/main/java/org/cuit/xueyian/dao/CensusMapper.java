package org.cuit.xueyian.dao;

import org.apache.ibatis.annotations.Mapper;
import org.cuit.xueyian.model.Census;
import org.cuit.xueyian.model.CensusItem;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 员工信息表(Employee)表数据库访问层
 *
 * @author Miracle
 * @since 2022-05-07
 */

@Mapper
public interface CensusMapper {

    List<CensusItem> getSchoolInfo();

    List<CensusItem> getDepartmentPerson();

    List<CensusItem> getTopDegree();

    List<CensusItem> getMajor();

    List<CensusItem> getTitle();

    List<CensusItem> getWedlock();

    Integer getWorkAge(HashMap<String, Integer> map);

    Integer getBirthYear(HashMap<String, Date> map);

    List<CensusItem> getGender();

    List<CensusItem> getPoliticStatus();

    Integer getMinWorkAge();

    Integer getMaxWorkAge();

    Date getMinYear();

    Date getMaxYear();

}
