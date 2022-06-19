package org.cuit.xueyian.api;

import cn.hutool.http.HttpUtil;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthGithubRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.cuit.xueyian.config.VerificationCode;
import org.cuit.xueyian.model.Hr;
import org.cuit.xueyian.model.RespBean;
import org.cuit.xueyian.service.GithubClientService;
import org.cuit.xueyian.utils.SendSms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Value("${github.clientId}")
    private String clientId;

    @Value("${github.clientSecret}")
    private String clientSecret;

    @Value("${github.redirectUri}")
    private String redirectUri;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private GithubClientService githubClientService;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/test")
    public void test12321(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("http://localhost:8080/test?result=123");
    }

    @GetMapping("/login")
    public RespBean Login() {
        return RespBean.error("尚未登陆请登录！");
    }

    @GetMapping("/verifyCode")
    public void verifyCode(HttpSession session, HttpServletResponse resp) throws IOException {
        VerificationCode code = new VerificationCode();
        BufferedImage image = code.getImage();
        String text = code.getText();
        System.out.println("text = " + text);
        session.setAttribute("verify_code", text);
        VerificationCode.output(image, resp.getOutputStream());
    }

    @RequestMapping("/sms/code")
    @ResponseBody
    public void sms(String mobile, HttpSession session) {
        int vode = SendSms.generateValidateCode(6);
        String code = String.valueOf(vode);
        Map<String, Object> map = new HashMap<>();

        map.put("mobile", mobile);
        map.put("code", code);

        SendSms.sendSms(mobile, code);
        session.setAttribute("smsCode", map);

        logger.info("{}：为 {} 设置短信验证码：{}", session.getId(), mobile, code);
    }

    @GetMapping("/email/code")
    public void emailCode(String email, HttpSession session) {
        int vode = SendSms.generateValidateCode(6);
        String code = String.valueOf(vode);
        Map<String, Object> map = new HashMap<>();

        map.put("email", email);
        map.put("code", code);

        session.setAttribute("emailCode", map);

        rabbitTemplate.convertAndSend("xueyian.mail.sendCode", map);

        logger.info("{}：为 {} 设置短信验证码：{}", session.getId(), email, code);
    }


    @RequestMapping("/render/github")
    public void renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    //    @RequestMapping("/callback/github")
    public Object login(AuthCallback callback, HttpSession session) {
        AuthRequest authRequest = getAuthRequest();
        AuthResponse login = authRequest.login(callback);
        AuthUser authUser = (AuthUser) login.getData();
//        if (authUser.getUsername().equals("Wr1sw")) {
////            Map<String, Object> map = new HashMap<>();
////            map.put("username", "adimn");
////            map.put("passsword", "123");
////            HttpUtil.get("/verifyCode");
////            String verify_code = (String) session.getAttribute("verify_code");
////            map.put("code", verify_code);
////            HttpUtil.post("/doLogin", map);
////        }
        return authUser;
    }

    private AuthRequest getAuthRequest() {
        return new AuthGithubRequest(AuthConfig.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .redirectUri(redirectUri)
                .build());
    }

    @GetMapping("/authorization_code")
    public String authorization_code(String code, HttpServletResponse response) throws Exception {
        System.out.println("=>>>>>>>>>>>");
        System.out.println("code = " + code);
        String accessToken = githubClientService.queryAccessToken(code);

        String url = redirectUri;
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", accessToken);
        HttpUtil.post(url, map);
        return "123";
    }


}
