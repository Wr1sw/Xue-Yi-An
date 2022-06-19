package org.cuit.xueyian.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cuit.xueyian.model.Hr;
import org.cuit.xueyian.model.Position;
import org.cuit.xueyian.model.Role;
import org.cuit.xueyian.model.TestHr;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    Hr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);

    Hr loadUserByUsername(String username);

    List<Role> getHrRoleById(Integer id);

    List<Hr> getAllHrExcludeCurHr(Integer id);

    List<Hr> getAllHrs(@Param("hrid") Integer hrid, @Param("keywords") String keywords);

    Integer updatePasswd(@Param("hrid") Integer hrid, @Param("encodePass") String encodePass);

    Integer updateUserface(@Param("url") String url, @Param("id") Integer id);

    Hr loadUserByPhone(String username);

    Hr selectById(Integer id);

    Hr loadUserByAccessToken(String username);

    Hr loadUserByEmail(String username);

    void deleteHr(Hr hr);
}