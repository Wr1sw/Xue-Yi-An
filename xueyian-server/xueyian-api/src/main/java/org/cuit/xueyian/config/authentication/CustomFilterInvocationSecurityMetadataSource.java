package org.cuit.xueyian.config.authentication;

import org.cuit.xueyian.model.Menu;
import org.cuit.xueyian.model.Role;
import org.cuit.xueyian.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import java.util.Collection;
import java.util.List;
import org.springframework.security.access.SecurityConfig;
/**
 * 主要功能：根据用户传来的请求地址，分析出请求需要的角色
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    MenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取到前端请求的RequestURL
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // 获取数据库中的全部menusWithRole数据
        List<Menu> menus = menuService.getAllMenusWithRole();
        for (Menu menu : menus) {
            // 使用antPathMather进行路径匹配 param1 eg: /employee/basic/**  param2 eg:/employee/basic/xxx
             if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                 // 匹配成功，取出所有Roles 目的是为了转换成String[]，因为SecurityConfig.createList的函数签名需要的是String[]
                 List<Role> roles = menu.getRoles();
                 String[] str = new String[roles.size()];
                 for (int i = 0;i < roles.size(); i++) {
                     str[i] = roles.get(i).getName();
                 }
                 return SecurityConfig.createList(str);
             }
        }
        // 此处 ROLE_LOGIN 是一个标记
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
