package org.cuit.xueyian.config.validate.email;

import org.cuit.xueyian.config.validate.mobile.SmsCodeAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description
 */
public class EmailCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        EmailCodeAuthenticationToken authenticationToken = (EmailCodeAuthenticationToken) authentication;

        String mobile = (String) authenticationToken.getPrincipal();


        checkSmsCode(mobile);

        UserDetails userDetails = userDetailsService.loadUserByUsername(mobile);

        // 此时鉴权成功，应当重新 new 一个拥有鉴权的 authenticationResult 返回
        EmailCodeAuthenticationToken authenticationResult = new EmailCodeAuthenticationToken(userDetails, userDetails.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    private void checkSmsCode(String mobile) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String inputCode = request.getParameter("emailCode");

        Map<String, Object> emailCode = (Map<String, Object>) request.getSession().getAttribute("emailCode");
        if (emailCode == null) {
            throw new BadCredentialsException("未检测到申请验证码");
        }

        String applyMobile = (String) emailCode.get("email");
        String code = (String) emailCode.get("code");

        if (!applyMobile.equals(mobile)) {
            throw new BadCredentialsException("申请的邮箱与登录邮箱不一致");
        }
        if (!code.equals(inputCode)) {
            throw new BadCredentialsException("验证码错误");
        }

    }

    /**
     * 这个方法决定了这个Provider要怎么被AuthenticationManager 选中，在（用户名密码登录方式中），
     * AuthenticationManager选中的是DaoAuthenticationProvider.
     * 这里return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication)
     * 处理所有 SmsCodeAuthenticationToken 及其子类或子接口。
     *
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        // 判断 authentication 是不是 SmsCodeAuthenticationToken 的子类或者子接口
        return EmailCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }


    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
