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
            throw new InternalAuthenticationServiceException("??????accessToken???" + accessToken + "???????????????????????????????????????");
        }
        GithubAuthenticationToken authenticationResult = new GithubAuthenticationToken(user, user.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;


//        UserDetails userDetails = userDetailsService.loadUserByUsername(mobile);

        // ????????????????????????????????? new ????????????????????? authenticationResult ??????
//        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());

//        authenticationResult.setDetails(authenticationToken.getDetails());

//        return authenticationResult;
    }


    /**
     * ???????????????????????????Provider????????????AuthenticationManager ???????????????????????????????????????????????????
     * AuthenticationManager????????????DaoAuthenticationProvider.
     * ??????return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication)
     * ???????????? GithubCodeAuthenticationToken ???????????????????????????
     *
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        // ?????? authentication ????????? SmsCodeAuthenticationToken ????????????????????????
        return GithubAuthenticationToken.class.isAssignableFrom(authentication);
    }


    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
