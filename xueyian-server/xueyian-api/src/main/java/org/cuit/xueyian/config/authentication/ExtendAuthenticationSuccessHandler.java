package org.cuit.xueyian.config.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cuit.xueyian.model.Hr;
import org.cuit.xueyian.model.RespBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description
 */
@Component
public class ExtendAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");

        response.setContentType("application/json;charset=UTF-8");
        logger.info(objectMapper.writeValueAsString(authentication));

        PrintWriter out = response.getWriter();
        Hr hr = (Hr) authentication.getPrincipal();
        hr.setPassword(null);
        RespBean ok = RespBean.ok("登录成功", hr);
        String s = new ObjectMapper().writeValueAsString(ok);
        out.write(s);
        response.sendRedirect("http://localhost:8080/#/temp?result=" + s);
        out.flush();
        out.close();

    }
}
