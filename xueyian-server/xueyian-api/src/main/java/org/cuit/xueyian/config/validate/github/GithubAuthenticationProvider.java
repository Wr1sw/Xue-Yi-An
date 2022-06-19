package org.cuit.xueyian.config.validate.github;

import org.cuit.xueyian.config.validate.mobile.SmsCodeAuthenticationToken;
import org.cuit.xueyian.service.GithubClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description
 */
public class GithubAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    private GithubClientService githubClientService;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        GithubAuthenticationToken authenticationToken = (GithubAuthenticationToken) authentication;

        String accessToken = (String) authenticationToken.getPrincipal();
        System.out.println("=>>>>accessToken = " + accessToken);
        GithubClientService githubClientService1 = new GithubClientService();
        Map<String, Object> userInfo = githubClientService1.queryUser(accessToken);

        UserDetails user = userDetailsService.loadUserByUsername((String) userInfo.get("login"));

        if (Objects.isNull(user)) {
            throw new InternalAuthenticationServiceException("根据accessToken：" + accessToken + "，无法获取对应的用户信息！");
        }
        GithubAuthenticationToken authenticationResult = new GithubAuthenticationToken(user, user.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;


//        UserDetails userDetails = userDetailsService.loadUserByUsername(mobile);

        // 此时鉴权成功，应当重新 new 一个拥有鉴权的 authenticationResult 返回
//        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());

//        authenticationResult.setDetails(authenticationToken.getDetails());

//        return authenticationResult;
    }


    /**
     * 这个方法决定了这个Provider要怎么被AuthenticationManager 选中，在（用户名密码登录方式中），
     * AuthenticationManager选中的是DaoAuthenticationProvider.
     * 这里return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication)
     * 处理所有 GithubCodeAuthenticationToken 及其子类或子接口。
     *
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        // 判断 authentication 是不是 SmsCodeAuthenticationToken 的子类或者子接口
        return GithubAuthenticationToken.class.isAssignableFrom(authentication);
    }


    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
