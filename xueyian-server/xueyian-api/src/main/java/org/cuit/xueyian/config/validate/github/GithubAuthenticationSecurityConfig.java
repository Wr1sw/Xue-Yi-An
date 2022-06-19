package org.cuit.xueyian.config.validate.github;

import org.cuit.xueyian.config.authentication.*;
import org.cuit.xueyian.config.validate.mobile.SmsCodeAuthenticationFilter;
import org.cuit.xueyian.config.validate.mobile.SmsCodeAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description
 */
@Component
public class GithubAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private GithubUserDetailsService userDetailsService;

    @Autowired
    private ExtendAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        GithubAuthenticationFilter githubAuthenticationFilter = new GithubAuthenticationFilter();
        githubAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        githubAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        githubAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);

        GithubAuthenticationProvider githubAuthenticationProvider = new GithubAuthenticationProvider();
        githubAuthenticationProvider.setUserDetailsService(userDetailsService);

        http.authenticationProvider(githubAuthenticationProvider)
                .addFilterAfter(githubAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
