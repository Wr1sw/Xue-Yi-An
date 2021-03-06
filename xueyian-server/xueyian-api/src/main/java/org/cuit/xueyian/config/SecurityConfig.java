package org.cuit.xueyian.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cuit.xueyian.config.authentication.CustomFilterInvocationSecurityMetadataSource;
import org.cuit.xueyian.config.authentication.CustomUrlDecisionManager;
import org.cuit.xueyian.config.authentication.DefaultUserDetailsService;
import org.cuit.xueyian.config.validate.code.VerificationCodeFilter;
import org.cuit.xueyian.config.validate.email.EmailCodeAuthenticationSecurityConfig;
import org.cuit.xueyian.config.validate.github.GithubAuthenticationSecurityConfig;
import org.cuit.xueyian.config.validate.mobile.SmsCodeAuthenticationSecurityConfig;
import org.cuit.xueyian.model.Hr;
import org.cuit.xueyian.model.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DefaultUserDetailsService hrService;

    @Autowired
    CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;

    @Autowired
    CustomUrlDecisionManager customUrlDecisionManager;

    @Autowired
    VerificationCodeFilter verificationCodeFilter;

    @Autowired
    SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    GithubAuthenticationSecurityConfig githubAuthenticationSecurityConfig;

    @Autowired
    EmailCodeAuthenticationSecurityConfig emailCodeAuthenticationSecurityConfig;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.userDetailsService(hrService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/index.html", "/img/**", "/fonts/**", "/favicon.ico",
                "/login", "/css/**", "/js/**", "/index.html", "/img/**",
                "/fonts/**", "/favicon.ico", "/verifyCode", "/sms/code", "/test", "/render/github",
                "/callback", "/callback/github", "/test1", "/authorization_code", "/wuwuwu", "/temp/**", "/email/code");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(verificationCodeFilter, UsernamePasswordAuthenticationFilter.class);
        http.apply(smsCodeAuthenticationSecurityConfig);
        http.apply(githubAuthenticationSecurityConfig);
        http.apply(emailCodeAuthenticationSecurityConfig);
        http.authorizeRequests().antMatchers("/temp").permitAll().anyRequest().authenticated();

        http.authorizeRequests()
//                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(customUrlDecisionManager);
                        object.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                        return object;
                    }
                })
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")
                .loginPage("/temp")
                .loginPage("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        Hr hr = (Hr) authentication.getPrincipal();
                        hr.setPassword(null);
                        RespBean ok = RespBean.ok("????????????", hr);
                        String s = new ObjectMapper().writeValueAsString(ok);
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                        AuthenticationException exception) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        RespBean respBean = RespBean.error("????????????");
                        if (exception instanceof LockedException) {
                            respBean.setMsg("????????????????????????????????????!");
                        } else if (exception instanceof CredentialsExpiredException) {
                            respBean.setMsg("?????????????????????????????????!");
                        } else if (exception instanceof AccountExpiredException) {
                            respBean.setMsg("?????????????????????????????????!");
                        } else if (exception instanceof DisabledException) {
                            respBean.setMsg("????????????????????????????????????!");
                        } else if (exception instanceof BadCredentialsException) {
                            respBean.setMsg("???????????????????????????????????????????????????!");
                        }
                        out.write(new ObjectMapper().writeValueAsString(respBean));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(RespBean.ok("???????????????")));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .cors()
                .and()
                .csrf().disable()
                // ???????????????????????????????????????????????????????????????
                .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(401);
                PrintWriter out = response.getWriter();
                RespBean respBean = RespBean.error("????????????");
                if (authException instanceof InsufficientAuthenticationException) {
                    respBean.setMsg("??????????????????????????????");
                }
                out.write(new ObjectMapper().writeValueAsString(respBean));
                out.flush();
                out.close();
            }
        });
    }
}
