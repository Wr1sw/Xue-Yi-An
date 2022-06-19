package org.cuit.xueyian.config.authentication;

import org.cuit.xueyian.dao.HrMapper;
import org.cuit.xueyian.dao.HrRoleMapper;
import org.cuit.xueyian.model.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description
 */
@Service
public class MobileUserDetailsService implements UserDetailsService {

    @Autowired
    HrMapper hrMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByPhone(username);
        if (hr == null) {
            throw new UsernameNotFoundException("手机号未注册");
        }
        hr.setRoles(hrMapper.getHrRoleById(hr.getId()));
        return hr;
    }
}
