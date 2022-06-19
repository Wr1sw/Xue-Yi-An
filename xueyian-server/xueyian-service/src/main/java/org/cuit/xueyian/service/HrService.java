package org.cuit.xueyian.service;


import org.cuit.xueyian.dao.HrMapper;
import org.cuit.xueyian.dao.HrRoleMapper;
import org.cuit.xueyian.model.Hr;
import org.cuit.xueyian.model.Position;
import org.cuit.xueyian.model.TestHr;
import org.cuit.xueyian.utils.FastDFSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class HrService {

    @Autowired
    HrMapper hrMapper;
    @Autowired
    HrRoleMapper hrRoleMapper;
    @Autowired
    private FastDFSUtil fastDFSUtil;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Hr hr = hrMapper.loadUserByUsername(username);
//        if (hr == null) {
//            throw new UsernameNotFoundException("用户名不存在");
//        }
//        hr.setRoles(hrMapper.getHrRoleById(hr.getId()));
//        return hr;
//    }

    public List<Hr> getAllHrExcludeCurHr() {
        return hrMapper.getAllHrExcludeCurHr(this.getCurHr().getId());
    }

    private Hr getCurHr() {
        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public List<Hr> getAllHrs(String keywords) {
        return hrMapper.getAllHrs(HrUtils.getCurrentHr().getId(),keywords);
    }

    public Integer addHr(Hr hr){
        return hrMapper.insertSelective(hr);
    }

    public Integer updateHr(Hr hr) {
        return hrMapper.updateByPrimaryKeySelective(hr);
    }

    @Transactional
    public boolean addHrRole(Integer hrid, Integer[] rids){
        return hrRoleMapper.addRole(hrid, rids) == rids.length;
    }

    @Transactional
    public boolean updateHrRole(Integer hrid, Integer[] rids) {
        hrRoleMapper.deleteByHrid(hrid);
        return hrRoleMapper.addRole(hrid, rids) == rids.length;
    }

    public Integer deleteHrById(Integer id) {
        return hrMapper.deleteByPrimaryKey(id);
    }

    public void deleteHr(Hr hr) {
        hrMapper.deleteHr(hr);
    }

    public Integer updateHyById(Hr hr) {
        return hrMapper.updateByPrimaryKeySelective(hr);
    }

    public boolean updateHrPasswd(String oldpass, String pass, Integer hrid) {
        Hr hr = hrMapper.selectByPrimaryKey(hrid);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(oldpass, hr.getPassword())) {
            String encodePass = encoder.encode(pass);
            Integer result = hrMapper.updatePasswd(hrid, encodePass);
            if (result == 1) {
                return true;
            }
        }
        return false;
    }

    // 更新用户头像
    public Integer updateUserface(String url, Integer id) {
        return hrMapper.updateUserface(url, id);
    }

    //根据ID获取hr信息
    public Hr selectById(Integer id){
        return hrMapper.selectById(id);
    }


    //设置用户头像URL
    public String getImgUrl(MultipartFile file) throws Exception {
        return fastDFSUtil.uploadCommonFile(file);
    }
}
